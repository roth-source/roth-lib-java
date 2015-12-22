package roth.lib.java.reflector;

import java.lang.reflect.Type;
import java.util.LinkedHashSet;

import roth.lib.java.annotation.Entity;
import roth.lib.java.form.FormMapper;
import roth.lib.java.json.JsonMapper;
import roth.lib.java.mapper.Mapper;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.util.ReflectionUtil;
import roth.lib.java.xml.XmlMapper;

public class MapperReflector
{
	protected static MapperReflector instance;
	
	protected LinkedHashSet<String> nonEntityTypes = new LinkedHashSet<String>();
	
	public MapperReflector()
	{
		
	}
	
	public static MapperReflector get()
	{
		if(instance == null)
		{
			instance = new MapperReflector();
		}
		return instance;
	}
	
	public boolean isEntity(Type type)
	{
		boolean entity = false;
		Class<?> klass = ReflectionUtil.getTypeClass(type);
		if(!nonEntityTypes.contains(klass.getName()))
		{
			entity = getEntityAnnotation(type) != null;
		}
		return entity;
	}
	
	public Entity getEntityAnnotation(Type type)
	{
		Entity entity = null;
		Class<?> klass = ReflectionUtil.getTypeClass(type);
		if(!klass.getName().startsWith("java."))
		{
			entity = klass.getDeclaredAnnotation(Entity.class);
			if(entity == null)
			{
				Class<?> superClass = klass.getSuperclass();
				if(superClass != null)
				{
					entity = getEntityAnnotation(superClass);
				}
			}
		}
		else
		{
			nonEntityTypes.add(klass.getName());
		}
		return entity;
	}
	
	public EntityReflector getEntityReflector(Type type)
	{
		EntityReflector entityReflector = null;
		Class<?> klass = ReflectionUtil.getTypeClass(type);
		if(!nonEntityTypes.contains(type))
		{
			Entity entity = getEntityAnnotation(type);
			if(entity != null)
			{
				entityReflector = new EntityReflector(type, entity);
			}
			else
			{
				nonEntityTypes.add(klass.getName());
			}
		}
		return entityReflector;
	}
	
	public Mapper getMapper(MapperType type)
	{
		return getMapper(type, MapperConfig.get());
	}
	
	public Mapper getMapper(MapperType type, MapperConfig mapperConfig)
	{
		Mapper mapper = null;
		switch(type)
		{
			case JSON:
			{
				mapper = getJsonMapper(mapperConfig);
				break;
			}
			case XML:
			{
				mapper = getXmlMapper(mapperConfig);
				break;
			}
			case FORM:
			{
				mapper = getFormMapper(mapperConfig);
				break;
			}
			case TABLE:
			{
				//mapper = getTableMapper(mapperConfig);
				break;
			}
			default:
			{
				mapper = getJsonMapper(mapperConfig);
				break;
			}
		}
		return mapper;
	}
	
	public JsonMapper getJsonMapper()
	{
		return getJsonMapper(MapperConfig.get());
	}
	
	public JsonMapper getJsonMapper(MapperConfig mapperConfig)
	{
		return new JsonMapper(mapperConfig);
	}

	public XmlMapper getXmlMapper()
	{
		return getXmlMapper(MapperConfig.get());
	}
	
	public XmlMapper getXmlMapper(MapperConfig mapperConfig)
	{
		return new XmlMapper(mapperConfig);
	}

	public FormMapper getFormMapper()
	{
		return getFormMapper(MapperConfig.get());
	}
	
	public FormMapper getFormMapper(MapperConfig mapperConfig)
	{
		return new FormMapper(mapperConfig);
	}
	/*
	public TableMapper getTableMapper()
	{
		return getTableMapper(MapperConfig.get());
	}
	
	public TableMapper getTableMapper(MapperConfig mapperConfig)
	{
		return new TableMapper(mapperConfig);
	}
	*/
}
