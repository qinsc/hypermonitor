/*******************************************************************************
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2016年7月28日 下午7:43:22
 *******************************************************************************/

package hyper.momitor.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hyper.momitor.util.DataContextManager;

/**
 * TODO 此处填写 class 信息
 *
 * @author qinsc (mailto:qinsc@primeton.com)
 */

public class LoginFilter implements Filter {
	public static final String SESSION_USER = "admin"; //$NON-NLS-1$
	public static final String SESSION_LANGUAGE = "language"; //$NON-NLS-1$

	private String excludedPages;
	private String homePage;
	private String loginPage;

	private String[] excludedPageArray;

	private static LoginFilter loginFilter;

	/**
	 * Default. <br>
	 */
	public LoginFilter() {
		super();
		loginFilter = this;
	}

	/**
	 * 
	 * @return
	 */
	public static LoginFilter getDefault() {
		return loginFilter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		String path = servletRequest.getRequestURI();

		// Setting current thread context
		DataContextManager.getCurrent().setContext(session);

		Object obj = session.getAttribute(SESSION_USER);
		// User user = (null != obj && obj instanceof User) ? (User)obj : null;

		// if already login and visit login page then go home
		if (null != obj && path.contains(servletRequest.getContextPath() + loginPage)) {
			redirectHomePage(servletRequest, servletResponse);
			return;
		}
		// No need login can visit resource
		if (isExcludedPath(path)) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		// Redirect login page if not login
		if (null == obj) {
			redirectLoginPage(servletRequest, servletResponse);
			return;
		}
		// can visit
		chain.doFilter(servletRequest, servletResponse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		homePage = config.getInitParameter("homePage"); //$NON-NLS-1$
		homePage = "/index.jsp";

		loginPage = config.getInitParameter("loginPage"); //$NON-NLS-1$
		loginPage = "/login.jsp";

		excludedPages = config.getInitParameter("excludedPages"); //$NON-NLS-1$
		if (excludedPages != null && excludedPages.trim().length() != 0) {
			excludedPageArray = excludedPages.split(",");
			if (null != excludedPageArray && excludedPageArray.length > 0) {
				List<String> urls = new ArrayList<String>();
				for (String url : excludedPageArray) {
					if (null == url) {
						continue;
					}
					String page = url.trim();
					if (page.length() > 0) {
						urls.add(page);
					}
				}
				excludedPageArray = urls.toArray(new String[urls.size()]);
			}
		}
	}

	/**
	 * @param path
	 * @return
	 */
	private boolean isExcludedPath(String path) {
		if (null == path || path.trim().length() == 0) {
			return false;
		}
		if (null != excludedPageArray && excludedPageArray.length > 0) {
			for (String page : excludedPageArray) {
				if (path.endsWith(page)) {
					return true;
				}
				if (page.endsWith("*") && path.contains(page.substring(0, page.length() - 1))) {
					return true;
				}
				if (page.startsWith("*") && path.contains(page.substring(1))) {
					return true;
				}
				if (page.endsWith("*") && page.startsWith("*") && page.length() > 2 && path.contains(page.substring(1, page.length() - 1))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void redirectHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (null == request || null == response) {
			return;
		}
		String url = request.getContextPath() + this.homePage;
		response.sendRedirect(url); // $NON-NLS-1$
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void redirectLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (null == request || null == response) {
			return;
		}
		String url = request.getContextPath() + this.loginPage;
		response.sendRedirect(url); // $NON-NLS-1$
	}

	/**
	 * @return Returns the excludedPages.
	 */
	public String getExcludedPages() {
		return excludedPages;
	}

	/**
	 * @return Returns the homePage.
	 */
	public String getHomePage() {
		return homePage;
	}

	/**
	 * @return Returns the loginPage.
	 */
	public String getLoginPage() {
		return loginPage;
	}

}
