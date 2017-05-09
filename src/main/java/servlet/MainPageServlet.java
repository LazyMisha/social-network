package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for Main Page (index.html)
 */

@WebServlet("/servlet")
public class MainPageServlet extends HttpServlet {

    User user = new User();
    UserDao userDao = new UserDao();

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

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

        if (pass.isEmpty()) {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("utf-8");

            PrintWriter out = response.getWriter();
            out.print("<h2>Error!!</h2>");
            out.print("<h2>Password is not correct</h2>");
            out.print("<h2>Password and Confirm Password are empty</h2>");
        }else {
            if (pass.equals(confirmPass)) {
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("utf-8");

                user.setFirstname(firstName);
                user.setLastname(surName);
                user.setEmail(email);
                user.setPassword(pass);
                user.setBirthDay(birthDate);
                userDao.save(user);

                PrintWriter out = response.getWriter();
                out.print("<h2>Hello </h2>" + firstName + "<h2>Welcom to New Social Network!</h2>");
                out.print("<h3>Your name is " + firstName + "</h3>");
                out.print("<h3>Your surname is " + surName + "</h3>");
                out.print("<h3>Your email is " + email + "</h3>");
                out.print("<h3>Your birthday is " + birthDate + "</h3>");
                out.print("<h3>Your pass is " + pass + "</h3>");
                out.close();
            } else {
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("utf-8");

                PrintWriter out = response.getWriter();
                out.print("<h2>Error!!</h2>");
                out.print("<h2>Password is not correct</h2>");
                out.print("<h2>Password and Confirm Password must be the same</h2>");
            }
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

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

        if (pass.isEmpty()) {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("utf-8");

            PrintWriter out = response.getWriter();
            out.print("<h2>Error!!</h2>");
            out.print("<h2>Password is not correct</h2>");
            out.print("<h2>Password and Confirm Password are empty</h2>");
        } else {
            if (pass.equals(confirmPass)) {
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("utf-8");

                user.setFirstname(firstName);
                user.setLastname(surName);
                user.setEmail(email);
                user.setPassword(pass);
                user.setBirthDay(birthDate);
                userDao.save(user);

                PrintWriter out = response.getWriter();
                out.print("<h2>Hello </h2>" + firstName + "<h2>Welcom to New Social Network!</h2>");
                out.print("<h3>Your name is " + firstName + "</h3>");
                out.print("<h3>Your surname is " + surName + "</h3>");
                out.print("<h3>Your email is " + email + "</h3>");
                out.print("<h3>Your birthday is " + birthDate + "</h3>");
                out.print("<h3>Your pass is " + pass + "</h3>");
                out.close();
            } else {
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("utf-8");

                PrintWriter out = response.getWriter();
                out.print("<h2>Error!!</h2>");
                out.print("<h2>Password is not correct</h2>");
                out.print("<h2>Password and Confirm Password must be the same</h2>");
            }
        }
    }
}