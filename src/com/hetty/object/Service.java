package com.hetty.object;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public  class Service implements Serializable{

	private static final long serialVersionUID = 2351769180636491630L;
	
	protected Class<?> typeClass;
	
	protected String id;
	
	protected String name;
	
	protected String defaultVersion = null;
	
	private Map<String,ServiceProvider> providerMap = new HashMap<String,ServiceProvider>();

	private boolean overload =false;
	
	public Service(){
		
	}
	
	public Service(String id,String name){
		this.id=id;
		this.name = name;
	}

	public Class<?> getTypeClass() {
		return typeClass;
	}

	public void setTypeClass(Class<?> typeClass) {
		this.typeClass = typeClass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultVersion() {
		return defaultVersion;
	}

	public void setDefaultVersion(String defaultVersion) {
		this.defaultVersion = defaultVersion;
	}
	public void addServiceProvider(String version,ServiceProvider provider){
		providerMap.put(version, provider);
	}
	public Map<String,ServiceProvider> getServiceProvider(){
		return Collections.unmodifiableMap(providerMap);
	}
	
	public boolean isOverload() {
		return overload;
	}

	public void setOverload(boolean overload) {
		this.overload = overload;
	}

	/**
	 * return serviceProvider according serviceName
	 * @param version
	 * @return
	 */
	public ServiceProvider getProviderByVersion(String version){
		if(version == null){
			return providerMap.get(defaultVersion);
		}else{
			return providerMap.get(version);
		}
	}
}