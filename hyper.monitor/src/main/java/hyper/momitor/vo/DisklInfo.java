/**
 * 
 */
package hyper.momitor.vo;

/**
 * @author qinscx
 *
 */
public class DisklInfo {
	private String diskId;
	private String device;
	private String path;
	private String fsType;
	private String opts;

	private int usedPercent;
	private long inodesTotal;
	private int inodesUsedPercent;

	/**
	 * @return the diskId
	 */
	public String getDiskId() {
		return diskId;
	}

	/**
	 * @param diskId
	 *            the diskId to set
	 */
	public void setDiskId(String diskId) {
		this.diskId = diskId;
	}

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
	 * @return the opts
	 */
	public String getOpts() {
		return opts;
	}

	/**
	 * @param opts
	 *            the opts to set
	 */
	public void setOpts(String opts) {
		this.opts = opts;
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

	/**
	 * @return the inodesTotal
	 */
	public long getInodesTotal() {
		return inodesTotal;
	}

	/**
	 * @param inodesTotal
	 *            the inodesTotal to set
	 */
	public void setInodesTotal(long inodesTotal) {
		this.inodesTotal = inodesTotal;
	}

	/**
	 * @return the inodesUsedPercent
	 */
	public int getInodesUsedPercent() {
		return inodesUsedPercent;
	}

	/**
	 * @param inodesUsedPercent
	 *            the inodesUsedPercent to set
	 */
	public void setInodesUsedPercent(int inodesUsedPercent) {
		this.inodesUsedPercent = inodesUsedPercent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DiskDetailInfo [" + (diskId != null ? "diskId=" + diskId + ", " : "")
				+ (device != null ? "device=" + device + ", " : "") + (path != null ? "path=" + path + ", " : "")
				+ (fsType != null ? "fsType=" + fsType + ", " : "") + (opts != null ? "opts=" + opts + ", " : "")
				+ "usedPercent=" + usedPercent + ", inodesTotal=" + inodesTotal + ", inodesUsedPercent="
				+ inodesUsedPercent + "]";
	}

}
