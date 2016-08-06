package hyper.momitor.service.impl;

import hyper.momitor.dao.IDiskDao;
import hyper.momitor.model.Disk;
import hyper.momitor.service.IDiskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by qinscx on 2016/8/2.
 */
@Service("diskService")
public class DiskServiceImpl implements IDiskService {
    @Autowired
    private IDiskDao diskDao;

    @Override
    public List<Disk> queryAll() {
        return diskDao.queryAll();
    }

    @Override
    public Disk queryOne(String id) {
        return diskDao.queryOne(id);
    }

    @Override
    public String add(Disk disk) {
        String id = UUID.randomUUID().toString();
        disk.setDiskId(id);
        diskDao.add(disk);
        return id;
    }

    @Override
    public void update(Disk disk) {
        diskDao.update(disk);
    }

    @Override
    public void delete(String id) {
        diskDao.delete(id);
    }
}
