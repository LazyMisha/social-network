package util;

import dao.MessageDao;
import dao.UserDao;
import entity.Message;
import entity.User;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author stepanyuk
 */

@ServerEndpoint(value="/dialogEndpoint", configurator=ServerConfigurator.class)
public class WebSocketServer {

    static Set<Session> onlineUsers=Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void onOpen(EndpointConfig endpointConfig, Session userSession) {
        System.out.println("onOpen---------------------");
        
        userSession.getUserProperties().put("userID", endpointConfig.getUserProperties().get("userID"));
        userSession.getUserProperties().put("friendID", endpointConfig.getUserProperties().get("friendID"));
        onlineUsers.add(userSession);
    }

    @OnClose
    public void onClose(Session userSession) {
        System.out.println("onClose---------------------");
        
        onlineUsers.remove(userSession);
    }

    @OnMessage
    public void onMessage(String message,Session session) {
        System.out.println("onMessage---------------------"+message);
        
        Long userID=(Long)session.getUserProperties().get("userID");
        Long friendID=(Long)session.getUserProperties().get("friendID");
                        
        User user= new UserDao().getById(userID);
        User friend= new UserDao().getById(friendID);
        
        System.out.println("--------messageToDB: "+message);
        Message m=new Message();
        m.setMessage(message);
        m.setUser_id_from(user);
        m.setUser_id_to(friend);
        new MessageDao().sendMessage(m);
        
        Iterator<Session> sessionIter=onlineUsers.iterator();
        while(sessionIter.hasNext()){
            Session sessionForSubmit=sessionIter.next();

            if(((Long)sessionForSubmit.getUserProperties().get("userID")).equals(friendID)
                    &&((Long)sessionForSubmit.getUserProperties().get("friendID")).equals(userID)){
                try {
                    sessionForSubmit.getBasicRemote().sendText(message);
                    System.out.println("sendText---------------------"+message);
                } catch (IOException ex) {
                    System.out.println("Error in onMessage submit------------------");
                }
                return;
            }
        }
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("onError--------------");
        System.err.println(t);
    }
}
