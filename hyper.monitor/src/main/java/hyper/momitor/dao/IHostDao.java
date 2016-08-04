package hyper.momitor.dao;

import java.util.List;

import hyper.momitor.model.Host;

/**
 * @author qinscx
 *
 */
public interface IHostDao extends MarkerInterface{
	List<Host> queryAll();
	Host queryOne(String id);
	void add(Host host);
	void update(Host host);
	void delete(String id);
}
