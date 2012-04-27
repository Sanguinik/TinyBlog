package de.hszg.tinyblog.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.hszg.tinyblog.service.AuthenticationService;
import de.hszg.tinyblog.service.AuthenticationServiceImpl;

/**
 * Temporaere Klasse, TODO: Login,Logout in extra Controller auslagern
 * AuthenticationContext koennte Daten zum aktuellen Nutzer enthalten
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

		AuthenticationService authenticationService = new AuthenticationServiceImpl();

		if (authenticationService.checkPassword(email, password)) {
			loggedIn = true;
			return "index";
		}

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
