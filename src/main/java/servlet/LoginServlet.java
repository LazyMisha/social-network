
package servlet;

import dao.UserDao;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stepanyuk
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

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
        
        PrintWriter pw=response.getWriter();

        String email=request.getParameter("email");
        String password=request.getParameter("password");
        
        boolean regConfirm=userDao.isRegistered(email, password);
        ServletContext sc=getServletContext();
        if(regConfirm)
            sc.getRequestDispatcher("/profile.html").forward(request, response);
        else
            sc.getRequestDispatcher("/loginerror.html").forward(request, response);
    }
}
