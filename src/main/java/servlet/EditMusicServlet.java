package servlet;

import dao.MusicDao;
import entity.Music;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static service.GetTags.*;

/**
 * Created by misha on 29.05.17.
 */

@WebServlet(name="editMusic", urlPatterns="/editMusic")
public class EditMusicServlet extends HttpServlet {

    Music music = new Music();
    MusicDao musicDao = new MusicDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            songName = request.getParameter("songName");
            genre = request.getParameter("genre");
            singer = request.getParameter("singer");
            composer = request.getParameter("composer");
            album = request.getParameter("album");

            System.out.println(songName);
            System.out.println(genre);
            System.out.println(singer);
            System.out.println(composer);
            System.out.println(album);

            music.setSong_name(songName);
            music.setSinger(singer);
            music.setPath(secondPart);
            music.setGenre(genre);
            music.setSize(fileSizeInMB);

            musicDao.save(music);

            request.setAttribute("message",
                    "Information has been successfully saved!");

        }catch (Exception e){

            System.out.println(e.getMessage());
            request.setAttribute("message",
                    "There was an error!" + "<br/>" +
                            "Try again, and select mp3 format please!");
        }

        getServletContext().getRequestDispatcher("/uploadMusic.jsp").forward(
                request, response);
    }
}
