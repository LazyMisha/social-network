package servlet;
 
import dao.MusicDao;
import dao.UserDao;
import dao.UserSongsDao;
import entity.Music;
import entity.User;
import entity.User_songs;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
import static service.GetTags.*;
 
/**
* Created by misha on 29.05.17.
*/
 
@WebServlet(name = "editMusic", urlPatterns = "/editMusic")
public class EditMusicServlet extends HttpServlet {
 
   Music music = new Music();
   MusicDao musicDao = new MusicDao();
   User_songs userSongs = new User_songs();
   UserSongsDao userSongsDao = new UserSongsDao();
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doPost(request, response);
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
 
       request.setCharacterEncoding("UTF-8");
 
       User user = (User) request.getSession().getAttribute("user");
       UserDao userDao = new UserDao();

       int songSize = 0;
       String strSongSize = new UserDao().getMusicsSize(user);
 
       try {
           songSize = Integer.parseInt(strSongSize);
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
 
       String firstName = user.getFirstName();
       String lastName = user.getLastName();
       String city = user.getCity();
       String country = user.getCountry();
       String photo = user.getPath_to_photo();

       try {
           if (strSongSize == null || strSongSize.isEmpty()) {
               request.setAttribute("count", "0");
           } else {
               request.setAttribute("count", strSongSize);
           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
           request.setAttribute("count", "0");
       }
 
       if (photo == null) {
           request.setAttribute("photo", "photo/default.jpg");
       } else {
           request.setAttribute("photo", photo);
       }
 
       if (firstName == null || firstName.isEmpty()) {
           request.setAttribute("name",
                   "no information");
       } else {
           request.setAttribute("name",
                   firstName);
       }
 
       if (lastName == null || lastName.isEmpty()) {
           request.setAttribute("lastName",
                   "no information");
       } else {
           request.setAttribute("lastName",
                   lastName);
       }
 
       if (country == null || country.isEmpty()) {
           request.setAttribute("country",
                   "no information");
       } else {
           request.setAttribute("country",
                   country);
       }
 
       if (city == null || city.isEmpty()) {
           request.setAttribute("city",
                   "no information");
       } else {
           request.setAttribute("city",
                   city);
       }
 
       try {
           long generalMusicSize = songSize + fileSizeInMB;
 
           if (generalMusicSize > 100) {
               request.setAttribute("message",
                       "Your music space is full!");
           } else {
 
               songName = request.getParameter("songName");
               genre = request.getParameter("genre");
               singer = request.getParameter("singer");
               composer = request.getParameter("composer");
               album = request.getParameter("album");
 
               System.out.println(songName);
               System.out.println(genre);
               System.out.println(singer);
               System.out.println(composer);
               System.out.println(album);
 
               music.setSong_name(songName);
               music.setSinger(singer);
               music.setPath(secondPart);
               music.setGenre(genre);
               music.setSize(fileSizeInMB);
 
               musicDao.save(music);
 
               userSongs.setMusic(music);
               userSongs.setUser(user);
               userSongsDao.save(userSongs);
 
               request.setAttribute("message",
                       "Information has been successfully saved!");
               System.out.println("Music " + music.getSong_name() + " is uploaded");
           }
 
       } catch (Exception e) {
           System.out.println(e.getMessage());
           request.setAttribute("message",
                   "There was an error!" + "<br/>" +
                           "Try again, and select mp3 format please!");
       }
 
       getServletContext().getRequestDispatcher("/uploadMusic.jsp").forward(
               request, response);
   }
}