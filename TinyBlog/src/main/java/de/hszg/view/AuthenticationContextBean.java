package de.hszg.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Temporaere Klasse, 
 * TODO: Login,Logout in extra Controller auslagern
 * AuthenticationContext koennte Daten zum aktuellen Nutzer enthalten
 */

@ManagedBean
@SessionScoped
public class AuthenticationContextBean {
	
	
	
	private boolean loggedIn;

	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public String login(){
		
		loggedIn = true;
		
		return "login";
	}
	
	public String logout(){
		
		loggedIn = false;
		
		return "logout";
	}
}
