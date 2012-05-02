package de.hszg.tinyblog.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import de.hszg.tinyblog.service.AuthenticationService;
import de.hszg.tinyblog.service.AuthenticationServiceImpl;

/**
 * This managed bean is responsible for keeping a user logged in within a
 * session and provides the methods for login and logout.
 */

@ManagedBean
@SessionScoped
public class AuthenticationContextBean implements Serializable {

	private static final long serialVersionUID = -3185273713868882607L;
	private String email;

	private String password;

	public AuthenticationContextBean() {

	}

	private boolean loggedIn;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();

		AuthenticationService authenticationService = new AuthenticationServiceImpl();

		if (authenticationService.checkPassword(email, password)) {
			loggedIn = true;
			return "index";
		}

		context.addMessage("inputForm:email", new FacesMessage(
				"E-Mail-Adresse und/oder Passwort sind falsch."));

		return null;
	}

	public String logout() {

		loggedIn = false;

		return "logout";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
}
