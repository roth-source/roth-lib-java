package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaInterface extends JavaEntity
{
	protected LinkedList<JavaDefinition> definitions = new LinkedList<JavaDefinition>();
	protected LinkedList<JavaType> superTypes = new LinkedList<JavaType>();
	protected LinkedList<JavaField> staticFields = new LinkedList<JavaField>();
	protected LinkedList<JavaMethod> methods = new LinkedList<JavaMethod>();
	
	public JavaInterface()
	{
		
	}
	
	public LinkedList<JavaDefinition> getDefinitions()
	{
		return definitions;
	}
	
	public LinkedList<JavaType> getSuperTypes()
	{
		return superTypes;
	}
	
	public LinkedList<JavaField> getStaticFields()
	{
		return staticFields;
	}
	
	public LinkedList<JavaMethod> getMethods()
	{
		return methods;
	}
	
	public void setDefinitions(LinkedList<JavaDefinition> definitions)
	{
		this.definitions.addAll(definitions);
	}
	
	public void setSuperTypes(LinkedList<JavaType> superTypes)
	{
		this.superTypes.addAll(superTypes);
	}
	
	public void setStaticFields(LinkedList<JavaField> staticFields)
	{
		this.staticFields.addAll(staticFields);
	}
	
	public void setMethods(LinkedList<JavaMethod> methods)
	{
		this.methods.addAll(methods);
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		
		return builder.toString();
	}
	
}
