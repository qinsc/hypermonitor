/**
 * 
 */
package hyper.momitor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import hyper.momitor.client.EtcdClient;
import hyper.momitor.client.HMHostClient;
import hyper.momitor.exception.HMException;
import hyper.momitor.model.Host;
import hyper.momitor.service.IHostService;
import hyper.momitor.util.ModelHelper;
import hyper.momitor.util.SpringUtil;
import hyper.momitor.vo.HostConfig;
import hyper.momitor.vo.HostDetailInfo;
import hyper.momitor.vo.HostInfo;
import hyper.momitor.vo.HostsMessage;

/**
 * @author qinscx
 *
 */
@Path("/hosts")
public class HostController {
	private Logger log = Logger.getLogger(HostController.class);
	private IHostService hostService = (IHostService) SpringUtil.getBean("hostService");

	private HMHostClient hMHostClient = new HMHostClient();
	private EtcdClient hMEtcdClient = new EtcdClient();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryAllHosts() throws HMException {
		List<HostInfo> hostInfos = new ArrayList<>();
		List<Host> hosts = hostService.queryAll();

		if (hosts != null) {
			for (Host host : hosts) {
				hostInfos.add(new HostInfo(host));
			}
		}
		hMEtcdClient.checkHostsOnline(hostInfos);
		Map<String, Object> result = new HashMap<>();
		result.put("data", hostInfos);
		return result;
	}

	@Path("/{hostId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HostInfo quertOneHost(@PathParam("hostId") String hostId) throws HMException {
		Host host = hostService.queryOne(hostId);
		if (host == null) {
			throw new HMException("主机不存在");
		}

		HostInfo hostInfo = new HostInfo(host);
		hostInfo.setOnline(hMEtcdClient.getHostOnline(hostId));

		return new HostInfo(host);
	}

	@Path("/scan/{startIpMask}/{endIpMask}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> scanHosts(@PathParam("startIpMask") String startIpMask,
			@PathParam("endIpMask") String endIpMask) throws HMException {
		log.info("Scan hosts: startIp = " + startIpMask + ", endIP = " + endIpMask);

		long t = System.currentTimeMillis();
		final List<HostInfo> hostInfos = new ArrayList<>();
		System.out.println("hostInfos = " + hostInfos.hashCode());
		List<String> ips = hMHostClient.getIPs(startIpMask, endIpMask);
		if (ips != null) {
			ExecutorService pool = Executors.newFixedThreadPool(20);
			for (String ip : ips) {
				pool.submit(new Runnable() {
					public void run() {
						long t1 = System.currentTimeMillis();
						System.out.println("Scan host: " + ip);
						HostConfig config = hMHostClient.getHostConfig(ip, 5000);	// 超时时间5秒
						if (config != null) {
							if (config.getMonitor() == null) {
								System.out.println("hostInfos = " + hostInfos.hashCode());
								HostInfo hostInfo = new HostInfo();
								hostInfo.setHostId(UUID.randomUUID().toString());
								hostInfo.setHostName(config.getHostName());
								hostInfo.setManageIp(ip);
								hostInfos.add(hostInfo);
								System.out.println("Add host: " + ip);
							}
						}
						System.out.println("Scan host " + ip + " cost: " + (System.currentTimeMillis() - t1)/1000 + "s");
					}
				});
			}
			
			pool.shutdown();
			try {
				pool.awaitTermination(5,TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				System.out.println("await error  ...");
			}
			System.out.println("finish ... ");
		}
		System.out.println("Scan " + startIpMask + " -> " + endIpMask + " cost :" + (System.currentTimeMillis() - t)/1000 + " s");

		Map<String, Object> result = new HashMap<>();
		System.out.println("Size: " + hostInfos.size());
		result.put("data", hostInfos);
		return result;
	}

	@Path("/logoff")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void osLogoff(List<String> hostIds) throws HMException {
		log.info("do logout for hosts: " + hostIds);
		if (hostIds != null) {
			for (String hostId : hostIds) {
				Host host = hostService.queryOne(hostId);
				if (host != null && hMEtcdClient.getHostOnline(hostId) == 1) {
					hMHostClient.osLogoff(host.getManageIp());
				} else {
					log.error("Host for id[" + hostId + "] not online.");
				}
			}
		}
	}

	@Path("/shutdown")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void osShutdown(List<String> hostIds) throws HMException {
		log.info("do shutdown hosts: " + hostIds);
		if (hostIds != null) {
			for (String hostId : hostIds) {
				Host host = hostService.queryOne(hostId);
				if (host != null && hMEtcdClient.getHostOnline(hostId) == 1) {
					hMHostClient.osShutdown(host.getManageIp());
				} else {
					log.error("Host for id[" + hostId + "] not online.");
				}
			}
		}
	}

	@Path("/reboot")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void osReboot(List<String> hostIds) throws HMException {
		log.info("do reboot hosts: " + hostIds);
		if (hostIds != null) {
			for (String hostId : hostIds) {
				Host host = hostService.queryOne(hostId);
				if (host != null && hMEtcdClient.getHostOnline(hostId) == 1) {
					hMHostClient.osReboot(host.getManageIp());
				} else {
					log.error("Host for id[" + hostId + "] not online.");
				}
			}
		}
	}

	@Path("/message")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendMessage(HostsMessage hostsMessage) throws HMException {
		log.info("send message to hosts: " + hostsMessage);
		if (hostsMessage != null && hostsMessage.getMessage() != null && hostsMessage.getHostIds() != null) {
			for (String hostId : hostsMessage.getHostIds()) {
				Host host = hostService.queryOne(hostId);
				if (host != null && hMEtcdClient.getHostOnline(hostId) == 1) {
					hMHostClient.sendMessage(host.getManageIp(), hostsMessage.getMessage());
				} else {
					log.error("Host for id[" + hostId + "] not online.");
				}
			}
		}
	}

	@Path("/desc")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateHostDesc(Host host) throws HMException {
		log.info("update host desc: " + host);
		Host h = hostService.queryOne(host.getHostId());
		if (h != null) {
			h.setDesc(host.getDesc());
			hostService.update(h);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addHosts(List<HostInfo> hostInfos) throws HMException {
		log.info("add hosts: " + hostInfos);
		if (hostInfos != null) {
			for (HostInfo hostInfo : hostInfos) {
				HostDetailInfo detailInfo = hMHostClient.addHost(hostInfo);
				if (detailInfo != null) {
					Host host = ModelHelper.toHost(detailInfo);
					host.setHostId(hostInfo.getHostId());
					host.setManageIp(hostInfo.getManageIp());
					hostService.add(host);
				}
			}
		}
	}

	@POST
	@Path("/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeHosts(List<String> hostIds) throws HMException {
		log.info("remove hosts: " + hostIds);
		if (hostIds != null) {
			for (String hostId : hostIds) {
				Host host = hostService.queryOne(hostId);
				if (host != null && hMEtcdClient.getHostOnline(hostId) == 1) {
					hMHostClient.removeHost(host);
				}
				hostService.delete(host.getHostId());
			}
		}
	}

	@GET
	@Path("/detail/{hostId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHostDetailInfo(@PathParam("hostId") String hostId) throws HMException {
		Host host = hostService.queryOne(hostId);
		if (host != null && hMEtcdClient.getHostOnline(hostId) == 1) {
			String info = new Gson().toJson(hMHostClient.getHostDetailInfo(host.getManageIp()));
			System.out.println("info json =" + info);
			return info;  
		}
		throw new HMException("主机不存在");
	}
	
//	public static void main(String[] args) {
//		ExecutorService pool = Executors.newCachedThreadPool();
//		for (int i = 0; i < 30; i++) {
//			final int n = i;
//			pool.execute(new Runnable() {
//				public void run() {
//					try {
//						TimeUnit.MICROSECONDS.sleep(50);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println(n);
//				}
//			});
//		}
//		
//		while(true) {
//			System.out.println("pool status = " + pool.aw);
//			if (pool.isTerminated() || pool.isShutdown()) {
//				break;
//			}
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		pool.shutdown();
//		System.out.println("finish");
//	}

}
