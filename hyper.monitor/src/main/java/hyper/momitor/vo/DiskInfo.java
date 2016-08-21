/**
 * 
 */
package hyper.momitor.vo;

/**
 * @author qinscx
 *
 */
public class DiskInfo {
	private String diskId;
	private String device;
	private String path;
	private String fsType;
	private long diskSize;
	private long diskUsed;
	private int usedPercent;

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
	 * @return the diskSize
	 */
	public long getDiskSize() {
		return diskSize;
	}

	/**
	 * @param diskSize
	 *            the diskSize to set
	 */
	public void setDiskSize(long diskSize) {
		this.diskSize = diskSize;
	}

	/**
	 * @return the diskUsed
	 */
	public long getDiskUsed() {
		return diskUsed;
	}

	/**
	 * @param diskUsed
	 *            the diskUsed to set
	 */
	public void setDiskUsed(long diskUsed) {
		this.diskUsed = diskUsed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DiskInfo [" + (diskId != null ? "diskId=" + diskId + ", " : "")
				+ (device != null ? "device=" + device + ", " : "") + (path != null ? "path=" + path + ", " : "")
				+ (fsType != null ? "fsType=" + fsType + ", " : "") + "diskSize=" + diskSize + ", diskUsed=" + diskUsed
				+ ", usedPercent=" + usedPercent + "]";
	}

}
