package test;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 3601470141407549788L;
	
	private String name;
	private String mail;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

}
