/**
 * 
 */
package hyper.momitor.vo;

/**
 * @author qinscx
 *
 */
public class HostInfo {
	private String hostId;
	private int online;
	private String hostName;
	private String ip;
	private String os;
	private String hostGroups;
	private String tags;

	/**
	 * @return the hostId
	 */
	public String getHostId() {
		return hostId;
	}

	/**
	 * @param hostId
	 *            the hostId to set
	 */
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	/**
	 * @return the online
	 */
	public int getOnline() {
		return online;
	}

	/**
	 * @param online
	 *            the online to set
	 */
	public void setOnline(int online) {
		this.online = online;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the hostGroups
	 */
	public String getHostGroups() {
		return hostGroups;
	}

	/**
	 * @param hostGroups
	 *            the hostGroups to set
	 */
	public void setHostGroups(String hostGroups) {
		this.hostGroups = hostGroups;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HostInfo [" + (hostId != null ? "hostId=" + hostId + ", " : "") + "online=" + online + ", "
				+ (hostName != null ? "hostName=" + hostName + ", " : "") + (ip != null ? "ip=" + ip + ", " : "")
				+ (os != null ? "os=" + os + ", " : "") + (hostGroups != null ? "hostGroups=" + hostGroups + ", " : "")
				+ (tags != null ? "tags=" + tags : "") + "]";
	}

}
