package com.huazie.flea.concurrency.basicbuildingmodule.demo6;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Jetty服务启动类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JettyStarter {

	private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(JettyStarter.class);
	
	public static void main(String[] args) throws Exception {
		long begin = System.currentTimeMillis();

		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });
		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		context.addServlet(Factorizer.class, "/memoizer");
		HandlerCollection handlers = new HandlerCollection();
		handlers.setHandlers(new Handler[] { context, new DefaultHandler() });
		server.setHandler(handlers);
		server.start();

		long end = System.currentTimeMillis();
		
		JettyStarter.LOGGER.debug("Jetty Server started , use " + (end - begin) + " ms");
	}
}
