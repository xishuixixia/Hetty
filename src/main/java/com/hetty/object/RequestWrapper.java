package com.hetty.object;

import java.io.Serializable;

/**
 * 
 * @author guolei
 *
 */
public class RequestWrapper implements Serializable {

	private static final long serialVersionUID = -6017954186180888313L;

	private String user = null;

	private String password = null;

	private String serviceName;

	private String methodName;
	
	private Object[] args = null;
	
	private Class<?>[] argsTypes =null;

	private String clientIP = null;
	
	public RequestWrapper() {

	}

	public RequestWrapper(String user, String password,String clientIP,String serviceName) {
		this.user = user;
		this.password = password;
		this.clientIP = clientIP;
		this.serviceName = serviceName;
	}


	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
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

	public Class<?>[] getArgsTypes() {
		return argsTypes;
	}

	public void setArgsTypes(Class<?>[] argsTypes) {
		this.argsTypes = argsTypes;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
}
