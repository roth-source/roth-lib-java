package roth.lib.template;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import roth.lib.util.ResourceUtil;

public class Template
{
	protected static final String ENGINE			= "nashorn";
	protected static final String ROTH_JS			= "roth-js.js";
	protected static final String ROTH_JS_TEMPLATE	= "roth-js-template.js";
	protected static final String VERSION			= "$_version";
	protected static final String CONFIG			= "$_config";
	protected static final String TEMPLATE			= "$_template";
	protected static final String RENDER			= "render";
	protected static final String SET_VERSION		= String.format("var %s = roth.js.template.version;", VERSION);
	protected static final String SET_TEMPLATE		= String.format("var %s = new roth.js.template.Template(" + CONFIG + ");", TEMPLATE);
	
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
	
	public String render(String html, Object data)
	{
		try
		{
			Object renderedHtml = ((Invocable) engine).invokeMethod(template, RENDER, html, data);
			return renderedHtml != null ? renderedHtml.toString() : "";
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
