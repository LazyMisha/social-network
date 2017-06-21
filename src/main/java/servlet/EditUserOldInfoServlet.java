package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by misha on 01.06.17.
 */

@WebServlet(name="editUserOldInfoServlet", urlPatterns="/editUserOldInfoServlet")
public class EditUserOldInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        User user = (User)request.getSession().getAttribute("user");
        UserDao userDao = new UserDao();

        String photo = user.getPath_to_photo();
        String name = user.getFirstName();
        String lastName = user.getLastName();
        String city = user.getCity();
        String country = user.getCountry();
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

        if(city == null){
            request.setAttribute("city", "no information");
        }else {
            request.setAttribute("city", city);
        }

        if(name == null){
            request.setAttribute("firstName", "no information");
        }else {
            request.setAttribute("firstName", name);
        }

        if(lastName == null){
            request.setAttribute("lastName", "no information");
        }else {
            request.setAttribute("lastName", lastName);
        }

        if(country == null){
            request.setAttribute("country", "no information");
        }else {
            request.setAttribute("country", country);
        }

        request.setAttribute("oldFirstName", user.getFirstName());
        request.setAttribute("oldLastName", user.getLastName());
        request.setAttribute("oldEmail", user.getEmail());
        request.setAttribute("oldUserInfo", user.getUser_info());
        request.setAttribute("oldCountry", user.getCountry());
        request.setAttribute("oldCity", user.getCity());

        getServletContext().getRequestDispatcher("/editProfile.jsp").forward(
                request, response);
    }
}
