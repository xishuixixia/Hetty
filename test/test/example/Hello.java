package test.example;

public interface Hello {
	String hello();
	String hello(String name);
	String hello(String name1,String name2);
	User getUser(int id);
	
	String getAppSecret(String key);
}
