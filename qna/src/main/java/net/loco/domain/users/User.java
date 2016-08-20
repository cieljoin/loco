package net.loco.domain.users;


public class User {
	
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String message;
	
	public User(){
		
	}
	
	public boolean matchPassword(Authenticate authenticate){
		if(this.password == null){
			return false;
		}
		
		return authenticate.matchPassword(this.password);
	}
	
	public boolean matchUserId(String inputUserId) {
		if(userId == null){
			return false;
		}
		return inputUserId.equals(this.userId);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User(String userId, String password, String name, String email, String phone, String message) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.message = message;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", message=" + message + "]";
	}

	
}
