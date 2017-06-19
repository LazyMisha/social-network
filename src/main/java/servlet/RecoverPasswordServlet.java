package servlet;

import dao.UserDao;
import entity.User;
import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.SecurePassword;
import util.SendEmail;

/**
 *
 * @author stepanyuk
 */
@WebServlet(name = "RecoverPasswordServlet", urlPatterns = {"/RecoverPasswordServlet"})
public class RecoverPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String email = request.getParameter("email");
        String passwordHash = request.getParameter("hash");
        
        if(passwordHash.equals(userDao.getPasswordHash(email))){
            User user = userDao.getUserByEmail(email);
            SecurePassword secPass = new SecurePassword();
            
            byte[] newPassBytes = new byte[3];
            StringBuilder newPass = new StringBuilder();
            new SecureRandom().nextBytes(newPassBytes);
            for(int i = 0; i < newPassBytes.length; i++){
                    newPass.append(Integer.toString((newPassBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
                                    
            String salt = secPass.getSalt();
            String securedPassword = secPass.getSecurePassword(newPass.toString(), salt);
            user.setSalt(salt);
            user.setPassword(securedPassword);
            userDao.update(user);
            
            String subject = "Password recovering";
            String to = email;
            String content = "Here's your new pass: "+newPass.toString();
            SendEmail.sendEmail(to, subject, content);
            
            response.getWriter().write("The new password was send to your email.");          
        }else
            response.getWriter().write("wrong hash!");
    }   
}