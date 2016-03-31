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

public class WebCompile2 implements Characters
{
	protected static Pattern LANG_PATTERN			= Pattern.compile("^(?:\\S+_)?([A-Za-z]{2})\\.json");
	
	protected static String USER_DIR_DEFAULT		= "user.dir";
	protected static String WEB_APP_DEFAULT			= "src/main/webapp";
	protected static String DEV_DEFAULT				= "dev";
	protected static String APP_DEFAULT				= "app";
	protected static String TARGET_DEFAULT			= "app";
	protected static String SCRIPT_DEFAULT			= "script";
	protected static String TEXT_DEFAULT			= "text";
	protected static String MIXIN_DEFAULT			= "mixin";
	protected static String LAYOUT_DEFAULT			= "layout";
	protected static String PAGE_DEFAULT			= "page";
	protected static String COMPONENT_DEFAULT		= "component";
	protected static String SERVICE_DEFAULT			= "service";
	protected static String CONFIG_DEFAULT			= "config";
	
	protected static String PROJECT_DIR				= "projectDir";
	protected static String WEB_APP_DIR				= "webAppDir";
	protected static String DEV_DIR					= "devDir";
	protected static String APP_DIR					= "appDir";
	protected static String TARGET_DIR				= "targetDir";
	protected static String SCRIPT_DIR				= "scriptDir";
	protected static String TEXT_DIR				= "textDir";
	protected static String MIXIN_DIR				= "mixinDir";
	protected static String LAYOUT_DIR				= "layoutDir";
	protected static String PAGE_DIR				= "pageDir";
	protected static String COMPONENT_DIR			= "componentDir";
	protected static String MODULE					= "module";
	protected static String TEXT					= "text";
	protected static String MIXIN					= "mixin";
	protected static String LAYOUT					= "layout";
	protected static String PAGE					= "page";
	protected static String COMPONENT				= "component";
	protected static String SOURCE					= "source";
	protected static String OPEN_CLOSURE			= "(function(){";
	protected static String CLOSE_CLOSURE			= "})();";
	
	protected PrintWriter writer = null;
	protected Template template = new Template();
	protected File projectDir;
	protected File webAppDir;
	protected File devDir;
	protected File appDir;
	protected File targetDir;
	protected String scriptDir;
	protected String textDir;
	protected String mixinDir;
	protected String layoutDir;
	protected String pageDir;
	protected String componentDir;
	
	public WebCompile2()
	{
		this(true);
	}
	
	public WebCompile2(boolean debug)
	{
		init();
		if(debug)
		{
			writer = new PrintWriter(System.out, true);
		}
	}
	
	protected void init()
	{
		projectDir = new File(System.getProperty(USER_DIR_DEFAULT));
		webAppDir = new File(projectDir, WEB_APP_DEFAULT);
		devDir = new File(webAppDir, DEV_DEFAULT);
		appDir = new File(devDir, APP_DEFAULT);
		targetDir = new File(webAppDir, TARGET_DEFAULT);
		scriptDir = SCRIPT_DEFAULT;
		textDir = TEXT_DEFAULT;
		mixinDir = MIXIN_DEFAULT;
		layoutDir = LAYOUT_DEFAULT;
		pageDir = PAGE_DEFAULT;
		componentDir = COMPONENT_DEFAULT;
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
	
	public File getAppDir()
	{
		return appDir;
	}
	
	public File getTargetDir()
	{
		return targetDir;
	}
	
	public File getScriptDir(File moduleDir)
	{
		return new File(moduleDir, scriptDir);
	}
	
	public File getTextDir(File moduleDir)
	{
		return new File(moduleDir, textDir);
	}
	
	public File getMixinDir(File moduleDir)
	{
		return new File(moduleDir, mixinDir);
	}
	
	public File getLayoutDir(File moduleDir)
	{
		return new File(moduleDir, layoutDir);
	}
	
	public File getPageDir(File moduleDir)
	{
		return new File(moduleDir, pageDir);
	}
	
	public File getComponentDir(File moduleDir)
	{
		return new File(moduleDir, componentDir);
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
	
	public void setAppDir(File appDir)
	{
		this.appDir = appDir;
	}
	
	public void setTargetDir(File targetDir)
	{
		this.targetDir = targetDir;
	}
	
	public void setScriptDir(String scriptDir)
	{
		this.scriptDir = scriptDir;
	}
	
	public void setTextDir(String textDir)
	{
		this.textDir = textDir;
	}
	
	public void setMixinDir(String mixinDir)
	{
		this.mixinDir = mixinDir;
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
	
	protected String relative(File baseDir, File file)
	{
		return baseDir.toPath().relativize(file.toPath()).toString();
	}
	
	protected void println()
	{
		println("");
	}
	
	protected void println(String message)
	{
		if(writer != null)
		{
			writer.println(message);
		}
	}
	
	public void compile() throws Exception
	{
		for(File appDir : this.appDir.listFiles())
		{
			if(appDir.isDirectory())
			{
				compileApp(appDir);
			}
		}
	}
	
	protected void compileApp(File appDir) throws Exception
	{
		String app = appDir.getName();
		println("--------------------------------------------------------------------");
		println("Compiling app " + app);
		println("--------------------------------------------------------------------");
		println();
		for(File moduleDir : appDir.listFiles())
		{
			if(moduleDir.isDirectory())
			{
				File targetDir = new File(this.targetDir, app);
				targetDir.mkdirs();
				compileModule(moduleDir, targetDir);
			}
		}
		println();
	}
	
	protected void compileModule(File moduleDir, File targetDir) throws Exception
	{
		String module = moduleDir.getName();
		println("Compiling module " + module);
		try(PrintWriter writer = new PrintWriter(new File(targetDir, module + ".js")))
		{
			File scriptDir = getScriptDir(moduleDir);
			if(scriptDir.exists() && scriptDir.list().length > 0)
			{
				println("Scripts");
				compileScripts(writer, scriptDir, scriptDir);
			}
			
			File textDir = getTextDir(moduleDir);
			if(textDir.exists() && textDir.list().length > 0)
			{
				println("Texts");
				compileTexts(writer, textDir, textDir, module);
			}
			
			File mixinDir = getMixinDir(moduleDir);
			if(mixinDir.exists() && mixinDir.list().length > 0)
			{
				println("Mixins");
				compileViews(writer, mixinDir, mixinDir, module, MIXIN);
			}
			
			File layoutDir = getLayoutDir(moduleDir);
			if(layoutDir.exists() && layoutDir.list().length > 0)
			{
				println("Layouts");
				compileViews(writer, layoutDir, layoutDir, module, LAYOUT);
			}
			
			File pageDir = getPageDir(moduleDir);
			if(pageDir.exists() && pageDir.list().length > 0)
			{
				println("Pages");
				compileViews(writer, pageDir, pageDir, module, PAGE);
			}
			
			File componentDir = getComponentDir(moduleDir);
			if(componentDir.exists() && componentDir.list().length > 0)
			{
				println("Components");
				compileViews(writer, componentDir, componentDir, module, COMPONENT);
			}
		}
		println();
	}
	
	protected String filterScript(File file) throws Exception
	{
		StringBuilder script = new StringBuilder();
		//script.replaceAll("//.*?\\n", "\n").replaceAll("[\\r\\n\\t]", "");
		try(FileReader reader = new FileReader(file))
		{
			/*
			String line = null;
			while((line = reader.readLine()) != null)
			{
				script.append(line.replaceAll("//.*$", "").replaceAll("[\\t]", ""));
			}
			*/
			script.append(IoUtil.toString(reader).replaceAll("[\\r\\n\\t]", ""));
		}
		return script.toString();
	}
	
	protected void compileScripts(PrintWriter writer, File dir, File baseDir) throws Exception
	{
		for(File file : dir.listFiles())
		{
			if(file.isDirectory())
			{
				compileScripts(writer, file, baseDir);
			}
			else if(file.isFile() && !file.isHidden() && file.getName().endsWith(".js"))
			{
				println("- " + relative(baseDir, file));
				writer.println("// " + file.getName());
				String script = filterScript(file);
				writer.println(script);
			}
		}
	}
	
	protected void compileTexts(PrintWriter writer, File dir, File baseDir, String module) throws Exception
	{
		for(File file : dir.listFiles())
		{
			if(file.isDirectory())
			{
				compileTexts(writer, file, baseDir, module);
			}
			else if(file.isFile() && !file.isHidden() && file.getName().endsWith(".json"))
			{
				Matcher matcher = LANG_PATTERN.matcher(file.getName());
				if(matcher.find())
				{
					println("- " + relative(baseDir, file));
					String lang = matcher.group(1);
					String text = FileUtil.toString(file).replaceAll("[\\r\\n\\t]", "");
					writer.print(MODULE);
					writer.print(DOT);
					writer.print(TEXT);
					writer.print(DOT);
					writer.print(lang);
					writer.print(SPACE);
					writer.print(EQUAL);
					writer.print(SPACE);
					writer.print(text);
					writer.print(SEMI_COLON);
					writer.println();
				}
			}
		}
	}
	
	protected void compileViews(PrintWriter writer, File dir, File baseDir, String module, String type) throws Exception
	{
		for(File file : dir.listFiles())
		{
			if(file.isDirectory())
			{
				compileViews(writer, file, baseDir, module, type);
			}
			else if(file.isFile() && !file.isHidden() && file.getName().endsWith(".js"))
			{
				String relative = relative(baseDir, file);
				File htmlFile = new File(file.getParentFile(), file.getName().replaceFirst("\\.js$", ".html"));
				if(htmlFile.exists() || type.equals(MIXIN))
				{
					String name = relative.replaceFirst("\\.js$", "").replaceAll("[^a-zA-Z_0-9]", "_");
					StringBuilder namespace = new StringBuilder();
					namespace.append(MODULE);
					namespace.append(DOT);
					namespace.append(type);
					namespace.append(DOT);
					namespace.append(name);
					
					println("- " + relative);
					String viewScript = filterScript(file);
					writer.print(namespace);
					writer.print(SPACE);
					writer.print(EQUAL);
					writer.print(SPACE);
					writer.print(OPEN_CLOSURE);
					writer.print(viewScript);
					writer.print(CLOSE_CLOSURE);
					writer.println();
					
					if(htmlFile.exists() && !type.equals(MIXIN))
					{
						String source = template.parse(FileUtil.toString(htmlFile)).replaceAll("[\\r\\n\\t]", "").replaceAll("\\\\n", "\\\\\\\\n").replaceAll("\\\\\\\"", "\\\\\\\\\"").replaceAll("\"", "\\\\\"");
						writer.print(namespace);
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
	}
	
	public static void main(String[] args) throws Exception
	{
		WebCompile2 webCompile = new WebCompile2();
		Map<String, String> argMap = parseArgMap(args);
		if(argMap.containsKey(PROJECT_DIR))
		{
			webCompile.setProjectDir(new File(argMap.get(PROJECT_DIR)));
		}
		if(argMap.containsKey(WEB_APP_DIR))
		{
			webCompile.setWebAppDir(new File(webCompile.getProjectDir(), argMap.get(WEB_APP_DIR)));
		}
		if(argMap.containsKey(DEV_DIR))
		{
			webCompile.setDevDir(new File(webCompile.getWebAppDir(), argMap.get(DEV_DIR)));
		}
		if(argMap.containsKey(APP_DIR))
		{
			webCompile.setAppDir(new File(webCompile.getDevDir(), argMap.get(APP_DIR)));
		}
		if(argMap.containsKey(TARGET_DIR))
		{
			webCompile.setTargetDir(new File(webCompile.getWebAppDir(), argMap.get(TARGET_DIR)));
		}
		if(argMap.containsKey(SCRIPT_DIR))
		{
			webCompile.setScriptDir(argMap.get(SCRIPT_DIR));
		}
		if(argMap.containsKey(TEXT_DIR))
		{
			webCompile.setTextDir(argMap.get(TEXT_DIR));
		}
		if(argMap.containsKey(MIXIN_DIR))
		{
			webCompile.setMixinDir(argMap.get(MIXIN_DIR));
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
