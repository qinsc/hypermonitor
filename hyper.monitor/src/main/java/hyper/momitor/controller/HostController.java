/**
 * 
 */
package hyper.momitor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

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

		List<HostInfo> hostInfos = new ArrayList<>();
		List<String> ips = hMHostClient.getIPs(startIpMask, endIpMask);
		if (ips != null) {
			for (String ip : ips) {
				HostConfig config = hMHostClient.getHostConfig(ip);
				if (config != null) {
					if (config.getMonitor() == null) {
						HostInfo hostInfo = new HostInfo();
						hostInfo.setHostId(UUID.randomUUID().toString());
						hostInfo.setHostName(config.getHostName());
						hostInfo.setManageIp(ip);
						hostInfos.add(hostInfo);
					}
				}
			}
		}

		Map<String, Object> result = new HashMap<>();
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
				Host host = ModelHelper.toHost(detailInfo);
				host.setHostId(hostInfo.getHostId());
				host.setManageIp(hostInfo.getManageIp());
				hostService.add(host);
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
	public HostDetailInfo getHostDetailInfo(@PathParam("hostId") String hostId) throws HMException {
		Host host = hostService.queryOne(hostId);
		if (host != null && hMEtcdClient.getHostOnline(hostId) == 1) {
			return hMHostClient.getHostDetailInfo(host.getManageIp());
		}
		throw new HMException("主机不存在");
	}

}
