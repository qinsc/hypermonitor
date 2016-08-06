package hyper.momitor.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hyper.momitor.dao.ITagDao;
import hyper.momitor.model.Tag;
import hyper.momitor.service.ITagService;

/**
 * Created by qinscx on 2016/8/2.
 */
@Service("tagService")
public class TagServiceImpl implements ITagService {
	@Autowired
	private ITagDao tagDao;

	@Override
	public List<Tag> queryAll() {
		return tagDao.queryAll();
	}

	@Override
	public Tag queryOne(String id) {
		return tagDao.queryOne(id);
	}

	@Override
	public String add(Tag tag) {
		String id = UUID.randomUUID().toString();
		tag.setTagId(id);
		tagDao.add(tag);
		return id;
	}

	@Override
	public void update(Tag tag) {
		tagDao.update(tag);
	}

	@Override
	public void delete(String id) {
		tagDao.delete(id);
	}

	@Override
	public List<Tag> queryAllTagValues() {
		return tagDao.queryAllTagValues();
	}

	@Override
	public Tag queryOneTagValue(String ownerId, String tagId) {
		return tagDao.queryOneTagValue(ownerId, tagId);
	}

	@Override
	public void addTagValue(Tag tag) {
		tag.setRelationId(UUID.randomUUID().toString());
		tagDao.addTagValue(tag);
	}

	@Override
	public void deleteTagValue(String ownerId, @Param("tagId") String tagId) {
		tagDao.deleteTagValue(ownerId, tagId);
	}
}
