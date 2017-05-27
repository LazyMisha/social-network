package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static servlet.LoginServlet.currentUserId;

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

        user = new UserDao().getById(currentUserId);

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String city = user.getCity();
        Date birthDay = user.getBirthday();
        String country = user.getCountry();
        String userInfo = user.getUser_info();
        String userLink = user.getLink();

        if(firstName == null || firstName.isEmpty()){
            request.setAttribute("name",
                    "not information");
        }else {
            request.setAttribute("name",
                    firstName);
        }

        if(lastName == null || lastName.isEmpty()){
            request.setAttribute("lastName",
                    "not information");
        }else{
            request.setAttribute("lastName",
                    lastName);
        }

        if(email == null || email.isEmpty()){
            request.setAttribute("email",
                    "not information");
        }else{
            request.setAttribute("email",
                    email);
        }

        if(birthDay == null){
            request.setAttribute("birthday",
                    "not information");
        }else{
            request.setAttribute("birthday",
                    birthDay);
        }

        if(country == null || country.isEmpty()){
            request.setAttribute("country",
                    "not information");
        }else {
            request.setAttribute("country",
                    country);
        }

        if(city == null || city.isEmpty()){
            request.setAttribute("city",
                    "not information");
        }else{
            request.setAttribute("city",
                    city);
        }

        if(userInfo == null || userInfo.isEmpty()){
            request.setAttribute("userInfo",
                    "not information");
        }else {
            request.setAttribute("userInfo",
                    userInfo);
        }

        if(userLink == null || userLink.isEmpty()){
            request.setAttribute("userLink",
                    "not information");
        }else {
            request.setAttribute("userLink",
                    userLink);
        }

        getServletContext().getRequestDispatcher("/profile.jsp").forward(
                request, response);

    }
}
