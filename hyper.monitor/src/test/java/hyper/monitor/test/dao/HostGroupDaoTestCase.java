package hyper.monitor.test.dao;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import hyper.momitor.model.HostGroup;
import hyper.momitor.service.IHostGroupService;

/**
 * Created by qinscx on 2016/8/2.
 */
public class HostGroupDaoTestCase extends AbstractTestCase{
    @Test
    public void test() {
        IHostGroupService hostGroupService = (IHostGroupService)context.getBean("hostGroupService");

        // test add
        String id = this.testAdd(hostGroupService);
        testUpdate(hostGroupService, id);
        testQueryAll(hostGroupService);
        testDelete(hostGroupService, id);
    }

    private String testAdd(IHostGroupService hostGroupService) {
        HostGroup hostGroup = new HostGroup();
        hostGroup.setGroupId(UUID.randomUUID().toString());
        hostGroup.setGroupName("group1");
        hostGroup.setGroupDesc("hostGroupDesc");

        return hostGroupService.add(hostGroup);
    }

    private void testUpdate(IHostGroupService hostGroupService, String id) {
    	HostGroup hostGroup = hostGroupService.queryOne(id);
    	
    	hostGroup.setGroupName("group1New");
        hostGroup.setGroupDesc("hostGroupDescNew");
        
        hostGroupService.update(hostGroup);
    }
    
    private void testQueryAll(IHostGroupService hostGroupService) {
    	 List<HostGroup> hostGroups = hostGroupService.queryAll();
    	 Assert.assertTrue(hostGroups.size() > 0);
    }

    private void testDelete(IHostGroupService hostGroupService, String id) {
    	hostGroupService.delete(id);
    	HostGroup hostGroup = hostGroupService.queryOne(id);
    	Assert.assertNull(hostGroup);
    }
}
