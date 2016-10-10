/**
 * 
 */
package hyper.momitor.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hyper.momitor.client.EtcdClient;
import hyper.momitor.model.Host;
import hyper.momitor.service.IHostService;
import hyper.momitor.util.SpringUtil;
import hyper.momitor.vo.NumbersInfo;

/**
 * @author qinscx
 *
 */
@Path("/dashboard")
public class DashboardController {
	private IHostService hostService = (IHostService) SpringUtil.getBean("hostService");
	private EtcdClient hMEtcdClient = new EtcdClient();
	
	@Path("/numbers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public NumbersInfo getNumbers() {
		NumbersInfo info = new NumbersInfo();
		
		// hosts
		List<Host> hosts = hostService.queryAll();
		if (hosts != null) {
			info.setHosts(hosts.size());
		}
		
		// onlineHosts
		info.setOnlineHosts(hMEtcdClient.getOnlineHostsNumber(hosts));
		
		// groups
		
		// tags
		
		return info;
	}

}
