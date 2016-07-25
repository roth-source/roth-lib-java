package roth.lib.java.web.translate;

import java.io.File;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.Characters;
import roth.lib.java.json.JsonMapper;
import roth.lib.java.lang.Map;
import roth.lib.java.lang.Set;
import roth.lib.java.mapper.MapperConfig;

public class WebTranslate implements Characters
{
	protected static Pattern LANG_PATTERN			= Pattern.compile("^(?:\\S+_)?([A-Za-z]{2})\\.json");
	
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
	
	protected MapperConfig mapperConfig = new MapperConfig();
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
		Map<String, Map<String, Text>> textMap = getTextMap(appDir);
		Set<String> langs = getLangs(textMap);
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Missing File Report (" + langs.toString() + ")");
		System.out.println("--------------------------------------------------------------------");
		System.out.println();
		missingFileReport(textMap, langs);
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Missing Translation Report");
		System.out.println("--------------------------------------------------------------------");
		System.out.println();
		missingTranslationReport(textMap);
	}
	
	protected Map<String, Map<String, Text>> getTextMap(File appDir)
	{
		Map<String, Map<String, Text>> textMap = new Map<>();
		for(File moduleDir : appDir.listFiles())
		{
			if(moduleDir.isDirectory())
			{
				String module = moduleDir.getName();
				Map<String, Text> moduleTextMap = new Map<>();
				File textDir = getTextDir(moduleDir);
				if(textDir.exists() && textDir.list().length > 0)
				{
					for(File file : textDir.listFiles())
					{
						if(file.isFile() && !file.isHidden() && file.getName().endsWith(".json"))
						{
							System.out.println(file.getAbsolutePath());
							Matcher matcher = LANG_PATTERN.matcher(file.getName());
							if(matcher.find())
							{
								String lang = matcher.group(1).toLowerCase();
								Text text = jsonMapper.deserialize(file, Text.class);
								jsonMapper.serialize(text, file);
								moduleTextMap.put(lang, text.setFile(file));
							}
						}
					}
				}
				if(!moduleTextMap.isEmpty())
				{
					textMap.put(module, moduleTextMap);
				}
			}
		}
		return textMap;
	}
	
	protected Set<String> getLangs(Map<String, Map<String, Text>> textMap)
	{
		Set<String> langs = new Set<>();
		for(Map<String, Text> moduleTextMap : textMap.values())
		{
			for(String lang : moduleTextMap.keySet())
			{
				langs.add(lang);
			}
		}
		return langs;
	}
	
	protected void missingFileReport(Map<String, Map<String, Text>> textMap, Set<String> langs)
	{
		for(Entry<String, Map<String, Text>> textEntry : textMap.entrySet())
		{
			String module = textEntry.getKey();
			Set<String> moduleLangs = new Set<String>().collection(langs);
			for(String lang : textEntry.getValue().keySet())
			{
				moduleLangs.remove(lang);
			}
			if(!moduleLangs.isEmpty())
			{
				System.out.println(module + " missing " + moduleLangs.toString());
			}
		}
	}
	
	protected void missingTranslationReport(Map<String, Map<String, Text>> textMap)
	{
		for(Entry<String, Map<String, Text>> textEntry : textMap.entrySet())
		{
			missingTranslationReport(textEntry.getKey(), textEntry.getValue());
		}
	}
	
	protected void missingTranslationReport(String module, Map<String, Text> moduleTextMap)
	{
		Set<String> missingTranslations = new Set<>();
		for(Entry<String, Text> moduleTextEntry1 : moduleTextMap.entrySet())
		{
			String lang1 = moduleTextEntry1.getKey();
			Text text1 = moduleTextEntry1.getValue();
			for(Entry<String, Text> moduleTextEntry2 : moduleTextMap.entrySet())
			{
				String lang2 = moduleTextEntry2.getKey();
				if(!lang1.equals(lang2))
				{
					Text text2 = moduleTextEntry2.getValue();
					missingTranslations.addAll(getMissingTranslations(text1, text2));
				}
			}
		}
		for(String missingTranslation : missingTranslations)
		{
			System.out.println(missingTranslation);
		}
	}
	
	protected Set<String> getMissingTranslations(Text text1, Text text2)
	{
		Set<String> missingTranslations = new Set<>();
		String filename = text2.getFile().getName().replaceFirst("\\.json$", "");
		missingTranslations.addAll(getMissingTranslations(filename, "type", text1.getType(), text2.getType()));
		missingTranslations.addAll(getMissingTranslations(filename, "layout", text1.getLayout(), text2.getLayout()));
		missingTranslations.addAll(getMissingTranslations(filename, "page", text1.getPage(), text2.getPage()));
		missingTranslations.addAll(getMissingTranslations(filename, "component", text1.getComponent(), text2.getComponent()));
		return missingTranslations;
	}
	
	@SuppressWarnings("unchecked")
	protected Set<String> getMissingTranslations(String filename, String type, TreeMap<String, TreeMap<String, Object>> map1, TreeMap<String, TreeMap<String, Object>> map2)
	{
		Set<String> missingTranslations = new Set<>();
		if(map1 != null)
		{
			for(Entry<String, TreeMap<String, Object>> mapEntry : map1.entrySet())
			{
				String typeName = mapEntry.getKey();
				for(Entry<String, Object> entry : mapEntry.getValue().entrySet())
				{
					String name = entry.getKey();
					Object value1 = entry.getValue();
					if(value1 != null)
					{
						Object value2 = getValue(map2, typeName, name);
						if(value2 == null)
						{
							missingTranslations.add(filename + ":" + type + "." + typeName + "." + name);
						}
						else if(value1 instanceof Map)
						{
							Set<String> keys1 = ((Map<String, ?>) value1).keySet();
							Set<String> keys2 = ((Map<String, ?>) value2).keySet();
							for(String key : keys1)
							{
								if(!keys2.contains(key))
								{
									missingTranslations.add(filename + ":" + type + "." + typeName + "." + name + "." + key);
								}
							}
						}
					}
				}
			}
		}
		return missingTranslations;
	}
	
	protected Object getValue(TreeMap<String, TreeMap<String, Object>> map, String typeName, String name)
	{
		Object value = null;
		if(map != null)
		{
			TreeMap<String, Object> typeMap = map.get(typeName);
			if(typeMap != null)
			{
				value = typeMap.get(name);
			}
		}
		return value;
	}
	
	protected void translateModule(File moduleDir) throws Exception
	{
		String module = moduleDir.getName();
		System.out.println("Translating module " + module);
		
		
		File textDir = getTextDir(moduleDir);
		if(textDir.exists() && textDir.list().length > 0)
		{
			System.out.println("Texts");
		}

		File mixinDir = getMixinDir(moduleDir);
		if(mixinDir.exists() && mixinDir.list().length > 0)
		{
			System.out.println("Mixins");
			
		}
		
		File layoutDir = getLayoutDir(moduleDir);
		if(layoutDir.exists() && layoutDir.list().length > 0)
		{
			System.out.println("Layouts");
		}
		
		File pageDir = getPageDir(moduleDir);
		if(pageDir.exists() && pageDir.list().length > 0)
		{
			System.out.println("Pages");
		}
		
		File componentDir = getComponentDir(moduleDir);
		if(componentDir.exists() && componentDir.list().length > 0)
		{
			System.out.println("Components");
		}
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
