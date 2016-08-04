package hyper.momitor.model;

import java.io.Serializable;

public class Disk implements Serializable {
	private static final long serialVersionUID = 87014977063148416L;

	private String diskId;
	private String device;
	private String path;
	private String fsType;
	private String opts;

	private String hostId;

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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Disk [" + (diskId != null ? "diskId=" + diskId + ", " : "")
				+ (device != null ? "device=" + device + ", " : "") + (path != null ? "path=" + path + ", " : "")
				+ (fsType != null ? "fsType=" + fsType + ", " : "") + (opts != null ? "opts=" + opts + ", " : "")
				+ (hostId != null ? "hostId=" + hostId : "") + "]";
	}

}
