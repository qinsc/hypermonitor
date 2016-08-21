/**
 * 
 */
package hyper.momitor.vo;

/**
 * @author qinscx
 *
 */
public class NicInfo {
	private String nicId;
	private String nicName;
	private String mac;
	private String ip;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NicInfo [" + (nicId != null ? "nicId=" + nicId + ", " : "")
				+ (nicName != null ? "nicName=" + nicName + ", " : "") + (mac != null ? "mac=" + mac + ", " : "")
				+ (ip != null ? "ip=" + ip : "") + "]";
	}

}
