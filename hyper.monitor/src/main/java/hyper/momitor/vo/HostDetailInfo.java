/**
 * 
 */
package hyper.momitor.vo;

import java.util.ArrayList;
import java.util.List;

import hyper.momitor.model.Host;

/**
 * @author qinscx
 *
 */
public class HostDetailInfo {
	private String hostId;
	private String hostName;
	private String desc;
	private String manageIp;

	private long bootTime;
	private long upTime;

	private String os;
	private String osPlatform;
	private String osPlatformFamily;
	private String osPlatformVersion;

	private int cpuCores;
	private String cpuModelName;
	private int cpuMhz;
	private int cpuUsage;

	private long memSize;
	private long memUsed;
	private int memUsage;

	private List<NicInfo> nicInfos = new ArrayList<>();
	private List<DiskInfo> diskInfos = new ArrayList<>();

	public HostDetailInfo() {
	}

	public HostDetailInfo(Host host) {
		this.hostId = host.getHostId();
		this.os = host.getOs();
		this.desc = host.getDesc();
		this.osPlatform = host.getOsPlatform();
		this.osPlatformFamily = host.getOsPlatformFamily();
		this.osPlatformVersion = host.getOsPlatformVersion();
		this.cpuCores = host.getCpuCores();
		this.cpuModelName = host.getCpuModelName();
		this.cpuMhz = host.getCpuMhz();
		this.memSize = host.getMemSize();
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
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the manageIp
	 */
	public String getManageIp() {
		return manageIp;
	}

	/**
	 * @param manageIp
	 *            the manageIp to set
	 */
	public void setManageIp(String manageIp) {
		this.manageIp = manageIp;
	}

	/**
	 * @return the bootTime
	 */
	public long getBootTime() {
		return bootTime;
	}

	/**
	 * @param bootTime
	 *            the bootTime to set
	 */
	public void setBootTime(long bootTime) {
		this.bootTime = bootTime;
	}

	/**
	 * @return the upTime
	 */
	public long getUpTime() {
		return upTime;
	}

	/**
	 * @param upTime
	 *            the upTime to set
	 */
	public void setUpTime(long upTime) {
		this.upTime = upTime;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the osPlatform
	 */
	public String getOsPlatform() {
		return osPlatform;
	}

	/**
	 * @param osPlatform
	 *            the osPlatform to set
	 */
	public void setOsPlatform(String osPlatform) {
		this.osPlatform = osPlatform;
	}

	/**
	 * @return the osPlatformFamily
	 */
	public String getOsPlatformFamily() {
		return osPlatformFamily;
	}

	/**
	 * @param osPlatformFamily
	 *            the osPlatformFamily to set
	 */
	public void setOsPlatformFamily(String osPlatformFamily) {
		this.osPlatformFamily = osPlatformFamily;
	}

	/**
	 * @return the osPlatformVersion
	 */
	public String getOsPlatformVersion() {
		return osPlatformVersion;
	}

	/**
	 * @param osPlatformVersion
	 *            the osPlatformVersion to set
	 */
	public void setOsPlatformVersion(String osPlatformVersion) {
		this.osPlatformVersion = osPlatformVersion;
	}

	/**
	 * @return the cpuCores
	 */
	public int getCpuCores() {
		return cpuCores;
	}

	/**
	 * @param cpuCores
	 *            the cpuCores to set
	 */
	public void setCpuCores(int cpuCores) {
		this.cpuCores = cpuCores;
	}

	/**
	 * @return the cpuModelName
	 */
	public String getCpuModelName() {
		return cpuModelName;
	}

	/**
	 * @param cpuModelName
	 *            the cpuModelName to set
	 */
	public void setCpuModelName(String cpuModelName) {
		this.cpuModelName = cpuModelName;
	}

	/**
	 * @return the cpuMhz
	 */
	public int getCpuMhz() {
		return cpuMhz;
	}

	/**
	 * @param cpuMhz
	 *            the cpuMhz to set
	 */
	public void setCpuMhz(int cpuMhz) {
		this.cpuMhz = cpuMhz;
	}

	/**
	 * @return the cpuUsage
	 */
	public int getCpuUsage() {
		return cpuUsage;
	}

	/**
	 * @param cpuUsage
	 *            the cpuUsage to set
	 */
	public void setCpuUsage(int cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	/**
	 * @return the memSize
	 */
	public long getMemSize() {
		return memSize;
	}

	/**
	 * @param memSize
	 *            the memSize to set
	 */
	public void setMemSize(long memSize) {
		this.memSize = memSize;
	}

	/**
	 * @return the memUsed
	 */
	public long getMemUsed() {
		return memUsed;
	}

	/**
	 * @param memUsed
	 *            the memUsed to set
	 */
	public void setMemUsed(long memUsed) {
		this.memUsed = memUsed;
	}

	/**
	 * @return the memUsage
	 */
	public int getMemUsage() {
		return memUsage;
	}

	/**
	 * @param memUsage
	 *            the memUsage to set
	 */
	public void setMemUsage(int memUsage) {
		this.memUsage = memUsage;
	}

	/**
	 * @return the nicInfos
	 */
	public List<NicInfo> getNicInfos() {
		return nicInfos;
	}

	/**
	 * @param nicInfos
	 *            the nicInfos to set
	 */
	public void setNicInfos(List<NicInfo> nicInfos) {
		this.nicInfos = nicInfos;
	}

	/**
	 * @return the diskInfos
	 */
	public List<DiskInfo> getDiskInfos() {
		return diskInfos;
	}

	/**
	 * @param diskInfos
	 *            the diskInfos to set
	 */
	public void setDiskInfos(List<DiskInfo> diskInfos) {
		this.diskInfos = diskInfos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HostDetailInfo [" + (hostId != null ? "hostId=" + hostId + ", " : "")
				+ (hostName != null ? "hostName=" + hostName + ", " : "") + (desc != null ? "desc=" + desc + ", " : "")
				+ "bootTime=" + bootTime + "upTime=" + upTime + ", " + (os != null ? "os=" + os + ", " : "")
				+ (osPlatform != null ? "osPlatform=" + osPlatform + ", " : "")
				+ (osPlatformFamily != null ? "osPlatformFamily=" + osPlatformFamily + ", " : "")
				+ (osPlatformVersion != null ? "osPlatformVersion=" + osPlatformVersion + ", " : "") + "cpuCores="
				+ cpuCores + ", " + (cpuModelName != null ? "cpuModelName=" + cpuModelName + ", " : "") + "cpuMhz="
				+ cpuMhz + ", cpuUsage=" + cpuUsage + ", memSize=" + memSize + ", memUsed=" + memUsed + ", memUsage="
				+ memUsage + ", " + (nicInfos != null ? "nicInfos=" + nicInfos + ", " : "")
				+ (diskInfos != null ? "diskInfos=" + diskInfos : "") + "]";
	}

}
