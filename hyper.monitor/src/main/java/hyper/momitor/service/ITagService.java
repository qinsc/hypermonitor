package hyper.momitor.service;

import hyper.momitor.model.Tag;

import java.util.List;

/**
 * Created by qinscx on 2016/8/2.
 */
public interface ITagService {
    List<Tag> queryAll();
    Tag queryOne(String id);
    String add(Tag tag);
    void update(Tag tag);
    void delete(String id);
    List<Tag> queryAllTagValues();
	Tag queryOneTagValue(String ownerId, String tagId);
    void addTagValue(Tag tag);
    void deleteTagValue(String ownerId, String tagId);
}
