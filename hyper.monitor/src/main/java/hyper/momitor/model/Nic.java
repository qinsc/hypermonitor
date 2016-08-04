package hyper.momitor.model;

import java.io.Serializable;

public class Nic implements Serializable {
	private static final long serialVersionUID = -3657249281266036973L;

	private String nicId;
	private String nicName;
	private String mac;
	private String ip;
	private String hostId;

	/**
	 * @return the nicId
	 */
	public String getNicId() {
		return nicId;
	}

	/**
	 * @param nicId
	 *            the nicId to set
	 */
	public void setNicId(String nicId) {
		this.nicId = nicId;
	}

	/**
	 * @return the nicName
	 */
	public String getNicName() {
		return nicName;
	}

	/**
	 * @param nicName
	 *            the nicName to set
	 */
	public void setNicName(String nicName) {
		this.nicName = nicName;
	}

	/**
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * @param mac
	 *            the mac to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Nic [" + (nicId != null ? "nicId=" + nicId + ", " : "")
				+ (nicName != null ? "nicName=" + nicName + ", " : "") + (mac != null ? "mac=" + mac + ", " : "")
				+ (ip != null ? "ip=" + ip + ", " : "") + (hostId != null ? "hostId=" + hostId : "") + "]";
	}

}
