package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for Registration Page (registration.html)
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
        String birthDay = request.getParameter("birth-day");
        String birthMonth = request.getParameter("birth-month");
        String birthYear = request.getParameter("birth-year");
        String birthDate = birthDay + "/" + birthMonth + "/" + birthYear;
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String confirmPass = request.getParameter("confirm-password");
        System.out.println(firstName);
        System.out.println(surName);
        System.out.println(email);
        System.out.println(pass);
        System.out.println(confirmPass);
        System.out.println(birthDate);

        ServletContext sc = getServletContext();

        if(pass.isEmpty()){
            sc.getRequestDispatcher("/registration.html").forward(request, response);
            System.out.println("user did not inputted password!");
        }else{
            if(pass.equals(confirmPass)){
                user.setFirstname(firstName);
                user.setLastname(surName);
                user.setEmail(email);
                user.setPassword(pass);
                user.setBirthDay(birthDate);
                userDao.save(user);

                sc.getRequestDispatcher("/profile.html").forward(request, response);
                System.out.println("user successfully registered");
            }else{
                sc.getRequestDispatcher("/registration.html").forward(request, response);
                System.out.println("user inputted not correct password!");
            }
        }
    }
}