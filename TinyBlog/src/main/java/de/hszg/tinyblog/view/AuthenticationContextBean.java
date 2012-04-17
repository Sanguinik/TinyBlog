package de.hszg.tinyblog.view;



import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Temporaere Klasse, 
 * TODO: Login,Logout in extra Controller auslagern
 * AuthenticationContext koennte Daten zum aktuellen Nutzer enthalten
 */

@Named
@SessionScoped
public class AuthenticationContextBean implements Serializable{
	

	private static final long serialVersionUID = -3185273713868882607L;

	public AuthenticationContextBean(){

	}
	
	
	private boolean loggedIn;

	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public String login(){
		
		loggedIn = true;
		
		return "index";
	}
	
	public String logout(){
		
		loggedIn = false;
		
		return "logout";
	}
}
