package hyper.momitor.dao;

import java.util.List;

import hyper.momitor.model.Disk;

public interface IDiskDao extends MarkerInterface{
	List<Disk> queryAll();
	Disk queryOne(String id);
	void add(Disk host);
	void update(Disk host);
	void delete(String id);
}