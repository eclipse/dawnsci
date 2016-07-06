package org.eclipse.dawnsci.remotedataset.client;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import org.eclipse.dawnsci.remotedataset.Format;
import org.eclipse.january.dataset.Slice;
import org.eclipse.january.dataset.SliceND;

/**
 * Holds data and builds urls from it when required.
 * @author Matthew Gerring
 *
 */
public class URLBuilder {

	// Http
	private boolean    get = true;
	private URL        url;	

	// Server and port
	private final String serverName;
	private final int    port;
	
	// Slice Data	
	private String     slice;
	private String     bin;
	private Format     format;
	private String     histo;
	private long       sleep=100;
	
	// Data
	private String path;
	private String dataset;
	private boolean writingExpected=false;

	/**
	 * Used to override the slice URL, for instance for MJPG streams.
	 * @param url
	 */
	public URLBuilder(URL url) {
		this(url.getHost(), url.getPort());
		this.url = url;
	}

	/**
	 * Normal usage
	 * @param serverName
	 * @param port
	 */
	public URLBuilder(String serverName, int port) {
		this.serverName = serverName;
		this.port       = port;
	}

	public URL getSliceURL() throws Exception {
		if (url!=null) return url;
		return new URL(getURL("http", "/slice/", true));
	}
	
	public String getEventURL() throws Exception {
		return getURL("ws", "/event/", false);
	}
	
	public String getInfoURL() throws Exception {
		return getURL("http", "/info/", false);
	}
	
	public String getTreeURL() throws Exception {
		return getURL("http", "/tree/", false);
	}

	private String getURL(String proto, String servlet, boolean isSlice) throws Exception {
		
		final StringBuilder buf = new StringBuilder();
		buf.append(proto);
		buf.append("://");
		buf.append(serverName);
		buf.append(":");
		buf.append(port);
		buf.append(servlet);
		if (isGet()) { // Add params
			buf.append("?");
			append(buf, "path",    path);
			append(buf, "dataset", dataset);
			append(buf, "writingExpected", writingExpected);
			
			if (isSlice) {
				append(buf, "slice",   slice);
				append(buf, "bin",     bin);
			    append(buf, "format",  format);
			    append(buf, "histo",   histo);
			    append(buf, "sleep",   sleep);
			}
		}
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

	public boolean isGet() {
		return get;
	}
	public void setGet(boolean get) {
		this.get = get;
	}

	public String getSlice() {
		return slice;
	}

	public void setSlice(String slice) {
		this.slice = slice;
	}
	
	public void setSlice(SliceND slice) {
		setSlice(slice.convertToSlice());
	}

	public void setSlice(Slice[] convertToSlice) {
		this.slice = Slice.createString(convertToSlice);
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public String getHisto() {
		return histo;
	}

	public void setHisto(String histo) {
		this.histo = histo;
	}

	public long getSleep() {
		return sleep;
	}

	public void setSleep(long sleep) {
		this.sleep = sleep;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bin == null) ? 0 : bin.hashCode());
		result = prime * result + ((dataset == null) ? 0 : dataset.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + (get ? 1231 : 1237);
		result = prime * result + ((histo == null) ? 0 : histo.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + port;
		result = prime * result + ((serverName == null) ? 0 : serverName.hashCode());
		result = prime * result + (int) (sleep ^ (sleep >>> 32));
		result = prime * result + ((slice == null) ? 0 : slice.hashCode());
		result = prime * result + (writingExpected ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		URLBuilder other = (URLBuilder) obj;
		if (bin == null) {
			if (other.bin != null)
				return false;
		} else if (!bin.equals(other.bin))
			return false;
		if (dataset == null) {
			if (other.dataset != null)
				return false;
		} else if (!dataset.equals(other.dataset))
			return false;
		if (format != other.format)
			return false;
		if (get != other.get)
			return false;
		if (histo == null) {
			if (other.histo != null)
				return false;
		} else if (!histo.equals(other.histo))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (port != other.port)
			return false;
		if (serverName == null) {
			if (other.serverName != null)
				return false;
		} else if (!serverName.equals(other.serverName))
			return false;
		if (sleep != other.sleep)
			return false;
		if (slice == null) {
			if (other.slice != null)
				return false;
		} else if (!slice.equals(other.slice))
			return false;
		if (writingExpected != other.writingExpected)
			return false;
		return true;
	}

	public boolean isWritingExpected() {
		return writingExpected;
	}

	public void setWritingExpected(boolean writingExpected) {
		this.writingExpected = writingExpected;
	}

	public String getServerName() {
		return serverName;
	}

	public int getPort() {
		return port;
	}

}
