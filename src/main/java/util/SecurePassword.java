package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author stepanyuk
 */
public class SecurePassword {    
    
    public String getSecurePassword(String password, String salt){
        
        StringBuilder sb = new StringBuilder();
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte [] bytes = md.digest(password.getBytes());
                        
            for(int i = 0; i < bytes.length; i++)
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }
        return sb.toString();
    }
    
    public String getSalt(){
        
        byte[] salt = new byte[16];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(salt);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < salt.length; i++){
                sb.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
        }        
        return sb.toString();
    }
}