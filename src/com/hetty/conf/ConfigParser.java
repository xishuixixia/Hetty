package com.hetty.conf;

import java.util.List;

import com.hetty.object.Application;
import com.hetty.object.Service;
import com.hetty.object.ServiceVersion;

public interface ConfigParser {
	List<Service> parseService();

	List<Application> parseApplication();

	List<ServiceVersion> parseSecurity();
}