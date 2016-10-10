package hyper.momitor.service;

import hyper.momitor.model.HostGroup;

import java.util.List;

/**
 * Created by qinscx on 2016/8/2.
 */
public interface IHostGroupService {
    List<HostGroup> queryAll();
    HostGroup queryOne(String id);
    HostGroup queryByName(String groupName);
    String add(HostGroup hostGroup);
    void update(HostGroup hostGroup);
    void delete(String id);
}
