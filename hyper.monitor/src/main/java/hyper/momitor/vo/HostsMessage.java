/**
 * 
 */
package hyper.momitor.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinscx
 *
 */
public class HostsMessage implements Serializable{
	private static final long serialVersionUID = 2284605027607363225L;
	
	private List<String> hostIds = new ArrayList<String>();
	private String message;
	/**
	 * @return the hostIds
	 */
	public List<String> getHostIds() {
		return hostIds;
	}
	/**
	 * @param hostIds the hostIds to set
	 */
	public void setHostIds(List<String> hostIds) {
		this.hostIds = hostIds;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HostsMessage [" + (hostIds != null ? "hostIds=" + hostIds + ", " : "")
				+ (message != null ? "message=" + message : "") + "]";
	}
}
