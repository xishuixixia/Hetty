package com.hetty.core;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hetty.conf.HettyConfig;
import com.hetty.object.Application;
import com.hetty.plugin.IPlugin;


/**
 * @author guolei
 *
 */
public final class Hetty {

	private static  Logger logger = LoggerFactory.getLogger(Hetty.class);
	
	private ServerBootstrap bootstrap = null;
	private HettyConfig hettyConfig = HettyConfig.getInstance();
	private int listenPort;
	
	public Hetty(){
		HettyConfig.getInstance().loadPropertyFile("server.properties");//default file is this.
	}
	public Hetty(String file){
		HettyConfig.getInstance().loadPropertyFile("file");
	}
	
	private void init(){
		initHettySecurity();
		initPlugins();
		initServiceMetaData();
		initBootstrap();
	}
	
	/**
	 * init bootstrap
	 */
    private void initBootstrap(){
    	logger.info("init nettyBootstrap...........");
		ThreadFactory serverBossTF = new NamedThreadFactory("HETTY-BOSS-");
		ThreadFactory serverWorkerTF = new NamedThreadFactory("HETTY-WORKER-");
		bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(serverBossTF),
				Executors.newCachedThreadPool(serverWorkerTF)));
		bootstrap.setOption("tcpNoDelay", Boolean.parseBoolean(hettyConfig
				.getProperty("hetty.tcp.nodelay", "true")));
		bootstrap.setOption("reuseAddress", Boolean.parseBoolean(hettyConfig
				.getProperty("hetty.tcp.reuseaddress", "true")));
		
		int coreSize = hettyConfig.getServerCorePoolSize();
		int maxSize = hettyConfig.getServerMaximumPoolSize();
		int keepAlive = hettyConfig.getServerKeepAliveTime();
		ThreadFactory threadFactory = new NamedThreadFactory("hetty-");
		ExecutorService threadPool = new ThreadPoolExecutor(coreSize, maxSize, keepAlive,
				TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), threadFactory);
		bootstrap.setPipelineFactory(new HettyChannelPipelineFactory(threadPool));
		
		listenPort = hettyConfig.getPort();
		if (!checkConfig(listenPort)) {
			throw new IllegalStateException("port: " + listenPort + " already in use!");
		}
		bootstrap.bind(new InetSocketAddress(listenPort));
    }

	/**
	 * init plugins
	 */
	private void initPlugins() {
		logger.info("init plugins...........");
		List<Class<?>> pluginList = hettyConfig.getPluginClassList();
		try {
			for (Class<?> cls : pluginList) {
				IPlugin p;
				p = (IPlugin) cls.newInstance();
				p.start();
			}
		} catch (InstantiationException e) {
			logger.error("init plugin failed.");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error("init plugin failed.");
			e.printStackTrace();
		}
	}
	/**
	 * init service metaData
	 */
    private void initServiceMetaData() {
		// TODO Auto-generated method stub
    	logger.info("init service MetaData...........");
    	MetadataProcessor.initMetaDataMap();
	}
    /**
	 * init service metaData
	 */
    private void initHettySecurity() {
		// TODO Auto-generated method stub
    	logger.info("init hetty security...........");
    	Application app = new Application(hettyConfig.getServerKey(),hettyConfig.getServerSecret());
    	HettySecurity.addToApplicationMap(app);
	}
    /**
     * check the netty listen port
     * @param listenPort
     * @return
     */
	private boolean checkConfig(int listenPort) {
		if (listenPort < 0 || listenPort > 65536) {
			throw new IllegalArgumentException("Invalid start port: "
					+ listenPort);
		}
		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(listenPort);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(listenPort);
			ds.setReuseAddress(true);
			return true;
		} catch (IOException e) {
		} finally {
			if (ds != null) {
				ds.close();
			}
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					// should not be thrown, just detect port available.
				}
			}
		}
		return false;
	}
	public void serverLog(){
		logger.info("devMod:"+hettyConfig.getDevMod());
		logger.info("server key:"+hettyConfig.getServerKey());
		logger.info("server secret:"+hettyConfig.getServerSecret());
		logger.info("Server started,listen at: "+listenPort);
	}
	/**
	 * start hetty
	 */
	public void start(){
		init();
		serverLog();
	}
	/**
	 * stop hetty
	 */
	public void stop(){
		logger.info("Server stop!");
		bootstrap.releaseExternalResources();
	}
	public static void main(String[] args) {
		new Hetty().start();
	}
}
