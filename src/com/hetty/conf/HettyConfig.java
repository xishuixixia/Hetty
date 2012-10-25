package com.hetty.conf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hetty.object.HettyException;
import com.hetty.util.StringUtil;


/**
 * @author guolei
 *
 */
public class HettyConfig {

	private static Logger logger = LoggerFactory.getLogger(HettyConfig.class);
	
	private static Properties properties = new Properties();
	private static HettyConfig instance = null;
	private HettyConfig(){
		
	}

	/**
	 * return instance
	 * @return
	 */
	public  static HettyConfig getInstance() {
		if (instance == null) {
			instance = new HettyConfig();
		}
		return instance;
	}
	/**
	 * load config file
	 * Example: loadPropertyFile("server.properties");
	 * @param file class path
	 */
	public void loadPropertyFile(String file) {
		if (StringUtil.isEmpty(file)) {
			throw new IllegalArgumentException(
					"Parameter of file can not be blank");
		}
		if (file.contains("..")) {
			throw new IllegalArgumentException(
					"Parameter of file can not contains \"..\"");
		}
		InputStream inputStream = null;
		try {
			inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(file);
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Properties file not found: "
					+ file);
		} catch (IOException e) {
			throw new IllegalArgumentException(
					"Properties file can not be loading: " + file);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		if (properties == null)
			throw new RuntimeException("Properties file loading failed: "+ file);
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public Properties getProperties() {
		return properties;
	}
	
	/**
	 * get server key
	 * @return server key
	 */
	public String getServerKey() {
		String serverKey = properties.getProperty("server.key");
		if (serverKey == null) {
			throw new RuntimeException("we cannot find the server.key,please check and add.");
		}
		return serverKey;
	}

	/**
	 * get server secret
	 * @return server secret
	 */
	public String getServerSecret() {
		String serverSecret = properties.getProperty("server.secret");
		if (serverSecret == null) {
			throw new RuntimeException("we cannot find the server.secret,please check and add.");
		}
		return serverSecret;
	}

	/**
	 * get the service properties file,default is config.xml
	 * @return
	 */
	public String getpropertiesFile() {
		String f = properties.getProperty("properties.file", "config.xml");
		return f;
	}

	/**
	 * get the server's connect timeout,default is 3s
	 * @return
	 */
	public int getConnectionTimeout() {
		String timeOutStr = properties.getProperty("server.connection.timeout",
				"3000");
		return Integer.parseInt(timeOutStr);
	}

	/**
	 * get the method's invoke timeout,default is 3s
	 * @return
	 */
	public int getMethodTimeout() {
		String timeOutStr = properties.getProperty("server.method.timeout", "3000");
		return Integer.parseInt(timeOutStr);
	}

	/**
	 * get the server's port,default is 8080
	 * @return
	 */
	public int getPort() {
		String port = properties.getProperty("server.port", "8080");
		return Integer.parseInt(port);
	}

	/**
	 * get the core number of threads
	 * @return
	 */
	public int getServerCorePoolSize(){
		String coreSize = properties.getProperty("server.thread.corePoolSize", "4");
		return Integer.parseInt(coreSize);
	}
	/**
	 * get the maximum allowed number of threads
	 * @return
	 */
	public int getServerMaximumPoolSize(){
		String maxSize = properties.getProperty("server.thread.maxPoolSize","16");
		return Integer.parseInt(maxSize);
	}
	/**
	 * get the thread keep-alive time, which is the amount of time that threads in excess of the core pool size may remain idle before being terminated
	 * @return
	 */
	public int getServerKeepAliveTime(){
		String aleveTime = properties.getProperty("server.thread.keepAliveTime", "3000");
		return Integer.parseInt(aleveTime);
	}

	/**
	 * get the plugin list which have configured in the server config file
	 * 
	 * @return config class List
	 */
	public List<Class<?>> getPluginClassList() {

		Set<String> keySet = properties.stringPropertyNames();
		List<Class<?>> list = new ArrayList<Class<?>>();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		try {
			for (String key : keySet) {
				if (key.startsWith("server.plugin")) {
					String cls = properties.getProperty(key);
					Class<?> cls1 = classLoader.loadClass(cls);
					list.add(cls1);
				}
			}
			if (list.size() == 0) {
				Class<?> defaultClazz = classLoader
						.loadClass("com.hetty.plugin.XmlConfigPlugin");
				list.add(defaultClazz);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new HettyException(
					"load plugin class failed.please check your plugin config.");
		}
		return list;
	}
	/**
	 * get develop mod,default is false
	 */
	public boolean getDevMod(){
		String dev = properties.getProperty("server.devmod", "false");
		return Boolean.parseBoolean(dev);
	}
}
