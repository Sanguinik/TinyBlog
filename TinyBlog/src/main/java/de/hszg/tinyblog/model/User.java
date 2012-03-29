package de.hszg.tinyblog.model;

/**
 * 
 * This is the model class for the user.
 * 
 * @author marlene
 *
 */
public class User {
	
	private String name;
	private String password;
	private String email;
	
	/**
	 * This constructor is used for creating a new user.
	 * 
	 * @param name The name of the user.
	 * @param password The password of the user.
	 * @param email The email address of the user.
	 */
	public User(String name, String password, String email){
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
