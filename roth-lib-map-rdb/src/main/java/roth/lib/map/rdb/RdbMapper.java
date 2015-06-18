package roth.lib.map.rdb;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import roth.lib.annotation.Property;
import roth.lib.map.Mapper;
import roth.lib.map.PropertyField;
import roth.lib.map.mapper.PropertyMapper;
import roth.lib.map.rdb.sql.Delete;
import roth.lib.map.rdb.sql.Insert;
import roth.lib.map.rdb.sql.Select;
import roth.lib.map.rdb.sql.Update;
import roth.lib.map.rdb.sql.Where;
import roth.lib.map.rdb.sql.Wheres;

public class RdbMapper extends Mapper
{
	
	public RdbMapper()
	{
		super();
		propertyMappers.add(new PropertyMapper<Property>(Property.class)
		{
			@Override
			public String getPropertyName(Field field, Property property)
			{
				if(property != null && property.rdb())
				{
					if(isValid(property.rdbName()))
					{
						return property.rdbName();
					}
					else if(isValid(property.name()))
					{
						return property.name();
					}
				}
				return null;
			}
			
			@Override
			public boolean isEntityName(Field field, Property property)
			{
				return property != null && property.entityName();
			}
		});
	}
	
	public PropertyField getGeneratedIdPropertyField(Class<?> klass)
	{
		for(PropertyField propertyField : getPropertyFields(klass))
		{
			if(propertyField.isId() && propertyField.isGenerated())
			{
				return propertyField;
			}
		}
		return null;
	}
	
	public boolean isGenerated(Class<?> klass)
	{
		return getGeneratedIdPropertyField(klass) != null;
	}
	
	public void setGeneratedIds(RdbResultSet resultSet, RdbModel model)
	{
		try
		{
			PropertyField generatedIdPropertyField = getGeneratedIdPropertyField(model.getClass());
			if(generatedIdPropertyField != null && resultSet.next())
			{
				Field field = generatedIdPropertyField.getField();
				field.set(model, resultSet.getObject(1, field.getType()));
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public Wheres toIdWheres(RdbModel model)
	{
		Wheres wheres = new Wheres();
		wheres = new Wheres();
		for(PropertyField idPropertyField : getIdPropertyFields(model.getClass()))
		{
			try
			{
				String name = idPropertyField.getPropertyName();
				Field field = idPropertyField.getField();
				Object value = field.get(model);
				if(value != null)
				{
					wheres.and(Where.equals(name, value));
				}
			}
			catch(Exception e)
			{
				
			}
		}
		return wheres;
	}
	
	public Select toSelect(RdbModel model)
	{
		String table = getEntityName(model.getClass());
		Wheres wheres = toIdWheres(model);
		if(!wheres.isEmpty())
		{
			return new Select(table).wheres(wheres);
		}
		return null;
	}
	
	public Insert toInsert(RdbModel model)
	{
		String table = getEntityName(model.getClass());
		LinkedHashMap<String, Object> nameValues = new LinkedHashMap<String, Object>();
		for(Entry<String, PropertyField> propertyNameFieldEntry : getPropertyNameFieldMap(model.getClass()).entrySet())
		{
			String column = propertyNameFieldEntry.getKey();
			PropertyField propertyField = propertyNameFieldEntry.getValue();
			Field field = propertyField.getField();
			try
			{
				Object value = field.get(model);
				nameValues.put(column, value);
			}
			catch(Exception e)
			{
				
			}
		}
		if(!nameValues.isEmpty())
		{
			return new Insert(table, nameValues);
		}
		return null;
	}
	
	public Update toUpdate(RdbModel model)
	{
		String table = getEntityName(model.getClass());
		LinkedHashMap<String, Object> nameValues = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, PropertyField> nameFields = getFieldNameFieldMap(model.getClass());
		if(model.isDirty())
		{
			for(String name : model.getDirtyNames())
			{
				PropertyField propertyField = nameFields.get(name);
				if(propertyField != null)
				{
					String column = propertyField.getPropertyName();
					Field field = propertyField.getField();
					try
					{
						Object value = field.get(model);
						nameValues.put(column, value);
					}
					catch(Exception e)
					{
						
					}
				}
			}
		}
		Wheres wheres = null;
		if(!nameValues.isEmpty() && !(wheres = toIdWheres(model)).isEmpty())
		{
			return new Update(table, nameValues).wheres(wheres);
		}
		return null;
	}
	
	public Delete toDelete(RdbModel model)
	{
		String table = getEntityName(model.getClass());
		Wheres wheres = toIdWheres(model);
		if(!wheres.isEmpty())
		{
			return new Delete(table).wheres(wheres);
		}
		return null;
	}
	
	public <T> LinkedList<T> fromRdb(RdbResultSet resultSet, Rdb rdb, Class<T> klass)
	{
		LinkedList<T> models = new LinkedList<T>();
		try
		{
			while(resultSet.next())
			{
				T model = null;
				try
				{
					Constructor<T> constructor = null;
					try
					{
						constructor = klass.getDeclaredConstructor(Rdb.class);
						constructor.setAccessible(true);
						model = constructor.newInstance(rdb);
					}
					catch(NoSuchMethodException e)
					{
						constructor = klass.getDeclaredConstructor();
						constructor.setAccessible(true);
						model = constructor.newInstance();
					}
					if(model instanceof RdbModel)
					{
						((RdbModel) model).persisted();
					}
					LinkedHashMap<String, PropertyField> propertyNameFieldMap = rdb.getMapper().getPropertyNameFieldMap(klass);
					ResultSetMetaData metaData = resultSet.getMetaData();
					for(int i = 1; i <= metaData.getColumnCount(); i++)
					{
						String columnLabel = metaData.getColumnLabel(i);
						PropertyField propertyField = propertyNameFieldMap.get(columnLabel);
						if(propertyField != null)
						{
							Field field = propertyField.getField();
							field.set(model, resultSet.getObject(columnLabel, field.getType()));
						}
					}
				}
				catch(Exception e)
				{
					
				}
				if(model != null)
				{
					models.add(model);
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return models;
	}
	
	public LinkedList<LinkedHashMap<String, Object>> fromRdb(RdbResultSet resultSet)
	{
		LinkedList<LinkedHashMap<String, Object>> dataMaps = new LinkedList<LinkedHashMap<String, Object>>();
		try
		{
			while(resultSet.next())
			{
				LinkedHashMap<String, Object> dataMap = new LinkedHashMap<String, Object>();
				try
				{
					ResultSetMetaData metaData = resultSet.getMetaData();
					for(int i = 1; i <= metaData.getColumnCount(); i++)
					{
						String columnLabel = metaData.getColumnLabel(i);
						dataMap.put(columnLabel, resultSet.getObject(columnLabel));
					}
				}
				catch(Exception e)
				{
					
				}
				if(!dataMap.isEmpty())
				{
					dataMaps.add(dataMap);
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return dataMaps;
	}
	/*
	@Override
	public LinkedHashMap<String, PropertyField> getPropertyNameFieldMap(Type type)
	{
		return new RdbHashMap<PropertyField>(super.getPropertyNameFieldMap(type));
	}
	*/
}
