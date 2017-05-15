package servlet;

import dao.UserDao;
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

<<<<<<< HEAD
    User user=null;
=======
>>>>>>> misha/master
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
        
<<<<<<< HEAD
        user=userDao.isRegistered(email, password);
        ServletContext sc=getServletContext();
        if(user!=null){

            HttpSession hsession=request.getSession(true);
            hsession.setAttribute("id", user.getId());
            hsession.setAttribute("nickname", user.getFirstname());
            
            sc.getRequestDispatcher("/profile.jsp").forward(request, response);
        }else
=======
        boolean regConfirm=userDao.isRegistered(email, password);

        ServletContext sc = getServletContext();

        if(regConfirm){
            sc.getRequestDispatcher("/profile.html").forward(request, response);
            System.out.println("user successfully registered");
            System.out.println("email: " + email);
            System.out.println("password: " + password);
        }else{
>>>>>>> misha/master
            sc.getRequestDispatcher("/loginerror.html").forward(request, response);
            System.out.println("user inputted not correct email or password");
            System.out.println("inputted email: " + email);
            System.out.println("inputted password: " + password);
        }
    }
}