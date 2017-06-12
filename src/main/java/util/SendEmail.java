package util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author stepanyuk
 */
public class SendEmail {
    
    private static final String SMTP_HOST="smtp.gmail.com";
    private static final String SMTP_PORT="587";
    private static final String FROM_EMAIL="yetanothermusicsocialnetwork@gmail.com";
    private static final String FROM="YAMSN";
    private static final boolean AUTH=true;
    private static final boolean STARTTLS=true;

    public static void sendEmail(String to, String subject, String content){
        Authenticator auth=new MailAuthenticator();

        Properties props=new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", AUTH);
        props.put("mail.smtp.starttls.enable", STARTTLS);
        
        Session session=Session.getDefaultInstance(props, auth);
        
        Message msg=new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(FROM_EMAIL, FROM));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(content);
            Transport.send(msg);
        } catch (MessagingException | UnsupportedEncodingException ex) {
            System.err.println(ex);
        }
    }
}
