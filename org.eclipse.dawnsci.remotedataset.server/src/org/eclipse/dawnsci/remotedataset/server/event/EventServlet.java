package org.eclipse.dawnsci.remotedataset.server.event;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

@SuppressWarnings("serial")
public class EventServlet extends WebSocketServlet {

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request,
			                            String             protocol) {
		return new FileMonitorSocket(request);
	}
}