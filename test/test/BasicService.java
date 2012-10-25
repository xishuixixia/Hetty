package test;


public class BasicService  implements BasicAPI {


	public User hello(String name) {
		User u=new User();
		u.setName(name);
		u.setMail(name+"@test.com");
		u.setAge(11);
		System.out.println("call hello method:"+name);
		return u;
	}
}
