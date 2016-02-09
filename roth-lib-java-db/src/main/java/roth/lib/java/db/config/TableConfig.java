package roth.lib.java.db.config;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.lang.Map;

@Entity
@SuppressWarnings("serial")
public class TableConfig implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Property(name = "idConfig")
	protected IdConfig idConfig;
	
	@Property(name = "fixedFieldConfigMap")
	protected Map<String, FixedFieldConfig> fixedFieldConfigMap = new Map<String, FixedFieldConfig>();
	
	@Property(name = "variableFieldConfigMap")
	protected Map<String, VariableFieldConfig> variableFieldConfigMap = new Map<String, VariableFieldConfig>();
	
	protected TableConfig()
	{
		
	}
	
	public TableConfig(String name, IdConfig idConfig)
	{
		this.name = name;
		this.idConfig = idConfig;
	}
	
	public String getName()
	{
		return name;
	}
	
	public IdConfig getIdConfig()
	{
		return idConfig;
	}
	
	public TableConfig setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public TableConfig setIdConfig(IdConfig idConfig)
	{
		this.idConfig = idConfig;
		return this;
	}
	
	public FixedFieldConfig getFixedFieldConfig(String fieldName)
	{
		return fixedFieldConfigMap.get(fieldName);
	}
	
	public TableConfig putFixedFieldConfig(String fieldName, FixedFieldConfig fixedFieldConfig)
	{
		fixedFieldConfigMap.put(fieldName, fixedFieldConfig);
		return this;
	}
	
	public TableConfig removeFixedFieldConfig(String fieldName)
	{
		fixedFieldConfigMap.remove(fieldName);
		return this;
	}
	
	public VariableFieldConfig getVariableFieldConfig(String fieldName)
	{
		return variableFieldConfigMap.get(fieldName);
	}
	
	public TableConfig putVariableFieldConfig(String fieldName, VariableFieldConfig variableFieldConfig)
	{
		variableFieldConfigMap.put(fieldName, variableFieldConfig);
		return this;
	}
	
	public TableConfig removeVariableFieldConfig(String fieldName)
	{
		variableFieldConfigMap.remove(fieldName);
		return this;
	}
	
}
