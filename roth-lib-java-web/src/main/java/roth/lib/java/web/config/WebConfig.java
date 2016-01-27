package roth.lib.java.web.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.json.JsonMapper;
import roth.lib.java.lang.Map;
import roth.lib.java.lang.Set;

public class WebConfig
{
	protected static String USER_DIR_DEFAULT		= "user.dir";
	protected static String WEB_APP_DEFAULT			= "src/main/webapp/";
	protected static String TEXT_DEFAULT			= "text/";
	protected static String LAYOUT_DEFAULT			= "view/layout/";
	protected static String PAGE_DEFAULT			= "view/page/";
	protected static String SERVICE_DEFAULT			= "dev/service/";
	protected static String CONFIG_DEFAULT			= "config.json";
	protected static String DEV_DEFAULT				= "dev/dev.json";
	protected static String PROJECT_DIR				= "projectDir";
	protected static String WEB_APP_DIR				= "webAppDir";
	protected static String TEXT_DIR				= "textDir";
	protected static String LAYOUT_DIR				= "layoutDir";
	protected static String PAGE_DIR				= "pageDir";
	protected static String SERVICE_DIR				= "serviceDir";
	protected static String CONFIG_FILE				= "configFile";
	protected static String DEV_FILE				= "devFile";
	protected static String LOCAL_ENV				= "local";
	protected static String LOCAL_DEFAULT			= "localhost:8443";
	protected static Pattern LANG_PATTERN			= Pattern.compile("^\\S+_([A-Za-z]{2})\\.");
	protected static Pattern VIEW_PATTERN			= Pattern.compile("^(\\S+)\\.");
	protected static Pattern REQUEST_PATTERN		= Pattern.compile("-request-(\\w+?)\\.");
	protected static Pattern RESPONSE_PATTERN		= Pattern.compile("-response-(\\w+?)\\.");
	
	protected JsonMapper mapper = new JsonMapper();
	protected File projectDir;
	protected File webAppDir;
	protected File textDir;
	protected File layoutDir;
	protected File pageDir;
	protected File serviceDir;
	protected File configFile;
	protected File devFile;
	
	public WebConfig()
	{
		mapper.setPrettyPrint(true);
		init();
	}
	
	protected void init()
	{
		projectDir = new File(System.getProperty(USER_DIR_DEFAULT));
		webAppDir = new File(projectDir, WEB_APP_DEFAULT);
		textDir = new File(webAppDir, TEXT_DEFAULT);
		layoutDir = new File(webAppDir, LAYOUT_DEFAULT);
		pageDir = new File(webAppDir, PAGE_DEFAULT);
		serviceDir = new File(webAppDir, SERVICE_DEFAULT);
		configFile = new File(webAppDir, CONFIG_DEFAULT);
		devFile = new File(webAppDir, DEV_DEFAULT);
	}
	
	public File getProjectDir()
	{
		return projectDir;
	}
	
	public File getWebAppDir()
	{
		return webAppDir;
	}
	
	public File getTextDir()
	{
		return textDir;
	}
	
	public File getLayoutDir()
	{
		return layoutDir;
	}
	
	public File getPageDir()
	{
		return pageDir;
	}
	
	public File getServiceDir()
	{
		return serviceDir;
	}
	
	public File getConfigFile()
	{
		return configFile;
	}
	
	public File getDevFile()
	{
		return devFile;
	}
	
	public void setProjectDir(File projectDir)
	{
		this.projectDir = projectDir;
	}
	
	public void setWebAppDir(File webAppDir)
	{
		this.webAppDir = webAppDir;
	}
	
	public void setTextDir(File textDir)
	{
		this.textDir = textDir;
	}
	
	public void setLayoutDir(File layoutDir)
	{
		this.layoutDir = layoutDir;
	}
	
	public void setPageDir(File pageDir)
	{
		this.pageDir = pageDir;
	}
	
	public void setServiceDir(File serviceDir)
	{
		this.serviceDir = serviceDir;
	}
	
	public void setConfigFile(File configFile)
	{
		this.configFile = configFile;
	}
	
	public void setDevFile(File devFile)
	{
		this.devFile = devFile;
	}
	
	public void sync() throws Exception
	{
		{
			Config config = new Config();
			if(configFile.exists())
			{
				try(FileInputStream input = new FileInputStream(configFile))
				{
					config = mapper.deserialize(input, Config.class);
				}
			}
			
			System.out.println("Syncing langs");
			if(textDir.exists() && textDir.isDirectory())
			{
				Set<String> langs = new Set<String>();
				for(File file : textDir.listFiles())
				{
					if(file.isFile() && !file.isHidden())
					{
						Matcher langMatcher = LANG_PATTERN.matcher(file.getName());
						if(langMatcher.find())
						{
							String lang = langMatcher.group(1).toLowerCase();
							langs.add(lang);
						}
					}
				}
				config.setLangs(langs);
			}
			
			System.out.println("Syncing endpoint map");
			Map<String, Set<String>> endpointMap = config.getEndpointMap();
			if(endpointMap == null)
			{
				endpointMap = new Map<String, Set<String>>();
			}
			Set<String> localEndpoints = endpointMap.get(LOCAL_ENV);
			if(localEndpoints == null || localEndpoints.isEmpty())
			{
				localEndpoints = new Set<String>();
				localEndpoints.add(LOCAL_DEFAULT);
				endpointMap.put(LOCAL_ENV, localEndpoints);
			}
			config.setEndpointMap(endpointMap);
			
			System.out.println("Syncing layout map");
			if(layoutDir.exists() && layoutDir.isDirectory())
			{
				Set<String> layouts = new Set<String>();
				for(File file : layoutDir.listFiles())
				{
					if(file.isFile() && !file.isHidden())
					{
						Matcher viewMatcher = VIEW_PATTERN.matcher(file.getName());
						if(viewMatcher.find())
						{
							String layoutName = viewMatcher.group(1);
							layouts.add(layoutName);
							Layout layout = config.getLayoutMap().get(layoutName);
							if(layout == null)
							{
								layout = new Layout();
							}
							config.getLayoutMap().put(layoutName, layout);
						}
					}
				}
				Iterator<Entry<String, Layout>> iterator = config.getLayoutMap().entrySet().iterator();
				while(iterator.hasNext())
				{
					if(!layouts.contains(iterator.next().getKey()))
					{
						iterator.remove();
					}
				}
			}
			
			System.out.println("Syncing module map");
			if(pageDir.exists() && pageDir.isDirectory())
			{
				Set<String> modules = new Set<String>();
				for(File dir : pageDir.listFiles())
				{
					if(dir.isDirectory() && !dir.isHidden())
					{
						String moduleName = dir.getName();
						Module module = config.getModuleMap().get(moduleName);
						Set<String> pages = new Set<String>();
						for(File file : dir.listFiles())
						{
							if(file.isFile() && !file.isHidden())
							{
								modules.add(moduleName);
								if(module == null)
								{
									module = new Module();
									config.getModuleMap().put(moduleName, module);
								}
								Matcher viewMatcher = VIEW_PATTERN.matcher(file.getName());
								if(viewMatcher.find())
								{
									String pageName = viewMatcher.group(1);
									pages.add(pageName);
									Page page = module.getPageMap().get(pageName);
									if(page == null)
									{
										page = new Page();
									}
									module.getPageMap().put(pageName, page);
								}
							}
						}
						if(module != null)
						{
							Iterator<Entry<String, Page>> iterator = module.getPageMap().entrySet().iterator();
							while(iterator.hasNext())
							{
								if(!pages.contains(iterator.next().getKey()))
								{
									iterator.remove();
								}
							}
						}
					}
				}
				Iterator<Entry<String, Module>> iterator = config.getModuleMap().entrySet().iterator();
				while(iterator.hasNext())
				{
					if(!modules.contains(iterator.next().getKey()))
					{
						iterator.remove();
					}
				}
			}
			try(FileOutputStream output = new FileOutputStream(configFile))
			{
				mapper.serialize(config, output);
			}
		}
		
		if(serviceDir.exists() && serviceDir.isDirectory())
		{
			Dev dev = new Dev();
			if(devFile.exists())
			{
				try(FileInputStream input = new FileInputStream(devFile))
				{
					dev = mapper.deserialize(input, Dev.class);
				}
			}
			
			System.out.println("Syncing services");
			Set<String> services = new Set<String>();
			for(File methodsDir : serviceDir.listFiles())
			{
				if(methodsDir.isDirectory() && !methodsDir.isHidden())
				{
					String service = methodsDir.getName();
					services.add(service);
					Map<String, Scenario> methodMap = dev.getServiceMap().get(service);
					Set<String> methods = new Set<String>();
					for(File scenariosDir : methodsDir.listFiles())
					{
						if(scenariosDir.isDirectory() && !scenariosDir.isHidden())
						{
							if(methodMap == null)
							{
								methodMap = new Map<String, Scenario>();
								dev.getServiceMap().put(service, methodMap);
							}
							String method = scenariosDir.getName();
							methods.add(method);
							Scenario scenario = methodMap.get(method);
							if(scenario == null)
							{
								scenario = new Scenario();
								methodMap.put(method, scenario);
							}
							Set<String> requests = new Set<String>();
							Set<String> responses = new Set<String>();
							for(File file : scenariosDir.listFiles())
							{
								if(file.isFile() && !file.isHidden())
								{
									if(file.getName().startsWith(method))
									{
										Matcher requestMatcher = REQUEST_PATTERN.matcher(file.getName());
										Matcher responseMatcher = RESPONSE_PATTERN.matcher(file.getName());
										if(requestMatcher.find())
										{
											String request = requestMatcher.group(1);
											scenario.getRequests().add(request);
											requests.add(request);
										}
										else if(responseMatcher.find())
										{
											String response = responseMatcher.group(1);
											scenario.getResponses().add(response);
											responses.add(response);
										}
									}
								}
							}
							Iterator<String> iterator = null;
							iterator = scenario.getRequests().iterator();
							while(iterator.hasNext())
							{
								if(!requests.contains(iterator.next()))
								{
									iterator.remove();
								}
							}
							iterator = scenario.getResponses().iterator();
							while(iterator.hasNext())
							{
								if(!responses.contains(iterator.next()))
								{
									iterator.remove();
								}
							}
						}
					}
					if(methodMap != null)
					{
						Iterator<Entry<String, Scenario>> iterator = methodMap.entrySet().iterator();
						while(iterator.hasNext())
						{
							if(!methods.contains(iterator.next().getKey()))
							{
								iterator.remove();
							}
						}
					}
				}
			}
			Iterator<Entry<String, Map<String, Scenario>>> iterator = dev.getServiceMap().entrySet().iterator();
			while(iterator.hasNext())
			{
				if(!services.contains(iterator.next().getKey()))
				{
					iterator.remove();
				}
			}
			try(FileOutputStream output = new FileOutputStream(devFile))
			{
				mapper.serialize(dev, output);
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		WebConfig staticConfig = new WebConfig();
		Map<String, String> argMap = parseArgMap(args);
		if(argMap.containsKey(PROJECT_DIR))
		{
			staticConfig.setProjectDir(new File(argMap.get(PROJECT_DIR)));
		}
		if(argMap.containsKey(WEB_APP_DIR))
		{
			staticConfig.setWebAppDir(new File(staticConfig.getProjectDir(), argMap.get(WEB_APP_DIR)));
		}
		if(argMap.containsKey(TEXT_DIR))
		{
			staticConfig.setTextDir(new File(staticConfig.getWebAppDir(), argMap.get(TEXT_DIR)));
		}
		if(argMap.containsKey(LAYOUT_DIR))
		{
			staticConfig.setLayoutDir(new File(staticConfig.getWebAppDir(), argMap.get(LAYOUT_DIR)));
		}
		if(argMap.containsKey(PAGE_DIR))
		{
			staticConfig.setPageDir(new File(staticConfig.getWebAppDir(), argMap.get(PAGE_DIR)));
		}
		if(argMap.containsKey(SERVICE_DIR))
		{
			staticConfig.setServiceDir(new File(staticConfig.getWebAppDir(), argMap.get(SERVICE_DIR)));
		}
		if(argMap.containsKey(CONFIG_FILE))
		{
			staticConfig.setConfigFile(new File(staticConfig.getWebAppDir(), argMap.get(CONFIG_FILE)));
		}
		if(argMap.containsKey(DEV_FILE))
		{
			staticConfig.setDevFile(new File(staticConfig.getWebAppDir(), argMap.get(DEV_FILE)));
		}
		staticConfig.sync();
	}
	
	protected static Map<String, String> parseArgMap(String[] args)
	{
		Map<String, String> argMap = new Map<String, String>();
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
