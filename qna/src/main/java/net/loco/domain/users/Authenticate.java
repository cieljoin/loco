package net.loco.domain.users;

public class Authenticate {
	
	private String userId;
	
	private String password;

	public Authenticate(){
		
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

	public Authenticate(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Authenticate [userId=" + userId + ", password=" + password + "]";
	}
	
	public boolean matchPassword(String password){
		return this.password.equals(password);
	}
	
}
