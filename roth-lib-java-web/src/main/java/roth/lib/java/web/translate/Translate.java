package roth.lib.java.web.translate;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Translate implements Serializable
{
	@Property(name = "module")
	protected String module;
	
	@Property(name = "type")
	protected String type;
	
	@Property(name = "name")
	protected String name;
	
	@Property(name = "field")
	protected String field;
	
	@Property(name = "key")
	protected String key;
	
	@Property(name = "en")
	protected String en;
	
	@Property(name = "translation")
	protected String translation;
	
	protected Translate()
	{
		
	}
	
	public Translate(String module, String type, String name, String field, String key, String en)
	{
		super();
		this.module = module;
		this.type = type;
		this.name = name;
		this.field = field;
		this.key = key;
		this.en = en;
	}
	
	public String getModule()
	{
		return module != null ? module.toLowerCase() : null;
	}
	
	public String getType()
	{
		return type != null ? type.toLowerCase() : null;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getField()
	{
		return field;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public String getEn()
	{
		return en;
	}
	
	public boolean hasTranslation()
	{
		return translation != null && !translation.isEmpty();
	}
	
	public String getTranslation()
	{
		return translation;
	}
	
	public void setModule(String module)
	{
		this.module = module;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setField(String field)
	{
		this.field = field;
	}
	
	public void setKey(String key)
	{
		this.key = key;
	}
	
	public void setEn(String en)
	{
		this.en = en;
	}
	
	public void setTranslation(String translation)
	{
		this.translation = translation;
	}
	
	@Override
	public String toString()
	{
		return "Translate [module=" + module + ", type=" + type + ", name=" + name + ", field=" + field + ", key=" + key + ", en=" + en + ", translation=" + translation + "]";
	}
	
}
