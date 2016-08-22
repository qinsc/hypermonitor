/**
 * 
 */
package hyper.momitor.vo;

/**
 * @author qinscx
 *
 */
public class DiskInfo {
	private String device;
	private String path;
	private String fsType;
	private long total;
	private long used;
	private int usedPercent;

	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * @param device
	 *            the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the fsType
	 */
	public String getFsType() {
		return fsType;
	}

	/**
	 * @param fsType
	 *            the fsType to set
	 */
	public void setFsType(String fsType) {
		this.fsType = fsType;
	}

	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * @return the used
	 */
	public long getUsed() {
		return used;
	}

	/**
	 * @param used
	 *            the used to set
	 */
	public void setUsed(long used) {
		this.used = used;
	}

	/**
	 * @return the usedPercent
	 */
	public int getUsedPercent() {
		return usedPercent;
	}

	/**
	 * @param usedPercent
	 *            the usedPercent to set
	 */
	public void setUsedPercent(int usedPercent) {
		this.usedPercent = usedPercent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DiskInfo [" + (device != null ? "device=" + device + ", " : "")
				+ (path != null ? "path=" + path + ", " : "") + (fsType != null ? "fsType=" + fsType + ", " : "")
				+ "total=" + total + ", used=" + used + ", usedPercent=" + usedPercent + "]";
	}

}
