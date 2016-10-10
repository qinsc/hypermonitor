package hyper.momitor.service.impl;

import hyper.momitor.dao.IHostGroupDao;
import hyper.momitor.model.HostGroup;
import hyper.momitor.service.IHostGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by qinscx on 2016/8/2.
 */
@Service("hostGroupService")
public class HostGroupServiceImpl implements IHostGroupService {
    @Autowired
    private IHostGroupDao hostGroupDao;

    @Override
    public List<HostGroup> queryAll() {
        return hostGroupDao.queryAll();
    }

    @Override
    public HostGroup queryOne(String id) {
        return hostGroupDao.queryOne(id);
    }
    
    @Override
    public HostGroup queryByName(String groupName) {
    	return hostGroupDao.queryByName(groupName);
    }

    @Override
    public String add(HostGroup hostGroup) {
        String id = UUID.randomUUID().toString();
        hostGroup.setGroupId(id);
        hostGroupDao.add(hostGroup);
        return id;
    }

    @Override
    public void update(HostGroup hostGroup) {
        hostGroupDao.update(hostGroup);
    }

    @Override
    public void delete(String id) {
        hostGroupDao.delete(id);
    }
}
