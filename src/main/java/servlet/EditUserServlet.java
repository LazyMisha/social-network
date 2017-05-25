package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static servlet.LoginServlet.currentUserId;

/**
 * Created by misha on 23.05.17.
 */

@WebServlet(name="editUser", urlPatterns="/editUser")
public class EditUserServlet extends HttpServlet {

    User user = new User();

    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        user = userDao.getById(currentUserId);

        String oldFirstName = user.getFirstName();
        String oldLastName = user.getLastName();
        String oldEmail = user.getEmail();
        String oldUserInfo = user.getUser_info();
        String oldPassword = user.getPassword();
        Date oldBirthDay = user.getBirthday();
        String oldCity = user.getCity();
        String oldCountry = user.getCountry();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDay = request.getParameter("birth-day");
        String birthMonth = request.getParameter("birth-month");
        String birthYear = request.getParameter("birth-year");
        String birthDate = birthDay + "-" + birthMonth + "-" + birthYear;
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String userInfo = request.getParameter("userInfo");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(pass);
        System.out.println(birthDate);
        System.out.println(userInfo);
        System.out.println(city);
        System.out.println(country);

        if(firstName.isEmpty()){
            user.setFirstName(oldFirstName);
        }else{
            user.setFirstName(firstName);
        }

        if(lastName.isEmpty()){
            user.setLastName(oldLastName);
        }else{
            user.setLastName(lastName);
        }

        if(email.isEmpty()){
            user.setEmail(oldEmail);
        }else {
            user.setEmail(email);
        }

        if(birthDate.isEmpty()){
            user.setBirthday(oldBirthDay);
        }else{
            Date date = null;
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            try {
                date = formatter.parse(birthDate);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
            user.setBirthday(date);
        }

        if(userInfo.isEmpty()){
            user.setUser_info(oldUserInfo);
        }else{
            user.setUser_info(userInfo);
        }

        if(pass.isEmpty()){
            user.setPassword(oldPassword);
        }else {
            user.setPassword(pass);
        }

        if(city.isEmpty()){
            user.setCity(oldCity);
        }else{
            user.setCity(city);
        }

        if(country.isEmpty()){
            user.setCity(oldCountry);
        }else {
            user.setCountry(country);
        }

        userDao.update(user);

        request.setAttribute("message",
                "Your profile is updated");

        getServletContext().getRequestDispatcher("/editProfile.jsp").forward(
            request, response);
    }
}
