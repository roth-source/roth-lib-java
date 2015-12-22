package roth.lib.java.deserializer;

public abstract class PrimitiveDeserializer<T> extends Deserializer<T>
{
	protected boolean nullable;
	
	protected PrimitiveDeserializer(boolean nullable)
	{
		this.nullable = nullable;
	}
	
	public boolean isNullable()
	{
		return nullable;
	}
	
}
