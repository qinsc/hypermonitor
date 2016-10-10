package hyper.momitor.dao;

import java.util.List;

import hyper.momitor.model.HostGroup;

public interface IHostGroupDao extends MarkerInterface{
	List<HostGroup> queryAll();
	HostGroup queryOne(String id);
	HostGroup queryByName(String groupName);
	void add(HostGroup hostGroup);
	void update(HostGroup hostGroup);
	void delete(String id);
}