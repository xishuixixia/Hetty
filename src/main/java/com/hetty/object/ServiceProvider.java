package com.hetty.object;

import java.io.Serializable;

import com.esotericsoftware.reflectasm.MethodAccess;

public class ServiceProvider implements Serializable {

	private static final long serialVersionUID = 2323169647020692097L;

	private String version;

	private Class<?> processorClass;
	
	private MethodAccess methodAccess;


	public ServiceProvider() {

	}

	public ServiceProvider(String version, Class<?> processorClass) {
		this.version = version;
		this.processorClass = processorClass;
		this.methodAccess = MethodAccess.get(processorClass);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setProcessorClass(Class<?> processorClass) {
		this.processorClass = processorClass;
	}
	public Class<?> getProcessorClass(){
		return processorClass;
	}
	public MethodAccess getMethodAccess(){
		return methodAccess;
	}

}