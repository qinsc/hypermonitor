package hyper.momitor.service;

import hyper.momitor.model.Disk;

import java.util.List;

/**
 * Created by qinscx on 2016/8/2.
 */
public interface IDiskService {
    List<Disk> queryAll();
    Disk queryOne(String id);
    String add(Disk disk);
    void update(Disk disk);
    void delete(String id);
}
