package servlet;

import dao.MusicDao;
import dao.UserDao;
import entity.Music;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * @author Stepanyuk
 */

@WebServlet(name = "SerchMusicServlet", urlPatterns = {"/SerchMusicServlet"})
public class SearchMusicServlet extends HttpServlet {

    ArrayList<Music> musicArr;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("charset=UTF-8");

        User user = (User)request.getSession().getAttribute("user");
        UserDao userDao = new UserDao();

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String city = user.getCity();
        String country = user.getCountry();
        String photo = user.getPath_to_photo();
        try {
            String musicSize = userDao.getMusicsSize(user);
            if(musicSize == null || musicSize.isEmpty()){
                request.setAttribute("count", "0");
            }else {
                request.setAttribute("count", musicSize);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            request.setAttribute("count", "0");
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
        
        musicArr=new MusicDao().searchMusic(request.getParameter("search"));
        String result="";

        if(!musicArr.isEmpty()){
            for(int i=0;i<musicArr.size();i++){

                String fullPath = musicArr.get(i).getPath();
                int index = fullPath.indexOf("upload");
                String path= fullPath.substring(index);

                result+="<p>"+musicArr.get(i).getSinger()+" - "+musicArr.get(i).getSong_name()+
                        "</p>"+"<audio controls><source src=\""+path+
                        "\" type=\"audio/mpeg\"></audio><br/><br/>";
            }

            request.setAttribute("song", result);

        }else{
            request.setAttribute("song","Can't find such song!");
        }

        getServletContext().getRequestDispatcher("/searchResults.jsp").forward(
                request, response);
    }
}
