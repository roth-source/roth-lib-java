package roth.lib.java.code.java;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class JavaEnum extends JavaEntity
{
	protected List<JavaType> interfaceTypes = new List<JavaType>();
	protected List<JavaField> staticFields = new List<JavaField>();
	protected List<JavaInitializer> staticInitializers = new List<JavaInitializer>();
	protected List<JavaMethod> staticMethods = new List<JavaMethod>();
	protected List<JavaEntity> staticEntities = new List<JavaEntity>();
	protected List<JavaField> fields = new List<JavaField>();
	protected List<JavaInitializer> initializers = new List<JavaInitializer>();
	protected List<JavaConstructor> constructors = new List<JavaConstructor>();
	protected List<JavaMethod> methods = new List<JavaMethod>();
	protected List<JavaEntity> entities = new List<JavaEntity>();
	
	public JavaEnum()
	{
		
	}
	
	public List<JavaType> getInterfaceTypes()
	{
		return interfaceTypes;
	}
	
	public List<JavaField> getStaticFields()
	{
		return staticFields;
	}
	
	public List<JavaInitializer> getStaticInitializers()
	{
		return staticInitializers;
	}
	
	public List<JavaMethod> getStaticMethods()
	{
		return staticMethods;
	}
	
	public List<JavaEntity> getStaticEntities()
	{
		return staticEntities;
	}
	
	public List<JavaField> getFields()
	{
		return fields;
	}
	
	public List<JavaInitializer> getInitializers()
	{
		return initializers;
	}
	
	public List<JavaConstructor> getConstructors()
	{
		return constructors;
	}
	
	public List<JavaMethod> getMethods()
	{
		return methods;
	}
	
	public List<JavaEntity> getEntities()
	{
		return entities;
	}
	
	public void setInterfaceTypes(List<JavaType> interfaceTypes)
	{
		this.interfaceTypes.addAll(interfaceTypes);
	}
	
	public void setStaticFields(List<JavaField> staticFields)
	{
		this.staticFields.addAll(staticFields);
	}
	
	public void setStaticInitializers(List<JavaInitializer> staticInitializers)
	{
		this.staticInitializers.addAll(staticInitializers);
	}
	
	public void setStaticMethods(List<JavaMethod> staticMethods)
	{
		this.staticMethods.addAll(staticMethods);
	}
	
	public void setStaticEntities(List<JavaEntity> staticEntities)
	{
		this.staticEntities.addAll(staticEntities);
	}
	
	public void setFields(List<JavaField> fields)
	{
		this.fields.addAll(fields);
	}
	
	public void setInitializers(List<JavaInitializer> initializers)
	{
		this.initializers.addAll(initializers);
	}
	
	public void setConstructors(List<JavaConstructor> constructors)
	{
		this.constructors.addAll(constructors);
	}
	
	public void setMethods(List<JavaMethod> methods)
	{
		this.methods.addAll(methods);
	}
	
	public void setEntities(List<JavaEntity> entities)
	{
		this.entities.addAll(entities);
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		
		return builder.toString();
	}
	
}
