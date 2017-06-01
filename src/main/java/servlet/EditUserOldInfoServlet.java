package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.LoginServlet.currentUserId;

/**
 * Created by misha on 01.06.17.
 */

@WebServlet(name="editUserOldInfoServlet", urlPatterns="/editUserOldInfoServlet")
public class EditUserOldInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        UserDao userDao = new UserDao();

        User user = userDao.getById(currentUserId);

        request.setAttribute("oldFirstName", user.getFirstName());
        request.setAttribute("oldLastName", user.getLastName());
        request.setAttribute("oldEmail", user.getEmail());
        request.setAttribute("oldUserInfo", user.getUser_info());
        request.setAttribute("oldCountry", user.getCountry());
        request.setAttribute("oldCity", user.getCity());

        getServletContext().getRequestDispatcher("/editProfile.jsp").forward(
                request, response);
    }
}
