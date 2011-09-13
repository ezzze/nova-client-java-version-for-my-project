/**
 * 
 * @author ezzze
 * 
 */
public class StopInstanceRequest extends ThorRequest {

	private String action = "StopInstance";

	public StopInstanceRequest() {
		super();
		super.buildDefaultParams();
		super.setParam("Action", action);
	}

}
