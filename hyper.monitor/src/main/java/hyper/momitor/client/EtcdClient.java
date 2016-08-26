package hyper.momitor.client;

import java.util.List;
import java.util.Map;

import hyper.momitor.util.MonitorUtil;
import hyper.momitor.util.etcd.HMEtcdClient;
import hyper.momitor.util.etcd.HMEtcdClientFactory;
import hyper.momitor.util.etcd.HMEtcdException;
import hyper.momitor.vo.HostInfo;

public class EtcdClient {
	private HMEtcdClient etcdClient = null;
	private static final String KEY_PRE = "/hyper/agent/heartbeat/";

	public HMEtcdClient getEtcdClient() {
		if (etcdClient == null) {
			String uri = "http://" + MonitorUtil.getMonitor().getEtcdIp() + ":"
					+ MonitorUtil.getMonitor().getEtcdPort();
			etcdClient = HMEtcdClientFactory.newInstance(uri);
		}
		return etcdClient;
	}

	public int getHostOnline(String hostId) {
		try {
			getEtcdClient().get(KEY_PRE + hostId);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	public void checkHostsOnline(List<HostInfo> hosts) {
		Map<String,String> kvs  = null;
		
		try {
			kvs = getEtcdClient().list(KEY_PRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (kvs != null) {
			for (HostInfo host : hosts) {
				host.setOnline(kvs.get(KEY_PRE + host.getHostId()) == null?0:1);
			}
		}
	}

}
