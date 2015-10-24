package roth.lib.java._static.config;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.map.MapperConfig;
import roth.lib.java.map.json.JsonMapper;
import roth.lib.java.util.FileUtil;

public class StaticConfig
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
	protected static Pattern TEXT_PATTERN			= Pattern.compile("^(\\S+_([A-Za-z]{2}))\\.");
	protected static Pattern VIEW_PATTERN			= Pattern.compile("^(\\S+)\\.");
	protected static Pattern REQUEST_PATTERN		= Pattern.compile("-request-(\\w+?)\\.");
	protected static Pattern RESPONSE_PATTERN		= Pattern.compile("-response-(\\w+?)\\.");
	
	protected JsonMapper mapper = new JsonMapper(new MapperConfig(true));
	protected File projectDir;
	protected File webAppDir;
	protected File textDir;
	protected File layoutDir;
	protected File pageDir;
	protected File serviceDir;
	protected File configFile;
	protected File devFile;
	
	public StaticConfig()
	{
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
		Config currentConfig = mapper.deserialize(FileUtil.toString(configFile), Config.class);
		Config config = new Config();
		config.setEndpointMap(currentConfig.getEndpointMap());
		if(textDir.exists() && textDir.isDirectory())
		{
			for(File file : textDir.listFiles())
			{
				if(file.isFile() && !file.isHidden())
				{
					Matcher textMatcher = TEXT_PATTERN.matcher(file.getName());
					if(textMatcher.find())
					{
						String name = textMatcher.group(1);
						String lang = textMatcher.group(2).toLowerCase();
						LinkedHashSet<String> texts = config.getTextMap().get(lang);
						if(texts == null)
						{
							texts = new LinkedHashSet<String>();
							config.getTextMap().put(lang, texts);
						}
						texts.add(name);
					}
				}
			}
		}
		if(layoutDir.exists() && layoutDir.isDirectory())
		{
			for(File file : layoutDir.listFiles())
			{
				if(file.isFile() && !file.isHidden())
				{
					Matcher viewMatcher = VIEW_PATTERN.matcher(file.getName());
					if(viewMatcher.find())
					{
						String name = viewMatcher.group(1);
						Layout layout = currentConfig.getLayoutMap().get(name);
						if(layout == null)
						{
							layout = new Layout();
						}
						config.getLayoutMap().put(name, layout);
					}
				}
			}
		}
		if(pageDir.exists() && pageDir.isDirectory())
		{
			for(File dir : pageDir.listFiles())
			{
				if(dir.isDirectory() && !dir.isHidden())
				{
					LinkedHashMap<String, Page> currentPageMap = new LinkedHashMap<String, Page>();
					Module module = config.getModuleMap().get(dir.getName());
					if(module == null)
					{
						Module currentModule = currentConfig.getModuleMap().get(dir.getName());
						if(currentModule != null)
						{
							currentPageMap.putAll(currentModule.getPageMap());
							currentModule.getPageMap().clear();
							module = currentModule;
						}
						else
						{
							module = new Module();
						}
						config.getModuleMap().put(dir.getName(), module);
					}
					for(File file : dir.listFiles())
					{
						if(file.isFile() && !file.isHidden())
						{
							Matcher viewMatcher = VIEW_PATTERN.matcher(file.getName());
							if(viewMatcher.find())
							{
								String name = viewMatcher.group(1);
								Page page = currentPageMap.get(name);
								if(page == null)
								{
									page = new Page();
								}
								module.getPageMap().put(name, page);
							}
						}
					}
				}
			}
		}
		try(FileOutputStream output = new FileOutputStream(configFile))
		{
			mapper.serialize(config, output);
		}
		
		if(serviceDir.exists() && serviceDir.isDirectory())
		{
			Dev dev = new Dev();
			for(File methodsDir : serviceDir.listFiles())
			{
				if(methodsDir.isDirectory() && !methodsDir.isHidden())
				{
					String service = methodsDir.getName();
					for(File scenariosDir : methodsDir.listFiles())
					{
						if(scenariosDir.isDirectory() && !scenariosDir.isHidden())
						{
							String method = scenariosDir.getName();
							LinkedHashMap<String, Scenario> methodMap = dev.getServiceMap().get(service);
							if(methodMap == null)
							{
								methodMap = new LinkedHashMap<String, Scenario>();
								dev.getServiceMap().put(service, methodMap);
							}
							Scenario scenario = methodMap.get(method);
							if(scenario == null)
							{
								scenario = new Scenario();
								methodMap.put(method, scenario);
							}
							for(File file : scenariosDir.listFiles())
							{
								if(file.isFile() && !file.isHidden())
								{
									Matcher requestMatcher = REQUEST_PATTERN.matcher(file.getName());
									Matcher responseMatcher = RESPONSE_PATTERN.matcher(file.getName());
									if(requestMatcher.find())
									{
										String request = requestMatcher.group(1);
										scenario.getRequests().add(request);
									}
									else if(responseMatcher.find())
									{
										String response = responseMatcher.group(1);
										scenario.getResponses().add(response);
									}
								}
							}
						}
					}
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
		StaticConfig staticConfig = new StaticConfig();
		LinkedHashMap<String, String> argMap = parseArgMap(args);
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
