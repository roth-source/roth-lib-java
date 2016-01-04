package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaEnum extends JavaEntity
{
	protected LinkedList<JavaType> interfaceTypes = new LinkedList<JavaType>();
	protected LinkedList<JavaField> staticFields = new LinkedList<JavaField>();
	protected LinkedList<JavaInitializer> staticInitializers = new LinkedList<JavaInitializer>();
	protected LinkedList<JavaMethod> staticMethods = new LinkedList<JavaMethod>();
	protected LinkedList<JavaEntity> staticEntities = new LinkedList<JavaEntity>();
	protected LinkedList<JavaField> fields = new LinkedList<JavaField>();
	protected LinkedList<JavaInitializer> initializers = new LinkedList<JavaInitializer>();
	protected LinkedList<JavaConstructor> constructors = new LinkedList<JavaConstructor>();
	protected LinkedList<JavaMethod> methods = new LinkedList<JavaMethod>();
	protected LinkedList<JavaEntity> entities = new LinkedList<JavaEntity>();
	
	public JavaEnum()
	{
		
	}
	
	public LinkedList<JavaType> getInterfaceTypes()
	{
		return interfaceTypes;
	}
	
	public LinkedList<JavaField> getStaticFields()
	{
		return staticFields;
	}
	
	public LinkedList<JavaInitializer> getStaticInitializers()
	{
		return staticInitializers;
	}
	
	public LinkedList<JavaMethod> getStaticMethods()
	{
		return staticMethods;
	}
	
	public LinkedList<JavaEntity> getStaticEntities()
	{
		return staticEntities;
	}
	
	public LinkedList<JavaField> getFields()
	{
		return fields;
	}
	
	public LinkedList<JavaInitializer> getInitializers()
	{
		return initializers;
	}
	
	public LinkedList<JavaConstructor> getConstructors()
	{
		return constructors;
	}
	
	public LinkedList<JavaMethod> getMethods()
	{
		return methods;
	}
	
	public LinkedList<JavaEntity> getEntities()
	{
		return entities;
	}
	
	public void setInterfaceTypes(LinkedList<JavaType> interfaceTypes)
	{
		this.interfaceTypes.addAll(interfaceTypes);
	}
	
	public void setStaticFields(LinkedList<JavaField> staticFields)
	{
		this.staticFields.addAll(staticFields);
	}
	
	public void setStaticInitializers(LinkedList<JavaInitializer> staticInitializers)
	{
		this.staticInitializers.addAll(staticInitializers);
	}
	
	public void setStaticMethods(LinkedList<JavaMethod> staticMethods)
	{
		this.staticMethods.addAll(staticMethods);
	}
	
	public void setStaticEntities(LinkedList<JavaEntity> staticEntities)
	{
		this.staticEntities.addAll(staticEntities);
	}
	
	public void setFields(LinkedList<JavaField> fields)
	{
		this.fields.addAll(fields);
	}
	
	public void setInitializers(LinkedList<JavaInitializer> initializers)
	{
		this.initializers.addAll(initializers);
	}
	
	public void setConstructors(LinkedList<JavaConstructor> constructors)
	{
		this.constructors.addAll(constructors);
	}
	
	public void setMethods(LinkedList<JavaMethod> methods)
	{
		this.methods.addAll(methods);
	}
	
	public void setEntities(LinkedList<JavaEntity> entities)
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
