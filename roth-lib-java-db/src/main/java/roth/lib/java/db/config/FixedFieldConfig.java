package roth.lib.java.db.config;

import roth.lib.java.annotation.Entity;
import roth.lib.java.db.type.DataType;

@Entity
@SuppressWarnings("serial")
public class FixedFieldConfig extends FieldConfig
{
	protected int offset;
	
	protected FixedFieldConfig()
	{
		
	}
	
	public FixedFieldConfig(String name, DataType dataType, boolean nullable)
	{
		super(name, dataType, nullable);
	}
	
	public int getOffset()
	{
		return offset;
	}
	
	public FixedFieldConfig setOffset(int offset)
	{
		this.offset = offset;
		return this;
	}
	
}
