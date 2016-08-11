/**
 * 
 */
package hyper.momitor.util;

import hyper.momitor.model.Host;
import hyper.momitor.vo.HostDetailInfo;

/**
 * @author qinscx
 *
 */
public class ModelHelper {
	public static Host toHost(HostDetailInfo detailInfo) {
		Host host = new Host();
		host.setHostName(detailInfo.getHostName());
		host.setOs(detailInfo.getOs());
		host.setOsPlatform(detailInfo.getOsPlatform());
		host.setOsPlatformFamily(detailInfo.getOsPlatformFamily());
		host.setOsPlatformVersion(detailInfo.getOsPlatformVersion());
		host.setCpuCores(detailInfo.getCpuCores());
		host.setCpuModelName(detailInfo.getCpuModelName());
		host.setCpuMhz(detailInfo.getCpuMhz());
		host.setMemSize(detailInfo.getMemSize());
		host.setManageIp(detailInfo.getManageIp());
		return host;
	}
}
