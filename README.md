Hetty
=====
Hetty是一款构建于netty和hessian基础上的高性能的RPC框架.hessian是一款基于HTTP协议的RPC框架，采用的是二进制RPC协议，非常轻量级
，且速度较快。netty是一款Netty基于事件驱动的NIO框架，用以快速开发高性能、高可靠性的网络服务器和客户端程序。Hetty客户端完全由
用hessian实现，只是使用netty对服务端进行了实现。
使用方法：

1.配置hetty服务器。

server.devmod=false #配置开发模式
server.key=server1 #配置服务器Key，用于认证
server.secret=server1 #配置服务器secret，用于认证
server.port=8081 #配置端口号

server.thread.corePoolSize=4 #线程池配置
server.thread.maxPoolSize=100
server.thread.keepAliveTime=3000
server.method.timeout=3000
properties.file=config.xml #配置service定义文件

2.service配置

<?xml version="1.0" encoding="UTF-8"?>
<deployment>
  <applications>
		<application user="client1" password="client1"/>
	</applications>

	<services>
		<service name="basic" interface="test.BasicAPI">
			<provider version="1" class="test.BasicService" default="true" />
		</service>
		<service name="hello" interface="test.example.Hello" overload="true">
<!-- 			<provider version="1" class="test.example.HelloImpl" default="true"/> -->
			<provider version="2" class="test.example.Hello2Impl"/>
		</service>
<!-- 		<service name="hello" interface="test.example.Hello" overload="true"> -->
<!-- 			<provider version="1" class="test.example.HelloImpl" default="true"/> -->
<!-- 			<provider version="2" class="test.example.Hello2Impl"/> -->
<!-- 		</service> -->
	</services>

<!-- 	<security-settings> -->
<!-- 		<security-setting user="client1" service="hello" -->
<!-- 			version="1" /> -->

<!-- 		<security-setting appKey="client1" service="basic" /> -->
<!-- 	</security-settings> -->
</deployment>

配置包括三部分，第一部分applications，配置客户端的user和密码，来做权限认证。第二部分配置service接口，比如：
<service name="basic" interface="test.BasicAPI">
  		<provider version="1" class="test.BasicService" default="true" />
</service>
我们配置了test.BasicAPI接口，接口名字为basic，有一个版本，版本号为1，且为默认版本。
第三部分配置客户端的调用的版本，比如可以配置client1调用basic接口的1版本。

3.客户端调用

public static void main(String[] args) throws MalformedURLException {
		
		String url = "http://localhost:8081/apis/hello/";

		HessianProxyFactory factory = new HessianProxyFactory();

		factory.setUser("server1");

		factory.setPassword("server1");

		factory.setOverloadEnabled(true);

		final Hello basic = (Hello) factory.create(Hello.class, url);
		
		System.out.println(basic.hello());
		System.out.println(basic.hello("guolei"));
		System.out.println(basic.hello("guolei","hetty"));
	}
