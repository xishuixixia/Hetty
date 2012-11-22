package com.hetty.core.ssl;

import static org.jboss.netty.channel.Channels.pipeline;

import java.util.concurrent.ExecutorService;

import javax.net.ssl.SSLEngine;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpContentCompressor;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.ssl.SslHandler;

import com.hetty.conf.HettyConfig;

public class SslHettyChannelPipelineFactory implements ChannelPipelineFactory{
	private ExecutorService threadpool;

	public SslHettyChannelPipelineFactory(ExecutorService threadpool) {
		this.threadpool = threadpool;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		
		String mode = HettyConfig.getInstance().getClientAuth();
		
		ChannelPipeline pipeline = pipeline();
		
		// Add SSL handler first to encrypt and decrypt everything.
        SSLEngine engine = SslContextFactory.getServerContext().createSSLEngine();
        engine.setUseClientMode(false);
        if ("want".equalsIgnoreCase(mode)) {
            engine.setWantClientAuth(true);
        } else if ("need".equalsIgnoreCase(mode)) {
            engine.setNeedClientAuth(true);
        }
        engine.setEnableSessionCreation(true);
		
		pipeline.addLast("ssl", new SslHandler(engine));
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("aggregator", new HttpChunkAggregator(1048576));
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("deflater", new HttpContentCompressor());
		pipeline.addLast("handler", new SslHettyHandler(threadpool));
		return pipeline;
	}
}
