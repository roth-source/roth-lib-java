package roth.lib.reflector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import roth.lib.accessor.GetEntityNameAccessor;
import roth.lib.accessor.GetExcludesAccessor;
import roth.lib.accessor.GetPropertyNameAccessor;
import roth.lib.accessor.GetTimeFormatAccessor;
import roth.lib.accessor.IsEntityAccessor;
import roth.lib.accessor.IsGeneratedAccessor;
import roth.lib.accessor.IsIdAccessor;
import roth.lib.annotation.Entity;
import roth.lib.annotation.Generate;
import roth.lib.annotation.Id;
import roth.lib.annotation.Property;
import roth.lib.util.ReflectionUtil;

public abstract class EntityReflector
{
	protected LinkedList<IsEntityAccessor<? extends Annotation>> isEntityAccessors = new LinkedList<IsEntityAccessor<? extends Annotation>>();
	protected LinkedList<GetEntityNameAccessor<? extends Annotation>> getEntityNameAccessors = new LinkedList<GetEntityNameAccessor<? extends Annotation>>();
	protected LinkedList<GetPropertyNameAccessor<? extends Annotation>> getPropertyNameAccessors = new LinkedList<GetPropertyNameAccessor<? extends Annotation>>();
	protected LinkedList<IsIdAccessor<? extends Annotation>> isIdAccessors = new LinkedList<IsIdAccessor<? extends Annotation>>();
	protected LinkedList<IsGeneratedAccessor<? extends Annotation>> isGeneratedAccessors = new LinkedList<IsGeneratedAccessor<? extends Annotation>>();
	protected LinkedList<GetTimeFormatAccessor<? extends Annotation>> getTimeFormatAccessors = new LinkedList<GetTimeFormatAccessor<? extends Annotation>>();
	protected LinkedList<GetExcludesAccessor<? extends Annotation>> getExcludesAccessors = new LinkedList<GetExcludesAccessor<? extends Annotation>>();
	
	protected LinkedHashMap<Type, LinkedList<PropertyReflector>> propertyReflectorsMap = new LinkedHashMap<Type, LinkedList<PropertyReflector>>();
	
	protected EntityReflector()
	{
		addIsEntityAccessor(new IsEntityAccessor<Entity>(Entity.class){});
		addGetEntityNameAccessor(new GetEntityNameAccessor<Entity>(Entity.class)
		{
			@Override
			public String getEntityName(Entity entity)
			{
				return entity.name();
			}	
		});
		addIsIdAccessor(new IsIdAccessor<Id>(Id.class){});
		addIsGeneratedAccessor(new IsGeneratedAccessor<Generate>(Generate.class){});
		addGetTimeFormatAccessor(new GetTimeFormatAccessor<Property>(Property.class)
		{
			@Override
			public String getTimeFormat(Property property)
			{
				return property.timeFormat();
			}
		});
		addGetExcludesAccessor(new GetExcludesAccessor<Property>(Property.class)
		{
			@Override
			public String[] getExcludes(Property property)
			{
				return property.exclude();
			}
		});
	}
	
	public EntityReflector addIsEntityAccessor(IsEntityAccessor<? extends Annotation> isEntityAccessor)
	{
		isEntityAccessors.addFirst(isEntityAccessor);
		return this;
	}
	
	public EntityReflector addGetEntityNameAccessor(GetEntityNameAccessor<? extends Annotation> getEntityNameAccessor)
	{
		getEntityNameAccessors.addFirst(getEntityNameAccessor);
		return this;
	}
	
	public EntityReflector addGetPropertyNameAccessor(GetPropertyNameAccessor<? extends Annotation> getPropertyNameAccessor)
	{
		getPropertyNameAccessors.addFirst(getPropertyNameAccessor);
		return this;
	}
	
	public EntityReflector addIsIdAccessor(IsIdAccessor<? extends Annotation> isIdAccessor)
	{
		isIdAccessors.addFirst(isIdAccessor);
		return this;
	}
	
	public EntityReflector addIsGeneratedAccessor(IsGeneratedAccessor<? extends Annotation> isGeneratedAccessor)
	{
		isGeneratedAccessors.addFirst(isGeneratedAccessor);
		return this;
	}
	
	public EntityReflector addGetTimeFormatAccessor(GetTimeFormatAccessor<? extends Annotation> getTimeFormatAccessor)
	{
		getTimeFormatAccessors.addFirst(getTimeFormatAccessor);
		return this;
	}
	
	public EntityReflector addGetExcludesAccessor(GetExcludesAccessor<? extends Annotation> getExcludesAccessor)
	{
		getExcludesAccessors.addFirst(getExcludesAccessor);
		return this;
	}
	
	public LinkedList<IsEntityAccessor<? extends Annotation>> getIsEntityAccessors()
	{
		return isEntityAccessors;
	}
	
	public LinkedList<GetEntityNameAccessor<? extends Annotation>> getGetEntityNameAccessors()
	{
		return getEntityNameAccessors;
	}
	
	public LinkedList<GetPropertyNameAccessor<? extends Annotation>> getGetPropertyNameAccessors()
	{
		return getPropertyNameAccessors;
	}
	
	public LinkedList<IsIdAccessor<? extends Annotation>> getIsIdAccessors()
	{
		return isIdAccessors;
	}
	
	public LinkedList<IsGeneratedAccessor<? extends Annotation>> getIsGeneratedAccessors()
	{
		return isGeneratedAccessors;
	}
	
	public LinkedList<GetTimeFormatAccessor<? extends Annotation>> getGetTimeFormatAccessors()
	{
		return getTimeFormatAccessors;
	}
	
	public LinkedList<GetExcludesAccessor<? extends Annotation>> getGetExcludesAccessors()
	{
		return getExcludesAccessors;
	}
	
	public LinkedHashMap<Type, LinkedList<PropertyReflector>> getPropertyReflectorsMap()
	{
		return propertyReflectorsMap;
	}
	
	public boolean isEntity(Type type)
	{
		for(IsEntityAccessor<? extends Annotation> isEntityAccessor : getIsEntityAccessors())
		{
			if(isEntityAccessor.isEntity(type))
			{
				return true;
			}
		}
		return false;
	}
	
	public String getEntityName(Type type)
	{
		for(GetEntityNameAccessor<? extends Annotation> getEntityNameAccessor : getGetEntityNameAccessors())
		{
			String entityName = getEntityNameAccessor.getEntityName(type);
			if(entityName != null)
			{
				return entityName;
			}
		}
		return null;
	}
	
	public String getPropertyName(Field field)
	{
		for(GetPropertyNameAccessor<? extends Annotation> getPropertyNameAccessor : getGetPropertyNameAccessors())
		{
			String propertyName = getPropertyNameAccessor.getPropertyName(field);
			if(propertyName != null)
			{
				return propertyName;
			}
		}
		return null;
	}
	
	public boolean isId(Field field)
	{
		for(IsIdAccessor<? extends Annotation> isIdAccessor : getIsIdAccessors())
		{
			if(isIdAccessor.isId(field))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isGenerated(Field field)
	{
		for(IsGeneratedAccessor<? extends Annotation> isGeneratedAccessor : getIsGeneratedAccessors())
		{
			if(isGeneratedAccessor.isGenerated(field))
			{
				return true;
			}
		}
		return false;
	}
	
	public String getTimeFormat(Field field)
	{
		for(GetTimeFormatAccessor<? extends Annotation> getTimeFormatAccessor : getGetTimeFormatAccessors())
		{
			String timeFormat = getTimeFormatAccessor.getTimeFormat(field);
			if(timeFormat != null)
			{
				return timeFormat;
			}
		}
		return null;
	}
	
	public LinkedList<String> getExcludes(Field field)
	{
		for(GetExcludesAccessor<? extends Annotation> getExcludesAccessor : getGetExcludesAccessors())
		{
			LinkedList<String> excludes = getExcludesAccessor.getExcludes(field);
			if(excludes != null && !excludes.isEmpty())
			{
				return excludes;
			}
		}
		return new LinkedList<String>();
	}
	
	public LinkedHashMap<String, PropertyReflector> getNamePropertyReflectorMap(Type type, boolean flexible)
	{
		LinkedHashMap<String, PropertyReflector> namePropertyReflectorMap = new LinkedHashMap<String, PropertyReflector>();
		for(PropertyReflector propertyReflector : getPropertyReflectors(type))
		{
			String name = flexible ? getFlexibleName(propertyReflector.getPropertyName()) : propertyReflector.getPropertyName();
			namePropertyReflectorMap.put(name, propertyReflector);
		}
		return namePropertyReflectorMap;
	}
	
	public String getFlexibleName(String name)
	{
		return name.toUpperCase();
	}
	
	public LinkedHashMap<String, PropertyReflector> getFieldPropertyReflectorMap(Type type)
	{
		LinkedHashMap<String, PropertyReflector> fieldPropertyReflectorMap = new LinkedHashMap<String, PropertyReflector>();
		for(PropertyReflector propertyReflector : getPropertyReflectors(type))
		{
			fieldPropertyReflectorMap.put(propertyReflector.getFieldName(), propertyReflector);
		}
		return fieldPropertyReflectorMap;
	}
	
	public LinkedList<PropertyReflector> getPropertyReflectors(Type type)
	{
		LinkedList<PropertyReflector> propertyReflectors = getPropertyReflectorsMap().get(type);
		if(propertyReflectors == null)
		{
			propertyReflectors = new LinkedList<PropertyReflector>();
			for(Field field : ReflectionUtil.getFields(type))
			{
				String propertyName = getPropertyName(field);
				if(propertyName != null)
				{
					field.setAccessible(true);
					Type fieldType = ReflectionUtil.getGenericType(type, field.getGenericType());
					boolean id = isId(field);
					boolean generated = isGenerated(field);
					String timeFormat = getTimeFormat(field);
					LinkedList<String> excludes = getExcludes(field);
					propertyReflectors.add(createPropertyReflector(field, fieldType, propertyName, id, generated, timeFormat, excludes));
				}
			}
			getPropertyReflectorsMap().put(type, propertyReflectors);
		}
		return propertyReflectors;
	}
	
	protected PropertyReflector createPropertyReflector(Field field, Type fieldType, String propertyName, boolean id, boolean generated, String timeFormat, LinkedList<String> excludes)
	{
		return new PropertyReflector(field, fieldType, propertyName, id, generated, timeFormat, excludes);
	}
	
	public LinkedList<PropertyReflector> getIdPropertyReflectors(Type type)
	{
		LinkedList<PropertyReflector> idPropertyReflectors = new LinkedList<PropertyReflector>();
		for(PropertyReflector propertyReflector : getPropertyReflectors(type))
		{
			if(propertyReflector.isId())
			{
				idPropertyReflectors.add(propertyReflector);
			}
		}
		return idPropertyReflectors;
	}
	
	public LinkedList<PropertyReflector> getGeneratedPropertyReflectors(Type type)
	{
		LinkedList<PropertyReflector> generatedPropertyReflectors = new LinkedList<PropertyReflector>();
		for(PropertyReflector propertyReflector : getPropertyReflectors(type))
		{
			if(propertyReflector.isGenerated())
			{
				generatedPropertyReflectors.add(propertyReflector);
			}
		}
		return generatedPropertyReflectors;
	}
	
	public boolean hasGeneratedPropertyReflectors(Type type)
	{
		for(PropertyReflector propertyReflector : getPropertyReflectors(type))
		{
			if(propertyReflector.isGenerated())
			{
				return true;
			}
		}
		return false;
	}
	
}
