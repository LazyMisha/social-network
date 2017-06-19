package servlet;

import dao.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.SendEmail;

/**
 *
 * @author stepanyuk
 */
public class SendHashServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String passwordHash = new UserDao().getPasswordHash(request.getParameter("email"));
        
        if(passwordHash != null){
            String subject = "Password recovering";
            String to = request.getParameter("email");
            String content = "Folow this link to recover your password:\n" +
                    request.getScheme() + "://"+request.getServerName() + ":" +
                    request.getServerPort() + request.getContextPath() +                    
                    "/recoverPassword.jsp?email=" + to + "&passwordHash=" + passwordHash;
            SendEmail.sendEmail(to, subject, content);
        }else{
            request.setAttribute("noSuchEmail", "There is no users with such Email");
            getServletContext().getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
        }
        response.getWriter().write("The special link was successfully sent at your email!");
    }
}
