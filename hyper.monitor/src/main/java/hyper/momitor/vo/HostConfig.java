package hyper.momitor.vo;

public class HostConfig {
	private String hostName;
	private HostMonitor monitor;

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
	 * @return the monitor
	 */
	public HostMonitor getMonitor() {
		return monitor;
	}

	/**
	 * @param monitor
	 *            the monitor to set
	 */
	public void setMonitor(HostMonitor monitor) {
		this.monitor = monitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HostConfig [" + (hostName != null ? "hostName=" + hostName + ", " : "")
				+ (monitor != null ? "monitor=" + monitor : "") + "]";
	}

}
