package com.hetty.object;

import java.io.Serializable;

public class Application implements Serializable{

	private static final long serialVersionUID = 2485662854684252982L;

	private String user;
	private String password;

	public Application(){
		
	}
	public Application(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
