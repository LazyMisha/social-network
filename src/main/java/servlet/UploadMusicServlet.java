package servlet;

import dao.UserDao;
import entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.GetTags;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static service.GetTags.*;

/**
 * Created by misha on 17.05.17.
 * servlet for upload music to "web/upload" directory
 * getting tags from mp3 file
 * save music to DB
 */

@WebServlet(name="uploadFile", urlPatterns="/uploadFile")
public class UploadMusicServlet extends HttpServlet {

    GetTags getTags = new GetTags();

    private static final String UPLOAD_DIRECTORY = "upload";

    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; 	// 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;      // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;   // 50MB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        User user = (User) request.getSession().getAttribute("user");
        UserDao userDao = new UserDao();
        String musicSize;
        int songSize = 0;

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String city = user.getCity();
        String country = user.getCountry();
        String photo = user.getPath_to_photo();
        try {
            musicSize = userDao.getMusicsSize(user);
            songSize = Integer.parseInt(musicSize);
            if(musicSize == null || musicSize.isEmpty()){
                request.setAttribute("count", "0");
            }else {
                request.setAttribute("count", musicSize);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            request.setAttribute("count", "0");
        }

        if(photo == null){
            request.setAttribute("photo", "photo/default.jpg");
        }else {
            request.setAttribute("photo", photo);
        }

        if(firstName == null || firstName.isEmpty()){
            request.setAttribute("name",
                    "no information");
        }else {
            request.setAttribute("name",
                    firstName);
        }

        if(lastName == null || lastName.isEmpty()){
            request.setAttribute("lastName",
                    "no information");
        }else{
            request.setAttribute("lastName",
                    lastName);
        }

        if(country == null || country.isEmpty()){
            request.setAttribute("country",
                    "no information");
        }else {
            request.setAttribute("country",
                    country);
        }

        if(city == null || city.isEmpty()){
            request.setAttribute("city",
                    "no information");
        }else{
            request.setAttribute("city",
                    city);
        }

        if(songSize >= 100){
            request.setAttribute("message",
                    "You can not upload this song!" + "<br/>" +
                            "Your music space is full!");
        }else {
            if (!ServletFileUpload.isMultipartContent(request)) {
                PrintWriter writer = response.getWriter();
                writer.println("Error: Form must has enctype = multipart/form-data.");
                writer.flush();
                return;
            }

            // configures upload settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);

            String uploadPath = getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIRECTORY;

            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                @SuppressWarnings("unchecked")
                List<FileItem> formItems = upload.parseRequest(request);

                if (formItems != null && formItems.size() > 0) {
                    // iterates over form's fields
                    for (FileItem item : formItems) {
                        // processes only fields that are not form fields
                        if (!item.isFormField()) {
                            String fileName = UUID.randomUUID().toString() + ".mp3";
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);

                            System.out.println(storeFile.getName());

                            // saves the file on disk
                            item.write(storeFile);
                            request.setAttribute("message",
                                    "Upload has been done successfully!");

                            //Parse mp3 tags
                            getTags.getTagsFromMP3(storeFile);

                            if (songName == null) {
                                request.setAttribute("songName", "no information");
                            } else {
                                request.setAttribute("songName", songName);
                            }

                            if (genre == null) {
                                request.setAttribute("genre", "no information");
                            } else {
                                request.setAttribute("genre", genre);
                            }

                            if (singer == null) {
                                request.setAttribute("singer", "no information");
                            } else {
                                request.setAttribute("singer", singer);
                            }

                            if (composer == null) {
                                request.setAttribute("composer", "no information");
                            } else {
                                request.setAttribute("composer", composer);
                            }

                            if (album == null) {
                                request.setAttribute("album", "no information");
                            } else {
                                request.setAttribute("album", album);
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                request.setAttribute("message",
                        "There was an error!" + "<br/>" +
                                "Try again, and select mp3 format please!");
            }
        }

        getServletContext().getRequestDispatcher("/uploadMusic.jsp").forward(
                request, response);
    }
}