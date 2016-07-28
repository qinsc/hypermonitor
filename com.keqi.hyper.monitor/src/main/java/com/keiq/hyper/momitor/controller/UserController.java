/*******************************************************************************
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2016年7月28日 下午8:29:52
 *******************************************************************************/

package com.keiq.hyper.momitor.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.keiq.hyper.momitor.filter.LoginFilter;
import com.keiq.hyper.momitor.model.User;

/**
 * TODO 此处填写 class 信息
 *
 * @author qinsc (mailto:qinsc@primeton.com)
 */
@Path("/users")
public class UserController {
	@Path("/login")
	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
	public void login(@FormParam("username") String userName, @FormParam("password") String password, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		String homePage = "/views/index.jsp";
		String loginPage = "/login.jsp";
		// if already login
		if (null != request.getSession().getAttribute(LoginFilter.SESSION_USER)) {
			redirectPage(request, response, request.getContextPath() + homePage);
		}

		User user = new User();
		user.setUserName("admin");
		user.setPassword("keqi");
		String code = "0"; //$NON-NLS-1$
		boolean yes = false;
		if (null == user) {
			code = "1"; // not exists //$NON-NLS-1$
		} else {
			if (null != password && password.equals(user.getPassword())) {
				// Login success and go index
				request.getSession().setAttribute(LoginFilter.SESSION_USER, user);
				yes = true;
				redirectPage(request, response, request.getContextPath() + homePage);
				return;
			} else {
				code = "2"; // error password //$NON-NLS-1$
			}
		}
		if (!yes) {
			redirectPage(request, response, request.getContextPath() + loginPage + "?username=" + user.getUserName() + "#");
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param url
	 */
	protected void redirectPage(HttpServletRequest request, HttpServletResponse response, String url) {
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
		}
	}
}
