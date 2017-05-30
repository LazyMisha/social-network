package servlet;

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
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("message",
                                "Upload has been done successfully!");

                        //Parse mp3 tags
                        getTags.getTagsFromMP3(storeFile);

                        if(songName == null){
<<<<<<< HEAD
                            request.setAttribute("songName", "not information");
=======
                            request.setAttribute("songName", "Song Name: no information");
>>>>>>> Added password hashing function
                        }else {
                            request.setAttribute("songName", songName);
                        }

                        if(genre == null){
<<<<<<< HEAD
                            request.setAttribute("genre", "not information");
=======
                            request.setAttribute("genre", "Genre: no information");
>>>>>>> Added password hashing function
                        }else {
                            request.setAttribute("genre", genre);
                        }

                        if(singer == null){
<<<<<<< HEAD
                            request.setAttribute("singer", "not information");
=======
                            request.setAttribute("singer", "Singer: no information");
>>>>>>> Added password hashing function
                        }else {
                            request.setAttribute("singer", singer);
                        }

                        if(composer == null){
<<<<<<< HEAD
                            request.setAttribute("composer", "not information");
=======
                            request.setAttribute("composer", "Composer: no information");
>>>>>>> Added password hashing function
                        }else {
                            request.setAttribute("composer", composer);
                        }

                        if(album == null){
<<<<<<< HEAD
                            request.setAttribute("album", "not information");
=======
                            request.setAttribute("album", "Album: no information");
>>>>>>> Added password hashing function
                        }else {
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

        getServletContext().getRequestDispatcher("/uploadMusic.jsp").forward(
                request, response);
    }
}