package com.hetty;

import java.net.MalformedURLException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.caucho.hessian.client.HessianProxyFactory;
import com.hetty.server.Hello;

public class TestSSL {

	public static void main(String[] args) throws MalformedURLException {

		String url = "https://192.168.1.183:9000/apis/hello/";
		HostnameVerifier hv = new HostnameVerifier() {  
            public boolean verify(String urlHostName, SSLSession session) { 
            	System.out.println(urlHostName);
            	System.out.println(session.getPeerHost());
             return urlHostName.equals(session.getPeerHost());  
            }  
       };  
       	HttpsURLConnection.setDefaultHostnameVerifier(hv); 
		HessianProxyFactory factory = new HessianProxyFactory();
		factory.setUser("client1");
		factory.setPassword("client1");
		factory.setDebug(true);
		factory.setOverloadEnabled(true);
		// factory.setReadTimeout(100);
		final Hello basic = (Hello) factory.create(Hello.class, url);
		// 测试方法重载
		System.out.println(basic.hello("郭蕾"));
	}
}
