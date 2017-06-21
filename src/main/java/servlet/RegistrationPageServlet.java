package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import util.SecurePassword;

/**
 * Servlet for Registration Page (registration.jsp)
 */

@WebServlet(name="servlet", urlPatterns="/servlet")
public class RegistrationPageServlet extends HttpServlet {

    User user = new User();
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

        String firstName = request.getParameter("name");
        String surName = request.getParameter("surname");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String confirmPass = request.getParameter("confirm-password");

        String url = ((HttpServletRequest)request).getRequestURL().toString();
        System.out.println(url);
        if(url.toLowerCase().contains("servlet")){
            url = url.replaceAll("servlet", "profile");
        }

        User checkUser = new UserDao().getUserByEmail(email);

        if(pass.isEmpty() || pass.length() < 6){
            request.setAttribute("message",
                    "Sorry, but your password isn't long enough." + "<br/>" +
                            "Try again");
        }else{
            if(pass.equals(confirmPass)){
                if(checkUser != null){
                    request.setAttribute("message",
                            "User with this Email already registered" + "</br>" +
                                    "Please, use another Email");

                    getServletContext().getRequestDispatcher("/registration.jsp").forward(
                            request, response);

                    System.out.println("User tried registration with existing email");
                }else {
                    String salt = secPass.getSalt();
                    String securedPassword = secPass.getSecurePassword(pass, salt);

                    user.setSalt(salt);
                    user.setPassword(securedPassword);
                    user.setFirstName(firstName);
                    user.setLastName(surName);
                    user.setEmail(email);
                    userDao.save(user);
                    user.setLink(url + "?" + user.getId());
                    userDao.update(user);

            /*request.setAttribute("message",
                    "Welcome! You are registered successfully!" +
                            "<p><a href='index.html'>Go to Main Page For Login</a></p>");*/
                    System.out.println("User " + user.getFirstName() + " successfully registered");

                    getServletContext().getRequestDispatcher("/index.html").forward(
                            request, response);
                }
            }else{
                request.setAttribute("message",
                        "Password and Confirm password must be the same!" + "<br/>" +
                                "Try again!");

                System.out.println("user inputted not correct password!");

                getServletContext().getRequestDispatcher("/registration.jsp").forward(
                        request, response);
            }
        }
    }
}