package hyper.monitor.test.dao;

import hyper.momitor.model.Disk;
import hyper.momitor.service.IDiskService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by qinscx on 2016/8/2.
 */
public class DiskDaoTestCase extends AbstractTestCase{
    @Test
    public void test() {
        IDiskService diskService = (IDiskService)context.getBean("diskService");

        // test add
        String id = this.testAdd(diskService);
        testUpdate(diskService, id);
        testQueryAll(diskService);
        testDelete(diskService, id);
    }

    private String testAdd(IDiskService diskService) {
        Disk disk = new Disk();

        disk.setDevice("/dev/sda1");
        disk.setPath("/mnt");
        disk.setFsType("ntfs");
        disk.setHostId("11");

        return diskService.add(disk);
    }

    private void testUpdate(IDiskService diskService, String id) {
    	Disk disk = diskService.queryOne(id);

        disk.setDevice("/dev/sda2");
        disk.setPath("/mnt1");
        disk.setFsType("ntfs1");
        disk.setHostId("111");

        diskService.update(disk);
    }
    
    private void testQueryAll(IDiskService diskService) {
    	 List<Disk> disks = diskService.queryAll();
    	 Assert.assertTrue(disks.size() > 0);
    }

    private void testDelete(IDiskService diskService, String id) {
    	diskService.delete(id);
    	Disk disk = diskService.queryOne(id);
    	Assert.assertNull(disk);
    }
}
