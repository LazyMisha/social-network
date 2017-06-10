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

import util.SecurePassword;

/**
 * Created by misha on 23.05.17.
 */

@WebServlet(name="editUser", urlPatterns="/editUser")
public class EditUserServlet extends HttpServlet {

    UserDao userDao = new UserDao();
    
    SecurePassword secPass=new SecurePassword();

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

        String firstNameMain = user.getFirstName();
        String lastNameMain = user.getLastName();
        String cityMain = user.getCity();
        String countryMain = user.getCountry();
        String photoMain = user.getPath_to_photo();
        String musicSize = userDao.getMusicsSize(user);

        if(musicSize == null){
            request.setAttribute("count", "0");
        }else {
            request.setAttribute("count", musicSize);
        }

        if(photoMain == null){
            request.setAttribute("photo", "photo/default.jpg");
        }else {
            request.setAttribute("photo", photoMain);
        }

        if(firstNameMain == null || firstNameMain.isEmpty()){
            request.setAttribute("name",
                    "no information");
        }else {
            request.setAttribute("name",
                    firstNameMain);
        }

        if(lastNameMain == null || lastNameMain.isEmpty()){
            request.setAttribute("lastName",
                    "no information");
        }else{
            request.setAttribute("lastName",
                    lastNameMain);
        }

        if(countryMain == null || countryMain.isEmpty()){
            request.setAttribute("country",
                    "no information");
        }else {
            request.setAttribute("country",
                    countryMain);
        }

        if(cityMain == null || cityMain.isEmpty()){
            request.setAttribute("city",
                    "no information");
        }else{
            request.setAttribute("city",
                    cityMain);
        }

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

        if(!firstName.isEmpty())
            user.setFirstName(firstName);

        if(!lastName.isEmpty())
            user.setLastName(lastName);

        if(!email.isEmpty())
            user.setEmail(email);

        if(!birthDate.isEmpty()){
            Date date = null;
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            try {
                date = formatter.parse(birthDate);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
            user.setBirthday(date);
        }

        if(!userInfo.isEmpty())
            user.setUser_info(userInfo);

        if(!pass.isEmpty()){
                String salt=secPass.getSalt();
                String securedPassword=secPass.getSecurePassword(pass, salt);
                
                user.setSalt(salt);
                user.setPassword(securedPassword);
        }

        if(!city.isEmpty())
            user.setCity(city);

        if(!country.isEmpty())
            user.setCountry(country);

        userDao.update(user);

        request.setAttribute("oldFirstName", user.getFirstName());
        request.setAttribute("oldLastName", user.getLastName());
        request.setAttribute("oldEmail", user.getEmail());
        request.setAttribute("oldUserInfo", user.getUser_info());
        request.setAttribute("oldCountry", user.getCountry());
        request.setAttribute("oldCity", user.getCity());

        request.setAttribute("message",
                "Your profile is updated");

        System.out.println("User " + user.getFirstName() + " is updated");

        getServletContext().getRequestDispatcher("/editProfile.jsp").forward(
            request, response);
    }
}
