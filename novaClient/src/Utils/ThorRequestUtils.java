package Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import Request.ThorRequest;


public class ThorRequestUtils {
	
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	public static String calculateStringToSign(ThorRequest request) {
		StringBuilder data = new StringBuilder();
		data.append(request.getHttpMethod().name()).append("\n");
		data.append(request.getHost()).append("\n");
		data.append(request.getResourcePath()).append("\n");
		data.append(getCanonicalizedQueryString(request.getParams()));
		return data.toString();
	}

	public static String calculateStringToSign(String action, String hostUrl,
			String resource, String params) {
		StringBuilder data = new StringBuilder();
		data.append(action).append("\n");
		data.append(hostUrl).append("\n");
		data.append(resource).append("\n");
		data.append(params);
		return data.toString();
	}

	/**
	 * get changed query string
	 * 
	 * @param parameters
	 * @return
	 */
	public static String getCanonicalizedQueryString(
			Map<String, String> parameters) {

		SortedMap<String, String> sorted = new TreeMap<String, String>();
		sorted.putAll(parameters);

		StringBuilder builder = new StringBuilder();
		Iterator<Map.Entry<String, String>> pairs = sorted.entrySet()
				.iterator();
		while (pairs.hasNext()) {
			Map.Entry<String, String> pair = pairs.next();
			String key = pair.getKey();
			String value = pair.getValue();
			builder.append(urlEncode(key));
			builder.append("=");
			builder.append(urlEncode(value));
			if (pairs.hasNext()) {
				builder.append("&");
			}
		}

		return builder.toString();
	}



	public static String getFormattedTimestamp() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		return df.format(new Date());
	}

	/**
	 * Creates an encoded query string from all the parameters in the specified
	 * request.
	 * 
	 * @param request
	 *            The request containing the parameters to encode.
	 * 
	 * @return Null if no parameters were present, otherwise the encoded query
	 *         string for the parameters present in the specified request.
	 */
	public static String encodeParameters(Map<String, String> params) {
		List<NameValuePair> nameValuePairs = null;
		if (params.size() > 0) {
			nameValuePairs = new ArrayList<NameValuePair>(params.size());
			for (Entry<String, String> entry : params.entrySet()) {
				nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}

		String encodedParams = null;
		if (nameValuePairs != null) {
			encodedParams = URLEncodedUtils.format(nameValuePairs,
					DEFAULT_ENCODING);
		}

		return encodedParams;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String urlEncode(String value) {
		try {
			String encoded = URLEncoder.encode(value, DEFAULT_ENCODING)
					.replace("+", "%20").replace("*", "%2A")
					.replace("%7E", "~");

			return encoded;
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	
	public static HttpRequestBase configureHeader(ThorRequest req,String uri) {
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
