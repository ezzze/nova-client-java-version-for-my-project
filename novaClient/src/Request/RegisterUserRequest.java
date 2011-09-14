package Request;


public class RegisterUserRequest extends ThorAdminRequest {
	
	private String action = "register_user";
	
	public RegisterUserRequest() {
	
		super.setParam("Action", action);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
	
	
}
