package hyper.momitor.service;

import hyper.momitor.model.Host;

import java.util.List;

/**
 * Created by qinscx on 2016/8/2.
 */
public interface IHostService {
    List<Host> queryAll();
    Host queryOne(String id);
    String add(Host host);
    void update(Host host);
    void delete(String id);
}
