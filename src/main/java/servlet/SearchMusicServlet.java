package servlet;

import dao.MusicDao;
import entity.Music;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "SerchMusicServlet", urlPatterns = {"/SerchMusicServlet"})
public class SearchMusicServlet extends HttpServlet {

    MusicDao md=new MusicDao();
    ArrayList<Music> musicArr;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        musicArr=md.searchMusic(request.getParameter("search"));
        String result="";

        if(!musicArr.isEmpty()){
            for(int i=0;i<musicArr.size();i++){

                String fullPath = musicArr.get(i).getPath();
                int index = fullPath.indexOf("upload");
                String path= fullPath.substring(index);

                result+="<p>"+musicArr.get(i).getSinger()+" - "+musicArr.get(i).getSong_name()+
                        "</p>"+"<audio controls><source src=\""+path+
                        "\" type=\"audio/mpeg\"></audio><br/><br/>";
            }

            request.setAttribute("song", result);

        }else{
            request.setAttribute("song","<h1>Can't find such song!<h1>");
        }

        getServletContext().getRequestDispatcher("/home.jsp").forward(
                request, response);
    }
}
