package servlet;

import dao.UserDao;
import entity.User;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * @author Stepanyuk
 * Servlet for login page (index.html)
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public static Long currentUserId;

    User user = null;
    UserDao userDao = new UserDao();
    ServletContext sc = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        user = userDao.isRegistered(email, password);
        sc = getServletContext();

        if(user != null){

            HttpSession hsession=request.getSession(true);
            hsession.setAttribute("user", user);

            request.setAttribute("name", user.getFirstName());
            request.setAttribute("id", user.getId());

            currentUserId = user.getId();
            getServletContext().getRequestDispatcher("/profile.jsp").forward(
                    request, response);
        }else
            sc.getRequestDispatcher("/loginerror.html").forward(request, response);
    }
}