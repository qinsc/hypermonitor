package hyper.momitor.controller;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * 
 * 测试rest接口
 *
 * @author qinsc (mailto:qinscx@gmail.com)
 */
@Path("/hello")
public class HelloWorld {
	@GET
	public Response getIssue() throws URISyntaxException, FileNotFoundException {
		String result = "Helloword ";
		return Response.status(200).entity(result).build();
	}
}
