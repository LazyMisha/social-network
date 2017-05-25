package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet for Registration Page (registration.jsp)
 */

@WebServlet(name="servlet", urlPatterns="/servlet")
public class RegistrationPageServlet extends HttpServlet {

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

        String firstName = request.getParameter("name");
        String surName = request.getParameter("surname");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String confirmPass = request.getParameter("confirm-password");

        System.out.println(firstName);
        System.out.println(surName);
        System.out.println(email);
        System.out.println(pass);
        System.out.println(confirmPass);

        if(pass.isEmpty()){
            request.setAttribute("message",
                    "Password field is empty!" + "<br/>" +
                            "Try again");
            System.out.println("user did not inputted password!");
        }else{
            if(pass.equals(confirmPass)){

                user.setFirstName(firstName);
                user.setLastName(surName);
                user.setEmail(email);
                user.setPassword(pass);
                userDao.save(user);

                request.setAttribute("message",
                        "Welcome! You are registered successfully!" +
                                "<p><a href='index.html'>Go to Main Page For Login</a></p>");
                System.out.println("user successfully registered");
            }else{
                request.setAttribute("message",
                        "Password and Confirm password must be the same!" + "<br/>" +
                                "Try again!");
                System.out.println("user inputted not correct password!");
            }
        }
        getServletContext().getRequestDispatcher("/registration.jsp").forward(
                request, response);
    }
}