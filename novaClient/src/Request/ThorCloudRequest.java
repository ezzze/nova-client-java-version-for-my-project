package Request;


public class ThorCloudRequest extends ThorRequest{
	public ThorCloudRequest(){
		super();
		super.buildDefaultParams();
		super.setResourcePath("/services/Cloud");
	}
}
