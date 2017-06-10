package servlet;

import dao.UserDao;
import dao.UserSongsDao;
import entity.Music;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 07.06.17.
 */

@WebServlet(name="myMusic", urlPatterns="/myMusic")
public class MyMusicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String songs = "";

        request.setCharacterEncoding("UTF-8");
        response.setContentType("charset=UTF-8");

        User user = (User) request.getSession().getAttribute("user");
        UserDao userDao = new UserDao();

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String city = user.getCity();
        String country = user.getCountry();
        String photo = user.getPath_to_photo();
        String musicSize = userDao.getMusicsSize(user);

        if(musicSize == null){
            request.setAttribute("count", "0");
        }else {
            request.setAttribute("count", musicSize);
        }

        if(photo == null){
            request.setAttribute("photo", "photo/default.jpg");
        }else {
            request.setAttribute("photo", photo);
        }

        if(firstName == null || firstName.isEmpty()){
            request.setAttribute("name",
                    "no information");
        }else {
            request.setAttribute("name",
                    firstName);
        }

        if(lastName == null || lastName.isEmpty()){
            request.setAttribute("lastName",
                    "no information");
        }else{
            request.setAttribute("lastName",
                    lastName);
        }

        if(country == null || country.isEmpty()){
            request.setAttribute("country",
                    "no information");
        }else {
            request.setAttribute("country",
                    country);
        }

        if(city == null || city.isEmpty()){
            request.setAttribute("city",
                    "no information");
        }else{
            request.setAttribute("city",
                    city);
        }

        ArrayList<Music> music = new UserSongsDao().getByUserId(user);

        if(!music.isEmpty()) {
            for (int i = 0; i < music.size(); i++) {
                String path = music.get(i).getPath();
                int index = path.indexOf("upload");
                path = path.substring(index);

                songs += "<p>" + music.get(i).getSinger() + " - " + music.get(i).getSong_name() +
                        "</p>" + "<audio controls><source src=\"" + path +
                        "\" type=\"audio/mpeg\"></audio><br/><br/>";
            }

            request.setAttribute("songs", songs);
        }else {
            request.setAttribute("songs", "You don't have ane songs");
        }

        getServletContext().getRequestDispatcher("/yourSongs.jsp").forward(
                request, response);
    }
}
