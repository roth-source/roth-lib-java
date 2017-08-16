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
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.table.TableMapper;
import roth.lib.java.time.Day;

public class WebTranslateImport implements Characters
{
	protected static final String IMPORTED 					= "_imported_";
	protected static final String APP						= "app";
	protected static final String LANG						= "lang";
	protected static final Pattern APP_LANG_PATTERN			= Pattern.compile("translate_(?<" + APP + ">\\w+?)_(?<" + LANG + ">\\w{2})\\.csv");
	protected static final String TEXT_NAME 				= "%s_%s.json";
	protected static final String TYPE						= "type";
	protected static final String LAYOUT					= "layout";
	protected static final String PAGE						= "page";
	protected static final String COMPONENT					= "component";
	
	protected static final String USER_DIR_DEFAULT			= "user.dir";
	protected static final String TRANSLATE_DEFAULT			= "translate";
	protected static final String IMPORT_DEFAULT			= "import";
	protected static final String WEB_APP_DEFAULT			= "src/main/webapp";
	protected static final String DEV_DEFAULT				= "dev";
	protected static final String APP_DEFAULT				= "app";
	protected static final String TEXT_DEFAULT				= "text";
	
	protected static final String PROJECT_DIR				= "projectDir";
	protected static final String TRANSLATE_DIR				= "translateDir";
	protected static final String IMPORT_DIR				= "importDir";
	protected static final String WEB_APP_DIR				= "webAppDir";
	protected static final String DEV_DIR					= "devDir";
	protected static final String APP_DIR					= "appDir";
	protected static final String TEXT_DIR					= "textDir";
	
	protected MapperConfig mapperConfig = new MapperConfig().setSerializeEmptyArray(false).setSerializeEmptyMap(false).setSerializeNulls(false);
	protected JsonMapper jsonMapper = new JsonMapper();
	protected TableMapper tableMapper = new TableMapper();
	protected File projectDir;
	protected File translateDir;
	protected File importDir;
	protected File webAppDir;
	protected File devDir;
	protected File appDir;
	protected String textDir;
	
	public WebTranslateImport()
	{
		init();
	}
	
	protected void init()
	{
		mapperConfig.setSerializeEmptyMap(false);
		jsonMapper.setPrettyPrint(true).setMapperConfig(mapperConfig);
		projectDir = new File(System.getProperty(USER_DIR_DEFAULT));
		translateDir = new File(projectDir, TRANSLATE_DEFAULT);
		importDir = new File(translateDir, IMPORT_DEFAULT);
		webAppDir = new File(projectDir, WEB_APP_DEFAULT);
		devDir = new File(webAppDir, DEV_DEFAULT);
		appDir = new File(devDir, APP_DEFAULT);
		textDir = TEXT_DEFAULT;
	}
	
	public File getProjectDir()
	{
		return projectDir;
	}
	
	public File getTranslateDir()
	{
		return translateDir;
	}
	
	public File getImportDir()
	{
		return importDir;
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
	
	public void setProjectDir(File projectDir)
	{
		this.projectDir = projectDir;
	}
	
	public void setTranslateDir(File translateDir)
	{
		this.translateDir = translateDir;
	}
	
	public void setImportDir(File importDir)
	{
		this.importDir = importDir;
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
	
	protected void importFiles() throws Exception
	{
		if(importDir.exists() && importDir.isDirectory())
		{
			for(File file : importDir.listFiles())
			{
				if(file.isFile() && !file.isHidden())
				{
					if(!file.getName().startsWith(IMPORTED))
					{
						System.out.println("--------------------------------------------------------------------");
						System.out.println("Importing " + file.getName());
						System.out.println("--------------------------------------------------------------------");
						System.out.println();
						importFile(file);
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void importFile(File file) throws Exception
	{
		Matcher matcher = APP_LANG_PATTERN.matcher(file.getName());
		if(matcher.find())
		{
			String app = matcher.group(APP);
			String lang = matcher.group(LANG);
			File appDir = new File(getAppDir(), app);
			if(appDir.exists() && appDir.isDirectory())
			{
				List<Translate> translates = tableMapper.deserializeList(file, Translate.class);
				Map<String, List<Translate>> moduleTranslatesMap = new Map<>();
				for(Translate translate : translates)
				{
					if(translate.hasTranslation())
					{
						String module = translate.getModule();
						if(module != null)
						{
							if(moduleTranslatesMap.get(module) == null)
							{
								moduleTranslatesMap.put(module, new List<>());
							}
							moduleTranslatesMap.get(module).add(translate);
						}
					}
				}
				for(Entry<String, List<Translate>> moduleTranslatesEntry : moduleTranslatesMap.entrySet())
				{
					String module = moduleTranslatesEntry.getKey();
					File moduleDir = new File(appDir, module);
					if(moduleDir.exists() && moduleDir.isDirectory())
					{
						File textDir = getTextDir(moduleDir);
						if(textDir.exists() && textDir.isDirectory())
						{
							File textFile = new File(textDir, String.format(TEXT_NAME, module, lang));
							if(textFile.exists() && textFile.isFile())
							{
								Translation translation = jsonMapper.deserialize(textFile, Translation.class);
								for(Translate translate : moduleTranslatesEntry.getValue())
								{
									if(translate.getType() != null)
									{
										Map<String, Map<String, Object>> fieldMapMap = null;
										switch(translate.getType())
										{
											case TYPE:
											{
												fieldMapMap = translation.getTypeFieldMapMap();
												break;
											}
											case LAYOUT:
											{
												fieldMapMap = translation.getLayoutFieldMapMap();
												break;
											}
											case PAGE:
											{
												fieldMapMap = translation.getPageFieldMapMap();
												break;
											}
											case COMPONENT:
											{
												fieldMapMap = translation.getComponentFieldMapMap();
												break;
											}
										}
										if(fieldMapMap != null)
										{
											if(fieldMapMap.get(translate.getName()) == null)
											{
												fieldMapMap.put(translate.getName(), new Map<>());
											}
											Map<String, Object> fieldMap = fieldMapMap.get(translate.getName());
											if(translate.getKey() != null)
											{
												Object value = fieldMap.get(translate.getField());
												Map<Object, Object> valueMap = new Map<>();
												if(value instanceof Map)
												{
													valueMap = (Map<Object, Object>) value;
												}
												valueMap.put(translate.getKey(), translate.getTranslation());
												fieldMap.put(translate.getField(), valueMap);
											}
											else
											{
												fieldMap.put(translate.getField(), translate.getTranslation());
											}
										}
									}
								}
								System.out.println("Saving " + textFile.getName());
								jsonMapper.serialize(translation, textFile);
							}
							else
							{
								System.err.println("Can't find text file " + textFile.getAbsolutePath());
							}
						}
						else
						{
							System.err.println("Can't find text dir " + textDir.getAbsolutePath());
						}
					}
					else
					{
						System.err.println("Can't find module dir " + moduleDir.getAbsolutePath());
					}
				}
			}
			else
			{
				System.err.println("Can't find app dir " + appDir.getAbsolutePath());
			}
			file.renameTo(new File(file.getParentFile(), IMPORTED + new Day().format("yyyyMMdd") + "_" + file.getName()));
		}
		else
		{
			System.err.println("Can't find app and lang in file name");
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		WebTranslateImport webTranslateImport = new WebTranslateImport();
		Map<String, String> argMap = parseArgMap(args);
		if(argMap.containsKey(PROJECT_DIR))
		{
			webTranslateImport.setProjectDir(new File(argMap.get(PROJECT_DIR)));
		}
		if(argMap.containsKey(TRANSLATE_DIR))
		{
			webTranslateImport.setTranslateDir(new File(webTranslateImport.getProjectDir(), argMap.get(TRANSLATE_DIR)));
		}
		if(argMap.containsKey(IMPORT_DIR))
		{
			webTranslateImport.setImportDir(new File(webTranslateImport.getTranslateDir(), argMap.get(IMPORT_DIR)));
		}
		if(argMap.containsKey(WEB_APP_DIR))
		{
			webTranslateImport.setWebAppDir(new File(webTranslateImport.getProjectDir(), argMap.get(WEB_APP_DIR)));
		}
		if(argMap.containsKey(DEV_DIR))
		{
			webTranslateImport.setDevDir(new File(webTranslateImport.getWebAppDir(), argMap.get(DEV_DIR)));
		}
		if(argMap.containsKey(APP_DIR))
		{
			webTranslateImport.setAppDir(new File(webTranslateImport.getDevDir(), argMap.get(APP_DIR)));
		}
		if(argMap.containsKey(TEXT_DIR))
		{
			webTranslateImport.setTextDir(argMap.get(TEXT_DIR));
		}
		webTranslateImport.importFiles();
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
