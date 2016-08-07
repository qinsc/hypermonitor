/**
 * 
 */
package hyper.momitor.vo;

import hyper.momitor.model.Host;

/**
 * @author qinscx
 *
 */
public class HostInfo {
	private boolean selected;
	private String hostId;
	private int online; // 0 为离线，1为在线
	private String hostName;
	private String manageIp;
	private String os;
	private String hostGroups;
	private String tags;
	private int cpuUsage;
	private int memUsage;
	private String message;

	public HostInfo() {
	}

	public HostInfo(Host host) {
		this.hostId = host.getHostId();
		this.hostName = host.getHostName();
		this.os = host.getOs();
		this.manageIp = host.getManageIp();
	}

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
	 * @return the manageIp
	 */
	public String getManageIp() {
		return manageIp;
	}

	/**
	 * @param manageIp
	 *            the manageIp to set
	 */
	public void setManageIp(String manageIp) {
		this.manageIp = manageIp;
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
	

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the cpuUsage
	 */
	public int getCpuUsage() {
		return cpuUsage;
	}

	/**
	 * @param cpuUsage the cpuUsage to set
	 */
	public void setCpuUsage(int cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	/**
	 * @return the memUsage
	 */
	public int getMemUsage() {
		return memUsage;
	}

	/**
	 * @param memUsage the memUsage to set
	 */
	public void setMemUsage(int memUsage) {
		this.memUsage = memUsage;
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
		return "HostInfo [selected=" + selected + ", " + (hostId != null ? "hostId=" + hostId + ", " : "") + "online="
				+ online + ", " + (hostName != null ? "hostName=" + hostName + ", " : "")
				+ (manageIp != null ? "manageIp=" + manageIp + ", " : "") + (os != null ? "os=" + os + ", " : "")
				+ (hostGroups != null ? "hostGroups=" + hostGroups + ", " : "")
				+ (tags != null ? "tags=" + tags + ", " : "") + "cpuUsage=" + cpuUsage + ", memUsage=" + memUsage + ", "
				+ (message != null ? "message=" + message : "") + "]";
	}

	

}
