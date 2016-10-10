/**
 * 
 */
package hyper.momitor.controller;

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

import org.apache.log4j.Logger;

import hyper.momitor.exception.HMException;
import hyper.momitor.model.HostGroup;
import hyper.momitor.service.IHostGroupService;
import hyper.momitor.util.SpringUtil;

/**
 * @author qinscx
 *
 */
@Path("/hostgroups")
public class HostGroupController {
	private Logger log = Logger.getLogger(HostGroupController.class);
	private IHostGroupService hostGroupService = (IHostGroupService) SpringUtil.getBean("hostGroupService");

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryAllHostGroups() throws HMException {
		List<HostGroup> hostGroups = hostGroupService.queryAll();
		Map<String, Object> result = new HashMap<>();
		result.put("data", hostGroups);
		return result;
	}

	@Path("/{groupId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HostGroup quertOneHost(@PathParam("hostGroupId") String hostGroupId) throws HMException {
		return hostGroupService.queryOne(hostGroupId);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addHostGroup(HostGroup hostGroup) throws HMException {
		log.info("add host group: " + hostGroup);
		HostGroup hg = hostGroupService.queryByName(hostGroup.getGroupName());
		if (hg != null) {
			throw new HMException("主机组名称已存在");
		}
		
		log.info("add host group: " + hostGroup);
		if (hostGroup != null) {
			hostGroupService.add(hostGroup);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateHostGroup(HostGroup hostGroup) throws HMException {
		log.info("update host group: " + hostGroup);
		if (hostGroup != null) {
			HostGroup hg = hostGroupService.queryOne(hostGroup.getGroupId());
			if (hg != null) {
				hg.setGroupDesc(hostGroup.getGroupDesc());
				hostGroupService.update(hg);
			}
		}
	}

	@POST
	@Path("/{hostGroup}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteHostGroup(@PathParam("hostGroupId") String hostGroupId) throws HMException {
		log.info("delete host group: " + hostGroupId);
		if (hostGroupId != null) {
			hostGroupService.delete(hostGroupId);
		}
	}
}
