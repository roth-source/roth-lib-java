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
import roth.lib.java.table.TableMapper;
import roth.lib.java.util.FileUtil;

public class WebTranslateExport implements Characters
{
	protected static final String EN						= "en";
	protected static final String MODULE					= "module";
	protected static final String TYPE						= "type";
	protected static final String NAME						= "name";
	protected static final String FIELD						= "field";
	protected static final String VALUE						= "value";
	protected static final String LAYOUT					= "layout";
	protected static final String PAGE						= "page";
	protected static final String COMPONENT					= "component";
	protected static final Pattern DATA_TEXT_PATTERN		= Pattern.compile("data-text(?:-attr)?(?:\\s+)?=(?:\\s+)?['\"](?:\\w+:)?(?<" + VALUE + ">[\\w\\.]+?)['\"]");
	protected static final Pattern FULL_TEXT_PATTERN		= Pattern.compile("text\\.(?<" + VALUE + ">[\\w]+\\.[\\w]+\\.[\\w\\.]+)(?:\\W|$)");
	protected static final Pattern VALUE_PATTERN			= Pattern.compile("(?<" + MODULE + ">\\w+)\\.(?<" + TYPE + ">\\w+)\\.(?<" + NAME + ">\\w+)(?:\\.(?<" + FIELD + ">\\w+))?");
	protected static final String TRANSLATE_NAME			= "translate_%s_%s.csv";
	
	protected static final String USER_DIR_DEFAULT			= "user.dir";
	protected static final String WEB_APP_DEFAULT			= "src/main/webapp";
	protected static final String DEV_DEFAULT				= "dev";
	protected static final String APP_DEFAULT				= "app";
	protected static final String TRANSLATE_DEFAULT			= "translate";
	protected static final String EXPORT_DEFAULT			= "export";
	protected static final String TEXT_DEFAULT				= "text";
	protected static final String MIXIN_DEFAULT				= "mixin";
	protected static final String LAYOUT_DEFAULT			= "layout";
	protected static final String PAGE_DEFAULT				= "page";
	protected static final String COMPONENT_DEFAULT			= "component";
	
	protected static final String PROJECT_DIR				= "projectDir";
	protected static final String WEB_APP_DIR				= "webAppDir";
	protected static final String DEV_DIR					= "devDir";
	protected static final String APP_DIR					= "appDir";
	protected static final String TRANSLATE_DIR				= "translateDir";
	protected static final String EXPORT_DIR				= "exportDir";
	protected static final String TEXT_DIR					= "textDir";
	protected static final String MIXIN_DIR					= "mixinDir";
	protected static final String LAYOUT_DIR				= "layoutDir";
	protected static final String PAGE_DIR					= "pageDir";
	protected static final String COMPONENT_DIR				= "componentDir";
	
	protected MapperConfig mapperConfig = new MapperConfig().setSerializeEmptyArray(false).setSerializeEmptyMap(false).setSerializeNulls(false);
	protected JsonMapper jsonMapper = new JsonMapper();
	protected TableMapper tableMapper = new TableMapper();
	protected File projectDir;
	protected File webAppDir;
	protected File devDir;
	protected File appDir;
	protected File translateDir;
	protected File exportDir;
	protected String textDir;
	protected String mixinDir;
	protected String layoutDir;
	protected String pageDir;
	protected String componentDir;
	
	public WebTranslateExport()
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
		translateDir = new File(projectDir, TRANSLATE_DEFAULT);
		exportDir = new File(translateDir, EXPORT_DEFAULT);
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
	
	public File getTranslateDir()
	{
		return translateDir;
	}
	
	public File getExportDir()
	{
		return exportDir;
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
	
	public void setTranslateDir(File translateDir)
	{
		this.translateDir = translateDir;
	}
	
	public void setExportDir(File exportDir)
	{
		this.exportDir = exportDir;
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
		Map<String, Translation> enTranslationsMap = new Map<>();
		Map<String, List<Translation>> otherTranslationsMap = new Map<>();
		for(Entry<String, List<Translation>> translationsEntry : translationsMap.entrySet())
		{
			String module = translationsEntry.getKey();
			for(Translation translation : translationsEntry.getValue())
			{
				String lang = translation.getLang();
				if(lang != null)
				{
					if(EN.equalsIgnoreCase(lang))
					{
						enTranslationsMap.put(module, translation);
					}
					else
					{
						if(otherTranslationsMap.get(module) == null)
						{
							otherTranslationsMap.put(module, new List<>());
						}
						otherTranslationsMap.get(module).add(translation);
					}
				}
			}
		}
		boolean ready = true;
		for(Entry<String, Text> textEntry : textMap.entrySet())
		{
			String module = textEntry.getKey();
			Text text = textEntry.getValue();
			Translation translation = enTranslationsMap.get(module);
			if(translation != null)
			{
				List<String> missing = getMissing(text, translation);
				if(!missing.isEmpty())
				{
					ready = false;
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
					ready = false;
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
		if(ready)
		{
			Map<String, List<Translate>> langTranslatesMap = new Map<>();
			for(Entry<String, Translation> enTranslationEntry : enTranslationsMap.entrySet())
			{
				String module = enTranslationEntry.getKey();
				Translation enTranslation = enTranslationEntry.getValue();
				List<Translation> translations = otherTranslationsMap.get(module);
				if(translations != null && !translations.isEmpty())
				{
					for(Translation translation : translations)
					{
						String lang = translation.getLang();
						if(lang != null)
						{
							List<Translate> translates = getTranslates(module, enTranslation, translation);
							if(!translates.isEmpty())
							{
								if(langTranslatesMap.get(lang) == null)
								{
									langTranslatesMap.put(lang, new List<>());
								}
								langTranslatesMap.get(lang).addAll(translates);
							}
						}
					}
				}
			}
			if(!langTranslatesMap.isEmpty())
			{
				File exportDir = getExportDir();
				exportDir.mkdirs();
				for(Entry<String, List<Translate>> langTranslatesEntry : langTranslatesMap.entrySet())
				{
					String lang = langTranslatesEntry.getKey();
					List<Translate> translates = langTranslatesEntry.getValue();
					String name = String.format(TRANSLATE_NAME, app, lang);
					File file = new File(exportDir, name);
					System.out.println("Exporting " + file.getAbsolutePath());
					tableMapper.serialize(translates, file);
				}
			}
		}
		else
		{
			System.err.println();
			System.err.println("Correct EN translations before proceeding");
			System.err.println();
		}
	}
	
	protected List<String> getMissing(Text text, Translation translation)
	{
		List<String> missing = new List<>();
		missing.addAll(getMissing(text.getTypes(), translation.getTypeFieldMapMap()));
		missing.addAll(getMissing(LAYOUT, text.getLayoutFieldsMap(), translation.getLayoutFieldMapMap()));
		missing.addAll(getMissing(PAGE, text.getPageFieldsMap(), translation.getPageFieldMapMap()));
		missing.addAll(getMissing(COMPONENT, text.getComponentFieldsMap(), translation.getComponentFieldMapMap()));
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
				missing.add(TYPE + "." + type);
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
		unused.addAll(getUnused(LAYOUT, text.getLayoutFieldsMap(), translation.getLayoutFieldMapMap()));
		unused.addAll(getUnused(PAGE, text.getPageFieldsMap(), translation.getPageFieldMapMap()));
		unused.addAll(getUnused(COMPONENT, text.getComponentFieldsMap(), translation.getComponentFieldMapMap()));
		return unused;
	}

	protected List<String> getUnused(Set<String> types, Map<String, Map<String, Object>> translationFieldMapMap)
	{
		List<String> unused = new List<>();
		for(String type : translationFieldMapMap.keySet())
		{
			if(!types.contains(type))
			{
				unused.add(TYPE + "." + type);
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
	
	protected List<Translate> getTranslates(String module, Translation enTranslation, Translation translation)
	{
		List<Translate> translates = new List<>();
		translates.addAll(getTranslates(module, TYPE, enTranslation.getTypeFieldMapMap(), translation.getTypeFieldMapMap()));
		translates.addAll(getTranslates(module, LAYOUT, enTranslation.getLayoutFieldMapMap(), translation.getLayoutFieldMapMap()));
		translates.addAll(getTranslates(module, PAGE, enTranslation.getPageFieldMapMap(), translation.getPageFieldMapMap()));
		translates.addAll(getTranslates(module, COMPONENT, enTranslation.getComponentFieldMapMap(), translation.getComponentFieldMapMap()));
		return translates;
	}
	
	protected List<Translate> getTranslates(String module, String type, Map<String, Map<String, Object>> enFieldMapMap, Map<String, Map<String, Object>> fieldMapMap)
	{
		List<Translate> translates = new List<>();
		for(Entry<String, Map<String, Object>> enFieldMapEntry : enFieldMapMap.entrySet())
		{
			String name = enFieldMapEntry.getKey();
			Map<String, Object> enFieldMap = enFieldMapEntry.getValue();
			Map<String, Object> fieldMap = fieldMapMap.get(name);
			if(enFieldMap != null)
			{
				for(Entry<String, Object> enFieldEntry : enFieldMap.entrySet())
				{
					String field = enFieldEntry.getKey();
					Object enValue = enFieldEntry.getValue();
					Object value = fieldMap != null ? fieldMap.get(field) : null;
					translates.addAll(getTranslates(module, type, name, field, enValue, value));
				}
			}
		}
		return translates;
	}
	
	protected List<Translate> getTranslates(String module, String type, String name, String field, Object enValue, Object value)
	{
		List<Translate> translates = new List<>();
		if(enValue instanceof String)
		{
			String enValueString = (String) enValue;
			if(!enValueString.trim().isEmpty())
			{
				if(value == null || value.toString().trim().isEmpty())
				{
					translates.add(new Translate(module, type, name, field, null, enValueString));
				}
			}
		}
		else if(enValue instanceof Map)
		{
			Map<?, ?> valueMap = new Map<>();
			if(value instanceof Map)
			{
				valueMap = (Map<?, ?>) value;
			}
			Map<?, ?> enValueMap = (Map<?, ?>) enValue;
			for(Entry<?, ?> enValueEntry : enValueMap.entrySet())
			{
				String key = enValueEntry.getKey().toString();
				if(enValueEntry.getValue() != null)
				{
					String enValueString = enValueEntry.getValue().toString();
					if(!enValueString.trim().isEmpty())
					{
						Object valueObject = valueMap.get(key);
						if(valueObject == null || valueObject.toString().trim().isEmpty())
						{
							translates.add(new Translate(module, type, name, field, key, enValueString));
						}
					}
				}
			}
		}
		return translates;
	}
	
	protected Map<String, Text> getTextMap(File appDir)
	{
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
					case TYPE:
					{
						text.addType(name);
						break;
					}
					case LAYOUT:
					{
						text.addLayoutField(name, field);
						break;
					}
					case PAGE:
					{
						text.addPageField(name, field);
						break;
					}
					case COMPONENT:
					{
						text.addComponentField(name, field);
						break;
					}
				}
			}
		}
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
		WebTranslateExport webTranslateExport = new WebTranslateExport();
		Map<String, String> argMap = parseArgMap(args);
		if(argMap.containsKey(PROJECT_DIR))
		{
			webTranslateExport.setProjectDir(new File(argMap.get(PROJECT_DIR)));
		}
		if(argMap.containsKey(WEB_APP_DIR))
		{
			webTranslateExport.setWebAppDir(new File(webTranslateExport.getProjectDir(), argMap.get(WEB_APP_DIR)));
		}
		if(argMap.containsKey(DEV_DIR))
		{
			webTranslateExport.setDevDir(new File(webTranslateExport.getWebAppDir(), argMap.get(DEV_DIR)));
		}
		if(argMap.containsKey(APP_DIR))
		{
			webTranslateExport.setAppDir(new File(webTranslateExport.getDevDir(), argMap.get(APP_DIR)));
		}
		if(argMap.containsKey(TRANSLATE_DIR))
		{
			webTranslateExport.setTranslateDir(new File(webTranslateExport.getProjectDir(), argMap.get(TRANSLATE_DIR)));
		}
		if(argMap.containsKey(EXPORT_DIR))
		{
			webTranslateExport.setExportDir(new File(webTranslateExport.getTranslateDir(), argMap.get(EXPORT_DIR)));
		}
		if(argMap.containsKey(TEXT_DIR))
		{
			webTranslateExport.setTextDir(argMap.get(TEXT_DIR));
		}
		if(argMap.containsKey(MIXIN_DIR))
		{
			webTranslateExport.setMixinDir(argMap.get(MIXIN_DIR));
		}
		if(argMap.containsKey(LAYOUT_DIR))
		{
			webTranslateExport.setLayoutDir(argMap.get(LAYOUT_DIR));
		}
		if(argMap.containsKey(PAGE_DIR))
		{
			webTranslateExport.setPageDir(argMap.get(PAGE_DIR));
		}
		if(argMap.containsKey(COMPONENT_DIR))
		{
			webTranslateExport.setComponentDir(argMap.get(COMPONENT_DIR));
		}
		webTranslateExport.translate();
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
