package roth.lib.java.code.java;

import static roth.lib.java.code.java.JavaTag.CLASS;
import static roth.lib.java.code.java.JavaTag.EXTENDS;
import static roth.lib.java.code.java.JavaTag.IMPLEMENTS;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaClass extends JavaEntity
{
	protected LinkedList<JavaDefinition> definitions = new LinkedList<JavaDefinition>();
	protected JavaType superType;
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
	
	public JavaClass()
	{
		
	}
	
	public LinkedList<JavaDefinition> getDefinitions()
	{
		return definitions;
	}
	
	public JavaType getSuperType()
	{
		return superType;
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
	
	public void setDefinitions(LinkedList<JavaDefinition> definitions)
	{
		this.definitions.addAll(definitions);
	}
	
	public void setSuperType(JavaType superType)
	{
		this.superType = superType;
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
