package roth.lib.java.code.java.unused;

import roth.lib.java.code.java.JavaCode;
import roth.lib.java.code.java.JavaExpression;

@SuppressWarnings("serial")
public class Literal extends JavaCode implements JavaExpression
{
	public String value;
	
	public Literal()
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
		buffer.append(value);
		return buffer.toString();
	}
	
}
