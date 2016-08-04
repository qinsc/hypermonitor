package hyper.monitor.test.dao;

import hyper.momitor.model.Nic;
import hyper.momitor.service.INicService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by qinscx on 2016/8/2.
 */
public class NicDaoTestCase extends AbstractTestCase{
    @Test
    public void test() {
        INicService nicService = (INicService)context.getBean("nicService");

        // test add
        String id = this.testAdd(nicService);
        testUpdate(nicService, id);
        testQueryAll(nicService);
        testDelete(nicService, id);
    }

    private String testAdd(INicService nicService) {
        Nic nic = new Nic();

        nic.setNicName("nic1");
        nic.setMac("mac");
        nic.setIp("ip");
        nic.setHostId("11");

        return nicService.add(nic);
    }

    private void testUpdate(INicService nicService, String id) {
    	Nic nic = nicService.queryOne(id);

        nic.setNicName("nic111");
        nic.setMac("mac11");
        nic.setIp("ip11");
        nic.setHostId("111");

        nicService.update(nic);
    }
    
    private void testQueryAll(INicService nicService) {
    	 List<Nic> nics = nicService.queryAll();
    	 Assert.assertTrue(nics.size() > 0);
    }

    private void testDelete(INicService nicService, String id) {
    	nicService.delete(id);
    	Nic nic = nicService.queryOne(id);
    	Assert.assertNull(nic);
    }
}
