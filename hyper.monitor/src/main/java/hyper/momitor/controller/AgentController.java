/**
 * 
 */
package hyper.momitor.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * @author qinscx
 *
 */
@Path("/agent")
public class AgentController {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/download")
	public Response download(@Context HttpServletRequest request) {		
		String filePath = this.getClass().getClassLoader().getResource("/").getPath();
		File file = new File(filePath + "/agent/agent.rar");
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=\"agent.rar\"");
		return response.build();
	}
}
