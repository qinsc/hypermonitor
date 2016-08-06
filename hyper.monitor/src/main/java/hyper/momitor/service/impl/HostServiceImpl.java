package hyper.momitor.service.impl;

import hyper.momitor.dao.IHostDao;
import hyper.momitor.model.Host;
import hyper.momitor.service.IHostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by qinscx on 2016/8/2.
 */
@Service("hostService")
public class HostServiceImpl implements IHostService {
    @Autowired
    private IHostDao hostDao;

    @Override
    public List<Host> queryAll() {
        return hostDao.queryAll();
    }

    @Override
    public Host queryOne(String id) {
        return hostDao.queryOne(id);
    }

    @Override
    public String add(Host host) {
        String id = UUID.randomUUID().toString();
        host.setHostId(id);
        hostDao.add(host);
        return id;
    }

    @Override
    public void update(Host host) {
        hostDao.update(host);
    }

    @Override
    public void delete(String id) {
        hostDao.delete(id);
    }
}
