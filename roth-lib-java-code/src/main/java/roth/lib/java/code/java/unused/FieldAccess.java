package roth.lib.java.code.java.unused;

import roth.lib.java.code.java.JavaCode;
import roth.lib.java.code.java.JavaExpression;

@SuppressWarnings("serial")
public class FieldAccess extends JavaCode implements JavaExpression
{
	protected String selected;
	protected String name;
	
	public FieldAccess()
	{
		
	}
	
	@Override
	public String toString()
	{
		return toSource(0);
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuffer buffer = new StringBuffer();
		
		return buffer.toString();
	}
	
}
