package servlet;
 
import dao.UserDao;
import entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.CheckFile;
import service.UploadPhoto;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
 
/**
* Created by misha on 30.05.17.
*/
 
@WebServlet(name="uploadPhoto", urlPatterns="/uploadPhoto")
public class UploadPhotoServlet extends HttpServlet {
 
   UploadPhoto uploadPhoto = new UploadPhoto();
   CheckFile checkFile = new CheckFile();
 
   private static final String UPLOAD_DIRECTORY = "photo";
 
   private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;   // 3MB
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
 
       String firstName = user.getFirstName();
       String lastName = user.getLastName();
       String city = user.getCity();
       String country = user.getCountry();
       String photo = user.getPath_to_photo();
       try {
           String musicSize = userDao.getMusicsSize(user);
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
                       String fileName = new File(item.getName()).getName();
                       String filePath = uploadPath + File.separator + fileName;
                       File storeFile = new File(filePath);
                       String fileExtension = checkFile.getFileExtension(storeFile);
 
                       if(fileExtension.equalsIgnoreCase("jpg") ||
                               fileExtension.equalsIgnoreCase("png") ||
                                   fileExtension.equalsIgnoreCase("gif")){
                           // saves the file on disk
                           item.write(storeFile);
 
                           uploadPhoto.savePhoto(storeFile, request);
 
                           request.setAttribute("message",
                                   "Photo has been uploaded successfully!");
 
                       }else {
                           request.setAttribute("message",
                                   "Photo is not uploaded successfully!" + "</br>" +
                                           "Please, use \".jpg\", \".png\" or \".gif\" extension!");
 
                       }
 
                   }
               }
           }
       }catch (Exception ex) {
           request.setAttribute("message",
                   "There was an error!" + "<br/>" +
                           "Try again, and select image format please!");
       }
 
       getServletContext().getRequestDispatcher("/uploadPhoto.jsp").forward(
               request, response);
   }
}