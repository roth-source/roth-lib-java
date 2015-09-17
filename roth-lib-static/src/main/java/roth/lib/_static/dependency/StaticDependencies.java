package roth.lib._static.dependency;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import roth.lib.map.json.JsonMapper;
import roth.lib.util.FileUtil;
import roth.lib.util.IoUtil;
import roth.lib.util.ResourceUtil;

public class StaticDependencies
{
	protected static String USER_DIR		= "user.dir";
	protected static String WEB_APP			= "src/main/webapp/";
	protected static String ENV				= "script/env.js";
	protected static String PROJECT_DIR		= "projectDir";
	protected static String WEB_APP_DIR		= "webAppDir";
	protected static String ENV_FILE		= "envFile";
	protected static String ENGINE			= "nashorn";
	
	protected File projectDir;
	protected File webAppDir;
	protected File envFile;
	protected ScriptEngine engine;
	
	public StaticDependencies()
	{
		init();
	}
	
	protected void init()
	{
		projectDir = new File(System.getProperty(USER_DIR));
		webAppDir = new File(projectDir, WEB_APP);
		envFile = new File(webAppDir, ENV);
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
			engine.eval(ResourceUtil.toString("dependency.js"));
			engine.eval(FileUtil.toString(envFile));
			LinkedHashMap<String, Object> styleMap = mapper.deserialize((String) ((Invocable) engine).invokeFunction("getStyleMap"));
			LinkedHashMap<String, Object> assetMap = mapper.deserialize((String) ((Invocable) engine).invokeFunction("getAssetMap"));
			LinkedHashMap<String, Object> scriptMap = mapper.deserialize((String) ((Invocable) engine).invokeFunction("getScriptMap"));
			downlaod(styleMap);
			downlaod(assetMap);
			downlaod(scriptMap);
		}
		else
		{
			System.err.println("env file does not exist");
		}
	}
	
	public void downlaod(LinkedHashMap<String, Object> dependencyMap)
	{
		for(Entry<String, Object> dependencyEntry : dependencyMap.entrySet())
		{
			String local = dependencyEntry.getKey();
			Object external = dependencyEntry.getValue();
			if(external != null && external instanceof String)
			{
				downlaod(local, (String) external);
			}
		}
	}
	
	public void downlaod(String local, String external)
	{
		System.out.println("Downloading " + local);
		File file = new File(webAppDir, local);
		file.getParentFile().mkdirs();
		try(FileOutputStream output = new FileOutputStream(file))
		{
			HttpURLConnection.setFollowRedirects(true);
			HttpURLConnection connection = (HttpURLConnection) new URL(external).openConnection();
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
		StaticDependencies staticDependencies = new StaticDependencies();
		LinkedHashMap<String, String> argMap = parseArgMap(args);
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
