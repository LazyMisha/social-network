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

    ServletContext sc=null;
    MusicDao md=new MusicDao();
    ArrayList<Music> musicArr;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

            sc=getServletContext();
            sc.getRequestDispatcher("/searchResult.jsp").forward(request, response);
        }else{
            request.setAttribute("song","can't find such song");
            sc=getServletContext();
            sc.getRequestDispatcher("/searchResult.jsp").forward(request, response);
        }
    }
}
