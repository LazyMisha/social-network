package servlet;

import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by misha on 27.05.17.
 */
@WebServlet(name="profile", urlPatterns="/profile")
public class ProfileServlet  extends HttpServlet{

    User user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        user = (User)request.getSession().getAttribute("user");

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String city = user.getCity();
        Date birthDay = user.getBirthday();
        String country = user.getCountry();
        String userInfo = user.getUser_info();
        String userLink = user.getLink();
        String photo = user.getPath_to_photo();

        if(photo == null){
            request.setAttribute("pathToPhoto", "photo/default.jpg");
        }else {
            request.setAttribute("pathToPhoto", photo);
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

        if(email == null || email.isEmpty()){
            request.setAttribute("email",
                    "no information");
        }else{
            request.setAttribute("email",
                    email);
        }

        if(birthDay == null){
            request.setAttribute("birthday",
                    "no information");
        }else{
            request.setAttribute("birthday",
                    birthDay);
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

        if(userInfo == null || userInfo.isEmpty()){
            request.setAttribute("userInfo",
                    "no information");
        }else {
            request.setAttribute("userInfo",
                    userInfo);
        }

        if(userLink == null || userLink.isEmpty()){
            request.setAttribute("userLink",
                    "no information");
        }else {
            request.setAttribute("userLink",
                    userLink);
        }

        getServletContext().getRequestDispatcher("/profile.jsp").forward(
                request, response);

    }
}
