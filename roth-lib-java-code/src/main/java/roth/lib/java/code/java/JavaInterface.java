package roth.lib.java.code.java;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class JavaInterface extends JavaEntity
{
	protected List<JavaDefinition> definitions = new List<JavaDefinition>();
	protected List<JavaType> superTypes = new List<JavaType>();
	protected List<JavaField> staticFields = new List<JavaField>();
	protected List<JavaMethod> methods = new List<JavaMethod>();
	
	public JavaInterface()
	{
		
	}
	
	public List<JavaDefinition> getDefinitions()
	{
		return definitions;
	}
	
	public List<JavaType> getSuperTypes()
	{
		return superTypes;
	}
	
	public List<JavaField> getStaticFields()
	{
		return staticFields;
	}
	
	public List<JavaMethod> getMethods()
	{
		return methods;
	}
	
	public void setDefinitions(List<JavaDefinition> definitions)
	{
		this.definitions.addAll(definitions);
	}
	
	public void setSuperTypes(List<JavaType> superTypes)
	{
		this.superTypes.addAll(superTypes);
	}
	
	public void setStaticFields(List<JavaField> staticFields)
	{
		this.staticFields.addAll(staticFields);
	}
	
	public void setMethods(List<JavaMethod> methods)
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
