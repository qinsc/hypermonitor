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
 * 文件测试接口
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
