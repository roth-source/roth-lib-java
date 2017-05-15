package roth.lib.java.web.translate;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.json.JsonMapper;
import roth.lib.java.lang.Map;
import roth.lib.java.lang.Set;

@Entity
@SuppressWarnings("serial")
public class Text implements Serializable, Cloneable
{
	@Property(name = "type")
	protected Set<String> types = new Set<>();
	
	@Property(name = "layout")
	protected Map<String, Set<String>> layoutFieldsMap = new Map<String, Set<String>>();
	
	@Property(name = "page")
	protected Map<String, Set<String>> pageFieldsMap = new Map<String, Set<String>>();
	
	@Property(name = "component")
	protected Map<String, Set<String>> componentFieldsMap = new Map<String, Set<String>>();
	
	public Text()
	{
		
	}
	
	public Text addType(String name)
	{
		types.add(name);
		return this;
	}
	
	public Text addLayoutField(String name, String field)
	{
		Set<String> fields = layoutFieldsMap.get(name);
		if(fields == null)
		{
			fields = new Set<String>();
			layoutFieldsMap.put(name, fields);
		}
		fields.add(field);
		return this;
	}
	
	public Text addPageField(String name, String field)
	{
		Set<String> fields = pageFieldsMap.get(name);
		if(fields == null)
		{
			fields = new Set<String>();
			pageFieldsMap.put(name, fields);
		}
		fields.add(field);
		return this;
	}
	
	public Text addComponentField(String name, String field)
	{
		Set<String> fields = componentFieldsMap.get(name);
		if(fields == null)
		{
			fields = new Set<String>();
			componentFieldsMap.put(name, fields);
		}
		fields.add(field);
		return this;
	}

	public Set<String> getTypes()
	{
		return types;
	}
	
	public Map<String, Set<String>> getLayoutFieldsMap()
	{
		return layoutFieldsMap;
	}
	
	public Map<String, Set<String>> getPageFieldsMap()
	{
		return pageFieldsMap;
	}
	
	public Map<String, Set<String>> getComponentFieldsMap()
	{
		return componentFieldsMap;
	}
	
	@Override
	public String toString()
	{
		return new JsonMapper().setPrettyPrint(true).serialize(this);
	}
	
}
