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
import hyper.momitor.client.HostClient;
import hyper.momitor.exception.HMException;
import hyper.momitor.model.Host;
import hyper.momitor.service.IHostService;
import hyper.momitor.util.ModelHelper;
import hyper.momitor.util.SpringUtil;
import hyper.momitor.vo.DiskInfo;
import hyper.momitor.vo.HostConfig;
import hyper.momitor.vo.HostDetailInfo;
import hyper.momitor.vo.HostInfo;
import hyper.momitor.vo.HostsMessage;
import hyper.momitor.vo.NicInfo;

/**
 * @author qinscx
 *
 */
@Path("/hosts")
public class HostController {
	private Logger log = Logger.getLogger(HostController.class);
	private IHostService hostService = (IHostService) SpringUtil.getBean("hostService");

	private HostClient hostClient = new HostClient();
	private EtcdClient etcdClient = new EtcdClient();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryAllHosts() throws HMException {
		List<HostInfo> hostInfos = new ArrayList<>();
		List<Host> hosts = hostService.queryAll();

		if (hosts != null) {
			for (Host host : hosts) {
				hostInfos.add(etcdClient.getHostInfo(host));
			}
		}
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
		
		HostInfo hostInfo = etcdClient.getHostInfo(host.getHostId());
		if (hostInfo == null) {
			hostInfo = new HostInfo(host);
		} else {
			hostInfo.setOnline(1);
		}
		
		return new HostInfo(host);
	}

	@Path("/scan/{startIpMask}/{endIpMask}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> scanHosts(@PathParam("startIpMask") String startIpMask,
			@PathParam("endIpMask") String endIpMask) throws HMException {
		log.info("Scan hosts: startIp = " + startIpMask + ", endIP = " + endIpMask);
		
		List<HostInfo> hostInfos = new ArrayList<>();
		List<String> ips = hostClient.getIPs(startIpMask, endIpMask);
		if (ips != null) {
			for (String ip:ips) {
				HostConfig config = hostClient.getHostConfig(ip);
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
				HostInfo hostInfo = etcdClient.getHostInfo(hostId);
				if (hostInfo != null && hostInfo.getOnline() == 1) {
					hostClient.osLogoff(hostInfo.getManageIp());
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
				HostInfo hostInfo = etcdClient.getHostInfo(hostId);
				if (hostInfo != null && hostInfo.getOnline() == 1) {
					hostClient.osShutdown(hostInfo.getManageIp());
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
				HostInfo hostInfo = etcdClient.getHostInfo(hostId);
				if (hostInfo != null && hostInfo.getOnline() == 1) {
					hostClient.osReboot(hostInfo.getManageIp());
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
				HostInfo hostInfo = etcdClient.getHostInfo(hostId);
				if (hostInfo != null && hostInfo.getOnline() == 1) {
					hostClient.sendMessage(hostInfo.getManageIp(), hostsMessage.getMessage());
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
				HostDetailInfo detailInfo = hostClient.addHost(hostInfo);
				hostService.add(ModelHelper.toHost(detailInfo));
			}
		}
	}

	@GET
	@Path("/detail/{hostId}")
	@Produces(MediaType.APPLICATION_JSON)
	public HostDetailInfo getHostDetailInfo(@PathParam("hostId") String hostId) throws HMException {
		
		
		HostDetailInfo detailInfo = new HostDetailInfo();
		detailInfo.setHostId(hostId);
		detailInfo.setHostName("host1");
		detailInfo.setDesc(
				"信息: validateJarFile(E:\\\\work\\wkk\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\hyper.monitor\\WEB-INF\\lib\\servlet-api-2.5.jar) - jar not loaded. See Servlet Spec 2.3, section 9.7.2. Offending class: javax/servlet/Servlet.class");
		detailInfo.setBootTime(System.currentTimeMillis());
		detailInfo.setUpTime(3414145);

		detailInfo.setOs("windows7");
		detailInfo.setOsPlatform("win7");
		detailInfo.setOsPlatformFamily("windows");
		detailInfo.setOsPlatformVersion("7");

		detailInfo.setCpuCores(4);
		detailInfo.setCpuMhz(3400);
		detailInfo.setCpuModelName("intel i7");
		detailInfo.setCpuUsage(80);

		detailInfo.setMemSize(4096);
		detailInfo.setMemUsed(1458);
		detailInfo.setMemUsage(30);

		NicInfo nic1 = new NicInfo();
		nic1.setName("nic1");
		nic1.setIps(new String[] {"192.168.88.106"});
		nic1.setMac("00:4c:8e:9f:66:1a");
		detailInfo.getNicInfos().add(nic1);

		NicInfo nic2 = new NicInfo();
		nic2.setName("nic2");
		nic2.setIps(new String[] {"192.168.88.107"});
		nic2.setMac("00:4c:8e:9f:66:1b");
		detailInfo.getNicInfos().add(nic2);

		DiskInfo disk1 = new DiskInfo();
		disk1.setPath("C:/");
		disk1.setDevice("/dev/sda1");
		disk1.setFsType("ntfs");
		disk1.setTotal(690000);
		disk1.setUsed(4500000);
		disk1.setUsedPercent(60);
		detailInfo.getDiskInfos().add(disk1);

		DiskInfo disk2 = new DiskInfo();
		disk2.setPath("D:/");
		disk2.setDevice("/dev/sda2");
		disk2.setFsType("ntfs");
		disk2.setTotal(77777);
		disk2.setUsed(444);
		disk2.setUsedPercent(30);
		detailInfo.getDiskInfos().add(disk2);

		return detailInfo;
	}

}
