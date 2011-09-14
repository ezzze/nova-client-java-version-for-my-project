package test;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import Request.OSRequest;
import Utils.CustomResponseHandler;
import Utils.ThorRequestUtils;


public class OSApiTest {
	
	private static final String OS_HOST = "172.16.10.29:8773";
	
	public final static void main(String[] args) throws Exception {
		testOSApiTest();
		 //callStopInstanceTest();
		//testOSApiTest();
	}
	private static void testOSApiTest() {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			OSRequest req = new OSRequest();
			req.setHost(OS_HOST);
			

			HttpRequestBase httpRequest = ThorRequestUtils.configureHeader(req,req.getPreUrl());
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

}
