/*******************************************************************************
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2016年7月20日 下午2:45:44
 *******************************************************************************/

package hyper.momitor.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * TODO 此处填写 class 信息
 *
 * @author qinsc (mailto:qinsc@primeton.com)
 */
@Path("/files")
public interface FileApi {
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFiles(@Context HttpServletRequest request, @Context HttpServletResponse respond) throws Exception;
	
	@GET
	@Path("/hello")
	public String hello();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> listFiles();
}
