package servlet;

import dao.UserDao;
import entity.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * @author Stepanyuk
 */

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new UserDao().isRegistered(email, password);

        if(user != null){
            HttpSession hsession=request.getSession();
            hsession.setAttribute("user", user);
            hsession.setMaxInactiveInterval(30*60);
            response.sendRedirect(request.getContextPath() + "/homePage");
        }else
            getServletContext().getRequestDispatcher("/loginerror.html").forward(request, response);
    }
}