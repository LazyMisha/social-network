package servlet;

import dao.UserDao;
import entity.User;
import service.GetDate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet for Registration Page (registration.html)
 */

@WebServlet(name="servlet", urlPatterns="/servlet")
public class RegistrationPageServlet extends HttpServlet {

    User user = new User();
    UserDao userDao = new UserDao();
    GetDate getDate = new GetDate();

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
        String birthDate = birthDay + "-" + birthMonth + "-" + birthYear;
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
            response.sendRedirect("index.html");
            System.out.println("user did not inputted password!");
        }else{
            if(pass.equals(confirmPass)){

                user.setFirstName(firstName);
                user.setLastName(surName);
                user.setEmail(email);
                user.setPassword(pass);
                user.setDate(getDate.getToday());
                userDao.save(user);

                HttpSession hsession = request.getSession(true);
                hsession.setAttribute("id", user.getId());
                hsession.setAttribute("name", user.getFirstName());

                sc.getRequestDispatcher("/profile.jsp").forward(request, response);
                System.out.println("user successfully registered");
            }else{
                response.sendRedirect("registration.html");
                System.out.println("user inputted not correct password!");
            }
        }
    }
}