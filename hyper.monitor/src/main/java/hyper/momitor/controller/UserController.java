package hyper.momitor.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import hyper.momitor.filter.LoginFilter;
import hyper.momitor.model.User;

/**
 * 用户管理
 *
 * @author qinsc (mailto:qinsc@primeton.com)
 */
@Path("/users")
public class UserController {
	@Path("/login")
	@POST
	public void login(@FormParam("username") String userName, @FormParam("password") String password, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		String homePage = "/views/index.jsp";
		String loginPage = "/login.jsp";
		if (null != request.getSession().getAttribute(LoginFilter.SESSION_USER)) {
			redirectPage(request, response, request.getContextPath() + homePage);
			return;
		}

		User user = new User();
		user.setUserName("admin");
		user.setPassword("keqi");
		boolean yes = false;
		if (null != password && password.equals(user.getPassword())) {
			request.getSession().setAttribute(LoginFilter.SESSION_USER, user);
			yes = true;
			redirectPage(request, response, request.getContextPath() + homePage);
			return;
		} 
		if (!yes) {
			redirectPage(request, response, request.getContextPath() + loginPage + "?username=" + user.getUserName() + "#");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.primeton.iaas.deploy.rest.IUserRest#logout(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Path("/logout")
	@GET
	public void logout(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		System.out.println("Do logout ...");
		request.getSession().removeAttribute(LoginFilter.SESSION_USER);
		request.getSession().invalidate();
		
		String url = request.getContextPath() + "/login.jsp";
		redirectPage(request, response, url);
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
