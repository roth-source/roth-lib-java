package roth.lib.java.code.java;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class JavaDefinition extends JavaCode
{
	protected String name;
	protected List<JavaType> boundTypes = new List<JavaType>();
	
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
	
	public List<JavaType> getBoundTypes()
	{
		return boundTypes;
	}
	
	public void setBoundTypes(List<JavaType> boundTypes)
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
