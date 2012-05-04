package de.hszg.tinyblog.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * This is the model class for the user.
 * 
 * @author marlene
 * 
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String password;
	private String email;

	public User() {

	}

	/**
	 * This constructor is used for creating a new user.
	 * 
	 * @param name
	 *            The name of the user.
	 * @param password
	 *            The password of the user.
	 * @param email
	 *            The email address of the user.
	 */
	public User(final String name, final String password, final String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}

}
