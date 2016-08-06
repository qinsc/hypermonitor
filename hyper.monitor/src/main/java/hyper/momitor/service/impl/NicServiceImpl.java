package hyper.momitor.service.impl;

import hyper.momitor.dao.INicDao;
import hyper.momitor.model.Nic;
import hyper.momitor.service.INicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by qinscx on 2016/8/2.
 */
@Service("nicService")
public class NicServiceImpl implements INicService {
    @Autowired
    private INicDao nicDao;

    @Override
    public List<Nic> queryAll() {
        return nicDao.queryAll();
    }

    @Override
    public Nic queryOne(String id) {
        return nicDao.queryOne(id);
    }

    @Override
    public String add(Nic nic) {
        String id = UUID.randomUUID().toString();
        nic.setNicId(id);
        nicDao.add(nic);
        return id;
    }

    @Override
    public void update(Nic nic) {
        nicDao.update(nic);
    }

    @Override
    public void delete(String id) {
        nicDao.delete(id);
    }
}
