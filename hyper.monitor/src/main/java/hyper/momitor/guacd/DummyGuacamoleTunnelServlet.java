package hyper.momitor.guacd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;

import hyper.momitor.util.MonitorUtil;

/**
 * Simple tunnel example with hard-coded configuration parameters.
 *
 * @author Michael Jumper
 */
public class DummyGuacamoleTunnelServlet extends GuacamoleHTTPTunnelServlet {
	private static final long serialVersionUID = 1L;

	@Override
	@SuppressWarnings("unchecked")
    protected GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException {
    	 Map<String,String[]> map = request.getParameterMap();  
    	 String holstIp = null, protocol = null;
    	 if (map != null) {
    		 String[] connectionInfo = map.get("connectionInfo"); 
    		 if (connectionInfo != null && connectionInfo.length > 0) {
    			 String connInfo = connectionInfo[0];
    			 String[] infos = connInfo.split(":");
    			 if (infos.length == 2) {
    				 protocol = infos[0];
    				 holstIp = infos[1];
    			 }
    		 }
    	 }
    	 
    	 if (holstIp == null || protocol == null) {
    		 return null;
    	 }
    	 
    	 System.out.println("Protocol = " + protocol);
    	 System.out.println("HostIp = " + holstIp);

        // guacd connection information
        String hostname = MonitorUtil.getMonitor().getGuacdIp();
        int port = Integer.parseInt(MonitorUtil.getMonitor().getGuacdPort());
        
        System.out.println("Guacd hostName = " + hostname);
        System.out.println("Guacd port = " + port);

        // VNC connection information
        GuacamoleConfiguration config = new GuacamoleConfiguration();
        config.setProtocol(protocol);
        config.setParameter("hostname", holstIp);
        
        if ("vnc".equals(protocol)) {
	        config.setParameter("port", "5901");
	        config.setParameter("password", "hyper");
        } else {
        	config.setParameter("port", "3389");
        }
    	 
    	  // guacd connection information
//         String hostname = "192.168.88.107";
//         int port = 4822;
//
//         // VNC connection information
//         GuacamoleConfiguration config = new GuacamoleConfiguration();
//         config.setProtocol("rdp");
//         config.setParameter("hostname", "192.168.88.106");
//         config.setParameter("port", "3389");
//         config.setParameter("password", "potato");

        // Connect to guacd, proxying a connection to the VNC server above
        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(hostname, port),
                config
        );

        // Create tunnel from now-configured socket
        GuacamoleTunnel tunnel = new SimpleGuacamoleTunnel(socket);
        return tunnel;
    }
}
