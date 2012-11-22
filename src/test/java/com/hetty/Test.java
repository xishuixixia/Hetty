package com.hetty;
import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.hetty.server.Hello;

public class Test {

	public static void main(String[] args) throws MalformedURLException {

		String url = "http://localhost:8081/apis/hello/";
		HessianProxyFactory factory = new HessianProxyFactory();
		factory.setUser("client1");

		factory.setPassword("client1");

		factory.setDebug(true);
		factory.setOverloadEnabled(true);
		// factory.setConnectTimeout(timeout);

		// factory.setReadTimeout(100);
		final Hello basic = (Hello) factory.create(Hello.class, url);

		// System.out.println("SayHello:" + basic.hello("guolei"));
		// System.out.println("SayHello:" + basic.test());
		// System.out.println(basic.getAppSecret("11"));
		// User user = basic.getUser(1);
		// System.out.println(user.getRoleList().size());
		// 测试方法重载
		System.out.println(basic.hello());
		// System.out.println(basic.hello("guolei"));
		// System.out.println(basic.hello("guolei","hetty"));
	}
}
