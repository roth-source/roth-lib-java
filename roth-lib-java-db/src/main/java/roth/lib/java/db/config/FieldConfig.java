package roth.lib.java.db.config;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.db.type.DataType;

@Entity
@SuppressWarnings("serial")
public abstract class FieldConfig implements Serializable
{
	protected String name;
	protected DataType dataType;
	protected boolean nullable;
	protected Integer scale;
	protected String enumName;
	protected Object defaultValue;
	
	protected FieldConfig()
	{
		
	}
	
	public FieldConfig(String name, DataType dataType, boolean nullable)
	{
		setName(name);
		setDataType(dataType);
		setNullable(nullable);
	}
	
	public String getName()
	{
		return name;
	}
	
	public DataType getDataType()
	{
		return dataType;
	}
	
	public boolean isNullable()
	{
		return nullable;
	}
	
	public Integer getScale()
	{
		return scale;
	}
	
	public String getEnumName()
	{
		return enumName;
	}
	
	public Object getDefaultValue()
	{
		return defaultValue;
	}
	
	public FieldConfig setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public FieldConfig setDataType(DataType dataType)
	{
		this.dataType = dataType;
		return this;
	}
	
	public FieldConfig setNullable(boolean nullable)
	{
		this.nullable = nullable;
		return this;
	}
	
	public FieldConfig setScale(Integer scale)
	{
		this.scale = scale;
		return this;
	}
	
	public FieldConfig setEnumName(String enumName)
	{
		this.enumName = enumName;
		return this;
	}
	
	public FieldConfig setDefaultValue(Object defaultValue)
	{
		this.defaultValue = defaultValue;
		return this;
	}
	
}
