package roth.lib.java.web.server;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.Scanner;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebServer
{
	protected static String USER_DIR		= "user.dir";
	protected static String WEB_APP			= "src/main/webapp/";
	protected static String WEB_INF			= "WEB-INF/";
	protected static String PORT			= "port";
	protected static String CONTEXT_PATH	= "contextPath";
	protected static String PROJECT_DIR		= "projectDir";
	protected static String WEB_APP_DIR		= "webAppDir";
	protected static String WEB_INF_DIR		= "webInfDir";
	protected static String ENV				= "env";
	
	protected File projectDir;
	protected File webAppDir;
	protected File webInfDir;
	protected int port;
	protected Server server;
	protected SslContextFactory sslContextFactory;
	protected HttpConfiguration httpsConfig;
	protected ServerConnector serverConnector;
	protected WebAppContext webAppContext;
	protected String contextPath;
	protected Scanner scanner;
	protected int scanInterval;
	protected Scanner.Listener scanListener;
	
	public WebServer()
	{
		init();
	}
	
	protected void init()
	{
		projectDir = new File(System.getProperty(USER_DIR));
		webAppDir = new File(projectDir, WEB_APP);
		webInfDir = new File(webAppDir, WEB_INF);
		port = 9443;
		server = new Server();
		sslContextFactory = new SslContextFactory();
		sslContextFactory.setKeyStorePath(getClass().getClassLoader().getResource("localhost.jks").toExternalForm());
		sslContextFactory.setKeyStorePassword("localhost");
		sslContextFactory.setKeyManagerPassword("localhost");
		httpsConfig = new HttpConfiguration();
		httpsConfig.setSecureScheme("https");
		httpsConfig.addCustomizer(new SecureRequestCustomizer());
		serverConnector = new ServerConnector(server, new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()), new HttpConnectionFactory(httpsConfig));
		webAppContext = new WebAppContext();
		contextPath = "/";
		scanner = new Scanner();
		scanInterval = 2;
		scanListener = new Scanner.BulkListener()
		{
			@Override
			public void filesChanged(List<String> filenames) throws Exception
			{
				if(webAppContext.isRunning())
				{
					webAppContext.stop();
					webAppContext.start();
				}
			}
		};
	}
	
	public File getProjectDir()
	{
		return projectDir;
	}
	
	public File getWebAppDir()
	{
		return webAppDir;
	}
	
	public File getWebInfDir()
	{
		return webInfDir;
	}
	
	public int getPort()
	{
		return port;
	}
	
	public Server getServer()
	{
		return server;
	}
	
	public ServerConnector getServerConnector()
	{
		return serverConnector;
	}
	
	public WebAppContext getWebAppContext()
	{
		return webAppContext;
	}
	
	public String getContextPath()
	{
		return contextPath;
	}
	
	public Scanner getScanner()
	{
		return scanner;
	}
	
	public int getScanInterval()
	{
		return scanInterval;
	}
	
	public Scanner.Listener getScanListener()
	{
		return scanListener;
	}
	
	public WebServer setProjectDir(File projectDir)
	{
		this.projectDir = projectDir;
		this.webAppDir = new File(projectDir, WEB_APP);
		this.webInfDir = new File(webAppDir, WEB_INF);
		return this;
	}
	
	public WebServer setWebAppDir(File webAppDir)
	{
		this.webAppDir = webAppDir;
		this.webInfDir = new File(webAppDir, WEB_INF);
		return this;
	}
	
	public WebServer setWebInfDir(File webInfDir)
	{
		this.webInfDir = webInfDir;
		return this;
	}
	
	public WebServer setPort(int port)
	{
		this.port = port;
		return this;
	}
	
	public WebServer setServer(Server server)
	{
		this.server = server;
		return this;
	}
	
	public WebServer setServerConnector(ServerConnector serverConnector)
	{
		this.serverConnector = serverConnector;
		return this;
	}
	
	public WebServer setWebAppContext(WebAppContext webAppContext)
	{
		this.webAppContext = webAppContext;
		return this;
	}
	
	public WebServer setContextPath(String contextPath)
	{
		this.contextPath = contextPath;
		return this;
	}
	
	public WebServer setScanner(Scanner scanner)
	{
		this.scanner = scanner;
		return this;
	}
	
	public WebServer setScanInterval(int scanInterval)
	{
		this.scanInterval = scanInterval;
		return this;
	}
	
	public WebServer setScanListener(Scanner.Listener scanListener)
	{
		this.scanListener = scanListener;
		return this;
	}
	
	public void start()
	{
		try
		{
			serverConnector.setPort(port);
			httpsConfig.setSecurePort(port);
			server.setConnectors(new Connector[]{serverConnector});
			webAppContext.setWar(webAppDir.getAbsolutePath());
			webAppContext.setContextPath(contextPath);
			server.setHandler(webAppContext);
			scanner.setScanInterval(scanInterval);
			scanner.addScanDir(webInfDir);
			scanner.addListener(scanListener);
			scanner.start();
			server.start();
			server.join();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args)
	{
		WebServer devServer = new WebServer();
		LinkedHashMap<String, String> argMap = parseArgMap(args);
		if(argMap.containsKey(PORT))
		{
			try
			{
				devServer.setPort(Integer.parseInt(argMap.get(PORT)));
			}
			catch(Exception e)
			{
				
			}
		}
		if(argMap.containsKey(CONTEXT_PATH))
		{
			devServer.setContextPath(argMap.get(CONTEXT_PATH));
		}
		if(argMap.containsKey(PROJECT_DIR))
		{
			devServer.setProjectDir(new File(argMap.get(PROJECT_DIR)));
		}
		if(argMap.containsKey(WEB_APP_DIR))
		{
			devServer.setWebAppDir(new File(argMap.get(WEB_APP_DIR)));
		}
		if(argMap.containsKey(WEB_INF_DIR))
		{
			devServer.setWebInfDir(new File(argMap.get(WEB_INF_DIR)));
		}
		if(argMap.containsKey(ENV))
		{
			System.setProperty(ENV, argMap.get(ENV));
		}
		devServer.start();
	}
	
	protected static LinkedHashMap<String, String> parseArgMap(String[] args)
	{
		LinkedHashMap<String, String> argMap = new LinkedHashMap<String, String>();
		for(String arg : Arrays.asList(args))
		{
			if(arg.startsWith("-"))
			{
				String name = null;
				String value = null;
				int index = arg.indexOf("=");
				if(index > -1)
				{
					name = arg.substring(1, index);
					value = arg.substring(index + 1);
				}
				argMap.put(name, value);
			}
		}
		return argMap;
	}
	
}
