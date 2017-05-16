package servlet;

import dao.UserDao;
import entity.User;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Stepanyuk
 * Servlet for login page (index.html)
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    User user=null;
    UserDao userDao = new UserDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        user=userDao.isRegistered(email, password);
        ServletContext sc=getServletContext();
        if(user!=null){

            HttpSession hsession=request.getSession(true);
            hsession.setAttribute("id", user.getId());
            hsession.setAttribute("nickname", user.getFirstName());
            
            sc.getRequestDispatcher("/profile.jsp").forward(request, response);
        }else
            sc.getRequestDispatcher("/loginerror.html").forward(request, response);
    }
}