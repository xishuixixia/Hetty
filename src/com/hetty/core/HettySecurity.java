package com.hetty.core;

import java.util.HashMap;
import java.util.Map;

import com.hetty.object.Application;
import com.hetty.util.StringUtil;

public class HettySecurity {
	
	private static final Map<String, Application> applicationMap = new HashMap<String, Application> ();
	
	private static final Map<String, String> serviceVersionMap = new HashMap<String, String> ();
	
	public static void addToApplicationMap(Application app){
		applicationMap.put(app.getUser(), app);
	}
	public static void addToVersionMap(String serviceName,String version){
		if(!ServiceHandler.isServiceExits(serviceName)){
			
		}
		serviceVersionMap.put(serviceName, version);
	}
	
	/**
	 * check permission
	 * @param user
	 * @param password
	 */
	public static boolean checkPermission(String user,String password){
		if(StringUtil.isEmpty(user) || StringUtil.isEmpty(password)){
			throw new IllegalArgumentException("user or password is null,please check.");
		}
		if(applicationMap.containsKey(user) && applicationMap.get(user).getPassword().equals(password)){
			return true;
		}else{
			return false;
		}
	}
}
