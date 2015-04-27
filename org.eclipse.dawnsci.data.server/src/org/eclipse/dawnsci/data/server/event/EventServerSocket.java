package org.eclipse.dawnsci.data.server.event;

import java.util.List;
import java.util.Map;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class EventServerSocket extends WebSocketAdapter {
	
    @Override
    public void onWebSocketConnect(Session sess) {
    	
        super.onWebSocketConnect(sess); 
        
        Map<String,List<String>> params = sess.getUpgradeRequest().getParameterMap();
        final List<String> paths = params.get("path");
        final String path = paths.get(0);
        System.out.println(path);
        
        try {
        	int count = 0;
        	while(isConnected() && count<10) {
			    Thread.sleep(1000);
			    sess.getRemote().sendString(path+" changed!");
			    ++count;
        	}
        	sess.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			sess.close();
		}
    }
    
    @Override
    public void onWebSocketText(String message) {
        super.onWebSocketText(message);
    }
    
    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        super.onWebSocketClose(statusCode,reason);
    }
    
    @Override
    public void onWebSocketError(Throwable cause) {
        super.onWebSocketError(cause);
    }
}