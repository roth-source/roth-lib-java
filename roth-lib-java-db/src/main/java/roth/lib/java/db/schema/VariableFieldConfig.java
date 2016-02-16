package roth.lib.java.db.schema;

import roth.lib.java.annotation.Entity;
import roth.lib.java.db.type.DataType;
import roth.lib.java.db.type.VariableType;

@Entity
@SuppressWarnings("serial")
public class VariableFieldConfig extends FieldSchema
{
	protected VariableType variableType;
	
	protected VariableFieldConfig()
	{
		
	}
	
	public VariableFieldConfig(String name, DataType dataType, boolean nullable, VariableType variableType)
	{
		super(name, dataType, nullable);
		setVariableType(variableType);
	}
	
	public VariableType getVariableType()
	{
		return variableType;
	}
	
	public VariableFieldConfig setVariableType(VariableType variableType)
	{
		this.variableType = variableType;
		return this;
	}
	
}
