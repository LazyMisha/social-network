package util;

import dao.MessageDao;
import dao.UserDao;
import entity.Message;
import entity.User;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.simple.JSONObject;

/**
 *
 * @author stepanyuk
 */

@ServerEndpoint(value = "/dialogEndpoint", configurator = ServerConfigurator.class)
public class WebSocketServer {

    static Set<Session> onlineUsers = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void onOpen(EndpointConfig endpointConfig, Session userSession) {      
        userSession.getUserProperties().put("userID", endpointConfig.getUserProperties().get("userID"));
        userSession.getUserProperties().put("friendID", endpointConfig.getUserProperties().get("friendID"));
        onlineUsers.add(userSession);
    }

    @OnClose
    public void onClose(Session userSession) {      
        onlineUsers.remove(userSession);
    }

    @OnMessage
    public void onMessage(String message,Session session) {       
        Long userID = (Long)session.getUserProperties().get("userID");
        Long friendID = (Long)session.getUserProperties().get("friendID");
                        
        User user = new UserDao().getById(userID);
        User friend = new UserDao().getById(friendID);
        
        Date messageDate = new Date();
        Message m = new Message();
        m.setMessage(message);
        m.setUser_id_from(user);
        m.setUser_id_to(friend);
        m.setMessageDate(messageDate);
        new MessageDao().sendMessage(m);
        
        Iterator<Session> iter = onlineUsers.iterator();
        while(iter.hasNext()){
            Session sessionForSubmit = iter.next();
            if(((Long)sessionForSubmit.getUserProperties().get("userID")).equals(friendID)&&
                    ((Long)sessionForSubmit.getUserProperties().get("friendID")).equals(userID)){
                try {
                    sessionForSubmit.getBasicRemote().sendText(buildJson(user, messageDate, message));
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                return;
            }
        }
    }

    @OnError
    public void onError(Throwable t) {
        System.err.println(t);
    }
    
    private String buildJson(User user, Date messageDate, String text){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMMMMMM yyyy 'at' k:m", Locale.ENGLISH);
        String date = sdf.format(messageDate);
        
        JSONObject json = new JSONObject();
        json.put("name", user.getFirstName());
        json.put("date", date);
        json.put("text", text);
        return json.toJSONString();
    }
}
