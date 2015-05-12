package org.eclipse.dawnsci.data.server.event;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.FileTime;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.dawnsci.analysis.api.dataset.DataEvent;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.monitor.IMonitor;
import org.eclipse.dawnsci.data.server.ServiceHolder;
import org.eclipse.jetty.websocket.WebSocket;

class FileMonitorSocket implements WebSocket {
	
	private HttpServletRequest request;
	private boolean            connected;

	FileMonitorSocket(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void onOpen(Connection connection) {
		
		connected = true;
		final String spath = request.getParameter("path");
		final String sset  = request.getParameter("dataset");
		final Path   path  = Paths.get(spath);
		try {
			WatchService myWatcher = path.getFileSystem().newWatchService();
			
			QueueReader fileWatcher = new QueueReader(myWatcher, connection, spath, sset);
	        Thread th = new Thread(fileWatcher, path.getFileName()+" Watcher");
	        
	        // We may only monitor a directory
	        if (Files.isDirectory(path)) {
	        	path.register(myWatcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
	        } else {
	            path.getParent().register(myWatcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
	        }
	        th.start();
	 
    	} catch (Exception ne) {
			ne.printStackTrace();
			try {
				connection.sendMessage(ne.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void onClose(int closeCode, String message) {
		connected = false;
	}

    private class QueueReader implements Runnable {
    	 
        /** the watchService that is passed in from above */
        private WatchService watcher;
        private Connection   connection;
		private String spath;
		private String sdataset;
		
        public QueueReader(WatchService watcher, Connection connection, String path, String dataset) {
            this.watcher    = watcher;
            this.connection = connection;
            this.spath      = path;
            this.sdataset   = dataset;
        }
 
        /**
         * In order to implement a file watcher, we loop forever 
         * ensuring requesting to take the next item from the file 
         * watchers queue.
         */
        @Override
        public void run() {
            try {
            	// We are monitoring this file, check it against what has happened
       			final Path   path  = Paths.get(spath);
       			FileTime     time  = Files.getLastModifiedTime(path);
       			
       			// We wait until the file we are told to monitor exists.
       			while(!Files.exists(path)) {
       				Thread.sleep(200);
       			}
       			
                // get the first event before looping
                WatchKey key = null;
                while(((key = watcher.take()) != null) && connection.isOpen() && connected) {
                                 		
             		if (!Files.exists(path)) continue;
             		
            		for (WatchEvent<?> event : key.pollEvents()) {
            			
	             		if (!Files.exists(path)) continue;

 	             		Path   epath = (Path)event.context();
 	             		if (!path.endsWith(epath)) continue;
 	             		
 	             		FileTime tmp = Files.getLastModifiedTime(path);
 	             		if (time.equals(tmp)) continue;
 	             		time  = tmp;
 	             		
	             		// Data has changed, read its shape and publish the event using a web socket.
	             		final IDataHolder  holder = ServiceHolder.getLoaderService().getData(spath, new IMonitor.Stub());
	        			final ILazyDataset lz = sdataset!=null && !"".equals(sdataset)
				                              ? holder.getLazyDataset(sdataset)
				                              : holder.getLazyDataset(0);
	             		
	                	final DataEvent evt = new DataEvent(lz.getName(), lz.getShape());
	                	evt.setFilePath(spath);
	                    
	                    // We manually JSON the object because we
	                	// do not want a dependency and object simple
	                	String json = evt.encode();
	                	connection.sendMessage(json);
	                	
	                	break;
            		}
                	
                	key.reset();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                connection.close(403, e.getMessage());
            } 
        }
    }

}