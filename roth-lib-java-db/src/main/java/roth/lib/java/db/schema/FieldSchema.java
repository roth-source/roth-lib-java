package roth.lib.java.db.schema;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.db.type.DataType;

@Entity
@SuppressWarnings("serial")
public abstract class FieldSchema implements Serializable
{
	protected String name;
	protected DataType dataType;
	protected boolean nullable;
	protected Integer scale;
	protected String enumName;
	protected Object defaultValue;
	
	protected FieldSchema()
	{
		
	}
	
	public FieldSchema(String name, DataType dataType, boolean nullable)
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
	
	public FieldSchema setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public FieldSchema setDataType(DataType dataType)
	{
		this.dataType = dataType;
		return this;
	}
	
	public FieldSchema setNullable(boolean nullable)
	{
		this.nullable = nullable;
		return this;
	}
	
	public FieldSchema setScale(Integer scale)
	{
		this.scale = scale;
		return this;
	}
	
	public FieldSchema setEnumName(String enumName)
	{
		this.enumName = enumName;
		return this;
	}
	
	public FieldSchema setDefaultValue(Object defaultValue)
	{
		this.defaultValue = defaultValue;
		return this;
	}
	
}
