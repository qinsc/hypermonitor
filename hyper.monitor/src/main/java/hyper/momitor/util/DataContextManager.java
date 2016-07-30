package hyper.momitor.util;

import javax.servlet.http.HttpSession;

/**
 * 依附在线程上的session存储器
 *
 * @author qinsc (mailto:qinscx@gmail.com)
 */

public class DataContextManager {

	protected static ThreadLocal<DataContextManager> current = new ThreadLocal<>();
	private HttpSession context;

	/**
	 * Default. <br>
	 */
	private DataContextManager() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	public static DataContextManager getCurrent() {
		Object obj = current.get();
		DataContextManager manager = (null != obj && obj instanceof DataContextManager) ? (DataContextManager) obj : null;
		if (null == manager) {
			manager = new DataContextManager();
			current.set(manager);
		}
		return manager;
	}

	/**
	 * @return Returns the context.
	 */
	public HttpSession getContext() {
		return context;
	}

	/**
	 * @param context
	 *            The context to set.
	 */
	public HttpSession setContext(HttpSession context) {
		HttpSession old = this.context;
		this.context = context;
		return old;
	}
}
