package hyper.monitor.test.dao;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import hyper.momitor.model.Tag;
import hyper.momitor.service.ITagService;

/**
 * Created by qinscx on 2016/8/2.
 */
public class TagDaoTestCase extends AbstractTestCase {
	String ownerId = "owner1";
	@Test
	public void test() {
		ITagService tagService = (ITagService) context.getBean("tagService");

		// test add
		String id = this.testAdd(tagService);
		testAddTagValue(tagService, id);
		testUpdate(tagService, id);
		testQueryAll(tagService);
		testQueryAllTagValue(tagService);
		testDeleteTagValue(tagService,id);
		testDelete(tagService, id);
	}

	private String testAdd(ITagService tagService) {
		Tag tag = new Tag();
		tag.setTagId(UUID.randomUUID().toString());
		tag.setTagKey("tagKey1");
		tag.setTagDesc("tagDesc1");
		return tagService.add(tag);
	}

	private void testUpdate(ITagService tagService, String id) {
		Tag tag = tagService.queryOne(id);

		tag.setTagKey("tagKey1New");
		tag.setTagDesc("tagDesc1New");
		tagService.update(tag);
	}

	private void testQueryAll(ITagService tagService) {
		List<Tag> tags = tagService.queryAll();
		Assert.assertTrue(tags.size() > 0);
	}

	private void testDelete(ITagService tagService, String id) {
		tagService.delete(id);
		Tag tag = tagService.queryOne(id);
		Assert.assertNull(tag);
	}
	
	private void testAddTagValue(ITagService tagService, String id) {
		Tag tag = tagService.queryOne(id);
		tag.setTagValue("tagValue1");
		tag.setOwnerId(ownerId);
		tagService.addTagValue(tag);
	}

	private void testQueryAllTagValue(ITagService tagService) {
		List<Tag> tags = tagService.queryAllTagValues();
		Assert.assertTrue(tags.size() > 0);
	}

	private void testDeleteTagValue(ITagService tagService, String id) {
		tagService.deleteTagValue(ownerId, id);
		Tag tag = tagService.queryOneTagValue(ownerId, id);
		Assert.assertNull(tag);
	}
}
