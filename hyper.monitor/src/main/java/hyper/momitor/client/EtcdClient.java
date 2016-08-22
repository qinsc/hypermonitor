package hyper.momitor.client;

import org.springframework.stereotype.Service;

import hyper.momitor.model.Host;
import hyper.momitor.vo.HostInfo;

@Service
public class EtcdClient {
	public HostInfo getHostInfo(Host host) {
		HostInfo hostInfo = getHostInfoFromEtcd(host);
		if (hostInfo == null) {
			hostInfo = new HostInfo(host);
		} else {
			hostInfo.setOnline(1);
		}
		
		// TODO to delete
		hostInfo.setOnline(1);
		
		hostInfo.setManageIp("192.168.88.106");
		return hostInfo;
	}
	
	public HostInfo getHostInfo(String hostId) {
		HostInfo info = new HostInfo();
		info.setManageIp("192.168.88.106");
		info.setOnline(1);
		return info;
	}
	
	private HostInfo getHostInfoFromEtcd(Host host) {
		return null;
	}
	
}
