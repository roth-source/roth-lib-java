package roth.lib.java.db.schema;

import roth.lib.java.lang.Map;

@SuppressWarnings("serial")
public class DbSchema extends Schema
{
	protected String name;
	protected Map<String, EnvSchema> envSchemaMap = new  Map<String, EnvSchema>();
	
	protected DbSchema()
	{
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public DbSchema setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public byte[] toBytes()
	{
		return null;
	}
	
}
