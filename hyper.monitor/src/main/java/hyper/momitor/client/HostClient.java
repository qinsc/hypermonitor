/**
 * 
 */
package hyper.momitor.client;

import java.util.ArrayList;
import java.util.List;

import hyper.momitor.exception.HMException;
import hyper.momitor.model.Host;
import hyper.momitor.vo.HostDetailInfo;
import hyper.momitor.vo.HostInfo;

/**
 * @author qinscx
 *
 */
public class HostClient {
	private EtcdClient etcdClient = new EtcdClient();
	
	public HostDetailInfo getHostDetailInfo(Host host) throws HMException {
		HostInfo hostInfo = etcdClient.getHostInfo(host);
		if (hostInfo == null) {
			HostDetailInfo detailInfo = new HostDetailInfo(host);
			detailInfo.setOnline(0);
			return detailInfo;
		} 
		
		HostDetailInfo detailInfo = getHostDetailInfo(host.getManageIp());
		if (detailInfo == null) {
			detailInfo = new HostDetailInfo(host);
		} else {
			detailInfo.setOnline(1);
		}
		return detailInfo;
	}
	
	public List<HostDetailInfo> scanHosts(String startIp,String endIp) throws HMException {
		List<HostDetailInfo> detailInfos = new ArrayList<>();
		List<String> ips = getIPs(startIp,endIp);
		if (ips != null) {
			ips.forEach(ip -> {
				HostDetailInfo detailInfo = getHostDetailInfo(ip);
				if (detailInfo != null) {
					detailInfos.add(detailInfo);
				}
			});
		}
		return detailInfos;
	}
	
	public void osLogout(List<HostInfo> hosts) throws HMException {
		
	}
	
	public void osShutdown(List<HostInfo> hosts) throws HMException {
		
	}
	
	public void sendMessage(List<HostInfo> hosts) throws HMException {
		
	}
	
	private HostDetailInfo getHostDetailInfo(String manageId) {
		// TODO
		return null;
	}
	
	private List<String> getIPs(String startIp, String endIp){
		List<String> ips = new ArrayList<>();
		// TODO 
		return ips;
	}
	
}
