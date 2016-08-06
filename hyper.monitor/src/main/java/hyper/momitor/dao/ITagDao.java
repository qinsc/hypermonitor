package hyper.momitor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hyper.momitor.model.Tag;

public interface ITagDao extends MarkerInterface{
	List<Tag> queryAll();
	Tag queryOne(String id);
	void add(Tag tag);
	void update(Tag tag);
	void delete(String id);
	
	List<Tag> queryAllTagValues();
	Tag queryOneTagValue(@Param("ownerId")  String ownerId, @Param("tagId")  String tagId);
	void addTagValue(Tag tag);
	void deleteTagValue(@Param("ownerId")  String ownerId, @Param("tagId")  String tagId);
}