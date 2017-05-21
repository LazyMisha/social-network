package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by misha on 21.05.17.
 * get current date
 * or operations with dates
 */
public class GetDate {

    public Date getToday(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        dateFormat.format(date);
        return date;
    }
}