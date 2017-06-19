package util;

import entity.User;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 *
 * @author stepanyuk
 */
public class ServerConfigurator extends ServerEndpointConfig.Configurator{
 
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response){
        Long userID = ((User)((HttpSession)request.getHttpSession()).getAttribute("user")).getId();
        Long friendID = (Long)((HttpSession)request.getHttpSession()).getAttribute("friendID");
        sec.getUserProperties().put("userID", userID);
        sec.getUserProperties().put("friendID", friendID);
    }
}
