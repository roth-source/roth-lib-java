package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaDefinition extends JavaCode
{
	protected String name;
	protected LinkedList<JavaType> boundTypes = new LinkedList<JavaType>();
	
	public JavaDefinition()
	{
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public LinkedList<JavaType> getBoundTypes()
	{
		return boundTypes;
	}
	
	public void setBoundTypes(LinkedList<JavaType> boundTypes)
	{
		this.boundTypes = boundTypes;
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
		buffer.append(name);
		String seperator = " extends ";
		for(JavaType boundType : boundTypes)
		{
			buffer.append(seperator);
			buffer.append(boundType);
			seperator = " & ";
		}
		return buffer.toString();
	}
	
}
