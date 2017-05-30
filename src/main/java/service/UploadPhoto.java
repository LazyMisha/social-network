package service;


import dao.UserDao;
import entity.User;

import java.io.File;

import static servlet.LoginServlet.currentUserId;

/**
 * Created by misha on 30.05.17.
 */
public class UploadPhoto {

    User user = new User();

    UserDao userDao = new UserDao();

    public static String secondPartPhoto = "";

    public void savePhoto(File photo){

        String filePath = photo.getPath();
        int index = filePath.indexOf("photo");
        secondPartPhoto = filePath.substring(index);

        user = userDao.getById(currentUserId);

        if(secondPartPhoto != null)
            user.setPath_to_photo(secondPartPhoto);

        userDao.update(user);
    }

}
