package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaType extends JavaGeneric
{
	protected String qualifiedName;
	protected String name;
	protected LinkedList<JavaGeneric> generics = new LinkedList<JavaGeneric>();
	protected int dimensions;
	
	public JavaType()
	{
		
	}
	
	public String getQualifiedName()
	{
		return qualifiedName;
	}
	
	public void setQualifiedName(String qualifiedName)
	{
		this.qualifiedName = qualifiedName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public LinkedList<JavaGeneric> getGenerics()
	{
		return generics;
	}
	
	public void setGenerics(LinkedList<JavaGeneric> generics)
	{
		this.generics = generics;
	}
	
	public int getDimensions()
	{
		return dimensions;
	}
	
	public void setDimensions(int dimensions)
	{
		this.dimensions = dimensions;
	}
	
	public void addDimension()
	{
		dimensions++;
	}
	
	@Override
	public String toString()
	{
		return toSource(0);
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		if(!generics.isEmpty())
		{
			builder.append("<");
			String seperator = "";
			for(JavaGeneric generic : generics)
			{
				builder.append(seperator);
				builder.append(generic);
				seperator = ", ";
			}
			builder.append(">");
		}
		for(int i = 0; i < dimensions; i++)
		{
			builder.append("[]");
		}
		return builder.toString();
	}
	
}
