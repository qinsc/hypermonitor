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
	private String hostId;
	private int online; // 0 为离线，1为在线
	private String hostName;
	private String manageIp;
	private String os;
	private String desc;

	public HostInfo() {
	}

	public HostInfo(Host host) {
		this.hostId = host.getHostId();
		this.hostName = host.getHostName();
		this.desc = host.getDesc();
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
	 * @return Returns the desc.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            The desc to set.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HostInfo [" + (hostId != null ? "hostId=" + hostId + ", " : "") + "online=" + online + ", "
				+ (hostName != null ? "hostName=" + hostName + ", " : "")
				+ (manageIp != null ? "manageIp=" + manageIp + ", " : "") + (os != null ? "os=" + os + ", " : "")
				+ (desc != null ? "desc=" + desc + ", " : "") + "]";
	}

}
