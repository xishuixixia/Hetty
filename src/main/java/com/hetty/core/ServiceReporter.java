package com.hetty.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hetty.object.RequestWrapper;

final class ServiceReporter {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * Report action before action invoking when the common request coming
	 */
	static final boolean reportBeforeInvoke(RequestWrapper request) {
		doReport(request);
		return true;
	}
	
	private static final void doReport(RequestWrapper request) {
		StringBuilder tip = new StringBuilder("\nHetty request report -------- ").append(sdf.format(new Date())).append(" ------------------------------\n");
		String serviceName = request.getServiceName();
		String serviceClass = ServiceHandler.getServiceByName(serviceName).getClass().getName();
		tip.append("user    : ").append(request.getUser()).append("\n");
		tip.append("password: ").append(request.getPassword()).append("\n");
		tip.append("clientIP: ").append(request.getClientIP()).append("\n");
		tip.append("service : ").append(serviceName).append(".(").append(serviceClass).append(".java)");
		tip.append("method  : ").append(request.getMethodName()).append("\n");
		tip.append("--------------------------------------------------------------------------------\n");
		System.out.print(tip.toString());
	}
}
