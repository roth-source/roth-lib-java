package roth.lib.java.db.schema;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.lang.Map;

@Entity
@SuppressWarnings("serial")
public class TableSchema implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Property(name = "idConfig")
	protected IdSchema idConfig;
	
	@Property(name = "fixedFieldConfigMap")
	protected Map<String, FixedFieldConfig> fixedFieldConfigMap = new Map<String, FixedFieldConfig>();
	
	@Property(name = "variableFieldConfigMap")
	protected Map<String, VariableFieldConfig> variableFieldConfigMap = new Map<String, VariableFieldConfig>();
	
	protected TableSchema()
	{
		
	}
	
	public TableSchema(String name, IdSchema idConfig)
	{
		this.name = name;
		this.idConfig = idConfig;
	}
	
	public String getName()
	{
		return name;
	}
	
	public IdSchema getIdConfig()
	{
		return idConfig;
	}
	
	public TableSchema setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public TableSchema setIdConfig(IdSchema idConfig)
	{
		this.idConfig = idConfig;
		return this;
	}
	
	public FixedFieldConfig getFixedFieldConfig(String fieldName)
	{
		return fixedFieldConfigMap.get(fieldName);
	}
	
	public TableSchema putFixedFieldConfig(String fieldName, FixedFieldConfig fixedFieldConfig)
	{
		fixedFieldConfigMap.put(fieldName, fixedFieldConfig);
		return this;
	}
	
	public TableSchema removeFixedFieldConfig(String fieldName)
	{
		fixedFieldConfigMap.remove(fieldName);
		return this;
	}
	
	public VariableFieldConfig getVariableFieldConfig(String fieldName)
	{
		return variableFieldConfigMap.get(fieldName);
	}
	
	public TableSchema putVariableFieldConfig(String fieldName, VariableFieldConfig variableFieldConfig)
	{
		variableFieldConfigMap.put(fieldName, variableFieldConfig);
		return this;
	}
	
	public TableSchema removeVariableFieldConfig(String fieldName)
	{
		variableFieldConfigMap.remove(fieldName);
		return this;
	}
	
}
