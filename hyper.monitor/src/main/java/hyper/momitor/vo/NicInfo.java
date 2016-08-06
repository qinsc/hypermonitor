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
	private long bytesSent;
	private long bytesRecv;
	private long packetsSent;
	private long packetsRecv;
	private long errin;
	private long errout;
	private long dropin;
	private long dropout;
	private long fifoin;
	private long fifoout;

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
	 * @return the bytesSent
	 */
	public long getBytesSent() {
		return bytesSent;
	}

	/**
	 * @param bytesSent
	 *            the bytesSent to set
	 */
	public void setBytesSent(long bytesSent) {
		this.bytesSent = bytesSent;
	}

	/**
	 * @return the bytesRecv
	 */
	public long getBytesRecv() {
		return bytesRecv;
	}

	/**
	 * @param bytesRecv
	 *            the bytesRecv to set
	 */
	public void setBytesRecv(long bytesRecv) {
		this.bytesRecv = bytesRecv;
	}

	/**
	 * @return the packetsSent
	 */
	public long getPacketsSent() {
		return packetsSent;
	}

	/**
	 * @param packetsSent
	 *            the packetsSent to set
	 */
	public void setPacketsSent(long packetsSent) {
		this.packetsSent = packetsSent;
	}

	/**
	 * @return the packetsRecv
	 */
	public long getPacketsRecv() {
		return packetsRecv;
	}

	/**
	 * @param packetsRecv
	 *            the packetsRecv to set
	 */
	public void setPacketsRecv(long packetsRecv) {
		this.packetsRecv = packetsRecv;
	}

	/**
	 * @return the errin
	 */
	public long getErrin() {
		return errin;
	}

	/**
	 * @param errin
	 *            the errin to set
	 */
	public void setErrin(long errin) {
		this.errin = errin;
	}

	/**
	 * @return the errout
	 */
	public long getErrout() {
		return errout;
	}

	/**
	 * @param errout
	 *            the errout to set
	 */
	public void setErrout(long errout) {
		this.errout = errout;
	}

	/**
	 * @return the dropin
	 */
	public long getDropin() {
		return dropin;
	}

	/**
	 * @param dropin
	 *            the dropin to set
	 */
	public void setDropin(long dropin) {
		this.dropin = dropin;
	}

	/**
	 * @return the dropout
	 */
	public long getDropout() {
		return dropout;
	}

	/**
	 * @param dropout
	 *            the dropout to set
	 */
	public void setDropout(long dropout) {
		this.dropout = dropout;
	}

	/**
	 * @return the fifoin
	 */
	public long getFifoin() {
		return fifoin;
	}

	/**
	 * @param fifoin
	 *            the fifoin to set
	 */
	public void setFifoin(long fifoin) {
		this.fifoin = fifoin;
	}

	/**
	 * @return the fifoout
	 */
	public long getFifoout() {
		return fifoout;
	}

	/**
	 * @param fifoout
	 *            the fifoout to set
	 */
	public void setFifoout(long fifoout) {
		this.fifoout = fifoout;
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
				+ (ip != null ? "ip=" + ip + ", " : "") + "bytesSent=" + bytesSent + ", bytesRecv=" + bytesRecv
				+ ", packetsSent=" + packetsSent + ", packetsRecv=" + packetsRecv + ", errin=" + errin + ", errout="
				+ errout + ", dropin=" + dropin + ", dropout=" + dropout + ", fifoin=" + fifoin + ", fifoout=" + fifoout
				+ "]";
	}

}
