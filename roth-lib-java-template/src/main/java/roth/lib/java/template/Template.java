package roth.lib.java.template;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import roth.lib.java.util.ResourceUtil;

public class Template
{
	protected static final String ENGINE			= "nashorn";
	protected static final String ROTH_JS			= "roth-lib-js.js";
	protected static final String ROTH_JS_TEMPLATE	= "roth-lib-js-template.js";
	protected static final String ROTH_JS_JAVA		= "roth-lib-js-java.js";
	protected static final String VERSION			= "$_version";
	protected static final String CONFIG			= "$_config";
	protected static final String TEMPLATE			= "$_template";
	protected static final String PARSE				= "parse";
	protected static final String EVAL				= "eval";
	protected static final String RENDER			= "render";
	protected static final String SET_VERSION		= String.format("var %s = roth.lib.js.template.version;", VERSION);
	protected static final String SET_TEMPLATE		= String.format("var %s = new roth.lib.js.template.Template(" + CONFIG + ");", TEMPLATE);
	
	protected ScriptEngine engine;
	protected Object template;
	protected String version;
	
	public Template()
	{
		this(new TemplateConfig());
	}
	
	public Template(TemplateConfig config)
	{
		init(config);
	}
	
	protected void init(TemplateConfig config)
	{
		try
		{
			engine = new ScriptEngineManager().getEngineByName(ENGINE);
			engine.eval(ResourceUtil.toString(ROTH_JS));
			engine.eval(ResourceUtil.toString(ROTH_JS_TEMPLATE));
			engine.eval(ResourceUtil.toString(ROTH_JS_JAVA));
			engine.put(CONFIG, config);
			engine.eval(SET_VERSION + SET_TEMPLATE);
			version = (String) engine.get(VERSION);
			template = engine.get(TEMPLATE);
		}
		catch(ScriptException e)
		{
			throw new TemplateException(e);
		}
	}
	
	public String parse(String source)
	{
		try
		{
			Object parsedSource = ((Invocable) engine).invokeMethod(template, PARSE, source);
			return parsedSource != null ? parsedSource.toString() : "";
		}
		catch(NoSuchMethodException | ScriptException e)
		{
			throw new TemplateException(e);
		}
	}
	
	public String eval(String parsedSource, Object data)
	{
		try
		{
			Object rendered = ((Invocable) engine).invokeMethod(template, EVAL, parsedSource, data);
			return rendered != null ? rendered.toString() : "";
		}
		catch(NoSuchMethodException | ScriptException e)
		{
			throw new TemplateException(e);
		}
	}
	
	public String render(String source, Object data)
	{
		try
		{
			Object rendered = ((Invocable) engine).invokeMethod(template, RENDER, source, data);
			return rendered != null ? rendered.toString() : "";
		}
		catch(NoSuchMethodException | ScriptException e)
		{
			throw new TemplateException(e);
		}
	}
	
	public String getVersion()
	{
		return version;
	}
	
}
