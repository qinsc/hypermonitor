package hyper.monitor.test.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import hyper.momitor.model.Host;
import hyper.momitor.service.IHostService;

/**
 * Created by qinscx on 2016/8/2.
 */
public class HostDaoTestCase extends AbstractTestCase{
    @Test
    public void test() {
        IHostService hostService = (IHostService)context.getBean("hostService");

        // test add
        String id = this.testAdd(hostService);
        testUpdate(hostService, id);
        testQueryAll(hostService);
        testDelete(hostService, id);
    }

    private String testAdd(IHostService hostService) {
        Host host = new Host();
        host.setHostName("host1");
        host.setBootTime(new Date());
        host.setUpTime(190000);
        host.setCpuCores(12);
        host.setCpuThreads(24);
        host.setCpuModelName("intel core i7");
        host.setCpuMhz(3400);
        host.setMemSize(2048);
        host.setGroupId("141414");
        host.setOs("win7");
        host.setOsPlatform("windows");
        host.setOsPlatformFamily("windows 7");
        host.setOsPlatformVersion("7");

        return hostService.add(host);
    }

    private void testUpdate(IHostService hostService, String id) {
    	Host host = hostService.queryOne(id);
    	
    	host.setHostName("host1New");
        host.setBootTime(new Date());
        host.setUpTime(1900111);
        host.setCpuCores(3);
        host.setCpuThreads(6);
        host.setCpuModelName("intel core i5");
        host.setCpuMhz(3411);
        host.setMemSize(2099);
        host.setGroupId("14143114");
        host.setOs("win10");
        host.setOsPlatform("windows11");
        host.setOsPlatformFamily("windows 10");
        host.setOsPlatformVersion("10");
        
        hostService.update(host);
    }
    
    private void testQueryAll(IHostService hostService) {
    	 List<Host> hosts = hostService.queryAll();
    	 Assert.assertTrue(hosts.size() > 0);
    }

    private void testDelete(IHostService hostService, String id) {
    	hostService.delete(id);
    	Host host = hostService.queryOne(id);
    	Assert.assertNull(host);
    }
}
