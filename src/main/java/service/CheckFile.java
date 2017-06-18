package service;

import java.io.File;

/**
 * Created by misha on 18.06.17.
 */
public class CheckFile {

    public String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
}
