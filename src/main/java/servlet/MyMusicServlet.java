package servlet;

import dao.UserSongsDao;
import entity.Music;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 07.06.17.
 */

@WebServlet(name="myMusic", urlPatterns="/myMusic")
public class MyMusicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String songs = "";

        request.setCharacterEncoding("UTF-8");
        response.setContentType("charset=UTF-8");

        User user = (User) request.getSession().getAttribute("user");
        ArrayList<Music> music = new UserSongsDao().getByUserId(user.getId());

        if(!music.isEmpty()) {
            for (int i = 0; i < music.size(); i++) {
                String path = music.get(i).getPath();
                int index = path.indexOf("upload");
                path = path.substring(index);

                songs = "<p>" + music.get(i).getSinger() + " - " + music.get(i).getSong_name() +
                        "</p>" + "<audio controls><source src=\"" + path +
                        "\" type=\"audio/mpeg\"></audio><br/><br/>";
            }

            request.setAttribute("songs", songs);
        }else {
            request.setAttribute("songs", "You don't have ane songs");
        }

        getServletContext().getRequestDispatcher("/yourSongs.jsp").forward(
                request, response);
    }
}
