package org.eclipse.dawnsci.data.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.eclipse.dawnsci.analysis.api.dataset.DataEvent;
import org.eclipse.dawnsci.analysis.api.dataset.DataListenerDelegate;
import org.eclipse.dawnsci.analysis.api.dataset.IDataListener;
import org.eclipse.dawnsci.analysis.api.dataset.IRemoteDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.AbstractDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.LazyDataset;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.eclipse.jetty.websocket.WebSocketClient;
import org.eclipse.jetty.websocket.WebSocketClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class manages a remote connection to data and
 * allows datasets to work seamlessly remotely as if
 * they were ILazyDatasets.
 * 
 * 
 * 
 * @author Matthew Gerring
 *
 */
public class RemoteDataset extends LazyDataset implements IRemoteDataset {
	
	private static final Logger logger = LoggerFactory.getLogger(RemoteDataset.class);
	
	// Server and port
	private final String serverName;
	private final int    port;

	// Data
	private String path;
	private String dataset;
	
	// Web socket stuff
	private Connection connection;
    private DataListenerDelegate eventDelegate;
	/**
	 * 
	 */
	private static final long serialVersionUID = -9031675045219778735L;

	public RemoteDataset(String serverName, int port) {
		super("unknown", Dataset.INT, new int[]{1}, null);
		this.serverName = serverName;
		this.port       = port;
		this.eventDelegate = new DataListenerDelegate();
	}
	
	/**
	 * Call to read the dataset, set current shape and create event connnection for
	 * IDynamicDataset part of the dataset
	 */
    public void connect() throws Exception {
    	
		this.loader = new RemoteLoader(URI.create(getSliceURL()));
		createInfo();
		createFileListener();
    }
    
    public void disconnect() throws Exception {
        if (connection.isOpen()) {
        	connection.sendMessage("Disconnected from "+path);
       	    connection.close();
        }
       	// TODO Close loader as well?    
    }
	
	private void createFileListener() throws Exception {
		
        URI uri = URI.create(getEventURL());

        WebSocketClientFactory factory = new WebSocketClientFactory();
        factory.start();
        WebSocketClient        client = new WebSocketClient(factory);

        final DataEventSocket clientSocket = new DataEventSocket();
        // Attempt Connect
        Future<Connection> fut = client.open(uri, clientSocket);

        // Wait for Connect
        connection = fut.get();

        // Send a message
        connection.sendMessage("Connected to "+path);
	}
	
	private class DataEventSocket implements WebSocket, WebSocket.OnTextMessage {

		@Override
		public void onOpen(Connection connection) {
			logger.debug(getClass()+" opened");
		}

		@Override
		public void onClose(int closeCode, String message) {
			logger.debug(getClass()+" closed");
		}

		@Override
		public synchronized void onMessage(String data) {			
			DataEvent evt = DataEvent.decode(data);
			if (evt.getShape()!=null) setShape(shape);
			eventDelegate.fire(evt);
		}
	}

	private void createInfo() throws Exception {
		
		List<String> info = getInfo();
		
		this.name  = info.get(0);
		this.shape = toIntArray(info.get(1));
		this.oShape = shape;
		this.dtype = Integer.parseInt(info.get(2));
		this.isize = Integer.parseInt(info.get(3));
		try {
			size = AbstractDataset.calcLongSize(shape);
		} catch (IllegalArgumentException e) {
			size = Long.MAX_VALUE; // this indicates that the entire dataset cannot be read in! 
		}	
	}

	private int[] toIntArray(String array) {
		
		final String[] split = array.substring(1, array.length()-1).split(",");
		final int[]    ret   = new int[split.length];
		for (int i = 0; i < split.length; i++) {
			ret[i] = Integer.parseInt(split[i].trim());
 		}
		return ret;
	}

	private List<String> getInfo() throws Exception {
		
		final List<String> ret = new ArrayList<String>();
		final URL url = new URL(getInfoURL());
		URLConnection  conn = url.openConnection();

		final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		try {
			String line = null;
			while((line = reader.readLine())!=null) ret.add(line);
		} finally {
			reader.close();
		}
		return ret;
	}

	@Override
	public void addDataListener(IDataListener l) {
		eventDelegate.addDataListener(l);
	}

	@Override
	public void removeDataListener(IDataListener l) {
		eventDelegate.removeDataListener(l);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	
	
	private String getSliceURL() throws Exception {
		return getURL("/slice/");
	}
	
	private String getEventURL() throws Exception {
		return getURL("ws", "/event/");
	}
	
	private String getInfoURL() throws Exception {
		return getURL("/info/");
	}
	
	private String getURL(String servlet) throws Exception {
        return getURL("http", servlet);
	}
	private String getURL(String proto, String servlet) throws Exception {
		
		final StringBuilder buf = new StringBuilder();
		buf.append(proto);
		buf.append("://");
		buf.append(serverName);
		buf.append(":");
		buf.append(port);
		buf.append(servlet);
		buf.append("?");
		append(buf, "path",    path);
		append(buf, "dataset", dataset);
		return buf.toString();
	}
	
	private void append(StringBuilder buf, String name, Object object) throws UnsupportedEncodingException {
		if (object==null || "".equals(object)) return;
		
		String value = object.toString();
		buf.append(name);
		buf.append("=");
		buf.append(URLEncoder.encode(value, "UTF-8"));
		buf.append("&");
	}

}
