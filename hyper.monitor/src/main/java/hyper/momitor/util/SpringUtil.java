/*******************************************************************************
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2016年7月28日 下午3:37:00
 *******************************************************************************/

package hyper.momitor.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author qinsc (mailto:qinsc@primeton.com)
 */

public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	/**
	 * Default. <br>
	 * 
	 * Invoke by spring, load bean. <br>
	 */
	public SpringUtil() {
		super();
	}

	/**
	 * 
	 * @param beanId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanId) {
		return null == applicationContext ? null : (T) applicationContext.getBean(beanId);
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return null == applicationContext ? null : applicationContext.getBean(clazz);
	}

	/**
	 * 
	 * @param clazz
	 * @param highest
	 *            is priority highest, else lowest
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Priority> T getBean(Class<T> clazz, boolean highest) {
		if (null == applicationContext || null == clazz) {
			return null;
		}
		Map<String, T> beans = applicationContext.getBeansOfType(clazz);
		Priority[] beanArray = new Priority[beans.keySet().size()];
		beans.values().toArray(beanArray);
		Arrays.sort(beanArray, new Comparator<Priority>() {
			public int compare(Priority o1, Priority o2) {
				return o1.getPriority() - o2.getPriority();
			}
		});
		return (T) (highest ? beanArray[beanArray.length - 1] : beanArray[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}

	/**
	 * @return Returns the applicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public interface Priority {
		int PRIORITY_LOWEST = -300;
		int PRIORITY_LOWER = -200;
		int PRIORITY_LOW = -100;
		int PRIORITY_NORMAL = 0;
		int PRIORITY_HIGH = 100;
		int PRIORITY_HIGHER = 200;
		int PRIORITY_HIGHEST = 300;

		/**
		 * The priority value. <br>
		 * 
		 * @return
		 */
		int getPriority();

	}

}
