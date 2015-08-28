package roth.lib.db;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import roth.lib.Callback;
import roth.lib.accessor.GetPropertyNameAccessor;
import roth.lib.annotation.Property;
import roth.lib.db.sql.Delete;
import roth.lib.db.sql.Insert;
import roth.lib.db.sql.Select;
import roth.lib.db.sql.Update;
import roth.lib.db.sql.Where;
import roth.lib.db.sql.Wheres;
import roth.lib.reflector.EntityReflector;
import roth.lib.reflector.PropertyReflector;

public abstract class DbReflector extends EntityReflector
{
	
	public DbReflector()
	{
		super();
		addGetPropertyNameAccessor(new GetPropertyNameAccessor<Property>(Property.class)
		{
			@Override
			public String getPropertyName(Property property)
			{
				if(property.db())
				{
					if(isValid(property.dbName()))
					{
						return property.dbName();
					}
					else if(isValid(property.name()))
					{
						return property.name();
					}
				}
				return null;
			}
		});
	}
	
	public PropertyReflector getGeneratedIdPropertyReflector(Class<?> klass)
	{
		for(PropertyReflector propertyReflector : getPropertyReflectors(klass))
		{
			if(propertyReflector.isId() && propertyReflector.isGenerated())
			{
				return propertyReflector;
			}
		}
		return null;
	}
	
	public boolean isGenerated(Class<?> klass)
	{
		return getGeneratedIdPropertyReflector(klass) != null;
	}
	
	public void setGeneratedIds(DbResultSet resultSet, DbModel model)
	{
		try
		{
			PropertyReflector generatedIdPropertyReflector = getGeneratedIdPropertyReflector(model.getClass());
			if(generatedIdPropertyReflector != null && resultSet.next())
			{
				Field field = generatedIdPropertyReflector.getField();
				Object value = getValue(resultSet, 1, field.getType());
				field.set(model, value);
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public Wheres toIdWheres(DbModel model)
	{
		Wheres wheres = new Wheres();
		wheres = new Wheres();
		for(PropertyReflector idPropertyFieldReflector : getIdPropertyReflectors(model.getClass()))
		{
			try
			{
				String name = idPropertyFieldReflector.getPropertyName();
				Field field = idPropertyFieldReflector.getField();
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
	
	public Select toSelect(DbModel model)
	{
		String table = getEntityName(model.getClass());
		Wheres wheres = toIdWheres(model);
		if(!wheres.isEmpty())
		{
			return new Select(table).wheres(wheres);
		}
		return null;
	}
	
	public Insert toInsert(DbModel model)
	{
		String table = getEntityName(model.getClass());
		LinkedHashMap<String, Object> nameValues = new LinkedHashMap<String, Object>();
		for(Entry<String, PropertyReflector> propertyReflectorEntry : getNamePropertyReflectorMap(model.getClass(), false).entrySet())
		{
			String column = propertyReflectorEntry.getKey();
			PropertyReflector propertyField = propertyReflectorEntry.getValue();
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
	
	public Update toUpdate(DbModel model)
	{
		String table = getEntityName(model.getClass());
		LinkedHashMap<String, Object> nameValues = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, PropertyReflector> fieldPropertyReflectors = getFieldPropertyReflectorMap(model.getClass());
		if(model.isDirty())
		{
			for(String name : model.getDirtyNames())
			{
				PropertyReflector propertyField = fieldPropertyReflectors.get(name);
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
	
	public Delete toDelete(DbModel model)
	{
		String table = getEntityName(model.getClass());
		Wheres wheres = toIdWheres(model);
		if(!wheres.isEmpty())
		{
			return new Delete(table).wheres(wheres);
		}
		return null;
	}
	
	public <T> void fromDb(DbResultSet resultSet, DbDataSource db, Callback<T> callback)
	{
		try
		{
			Class<T> klass = callback.getKlass();
			ResultSetMetaData metaData = resultSet.getMetaData();
			LinkedHashMap<String, PropertyReflector> namePropertyReflectorMap = getNamePropertyReflectorMap(klass, true);
			while(resultSet.next())
			{
				T model = fromDb(resultSet, db, klass, metaData, namePropertyReflectorMap);
				if(model != null)
				{
					callback.call(model);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public <T> LinkedList<T> fromDb(DbResultSet resultSet, DbDataSource db, Class<T> klass)
	{
		LinkedList<T> models = new LinkedList<T>();
		try
		{
			ResultSetMetaData metaData = resultSet.getMetaData();
			LinkedHashMap<String, PropertyReflector> namePropertyReflectorMap = getNamePropertyReflectorMap(klass, true);
			while(resultSet.next())
			{
				T model = fromDb(resultSet, db, klass, metaData, namePropertyReflectorMap);
				if(model != null)
				{
					models.add(model);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return models;
	}
	
	protected <T> T fromDb(DbResultSet resultSet, DbDataSource db, Class<T> klass, ResultSetMetaData metaData, LinkedHashMap<String, PropertyReflector> namePropertyReflectorMap)
	{
		T model = null;
		try
		{
			Constructor<T> constructor = null;
			try
			{
				constructor = klass.getDeclaredConstructor(DbDataSource.class);
				constructor.setAccessible(true);
				model = constructor.newInstance(db);
			}
			catch(NoSuchMethodException e)
			{
				constructor = klass.getDeclaredConstructor();
				constructor.setAccessible(true);
				model = constructor.newInstance();
			}
			if(model instanceof DbModel)
			{
				((DbModel) model).persisted();
			}
			for(int i = 1; i <= metaData.getColumnCount(); i++)
			{
				String columnLabel = metaData.getColumnLabel(i).toUpperCase();
				PropertyReflector propertyReflector = namePropertyReflectorMap.get(columnLabel);
				if(propertyReflector != null)
				{
					Field field = propertyReflector.getField();
					Object value = getValue(resultSet, columnLabel, field.getType());
					field.set(model, value);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	
	public LinkedList<LinkedHashMap<String, Object>> fromDb(DbResultSet resultSet)
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
					e.printStackTrace();
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
	
	public Object getValue(DbResultSet resultSet, String columnLabel, Class<?> klass) throws SQLException
	{
		Object value = null;
		if(String.class.isAssignableFrom(klass))
		{
			value = resultSet.getString(columnLabel);
		}
		else if(Enum.class.isAssignableFrom(klass))
		{
			value = resultSet.getEnum(columnLabel, klass);
		}
		else if(Calendar.class.isAssignableFrom(klass))
		{
			value = resultSet.getCalendar(columnLabel);
		}
		else if(Timestamp.class.isAssignableFrom(klass))
		{
			value = resultSet.getTimestamp(columnLabel);
		}
		else if(Time.class.isAssignableFrom(klass))
		{
			value = resultSet.getTime(columnLabel);
		}
		else if(Date.class.isAssignableFrom(klass))
		{
			value = resultSet.getDate(columnLabel);
		}
		else if(Boolean.class.isAssignableFrom(klass))
		{
			value = resultSet.getBooleanWrapper(columnLabel);
		}
		else if(Byte.class.isAssignableFrom(klass))
		{
			value = resultSet.getByteWrapper(columnLabel);
		}
		else if(Short.class.isAssignableFrom(klass))
		{
			value = resultSet.getShortWrapper(columnLabel);
		}
		else if(Integer.class.isAssignableFrom(klass))
		{
			value = resultSet.getIntegerWrapper(columnLabel);
		}
		else if(Long.class.isAssignableFrom(klass))
		{
			value = resultSet.getLongWrapper(columnLabel);
		}
		else if(Float.class.isAssignableFrom(klass))
		{
			value = resultSet.getFloatWrapper(columnLabel);
		}
		else if(Double.class.isAssignableFrom(klass))
		{
			value = resultSet.getDoubleWrapper(columnLabel);
		}
		else if(BigDecimal.class.isAssignableFrom(klass))
		{
			value = resultSet.getBigDecimal(columnLabel);
		}
		else if(boolean.class.isAssignableFrom(klass))
		{
			value = resultSet.getBoolean(columnLabel);
		}
		else if(byte.class.isAssignableFrom(klass))
		{
			value = resultSet.getByte(columnLabel);
		}
		else if(short.class.isAssignableFrom(klass))
		{
			value = resultSet.getShort(columnLabel);
		}
		else if(int.class.isAssignableFrom(klass))
		{
			value = resultSet.getInt(columnLabel);
		}
		else if(long.class.isAssignableFrom(klass))
		{
			value = resultSet.getLong(columnLabel);
		}
		else if(float.class.isAssignableFrom(klass))
		{
			value = resultSet.getFloat(columnLabel);
		}
		else if(double.class.isAssignableFrom(klass))
		{
			value = resultSet.getDouble(columnLabel);
		}
		else
		{
			value = resultSet.getObject(columnLabel, klass);
		}
		return value;
	}
	
	public Object getValue(DbResultSet resultSet, int columnIndex, Class<?> klass) throws SQLException
	{
		Object value = null;
		if(String.class.isAssignableFrom(klass))
		{
			value = resultSet.getString(columnIndex);
		}
		else if(Enum.class.isAssignableFrom(klass))
		{
			value = resultSet.getEnum(columnIndex, klass);
		}
		else if(Calendar.class.isAssignableFrom(klass))
		{
			value = resultSet.getCalendar(columnIndex);
		}
		else if(Timestamp.class.isAssignableFrom(klass))
		{
			value = resultSet.getTimestamp(columnIndex);
		}
		else if(Time.class.isAssignableFrom(klass))
		{
			value = resultSet.getTime(columnIndex);
		}
		else if(Date.class.isAssignableFrom(klass))
		{
			value = resultSet.getDate(columnIndex);
		}
		else if(Boolean.class.isAssignableFrom(klass))
		{
			value = resultSet.getBooleanWrapper(columnIndex);
		}
		else if(Byte.class.isAssignableFrom(klass))
		{
			value = resultSet.getByteWrapper(columnIndex);
		}
		else if(Short.class.isAssignableFrom(klass))
		{
			value = resultSet.getShortWrapper(columnIndex);
		}
		else if(Integer.class.isAssignableFrom(klass))
		{
			value = resultSet.getIntegerWrapper(columnIndex);
		}
		else if(Long.class.isAssignableFrom(klass))
		{
			value = resultSet.getLongWrapper(columnIndex);
		}
		else if(Float.class.isAssignableFrom(klass))
		{
			value = resultSet.getFloatWrapper(columnIndex);
		}
		else if(Double.class.isAssignableFrom(klass))
		{
			value = resultSet.getDoubleWrapper(columnIndex);
		}
		else if(BigDecimal.class.isAssignableFrom(klass))
		{
			value = resultSet.getBigDecimal(columnIndex);
		}
		else if(boolean.class.isAssignableFrom(klass))
		{
			value = resultSet.getBoolean(columnIndex);
		}
		else if(byte.class.isAssignableFrom(klass))
		{
			value = resultSet.getByte(columnIndex);
		}
		else if(short.class.isAssignableFrom(klass))
		{
			value = resultSet.getShort(columnIndex);
		}
		else if(int.class.isAssignableFrom(klass))
		{
			value = resultSet.getInt(columnIndex);
		}
		else if(long.class.isAssignableFrom(klass))
		{
			value = resultSet.getLong(columnIndex);
		}
		else if(float.class.isAssignableFrom(klass))
		{
			value = resultSet.getFloat(columnIndex);
		}
		else if(double.class.isAssignableFrom(klass))
		{
			value = resultSet.getDouble(columnIndex);
		}
		else
		{
			value = resultSet.getObject(columnIndex, klass);
		}
		return value;
	}
	
}
