package roth.lib.java.web.compile;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.Characters;
import roth.lib.java.lang.Map;
import roth.lib.java.template.Template;
import roth.lib.java.util.FileUtil;
import roth.lib.java.util.IoUtil;

public class WebCompile implements Characters
{
	protected static Pattern LANG_PATTERN			= Pattern.compile("^\\S+_([A-Za-z]{2})\\.json");
	
	protected static String USER_DIR_DEFAULT		= "user.dir";
	protected static String WEB_APP_DEFAULT			= "src/main/webapp/";
	protected static String DEV_DEFAULT				= "dev/";
	protected static String SCRIPT_DEFAULT			= "script/";
	protected static String TEXT_DEFAULT			= "text/";
	protected static String LAYOUT_DEFAULT			= "view/layout/";
	protected static String PAGE_DEFAULT			= "view/page/";
	protected static String COMPONENT_DEFAULT		= "view/component/";
	protected static String SERVICE_DEFAULT			= "service";
	protected static String CONFIG_DEFAULT			= "config";
	protected static String WEB_DEFAULT				= "web";
	
	protected static String PROJECT_DIR				= "projectDir";
	protected static String WEB_APP_DIR				= "webAppDir";
	protected static String SCRIPT_DIR				= "scriptDir";
	protected static String TEXT_DIR				= "textDir";
	protected static String LAYOUT_DIR				= "layoutDir";
	protected static String PAGE_DIR				= "pageDir";
	protected static String COMPONENT_DIR			= "componentDir";
	protected static String SERVICE					= "service";
	protected static String CONFIG					= "config";
	protected static String WEB						= "web";
	protected static String REGISTER				= "register";
	protected static String TEXT					= "text";
	protected static String LAYOUT					= "layout";
	protected static String PAGE					= "page";
	protected static String COMPONENT				= "component";
	protected static String SOURCE					= "source";
	
	protected Template template = new Template();
	protected File projectDir;
	protected File webAppDir;
	protected File devDir;
	protected String scriptDir;
	protected String textDir;
	protected String layoutDir;
	protected String pageDir;
	protected String componentDir;
	protected String service;
	protected String config;
	protected String web;
	
	public WebCompile()
	{
		init();
	}
	
	protected void init()
	{
		projectDir = new File(System.getProperty(USER_DIR_DEFAULT));
		webAppDir = new File(projectDir, WEB_APP_DEFAULT);
		devDir = new File(webAppDir, DEV_DEFAULT);
		scriptDir = SCRIPT_DEFAULT;
		textDir = TEXT_DEFAULT;
		layoutDir = LAYOUT_DEFAULT;
		pageDir = PAGE_DEFAULT;
		componentDir = COMPONENT_DEFAULT;
		service = SERVICE_DEFAULT;
		config = CONFIG_DEFAULT;
		web = WEB_DEFAULT;
	}
	
	public File getProjectDir()
	{
		return projectDir;
	}
	
	public File getWebAppDir()
	{
		return webAppDir;
	}
	
	public File getDevDir()
	{
		return devDir;
	}
	
	public File getScriptDir(File appDir)
	{
		return new File(appDir, scriptDir);
	}
	
	public File getTextDir(File appDir)
	{
		return new File(appDir, textDir);
	}
	
	public File getLayoutDir(File appDir)
	{
		return new File(appDir, layoutDir);
	}
	
	public File getPageDir(File appDir)
	{
		return new File(appDir, pageDir);
	}
	
	public File getComponentDir(File appDir)
	{
		return new File(appDir, componentDir);
	}
	
	public String getService()
	{
		return service;
	}
	
	public String getConfig()
	{
		return config;
	}
	
	public String getWeb()
	{
		return web;
	}
	
	public void setProjectDir(File projectDir)
	{
		this.projectDir = projectDir;
	}
	
	public void setWebAppDir(File webAppDir)
	{
		this.webAppDir = webAppDir;
	}
	
	public void setDevDir(File devDir)
	{
		this.devDir = devDir;
	}
	
	public void setScriptDir(String scriptDir)
	{
		this.scriptDir = scriptDir;
	}
	
	public void setTextDir(String textDir)
	{
		this.textDir = textDir;
	}
	
	public void setLayoutDir(String layoutDir)
	{
		this.layoutDir = layoutDir;
	}
	
	public void setPageDir(String pageDir)
	{
		this.pageDir = pageDir;
	}
	
	public void setComponentDir(String componentDir)
	{
		this.componentDir = componentDir;
	}
	
	public void setService(String service)
	{
		this.service = service;
	}
	
	public void setConfig(String config)
	{
		this.config = config;
	}
	
	public void setWeb(String web)
	{
		this.web = web;
	}
	
	protected String relative(File baseDir, File file)
	{
		return baseDir.toPath().relativize(file.toPath()).toString();
	}
	
	public void compile() throws Exception
	{
		for(File dir : devDir.listFiles())
		{
			if(dir.isDirectory() && !dir.getName().equals(service) && !dir.getName().equals(config))
			{
				compileApp(dir);
			}
		}
	}
	
	public void compileApp(File appDir) throws Exception
	{
		String app = appDir.getName() + ".js";
		System.out.println("Compiling " + app);
		System.out.println();
		try(PrintWriter writer = new PrintWriter(new File(webAppDir, app)))
		{
			File scriptDir = getScriptDir(appDir);
			System.out.println("Scripts - " + relative(webAppDir, scriptDir));
			compileScripts(writer, scriptDir, scriptDir);
			System.out.println();
			
			File textDir = getTextDir(appDir);
			System.out.println("Texts - " + relative(webAppDir, textDir));
			compileTexts(writer, textDir, textDir);
			System.out.println();
			
			File layoutDir = getLayoutDir(appDir);
			System.out.println("Layouts - " + relative(webAppDir, layoutDir));
			compileViews(writer, layoutDir, layoutDir, LAYOUT);
			System.out.println();
			
			File pageDir = getPageDir(appDir);
			System.out.println("Pages - " + relative(webAppDir, pageDir));
			compileViews(writer, pageDir, pageDir, PAGE);
			System.out.println();
			
			File componentDir = getComponentDir(appDir);
			System.out.println("Components - " + relative(webAppDir, componentDir));
			compileViews(writer, componentDir, componentDir, COMPONENT);
			System.out.println();
		}
	}
	
	public void compileScripts(PrintWriter writer, File dir, File baseDir) throws Exception
	{
		for(File file : dir.listFiles())
		{
			if(file.isDirectory())
			{
				compileScripts(writer, file, baseDir);
			}
			else if(file.isFile() && !file.isHidden() && file.getName().endsWith(".js"))
			{
				try(FileReader reader = new FileReader(file))
				{
					System.out.println("- " + relative(baseDir, file));
					IoUtil.copy(reader, writer);
				}
			}
		}
	}
	
	public void compileTexts(PrintWriter writer, File dir, File baseDir) throws Exception
	{
		for(File file : dir.listFiles())
		{
			if(file.isDirectory())
			{
				compileTexts(writer, file, baseDir);
			}
			else if(file.isFile() && !file.isHidden() && file.getName().endsWith(".json"))
			{
				Matcher matcher = LANG_PATTERN.matcher(file.getName());
				if(matcher.find())
				{
					System.out.println("- " + relative(baseDir, file));
					String lang = matcher.group(1);
					String text = FileUtil.toString(file).replaceAll("[\\r\\n\\t]", "").replaceAll("\\\\\\\"", "\\\\\\\\\"").replaceAll("\"", "\\\\\"");
					writer.println();
					writer.print(web);
					writer.print(DOT);
					writer.print(REGISTER);
					writer.print(DOT);
					writer.print(TEXT);
					writer.print(DOT);
					writer.print(lang);
					writer.print(SPACE);
					writer.print(EQUAL);
					writer.print(SPACE);
					writer.print(QUOTE);
					writer.print(text);
					writer.print(QUOTE);
					writer.print(SEMI_COLON);
					writer.println();
				}
			}
		}
	}
	
	public void compileViews(PrintWriter writer, File dir, File baseDir, String type) throws Exception
	{
		for(File file : dir.listFiles())
		{
			if(file.isDirectory())
			{
				compileViews(writer, file, baseDir, type);
			}
			else if(file.isFile() && !file.isHidden() && file.getName().endsWith(".js"))
			{
				String relative = relative(baseDir, file);
				File htmlFile = new File(file.getParentFile(), file.getName().replaceFirst("\\.js$", ".html"));
				if(htmlFile.exists())
				{
					System.out.println("- " + relative);
					try(FileReader reader = new FileReader(file))
					{
						IoUtil.copy(reader, writer);
					}
					String name = relative.replaceFirst("\\.js$", "").replaceAll("[^a-zA-Z_0-9]", "_");
					String source = template.parse(FileUtil.toString(htmlFile)).replaceAll("[\\r\\n\\t]", "").replaceAll("\\\\\\\"", "\\\\\\\\\"").replaceAll("\"", "\\\\\"");
					writer.println();
					writer.print(web);
					writer.print(DOT);
					writer.print(REGISTER);
					writer.print(DOT);
					writer.print(type);
					writer.print(DOT);
					writer.print(name);
					writer.print(DOT);
					writer.print(SOURCE);
					writer.print(SPACE);
					writer.print(EQUAL);
					writer.print(SPACE);
					writer.print(QUOTE);
					writer.print(source);
					writer.print(QUOTE);
					writer.print(SEMI_COLON);
					writer.println();
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		WebCompile webCompile = new WebCompile();
		Map<String, String> argMap = parseArgMap(args);
		if(argMap.containsKey(PROJECT_DIR))
		{
			webCompile.setProjectDir(new File(argMap.get(PROJECT_DIR)));
		}
		if(argMap.containsKey(WEB_APP_DIR))
		{
			webCompile.setWebAppDir(new File(webCompile.getProjectDir(), argMap.get(WEB_APP_DIR)));
		}
		if(argMap.containsKey(SCRIPT_DIR))
		{
			webCompile.setScriptDir(argMap.get(SCRIPT_DIR));
		}
		if(argMap.containsKey(TEXT_DIR))
		{
			webCompile.setTextDir(argMap.get(TEXT_DIR));
		}
		if(argMap.containsKey(LAYOUT_DIR))
		{
			webCompile.setLayoutDir(argMap.get(LAYOUT_DIR));
		}
		if(argMap.containsKey(PAGE_DIR))
		{
			webCompile.setPageDir(argMap.get(PAGE_DIR));
		}
		if(argMap.containsKey(COMPONENT_DIR))
		{
			webCompile.setComponentDir(argMap.get(COMPONENT_DIR));
		}
		if(argMap.containsKey(SERVICE))
		{
			webCompile.setService(argMap.get(SERVICE));
		}
		if(argMap.containsKey(CONFIG))
		{
			webCompile.setConfig(argMap.get(CONFIG));
		}
		if(argMap.containsKey(WEB))
		{
			webCompile.setWeb(argMap.get(WEB));
		}
		webCompile.compile();
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
