package test;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import Request.RegisterUserRequest;
import Request.StopInstanceRequest;
import Utils.Credentials;
import Utils.CustomResponseHandler;
import Utils.Sign;
import Utils.ThorRequestUtils;

public class test {
	
	private static final String EC_HOST = "172.16.10.29:8773";
	
	

	public final static void main(String[] args) throws Exception {
		registerUserTest();
		 //callStopInstanceTest();
		//testOSApiTest();
	}

	private static void registerUserTest(){
		HttpClient httpclient = new DefaultHttpClient();
		try {

			RegisterUserRequest req = new RegisterUserRequest();

			req.setParam("name", "join");
			req.setHost(EC_HOST);
		
			
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

			HttpRequestBase httpRequest = ThorRequestUtils.configureHeader(req,uri);
			
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
			req.setHost(EC_HOST);

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

			HttpRequestBase httpRequest = ThorRequestUtils.configureHeader(req,uri);
			
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

	

}