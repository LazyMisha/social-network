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

/**
 * Created by misha on 27.05.17.
 */
@WebServlet(name="profile", urlPatterns="/profile")
public class ProfileServlet  extends HttpServlet{

    User user;
    User friend;
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = ((HttpServletRequest)request).getRequestURL().toString();
        String queryString = ((HttpServletRequest)request).getQueryString();
        System.out.println(url + queryString);

        if (queryString != null) {
            friend = new UserDao().getById(Long.parseLong(queryString));
            user = (User)request.getSession().getAttribute("user");

            System.out.println("This is friend" + user.getFirstName() + " profile");
            System.out.println("This is " + user.getFirstName() + " profile");

            request.setCharacterEncoding("UTF-8");

            String friendFirstName = friend.getFirstName();
            String friendLastName = friend.getLastName();
            String friendCity = friend.getCity();
            String friendCountry = friend.getCountry();
            String friendPhoto = friend.getPath_to_photo();
            Date friendBirthDay = friend.getBirthday();
            String friendEmail = friend.getEmail();
            String friendUserInfo = friend.getUser_info();
            String friendUserLink = friend.getLink();

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

            if(friendPhoto == null){
                request.setAttribute("friendPhoto", "photo/default.jpg");
            }else {
                request.setAttribute("friendPhoto", friendPhoto);
            }

            if(friendFirstName == null || friendFirstName.isEmpty()){
                request.setAttribute("friendName",
                        "no information");
            }else {
                request.setAttribute("friendName",
                        friendFirstName);
            }

            if(friendLastName == null || friendLastName.isEmpty()){
                request.setAttribute("friendLastName",
                        "no information");
            }else{
                request.setAttribute("friendLastName",
                        friendLastName);
            }

            if(friendCountry == null || friendCountry.isEmpty()){
                request.setAttribute("friendCountry",
                        "no information");
            }else {
                request.setAttribute("friendCountry",
                        friendCountry);
            }

            if(friendCity == null || friendCity.isEmpty()){
                request.setAttribute("friendCity",
                        "no information");
            }else{
                request.setAttribute("friendCity",
                        friendCity);
            }

            if(friendBirthDay == null){
                request.setAttribute("friendBirthDay",
                        "no information");
            }else{
                request.setAttribute("friendBirthDay",
                        friendBirthDay);
            }

            if(friendEmail == null || friendEmail.isEmpty()){
                request.setAttribute("friendEmail",
                        "no information");
            }else{
                request.setAttribute("friendEmail",
                        friendEmail);
            }

            if(friendUserInfo == null || friendUserInfo.isEmpty()){
                request.setAttribute("friendUserInfo",
                        "no information");
            }else {
                request.setAttribute("friendUserInfo",
                        friendUserInfo);
            }

            if(friendUserLink == null || friendUserLink.isEmpty()){
                request.setAttribute("friendUserLink",
                        "no information");
            }else {
                request.setAttribute("friendUserLink",
                        friendUserLink);
            }

            getServletContext().getRequestDispatcher("/userProfile.jsp").forward(
                    request, response);

        }else {
            user = (User) request.getSession().getAttribute("user");
            System.out.println("This is " + user.getFirstName() + " profile");

            request.setCharacterEncoding("UTF-8");

            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String city = user.getCity();
            String country = user.getCountry();
            String photo = user.getPath_to_photo();
            String email = user.getEmail();
            String userInfo = user.getUser_info();
            String userLink = user.getLink();
            Date birthDay = user.getBirthday();
            try {
                String musicSize = userDao.getMusicsSize(user);
                if (musicSize == null || musicSize.isEmpty()) {
                    request.setAttribute("count", "0");
                } else {
                    request.setAttribute("count", musicSize);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                request.setAttribute("count", "0");
            }

            if (photo == null) {
                request.setAttribute("pathToPhoto", "photo/default.jpg");
            } else {
                request.setAttribute("pathToPhoto", photo);
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

            if (email == null || email.isEmpty()) {
                request.setAttribute("email",
                        "no information");
            } else {
                request.setAttribute("email",
                        email);
            }

            if (birthDay == null) {
                request.setAttribute("birthday",
                        "no information");
            } else {
                request.setAttribute("birthday",
                        birthDay);
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

            if (userInfo == null || userInfo.isEmpty()) {
                request.setAttribute("userInfo",
                        "no information");
            } else {
                request.setAttribute("userInfo",
                        userInfo);
            }

            if (userLink == null || userLink.isEmpty()) {
                request.setAttribute("userLink",
                        "no information");
            } else {
                request.setAttribute("userLink",
                        userLink);
            }

            getServletContext().getRequestDispatcher("/profile.jsp").forward(
                    request, response);
        }
    }
}
