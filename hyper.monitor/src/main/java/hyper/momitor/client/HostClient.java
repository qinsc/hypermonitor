/**
 * 
 */
package hyper.momitor.client;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import hyper.momitor.exception.HMException;
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
public class HostClient {
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public HostClient(){
		// objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
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
	
	public List<String> getIPs(String startIp, String endIp){
		List<String> ips = new ArrayList<>();
		
		ips.add("192.168.88.100");
		
		return ips;
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
	
}
