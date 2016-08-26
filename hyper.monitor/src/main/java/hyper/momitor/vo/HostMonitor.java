package hyper.momitor.vo;

public class HostMonitor {
	private String hostId;
	private String monitorIp;
	private String etcdIp;
	private String etcdPort;
	private String heartBeatInterval;
	private String heartBeatTimeout;

	private String guacdIp;
	private String guacdPort;

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
	 * @return the monitorIp
	 */
	public String getMonitorIp() {
		return monitorIp;
	}

	/**
	 * @param monitorIp
	 *            the monitorIp to set
	 */
	public void setMonitorIp(String monitorIp) {
		this.monitorIp = monitorIp;
	}

	/**
	 * @return the ectdIp
	 */
	public String getEtcdIp() {
		return etcdIp;
	}

	/**
	 * @param ectdIp
	 *            the ectdIp to set
	 */
	public void setEtcdIp(String etcdIp) {
		this.etcdIp = etcdIp;
	}

	/**
	 * @return the etcdPort
	 */
	public String getEtcdPort() {
		return etcdPort;
	}

	/**
	 * @param etcdPort
	 *            the etcdPort to set
	 */
	public void setEtcdPort(String etcdPort) {
		this.etcdPort = etcdPort;
	}

	/**
	 * @return the guacdIp
	 */
	public String getGuacdIp() {
		return guacdIp;
	}

	/**
	 * @param guacdIp
	 *            the guacdIp to set
	 */
	public void setGuacdIp(String guacdIp) {
		this.guacdIp = guacdIp;
	}

	/**
	 * @return the guacdPort
	 */
	public String getGuacdPort() {
		return guacdPort;
	}

	/**
	 * @param guacdPort
	 *            the guacdPort to set
	 */
	public void setGuacdPort(String guacdPort) {
		this.guacdPort = guacdPort;
	}

	/**
	 * @return the heartBeatInterval
	 */
	public String getHeartBeatInterval() {
		return heartBeatInterval;
	}

	/**
	 * @param heartBeatInterval
	 *            the heartBeatInterval to set
	 */
	public void setHeartBeatInterval(String heartBeatInterval) {
		this.heartBeatInterval = heartBeatInterval;
	}

	/**
	 * @return the heartBeatTimeout
	 */
	public String getHeartBeatTimeout() {
		return heartBeatTimeout;
	}

	/**
	 * @param heartBeatTimeout
	 *            the heartbeatTimeout to set
	 */
	public void setHeartBeatTimeout(String heartBeatTimeout) {
		this.heartBeatTimeout = heartBeatTimeout;
	}

}
