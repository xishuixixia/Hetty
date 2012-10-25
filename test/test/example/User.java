package test.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3050667812951858045L;

	private int id;
	private String name;
	private String email;
	private int age;
	
	private List<Role> roleList=new ArrayList<Role>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public void addRole(Role role){
		roleList.add(role);
	}
	
	
	public static void main(String[] args){
		String s=System.getProperty("aa");
		System.out.println(s);
	}
}
