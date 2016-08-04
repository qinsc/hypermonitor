package hyper.momitor.service;

import hyper.momitor.model.Nic;

import java.util.List;

/**
 * Created by qinscx on 2016/8/2.
 */
public interface INicService {
    List<Nic> queryAll();
    Nic queryOne(String id);
    String add(Nic nic);
    void update(Nic nic);
    void delete(String id);
}
