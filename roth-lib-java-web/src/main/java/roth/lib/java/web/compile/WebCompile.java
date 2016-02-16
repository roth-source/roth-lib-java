package roth.lib.java.web.compile;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

import roth.lib.java.json.JsonMapper;
import roth.lib.java.lang.Map;
import roth.lib.java.mapper.Mapper;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.web.config.WebConfig;

public class WebCompile
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
	
	protected MapperConfig config = new MapperConfig().setSerializeNulls(true);
	protected Mapper mapper = new JsonMapper(config).setPrettyPrint(true);
	protected File projectDir;
	protected File webAppDir;
	protected File textDir;
	protected File layoutDir;
	protected File pageDir;
	protected File serviceDir;
	protected File configFile;
	protected File devFile;
	
	public WebCompile()
	{
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
