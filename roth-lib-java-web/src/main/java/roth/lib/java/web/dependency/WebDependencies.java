package roth.lib.java.web.dependency;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import roth.lib.java.lang.Map;
import roth.lib.java.lang.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import roth.lib.java.Generic;
import roth.lib.java.json.JsonMapper;
import roth.lib.java.util.FileUtil;
import roth.lib.java.util.IoUtil;
import roth.lib.java.util.ResourceUtil;

public class WebDependencies
{
	protected static String ROTH_LIB_JS_ENV		= "roth-lib-js-env.js";
	protected static String GET_DEPENDENCIES	= "getDependencies";
	protected static String USER_DIR_DEFAULT	= "user.dir";
	protected static String WEB_APP_DEFAULT		= "src/main/webapp/";
	protected static String ENV_DEFAULT			= "env.js";
	protected static String PROJECT_DIR			= "projectDir";
	protected static String WEB_APP_DIR			= "webAppDir";
	protected static String ENV_FILE			= "envFile";
	protected static String ENGINE				= "nashorn";
	
	protected File projectDir;
	protected File webAppDir;
	protected File envFile;
	protected ScriptEngine engine;
	
	public WebDependencies()
	{
		init();
	}
	
	protected void init()
	{
		projectDir = new File(System.getProperty(USER_DIR_DEFAULT));
		webAppDir = new File(projectDir, WEB_APP_DEFAULT);
		envFile = new File(webAppDir, ENV_DEFAULT);
		engine = new ScriptEngineManager().getEngineByName(ENGINE);
	}
	
	public File getProjectDir()
	{
		return projectDir;
	}
	
	public File getWebAppDir()
	{
		return webAppDir;
	}
	
	public File getEnvFile()
	{
		return envFile;
	}
	
	public void setProjectDir(File projectDir)
	{
		this.projectDir = projectDir;
	}
	
	public void setWebAppDir(File webAppDir)
	{
		this.webAppDir = webAppDir;
	}
	
	public void setEnvFile(File envFile)
	{
		this.envFile = envFile;
	}
	
	public void download() throws Exception
	{
		if(envFile.exists())
		{
			JsonMapper mapper = new JsonMapper();
			mapper.setPrettyPrint(true);
			engine.eval(ResourceUtil.toString(ROTH_LIB_JS_ENV));
			engine.eval(FileUtil.toString(envFile));
			String json = (String) ((Invocable) engine).invokeFunction(GET_DEPENDENCIES);
			List<Dependency> dependencies = mapper.deserialize(json, new Generic<List<Dependency>>(){});
			download(dependencies);
		}
		else
		{
			System.err.println("env file does not exist");
		}
	}
	
	public void download(List<Dependency> dependencies)
	{
		for(Dependency dependency : dependencies)
		{
			String local = dependency.getLocal();
			if(local != null)
			{
				String source = dependency.getSource();
				if(source == null)
				{
					source = dependency.getExternal();
				}
				if(source != null)
				{
					download(local, source);
				}
			}
			download(dependency.getAssets());
		}
	}
	
	public void download(String local, String source)
	{
		System.out.println("Downloading " + local);
		File file = new File(webAppDir, local);
		file.getParentFile().mkdirs();
		try(FileOutputStream output = new FileOutputStream(file))
		{
			HttpURLConnection.setFollowRedirects(true);
			HttpURLConnection connection = (HttpURLConnection) new URL(source).openConnection();
			connection.setDoInput(true);
			connection.connect();
			if(connection.getResponseCode() < 400)
			{
				try(InputStream input = connection.getInputStream())
				{
					IoUtil.copy(input, output);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		WebDependencies staticDependencies = new WebDependencies();
		Map<String, String> argMap = parseArgMap(args);
		if(argMap.containsKey(PROJECT_DIR))
		{
			staticDependencies.setProjectDir(new File(argMap.get(PROJECT_DIR)));
		}
		if(argMap.containsKey(WEB_APP_DIR))
		{
			staticDependencies.setWebAppDir(new File(staticDependencies.getProjectDir(), argMap.get(WEB_APP_DIR)));
		}
		if(argMap.containsKey(ENV_FILE))
		{
			staticDependencies.setEnvFile(new File(staticDependencies.getWebAppDir(), argMap.get(ENV_FILE)));
		}
		staticDependencies.download();
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
