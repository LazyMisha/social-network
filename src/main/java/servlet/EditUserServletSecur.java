package servlet;

import dao.UserDao;
import entity.User;
import util.SecurePassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by misha on 20.06.17.
 */

@WebServlet(name="editUserSecur", urlPatterns="/editUserSecur")
public class EditUserServletSecur extends HttpServlet {

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

        User user = (User) request.getSession().getAttribute("user");

        String firstNameMain = user.getFirstName();
        String lastNameMain = user.getLastName();
        String cityMain = user.getCity();
        String countryMain = user.getCountry();
        String photoMain = user.getPath_to_photo();
        try {
            String musicSize = new UserDao().getMusicsSize(user);
            if(musicSize == null || musicSize.isEmpty()){
                request.setAttribute("count", "0");
            }else {
                request.setAttribute("count", musicSize);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            request.setAttribute("count", "0");
        }

        if(photoMain == null){
            request.setAttribute("photo", "photo/default.jpg");
        }else {
            request.setAttribute("photo", photoMain);
        }

        if(firstNameMain == null || firstNameMain.isEmpty()){
            request.setAttribute("firstName",
                    "no information");
        }else {
            request.setAttribute("firstName",
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


        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String confPass = request.getParameter("confirm-password");

        if(pass.isEmpty() || pass.length() < 6){
            request.setAttribute("message",
                    "Password must be more longer");
        }else {
            if (pass.equals(confPass)) {
                if (!email.isEmpty())
                    user.setEmail(email);

                String salt = secPass.getSalt();
                String securedPassword = secPass.getSecurePassword(pass, salt);

                user.setSalt(salt);
                user.setPassword(securedPassword);

                new UserDao().update(user);

                request.setAttribute("message",
                        "Password is updated");
            }else {
                request.setAttribute("message",
                        "Password and Confirm Password must be the same");
                getServletContext().getRequestDispatcher("/editProfile.jsp").forward(
                        request, response);
            }
        }

        request.setAttribute("oldFirstName", user.getFirstName());
        request.setAttribute("oldLastName", user.getLastName());
        request.setAttribute("oldUserInfo", user.getUser_info());
        request.setAttribute("oldCountry", user.getCountry());
        request.setAttribute("oldCity", user.getCity());
        request.setAttribute("oldEmail", user.getEmail());

        getServletContext().getRequestDispatcher("/editProfile.jsp").forward(
                    request, response);
        }
    }
