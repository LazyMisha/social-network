package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author stepanyuk
 */
public class MailAuthenticator extends Authenticator{
    private final String mail = "yetanothermusicsocialnetwork@gmail.com";
    private final String password = "yamsn2017";
    
    @Override
    public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(mail,password);
    }
}