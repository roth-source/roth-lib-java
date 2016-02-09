package roth.lib.java.db.config;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.lang.Map;

@Entity
@SuppressWarnings("serial")
public class DbConfig implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Property(name = "tableConfigMap")
	protected Map<String, TableConfig> tableConfigMap = new  Map<String, TableConfig>();
	
	@Property(name = "enumConfigMap")
	protected Map<String, EnumConfig> enumConfigMap = new  Map<String, EnumConfig>();
	
	protected DbConfig()
	{
		
	}
	
	public DbConfig(String name)
	{
		setName(name);
	}
	
	public String getName()
	{
		return name;
	}
	
	public DbConfig setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public TableConfig getTableConfig(String tableName)
	{
		return tableConfigMap.get(tableName);
	}
	
	public DbConfig putTableConfig(String tableName, TableConfig tableConfig)
	{
		tableConfigMap.put(tableName, tableConfig);
		return this;
	}
	
	public DbConfig removeTableConfig(String tableName)
	{
		tableConfigMap.remove(tableName);
		return this;
	}
	
	public EnumConfig getEnumConfig(String enumName)
	{
		return enumConfigMap.get(enumName);
	}
	
	public DbConfig putEnumConfig(String enumName, EnumConfig enumConfig)
	{
		enumConfigMap.put(enumName, enumConfig);
		return this;
	}
	
	public DbConfig removeEnumConfig(String enumName)
	{
		enumConfigMap.remove(enumName);
		return this;
	}
	
}
