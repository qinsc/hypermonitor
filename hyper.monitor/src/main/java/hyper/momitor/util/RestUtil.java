/**
 * 
 */
package hyper.momitor.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class RestUtil {
	/**
	 * GET
	 * 
	 * @param method
	 * @param url
	 * @param jsonData
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url, Map<String, String> header) throws Exception {
		return doRest(url, "GET", null, header, 0);
	}

	/**
	 * POST
	 * 
	 * @param method
	 * @param url
	 * @param jsonData
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, String jsonData, Map<String, String> header) throws Exception {
		return doRest(url, "POST", jsonData, header, 0);
	}

	/**
	 * POST
	 * 
	 * @param method
	 * @param url
	 * @param jsonData
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, String jsonData, Map<String, String> header, int readTimeout)
			throws Exception {
		return doRest(url, "POST", jsonData, header, readTimeout);
	}

	/**
	 * PUT
	 * 
	 * @param url
	 * @param jsonData
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String doPut(String url, String jsonData, Map<String, String> header) throws Exception {
		return doRest(url, "PUT", jsonData, header, 0);
	}

	/**
	 * DELETE
	 * 
	 * @param url
	 * @param header
	 * @return
	 * @throws Exception
	 */
	public static String doDelete(String url, Map<String, String> header) throws Exception {
		return doRest(url, "DELETE", null, header, 0);
	}

	/**
	 * REST
	 * 
	 * @param url
	 * @param method
	 * @param jsonData
	 * @param header
	 * @return
	 * @throws Exception
	 */
	private static String doRest(String url, String method, String jsonData, Map<String, String> header,
			int readTimeout) throws Exception {
		URL restURL = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		if (readTimeout > 0) {
			conn.setReadTimeout(readTimeout);
		}

		conn.setInstanceFollowRedirects(true);
		if (header != null) {
			Set<String> keys = header.keySet();
			for (String key : keys) {
				conn.setRequestProperty(key, header.get(key));
			}
		}

		if (jsonData != null) {
			System.out.println("JSON date = " + jsonData);
			conn.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.write(jsonData.getBytes("utf-8"));
			out.flush();
			out.close();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String lines;
		StringBuffer sb = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			sb.append(lines);
		}
		reader.close();
		// 断开连接
		conn.disconnect();
		return sb.toString();
	}
}
