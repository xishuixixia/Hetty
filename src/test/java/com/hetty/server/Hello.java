package com.hetty.server;

public interface Hello {
	
	public String hello();
	public String hello(String name);
	public String hello(String name1,String name2);
	public User getUser(int id);
	public String getAppSecret(String key);
}
