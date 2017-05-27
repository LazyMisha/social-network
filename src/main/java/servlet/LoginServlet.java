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

    public static Long currentUserId;

    User user;

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

        user = new UserDao().isRegistered(email, password);

        if(user != null){
            HttpSession hsession=request.getSession(true);
            hsession.setAttribute("user", user);

            request.setAttribute("name", user.getFirstName());

            currentUserId = user.getId();

            response.sendRedirect(request.getContextPath() + "/profile");
        }else
            getServletContext().getRequestDispatcher("/loginerror.html").forward(request, response);
    }
}