package hyper.momitor.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import hyper.momitor.vo.HostMonitor;

/**
 * @author qinscx
 *
 */
public class MonitorUtil {
	private static HostMonitor hostMonitor = null;
	
	private static final String MONOTR_IP = "monitor.ip";
	private static final String ETCD_IP = "etcd.ip";
	private static final String ETCD_PORT = "etcd.port";
	private static final String GUACD_IP = "guacd.ip";
	private static final String GUACD_PORT = "guacd.port";
	private static final String HEARTBEAT_INTERVAL = "heartbeat.interval";
	private static final String HEARTBEAT_TIMEOUT = "heartbeat.timeout";
	
	public static HostMonitor getMonitor() {
		if (hostMonitor == null) {
			String rootPath =MonitorUtil.class.getResource("/").getFile();
			System.out.println("monitorPropPath = " + rootPath);
			Properties prop = new Properties();
			try {
				prop.load(new FileInputStream(new File(rootPath+"/monitor.properties")));
				hostMonitor = new HostMonitor();
				hostMonitor.setMonitorIp(prop.getProperty(MONOTR_IP));
				hostMonitor.setEtcdIp(prop.getProperty(ETCD_IP));
				hostMonitor.setEtcdPort(prop.getProperty(ETCD_PORT));
				hostMonitor.setGuacdIp(prop.getProperty(GUACD_IP));
				hostMonitor.setGuacdPort(prop.getProperty(GUACD_PORT));
				hostMonitor.setHeartBeatInterval(prop.getProperty(HEARTBEAT_INTERVAL));
				hostMonitor.setHeartBeatTimeout(prop.getProperty(HEARTBEAT_TIMEOUT));
				return hostMonitor;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return hostMonitor;
	}
	
}
