package de.hszg.tinyblog.persistence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class UserCreator implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("AUF WIEDERSEHEN");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("GUTEN TAG");
	}

}
