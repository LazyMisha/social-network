package servlet;

import dao.*;
import entity.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * @author Stepanyuk
 */

@WebServlet(name = "SendMessageServlet", urlPatterns = {"/SendMessageServlet"})
public class SendMessageServlet extends HttpServlet {
            
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        Long friendID=Long.valueOf(request.getParameter("friend_id"));

        User friend= new UserDao().getUser(friendID);
        User user=(User)request.getSession().getAttribute("user");
                
        String text=request.getParameter("message");
        
        if(text!=null && text.length()!=0){
            Message message=new Message();
            message.setMessage(text);
            message.setUser_id_from(user);
            message.setUser_id_to(friend);
            new MessageDao().sendMessage(message);
        }
        request.setAttribute("friend", friend);
        getServletContext().getRequestDispatcher("/dialog.jsp").forward(request, response);
    }
}
