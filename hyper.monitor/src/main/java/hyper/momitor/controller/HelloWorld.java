/*******************************************************************************
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2016年7月20日 下午4:03:36
 *******************************************************************************/

package hyper.momitor.controller;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorld {
	@GET
	public Response getIssue() throws URISyntaxException, FileNotFoundException {
		String result = "Helloword ";
		return Response.status(200).entity(result).build();
	}
}
