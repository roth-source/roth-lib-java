package roth.lib.java.web.translate;

import java.io.File;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.Characters;
import roth.lib.java.json.JsonMapper;
import roth.lib.java.lang.List;
import roth.lib.java.lang.Map;
import roth.lib.java.lang.Set;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.util.FileUtil;

public class WebTranslate implements Characters
{
	protected static String MODULE					= "module";
	protected static String TYPE					= "type";
	protected static String NAME					= "name";
	protected static String FIELD					= "field";
	protected static String VALUE					= "value";
	protected static Pattern LANG_PATTERN			= Pattern.compile("^(?:\\S+_)?([A-Za-z]{2})\\.json");
	protected static Pattern DATA_TEXT_PATTERN		= Pattern.compile("data-text(?:-attr)?(?:\\s+)?=(?:\\s+)?['\"](?:\\w+:)?(?<" + VALUE + ">[\\w\\.]+?)['\"]");
	protected static Pattern FULL_TEXT_PATTERN		= Pattern.compile("text\\.(?<" + VALUE + ">[\\w]+\\.[\\w]+\\.[\\w\\.]+)(?:\\W|$)");
	protected static Pattern VALUE_PATTERN			= Pattern.compile("(?<" + MODULE + ">\\w+)\\.(?<" + TYPE + ">\\w+)\\.(?<" + NAME + ">\\w+)(?:\\.(?<" + FIELD + ">\\w+))?");
	
	protected static String USER_DIR_DEFAULT		= "user.dir";
	protected static String WEB_APP_DEFAULT			= "src/main/webapp";
	protected static String DEV_DEFAULT				= "dev";
	protected static String APP_DEFAULT				= "app";
	protected static String TEXT_DEFAULT			= "text";
	protected static String MIXIN_DEFAULT			= "mixin";
	protected static String LAYOUT_DEFAULT			= "layout";
	protected static String PAGE_DEFAULT			= "page";
	protected static String COMPONENT_DEFAULT		= "component";
	
	protected static String PROJECT_DIR				= "projectDir";
	protected static String WEB_APP_DIR				= "webAppDir";
	protected static String DEV_DIR					= "devDir";
	protected static String APP_DIR					= "appDir";
	protected static String TEXT_DIR				= "textDir";
	protected static String MIXIN_DIR				= "mixinDir";
	protected static String LAYOUT_DIR				= "layoutDir";
	protected static String PAGE_DIR				= "pageDir";
	protected static String COMPONENT_DIR			= "componentDir";
	
	protected MapperConfig mapperConfig = new MapperConfig().setSerializeEmptyArray(false).setSerializeEmptyMap(false).setSerializeNulls(false);
	protected JsonMapper jsonMapper = new JsonMapper();
	protected File projectDir;
	protected File webAppDir;
	protected File devDir;
	protected File appDir;
	protected String textDir;
	protected String mixinDir;
	protected String layoutDir;
	protected String pageDir;
	protected String componentDir;
	
	public WebTranslate()
	{
		init();
	}
	
	protected void init()
	{
		mapperConfig.setSerializeEmptyMap(false);
		jsonMapper.setPrettyPrint(true).setMapperConfig(mapperConfig);
		projectDir = new File(System.getProperty(USER_DIR_DEFAULT));
		webAppDir = new File(projectDir, WEB_APP_DEFAULT);
		devDir = new File(webAppDir, DEV_DEFAULT);
		appDir = new File(devDir, APP_DEFAULT);
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
	
	public void translate() throws Exception
	{
		for(File appDir : this.appDir.listFiles())
		{
			if(appDir.isDirectory())
			{
				translateApp(appDir);
			}
		}
	}
	
	protected void translateApp(File appDir) throws Exception
	{
		String app = appDir.getName();
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Checking translations of app " + app);
		System.out.println("--------------------------------------------------------------------");
		System.out.println();
		Map<String, Text> textMap = getTextMap(appDir);
		Map<String, List<Translation>> translationsMap = getTranslationsMap(appDir);
		for(Entry<String, Text> textEntry : textMap.entrySet())
		{
			String module = textEntry.getKey();
			Text text = textEntry.getValue();
			List<Translation> translations = translationsMap.get(module);
			if(translations != null)
			{
				for(Translation translation : translations)
				{
					List<String> missing = getMissing(text, translation);
					if(!missing.isEmpty())
					{
						System.out.println();
						System.out.println("--------------------------------------------------------------------");
						System.out.println("Missing translations " + translation.getFile().getName());
						System.out.println("--------------------------------------------------------------------");
						System.out.println();
						for(String name : missing)
						{
							System.out.println(name);
						}
					}
					List<String> unused = getUnused(text, translation);
					if(!unused.isEmpty())
					{
						System.out.println();
						System.out.println("--------------------------------------------------------------------");
						System.out.println("Unused translations " + translation.getFile().getName());
						System.out.println("--------------------------------------------------------------------");
						System.out.println();
						for(String name : unused)
						{
							System.out.println(name);
						}
					}
				}
			}
		}
	}
	
	protected List<String> getMissing(Text text, Translation translation)
	{
		List<String> missing = new List<>();
		missing.addAll(getMissing(text.getTypes(), translation.getTypeFieldMapMap()));
		missing.addAll(getMissing("layout", text.getLayoutFieldsMap(), translation.getLayoutFieldMapMap()));
		missing.addAll(getMissing("page", text.getPageFieldsMap(), translation.getPageFieldMapMap()));
		missing.addAll(getMissing("component", text.getComponentFieldsMap(), translation.getComponentFieldMapMap()));
		return missing;
	}
	
	protected List<String> getMissing(Set<String> types, Map<String, Map<String, Object>> translationFieldMapMap)
	{
		List<String> missing = new List<>();
		for(String type : types)
		{
			Map<String, Object> translationFieldMap = translationFieldMapMap.get(type);
			if(translationFieldMap == null)
			{
				missing.add("type." + type);
			}
		}
		return missing;
	}
	
	protected List<String> getMissing(String type, Map<String, Set<String>> textFieldsMap, Map<String, Map<String, Object>> translationFieldMapMap)
	{
		List<String> missing = new List<>();
		for(Entry<String, Set<String>> textFieldsEntry : textFieldsMap.entrySet())
		{
			String name = textFieldsEntry.getKey();
			Map<String, Object> translationFieldMap = translationFieldMapMap.get(name);
			if(translationFieldMap == null)
			{
				translationFieldMap = new Map<>();
				translationFieldMapMap.put(name, translationFieldMap);
			}
			Set<String> fields = textFieldsEntry.getValue();
			for(String field : fields)
			{
				if(!translationFieldMap.containsKey(field))
				{
					missing.add(type + "." + name + "." + field);
				}
			}
		}
		return missing;
	}
	
	protected List<String> getUnused(Text text, Translation translation)
	{
		List<String> unused = new List<>();
		unused.addAll(getUnused(text.getTypes(), translation.getTypeFieldMapMap()));
		unused.addAll(getUnused("layout", text.getLayoutFieldsMap(), translation.getLayoutFieldMapMap()));
		unused.addAll(getUnused("page", text.getPageFieldsMap(), translation.getPageFieldMapMap()));
		unused.addAll(getUnused("component", text.getComponentFieldsMap(), translation.getComponentFieldMapMap()));
		return unused;
	}

	protected List<String> getUnused(Set<String> types, Map<String, Map<String, Object>> translationFieldMapMap)
	{
		List<String> unused = new List<>();
		for(String type : translationFieldMapMap.keySet())
		{
			if(!types.contains(type))
			{
				unused.add("type." + type);
			}
		}
		return unused;
	}
	
	protected List<String> getUnused(String type, Map<String, Set<String>> textFieldsMap, Map<String, Map<String, Object>> translationFieldMapMap)
	{
		List<String> unused = new List<>();
		for(Entry<String, Map<String, Object>> translationFieldMapEntry : translationFieldMapMap.entrySet())
		{
			String name = translationFieldMapEntry.getKey();
			Set<String> textFields = textFieldsMap.get(name);
			if(textFields == null)
			{
				textFields = new Set<>();
				textFieldsMap.put(name, textFields);
			}
			Map<String, Object> fieldMap = translationFieldMapEntry.getValue();
			for(String field : fieldMap.keySet())
			{
				if(!textFields.contains(field))
				{
					unused.add(type + "." + name + "." + field);
				}
			}
		}
		return unused;
	}
	
	protected Map<String, Text> getTextMap(File appDir)
	{
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Values");
		System.out.println("--------------------------------------------------------------------");
		System.out.println();
		Map<String, Text> textMap = new Map<>();
		for(String value : getValues(appDir))
		{
			Matcher matcher = VALUE_PATTERN.matcher(value);
			if(matcher.find())
			{
				String module = matcher.group(MODULE);
				String type = matcher.group(TYPE);
				String name = matcher.group(NAME);
				String field = matcher.group(FIELD);
				Text text = textMap.get(module);
				if(text == null)
				{
					text = new Text();
					textMap.put(module, text);
				}
				switch(type)
				{
					case "type":
					{
						text.addType(name);
						break;
					}
					case "layout":
					{
						text.addLayoutField(name, field);
						break;
					}
					case "page":
					{
						text.addPageField(name, field);
						break;
					}
					case "component":
					{
						text.addComponentField(name, field);
						break;
					}
				}
			}
		}
		System.out.println();
		return textMap;
	}
	
	protected Set<String> getValues(File appDir)
	{
		Set<String> values = new Set<>();
		for(File moduleDir : appDir.listFiles())
		{
			if(moduleDir.isDirectory())
			{
				String module = moduleDir.getName();
				File layoutDir = getLayoutDir(moduleDir);
				if(layoutDir.exists() && layoutDir.list().length > 0)
				{
					values.addAll(getTypeValue(module, layoutDir));
				}
				File pageDir = getPageDir(moduleDir);
				if(pageDir.exists() && pageDir.list().length > 0)
				{
					values.addAll(getTypeValue(module, pageDir));
				}
				File componentDir = getComponentDir(moduleDir);
				if(componentDir.exists() && componentDir.list().length > 0)
				{
					values.addAll(getTypeValue(module, componentDir));
				}
				File mixinDir = getMixinDir(moduleDir);
				if(mixinDir.exists() && mixinDir.list().length > 0)
				{
					values.addAll(getTypeValue(module, mixinDir));
				}
			}
		}
		return values;
	}
	
	protected Set<String> getTypeValue(String module, File dir)
	{
		Set<String> values = new Set<>();
		String type = dir.getName();
		for(File file : dir.listFiles())
		{
			if(file.isFile() && (file.getName().endsWith(".html") || (file.getName().endsWith(".js"))))
			{
				String name = file.getName().replaceAll("(\\.html|\\.js)$", "");
				String contents = FileUtil.toString(file);
				Matcher matcher = DATA_TEXT_PATTERN.matcher(contents);
				while(matcher.find())
				{
					String value = matcher.group(VALUE);
					if(!value.contains("."))
					{
						value = module + "." + type + "." + name + "." + value;
					}
					values.add(value);
				}
				matcher = FULL_TEXT_PATTERN.matcher(contents);
				while(matcher.find())
				{
					String value = matcher.group(VALUE);
					values.add(value);
				}
			}
		}
		return values;
	}
	
	protected Map<String, List<Translation>> getTranslationsMap(File appDir)
	{
		Map<String, List<Translation>> translationsMap = new Map<>();
		for(File moduleDir : appDir.listFiles())
		{
			if(moduleDir.isDirectory())
			{
				String module = moduleDir.getName();
				File textDir = getTextDir(moduleDir);
				if(textDir.exists() && textDir.list().length > 0)
				{
					for(File file : textDir.listFiles())
					{
						if(file.isFile() && !file.isHidden() && file.getName().endsWith(".json"))
						{
							Translation translation = jsonMapper.deserialize(file, Translation.class);
							translation.setFile(file);
							List<Translation> translations = translationsMap.get(module);
							if(translations == null)
							{
								translations = new List<>();
								translationsMap.put(module, translations);
							}
							translations.add(translation);
						}
					}
				}
			}
		}
		return translationsMap;
	}
	
	public static void main(String[] args) throws Exception
	{
		WebTranslate webTranslate = new WebTranslate();
		Map<String, String> argMap = parseArgMap(args);
		if(argMap.containsKey(PROJECT_DIR))
		{
			webTranslate.setProjectDir(new File(argMap.get(PROJECT_DIR)));
		}
		if(argMap.containsKey(WEB_APP_DIR))
		{
			webTranslate.setWebAppDir(new File(webTranslate.getProjectDir(), argMap.get(WEB_APP_DIR)));
		}
		if(argMap.containsKey(DEV_DIR))
		{
			webTranslate.setDevDir(new File(webTranslate.getWebAppDir(), argMap.get(DEV_DIR)));
		}
		if(argMap.containsKey(APP_DIR))
		{
			webTranslate.setAppDir(new File(webTranslate.getDevDir(), argMap.get(APP_DIR)));
		}
		if(argMap.containsKey(TEXT_DIR))
		{
			webTranslate.setTextDir(argMap.get(TEXT_DIR));
		}
		if(argMap.containsKey(MIXIN_DIR))
		{
			webTranslate.setMixinDir(argMap.get(MIXIN_DIR));
		}
		if(argMap.containsKey(LAYOUT_DIR))
		{
			webTranslate.setLayoutDir(argMap.get(LAYOUT_DIR));
		}
		if(argMap.containsKey(PAGE_DIR))
		{
			webTranslate.setPageDir(argMap.get(PAGE_DIR));
		}
		if(argMap.containsKey(COMPONENT_DIR))
		{
			webTranslate.setComponentDir(argMap.get(COMPONENT_DIR));
		}
		webTranslate.translate();
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
