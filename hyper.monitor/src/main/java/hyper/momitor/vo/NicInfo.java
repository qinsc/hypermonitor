/**
 * 
 */
package hyper.momitor.vo;

import java.util.Arrays;

/**
 * @author qinscx
 *
 */
public class NicInfo {
	private String name;
	private String mac;
	private String[] ips;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the ips
	 */
	public String[] getIps() {
		return ips;
	}

	/**
	 * @param ips
	 *            the ips to set
	 */
	public void setIps(String[] ips) {
		this.ips = ips;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NicInfo [" + (name != null ? "name=" + name + ", " : "") + (mac != null ? "mac=" + mac + ", " : "")
				+ (ips != null ? "ips=" + Arrays.toString(ips) : "") + "]";
	}

}
