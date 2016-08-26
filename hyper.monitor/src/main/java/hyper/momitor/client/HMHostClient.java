/**
 * 
 */
package hyper.momitor.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.net.util.SubnetUtils;
import org.codehaus.jackson.map.ObjectMapper;

import hyper.momitor.exception.HMException;
import hyper.momitor.model.Host;
import hyper.momitor.util.MonitorUtil;
import hyper.momitor.util.RestUtil;
import hyper.momitor.vo.HostConfig;
import hyper.momitor.vo.HostDetailInfo;
import hyper.momitor.vo.HostInfo;
import hyper.momitor.vo.HostMonitor;

/**
 * @author qinscx
 *
 */
public class HMHostClient {
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public HostDetailInfo getHostDetailInfo(HostInfo hostInfo) throws HMException {
		HostDetailInfo detailInfo = getHostDetailInfo(hostInfo.getManageIp());
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
	
	public void osLogoff(String manageId) throws HMException {
		String url = "http://" + manageId + ":8080/rest/host/logoff";
		try {
			RestUtil.doPost(url, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void osShutdown(String manageId) throws HMException {
		String url = "http://" + manageId + ":8080/rest/host/shutdown";
		try {
			RestUtil.doPost(url, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void osReboot(String manageId) throws HMException {
		String url = "http://" + manageId + ":8080/rest/host/reboot";
		try {
			RestUtil.doPost(url, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String manageId, String message) throws HMException {
		String url = "http://" + manageId + ":8080/rest/host/message";
		try {
			RestUtil.doPost(url, message, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HostDetailInfo getHostDetailInfo(String manageId) {
		String url = "http://" + manageId + ":8080/rest/host/info";
		try {
			String rs = RestUtil.doGet(url, null);
			System.out.println("HostDetailInfo = " + rs);
			if (rs != null) {
				return new ObjectMapper().readValue(rs, HostDetailInfo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HostConfig getHostConfig(String manageId) {
		String url = "http://" + manageId + ":8080/rest/host/config";
		try {
			String rs = RestUtil.doGet(url, null);
			if (rs != null) {
				return objectMapper.readValue(rs, HostConfig.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getIPs(String startIp, String endIp) throws HMException{
		startIp = startIp.replace(":", "/");
		endIp = endIp.replace(":", "/");
		
		SubnetUtils subnet = new SubnetUtils(startIp);
		String[] allIps = subnet.getInfo().getAllAddresses();
		if (allIps != null) {
			List<String> allIpsList = Arrays.asList(allIps);
			String ip1 = startIp.split("/")[0];
			String ip2 = endIp.split("/")[0];
			
			if (!allIpsList.contains(ip2)) {
				throw new HMException("两个IP不在同一个网段");
			}
			
			int index1 = allIpsList.indexOf(ip1);
			int index2 = allIpsList.indexOf(ip2);
			if (index2 >= index1) {
				return allIpsList.subList(index1, index2);
			} 
		}
		return new ArrayList<>();
	}
	
	public HostDetailInfo addHost(HostInfo hostInfo) throws HMException {
		String url = "http://" + hostInfo.getManageIp() + ":8080/rest/host/add";
		HostMonitor monitor = MonitorUtil.getMonitor();
		monitor.setHostId(hostInfo.getHostId());
		
		try {
			String monitorJson = objectMapper.writeValueAsString(monitor);
			String rs = RestUtil.doPost(url, monitorJson, null);
			if (rs != null) {
				return new ObjectMapper().readValue(rs, HostDetailInfo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void removeHost(Host host) throws HMException {
		String url = "http://" + host.getManageIp() + ":8080/rest/host/remove";
		try {
			String monitorJson = objectMapper.writeValueAsString(host.getHostId());
			String rs = RestUtil.doPost(url, monitorJson, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
