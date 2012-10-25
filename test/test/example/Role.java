package test.example;

import java.io.Serializable;

public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4773772817110244399L;
	private String name;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
