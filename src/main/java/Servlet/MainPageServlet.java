package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by misha on 06.05.17.
 */

@WebServlet("/servlet")
public class MainPageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String surName = request.getParameter("surname");
        System.out.println(firstName);
        System.out.println(surName);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        out.print("<h2>Hello </h2>" + firstName + "<h2>Welcom to New Social Network!</h2>");
        out.print("<h3>Your name is " + firstName + "</h3>");
        out.println("<h3>Your surname is " + surName + "</h3>");
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("New servlet application");

        request.setCharacterEncoding("utf-8");

        String firstName = request.getParameter("firstname");
        String surName = request.getParameter("surname");
        System.out.println(firstName);
        System.out.println(surName);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        out.print("<h2>Hello </h2>" + firstName + "<h2>Welcom to New Social Network!</h2>");
        out.print("<h3>Your name is " + firstName + "</h3>");
        out.println("<h3>Your surname is " + surName + "</h3>");
        out.close();
    }

}
