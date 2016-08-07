/**
 * 
 */
package hyper.momitor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.istack.internal.logging.Logger;

import hyper.momitor.client.EtcdClient;
import hyper.momitor.client.HostClient;
import hyper.momitor.exception.HMException;
import hyper.momitor.model.Host;
import hyper.momitor.service.IHostService;
import hyper.momitor.util.ModelHelper;
import hyper.momitor.util.SpringUtil;
import hyper.momitor.vo.HostDetailInfo;
import hyper.momitor.vo.HostInfo;

/**
 * @author qinscx
 *
 */
@Path("/hosts")
public class HostController {
	private Logger log = Logger.getLogger(HostController.class);
	private IHostService hostService = (IHostService)SpringUtil.getBean("hostService");
	
	private HostClient hostClient = new HostClient();
	private EtcdClient etcdClient = new EtcdClient();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> queryAllHosts() throws HMException {
		List<HostInfo> hostInfos = new ArrayList<>();
		List<Host> hosts = hostService.queryAll();
		
		if (hosts != null) {
			for (Host host: hosts) {
				HostInfo hostInfo = etcdClient.getHostInfo(host);
				if (hostInfo == null) {
					hostInfo = new HostInfo(host);
				} else {
					hostInfo.setOnline(1);
				}
				hostInfos.add(hostInfo);
			}
		}
		Map<String,Object> result = new HashMap<>();
		result.put("data", hostInfos);
		return result;
	}

	@Path("/{hostId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HostDetailInfo quertOneHost(@PathParam("hostId") String hostId) throws HMException {
		Host host = hostService.queryOne(hostId);
		if (host == null) {
			throw new HMException("主机不存在");
		}
		return hostClient.getHostDetailInfo(host);
	}
	
	@Path("/scan/{startIp}/{endIp}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<HostDetailInfo> scanHosts(@PathParam("startIp") String startIp, @PathParam("endIp") String endIp) throws HMException {
		return hostClient.scanHosts(startIp, endIp);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addHosts(List<HostDetailInfo> hostInfos) throws HMException {
		if (hostInfos != null) {
			for (HostDetailInfo detailInfo : hostInfos) {
				hostService.add(ModelHelper.toHost(detailInfo));
			}
		}
	}
	
	@Path("/logout")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void osLogout(List<HostInfo> hosts) throws HMException {
		log.info("do logout for hosts: " + hosts);
		hostClient.osLogout(hosts);
	}
	
	@Path("/logout")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void osShutdown(List<HostInfo> hosts) throws HMException {
		log.info("do shutdown for hosts: " + hosts);
		hostClient.osShutdown(hosts);
	}
	
	@Path("/logout")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendMessage(List<HostInfo> hosts) throws HMException {
		log.info("send message to hosts: " + hosts);
		hostClient.sendMessage(hosts);
	}
}

