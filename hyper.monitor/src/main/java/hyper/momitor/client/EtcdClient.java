package hyper.momitor.client;

import org.springframework.stereotype.Service;

import hyper.momitor.model.Host;
import hyper.momitor.vo.HostInfo;

@Service
public class EtcdClient {
	public HostInfo getHostInfo(Host host) {
		// TODO 
		return new HostInfo(host);
	}
}
