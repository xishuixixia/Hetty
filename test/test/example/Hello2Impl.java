package test.example;

public class Hello2Impl implements Hello {

	@Override
	public String hello(String name) {
		return "Hello1, "+name+"!";
	}

	@Override
	public User getUser(int id) {
		{
			User u=new User();
			u.setAge(1);
			u.setEmail("zhuzhsh@163.com");
			u.setId(1);
			u.setName("zhuzhsh");
			for(int i=0;i<5;i++){
				Role r=new Role();
				r.setName("role"+i);
				r.setDescription("role"+i);
				u.addRole(r);
			}
			System.out.println(u);
			return u;
		}
	}

	@Override
	public String getAppSecret(String key) {
		// TODO Auto-generated method stub
		return "111";
	}

	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return "Hello";
	}

	@Override
	public String hello(String name1, String name2) {
		// TODO Auto-generated method stub
		return name1+" say Hello to "+name2;
	}

}
