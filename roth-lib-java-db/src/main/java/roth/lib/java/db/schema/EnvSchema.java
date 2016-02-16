package roth.lib.java.db.schema;

import roth.lib.java.lang.Map;

@SuppressWarnings("serial")
public class EnvSchema extends Schema
{
	protected String name;
	protected Map<String, TableSchema> tableSchemaMap = new  Map<String, TableSchema>();
	protected Map<String, EnumSchema> enumSchemaMap = new  Map<String, EnumSchema>();
	
	public EnvSchema()
	{
		
	}
	
}
