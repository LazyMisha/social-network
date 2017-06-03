package service;


import dao.UserDao;
import entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * Created by misha on 30.05.17.
 */
public class UploadPhoto {

    User user = new User();

    UserDao userDao = new UserDao();

    public static String secondPartPhoto = "";

    public void savePhoto(File photo, HttpServletRequest request){

        String filePath = photo.getPath();
        int index = filePath.indexOf("photo");
        secondPartPhoto = filePath.substring(index);

        user = (User)request.getSession().getAttribute("user");

        if(secondPartPhoto != null)
            user.setPath_to_photo(secondPartPhoto);

        userDao.update(user);
    }

}
