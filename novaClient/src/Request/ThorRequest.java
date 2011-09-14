package Request;

import java.util.HashMap;
import java.util.Map;

import Utils.Credentials;
import Utils.Sign;
import Utils.ThorRequestUtils;



import constants.HttpMethodName;


/**
 * 
 * @author ezzze
 *
 */
public abstract class ThorRequest {
	
	private static final String VERSION = "2009-11-30";
	
	private static final String PRE_FIX ="http://";

	private Map<String, String> heads;

	private Map<String, String> params;
	
	private HttpMethodName httpMethod = HttpMethodName.GET;
	
	private Credentials credentials;
	
	private String resourcePath;
	
	private String host;
	
	private String project;
	
	
	/**
	 * default constructor
	 */
	public ThorRequest(){
		heads = new HashMap<String,String>();
		params = new HashMap<String,String>();
		
	}

	public Map<String, String> getParams() {
		if (null == params) {
			return new HashMap<String, String>();
		}
		return params;
	}

	public void setParam(String key, String value) {
		this.getParams().put(key, value);
	}

	public Map<String, String> getHeads() {
		if (null == heads) {
			return new HashMap<String, String>();
		}
		return heads;
	}

	public void setHead(String key, String value) {
		this.getHeads().put(key, value);
	}

	public HttpMethodName getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethodName httpMethod) {
		this.httpMethod = httpMethod;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	
	public void buildDefaultParams() {
		this.setParam("SignatureMethod", Sign.SIGN_ALGORITHM);
		this.setParam("SignatureVersion", Sign.SIGN_VERSION);
		this.setParam("Timestamp", ThorRequestUtils.getFormattedTimestamp());
		this.setParam("Version", VERSION);
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public String getPreUrl(){
		return PRE_FIX + this.host+this.resourcePath;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
}
