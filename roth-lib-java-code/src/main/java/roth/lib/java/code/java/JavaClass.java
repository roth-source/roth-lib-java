package roth.lib.java.code.java;

import static roth.lib.java.code.java.JavaTag.CLASS;
import static roth.lib.java.code.java.JavaTag.EXTENDS;
import static roth.lib.java.code.java.JavaTag.IMPLEMENTS;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class JavaClass extends JavaEntity
{
	protected List<JavaDefinition> definitions = new List<JavaDefinition>();
	protected JavaType superType;
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
	
	public JavaClass()
	{
		
	}
	
	public List<JavaDefinition> getDefinitions()
	{
		return definitions;
	}
	
	public JavaType getSuperType()
	{
		return superType;
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
	
	public void setDefinitions(List<JavaDefinition> definitions)
	{
		this.definitions.addAll(definitions);
	}
	
	public void setSuperType(JavaType superType)
	{
		this.superType = superType;
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
		String indent = indent(tabs);
		String memberIndent = indent(tabs + 1);
		builder.append(super.toSource(tabs));
		builder.append(indent);
		if(access != null)
		{
			builder.append(access);
			builder.append(SPACE);
		}
		for(JavaModifier modifier : modifiers)
		{
			builder.append(modifier);
			builder.append(SPACE);
		}
		builder.append(CLASS);
		builder.append(SPACE);
		builder.append(name);
		if(!definitions.isEmpty())
		{
			builder.append(LEFT_ANGLE_BRACKET);
			String seperator = BLANK;
			for(JavaDefinition definition : definitions)
			{
				builder.append(seperator);
				builder.append(definition);
				seperator = COMMA_SEPERATOR;
			}
			builder.append(RIGHT_ANGLE_BRACKET);
		}
		if(superType != null)
		{
			builder.append(SPACE);
			builder.append(EXTENDS);
			builder.append(SPACE);
			builder.append(superType);
		}
		if(!interfaceTypes.isEmpty())
		{
			builder.append(SPACE);
			builder.append(IMPLEMENTS);
			builder.append(SPACE);
			String seperator = BLANK;
			for(JavaType interfaceType : interfaceTypes)
			{
				builder.append(seperator);
				builder.append(interfaceType);
				seperator = COMMA_SEPERATOR;
			}
		}
		builder.append(NEW_LINE);
		builder.append(indent);
		builder.append(LEFT_BRACE);
		builder.append(NEW_LINE);
		for(JavaField field : staticFields)
		{
			builder.append(field.toSource(tabs + 1));
			builder.append(NEW_LINE);
			builder.append(memberIndent);
			builder.append(NEW_LINE);
		}
		for(JavaInitializer initializer : staticInitializers)
		{
			builder.append(initializer.toSource(tabs + 1));
			builder.append(NEW_LINE);
			builder.append(memberIndent);
			builder.append(NEW_LINE);
		}
		for(JavaMethod method : staticMethods)
		{
			builder.append(method.toSource(tabs + 1));
			builder.append(NEW_LINE);
			builder.append(memberIndent);
			builder.append(NEW_LINE);
		}
		for(JavaEntity entity : staticEntities)
		{
			builder.append(entity.toSource(tabs + 1));
			builder.append(NEW_LINE);
			builder.append(memberIndent);
			builder.append(NEW_LINE);
		}
		for(JavaField field : fields)
		{
			builder.append(field.toSource(tabs + 1));
			builder.append(NEW_LINE);
			builder.append(memberIndent);
			builder.append(NEW_LINE);
		}
		for(JavaInitializer initializer : initializers)
		{
			builder.append(initializer.toSource(tabs + 1));
			builder.append(NEW_LINE);
			builder.append(memberIndent);
			builder.append(NEW_LINE);
		}
		for(JavaConstructor constructor : constructors)
		{
			builder.append(constructor.toSource(tabs + 1));
			builder.append(NEW_LINE);
			builder.append(memberIndent);
			builder.append(NEW_LINE);
		}
		for(JavaMethod method : methods)
		{
			builder.append(method.toSource(tabs + 1));
			builder.append(NEW_LINE);
			builder.append(memberIndent);
			builder.append(NEW_LINE);
		}
		for(JavaEntity entity : entities)
		{
			builder.append(entity.toSource(tabs + 1));
			builder.append(NEW_LINE);
			builder.append(memberIndent);
			builder.append(NEW_LINE);
		}
		builder.append(RIGHT_BRACE);
		return builder.toString();
	}
	
}
