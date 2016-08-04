/**
 * 
 */
package hyper.momitor.dao;

import java.util.List;

import hyper.momitor.model.Nic;

/**
 * @author qinscx
 *
 */
public interface INicDao extends MarkerInterface{
	List<Nic> queryAll();
	Nic queryOne(String id);
	void add(Nic host);
	void update(Nic host);
	void delete(String id);
}