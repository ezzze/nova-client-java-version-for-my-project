import java.util.Map.Entry;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import org.apache.http.impl.client.DefaultHttpClient;

public class test {

	public final static void main(String[] args) throws Exception {

		 callStopInstanceTest();
		//testOSApiTest();
	}

	private static void testOSApiTest() {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			OSRequest req = new OSRequest();
			req.setHost("172.16.10.29:8774");
			req.setResourcePath("/v1.1/servers");

			HttpRequestBase httpRequest = configureHeader(req,req.getPreUrl());
			httpRequest.addHeader("X-Auth-User", "jay");
			httpRequest.addHeader("X-Auth-Key", "jay");
			httpRequest.addHeader("X-Auth-Token","73bacdd5486acbe9af21014106946baaeb273192");
		
			System.out.println("--------------------------------");
			ResponseHandler<String> responseHandler = new CustomResponseHandler();
			// System.out.println("finalString " + finalString);
			String responseBody = httpclient.execute(httpRequest,
					responseHandler);
			System.out.println("--------------------------------");
			System.out.println("response Body : /n");
			System.out.println(responseBody);
			System.out.println("--------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			httpclient.getConnectionManager().shutdown();
		}
	}

	private static void callStopInstanceTest() {
		HttpClient httpclient = new DefaultHttpClient();
		try {

			StopInstanceRequest req = new StopInstanceRequest();

			req.setParam("vm_id", "123");
			req.setHost("172.16.10.29:8773");
			req.setResourcePath("/services/Cloud");
			/**
			 * you need to get accessKey and secretKey by retrieve the USER
			 * table and project table;
			 * 
			 */
			String accessKey = "jay";
			String secretKey = "jay";
			req.setProject("pro1");
			Credentials credentials = new Credentials(accessKey, secretKey);
			req.setCredentials(credentials);

			Sign.sign(req);

			String uri = req.getPreUrl();

			String encodedParams = ThorRequestUtils.encodeParameters(req
					.getParams());
			if (encodedParams != null) {
				uri += "?" + encodedParams;
			}

			HttpRequestBase httpRequest = configureHeader(req,uri);
			
			System.out.println("--------------------------------");
			ResponseHandler<String> responseHandler = new CustomResponseHandler();
			// System.out.println("finalString " + finalString);
			String responseBody = httpclient.execute(httpRequest,
					responseHandler);

			System.out.println("--------------------------------");
			System.out.println("response Body : /n");
			System.out.println(responseBody);
			System.out.println("--------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			httpclient.getConnectionManager().shutdown();
		}
	}

	private static HttpRequestBase configureHeader(ThorRequest req,String uri) {
		HttpRequestBase httpRequest = new HttpGet(uri);
		httpRequest.addHeader("Host", req.getHost());

		// Copy over any other headers already in our request
		for (Entry<String, String> entry : req.getHeads().entrySet()) {

			if (entry.getKey().equalsIgnoreCase("Content-Length"))
				continue;

			httpRequest.addHeader(entry.getKey(), entry.getValue());
		}
		if (httpRequest.getHeaders("Content-Type") == null
				|| httpRequest.getHeaders("Content-Type").length == 0) {
			httpRequest.addHeader("Content-Type",
					"application/x-www-form-urlencoded; " + "charset="
							+ ThorRequestUtils.DEFAULT_ENCODING.toLowerCase());
		}
		return httpRequest;
	}

}