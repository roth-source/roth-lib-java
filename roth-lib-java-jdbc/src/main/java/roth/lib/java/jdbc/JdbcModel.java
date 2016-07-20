package roth.lib.java.jdbc;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Objects;

import roth.lib.java.Model;
import roth.lib.java.deserializer.Deserializer;
import roth.lib.java.jdbc.sql.SqlFactory;
import roth.lib.java.lang.Map;
import roth.lib.java.lang.Set;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.reflector.EntityReflector;
import roth.lib.java.reflector.PropertyReflector;

@SuppressWarnings({"serial","unchecked"})
public abstract class JdbcModel extends Model implements SqlFactory
{
	protected transient Map<String, Object> dirtyIdMap = new Map<String, Object>();
	protected transient Set<String> dirtyNames = new Set<String>();
	protected transient State state = State.NEW;
	
	protected JdbcModel()
	{
		
	}
	
	public abstract Jdbc getDb();
	public abstract JdbcModel setDb(Jdbc db);
	
	public boolean isNew()
	{
		return State.NEW.equals(state);
	}
	
	public boolean isPersisted()
	{
		return State.PERSISTED.equals(state);
	}
	
	public boolean isDeleted()
	{
		return State.DELETED.equals(state);
	}
	
	public void persisted()
	{
		state = State.PERSISTED;
	}
	
	public void deleted()
	{
		state = State.DELETED;
	}
	
	public void preInsert()
	{
		// override to implement
	}
	
	public void postInsert(int result)
	{
		// override to implement
	}
	
	public void preUpdate()
	{
		// override to implement
	}
	
	public void postUpdate(int result)
	{
		// override to implement
	}
	
	public void preDelete()
	{
		// override to implement
	}
	
	public void postDelete(int result)
	{
		// override to implement
	}
	
	public <T extends JdbcModel> T refresh()
	{
		T model = (T) this;
		if(isPersisted())
		{
			model = getDb().query(model);
		}
		return model;
	}
	
	public <T extends JdbcModel> T save()
	{
		return (T) (isNew() ? insert() : update());
	}
	
	public <T extends JdbcModel> T save(JdbcConnection connection) throws SQLException
	{
		return (T) (isNew() ? insert(connection) : update(connection));
	}
	
	public <T extends JdbcModel> T insert()
	{
		if(isNew())
		{
			preInsert();
			postInsert(getDb().executeInsert(this));
		}
		return (T) this;
	}
	
	public <T extends JdbcModel> T insert(JdbcConnection connection) throws SQLException
	{
		if(isNew())
		{
			preInsert();
			postInsert(getDb().executeInsert(this, connection));
		}
		return (T) this;
	}
	
	public <T extends JdbcModel> T update()
	{
		if(isPersisted() && isDirty())
		{
			preUpdate();
			postUpdate(getDb().executeUpdate(this));
		}
		return (T) this;
	}
	
	public <T extends JdbcModel> T update(JdbcConnection connection) throws SQLException
	{
		if(isPersisted() && isDirty())
		{
			preUpdate();
			postUpdate(getDb().executeUpdate(this, connection));
		}
		return (T) this;
	}
	
	public <T extends JdbcModel> T delete()
	{
		if(isPersisted())
		{
			preDelete();
			postDelete(getDb().executeDelete(this));
		}
		return (T) this;
	}
	
	public <T extends JdbcModel> T delete(JdbcConnection connection) throws SQLException
	{
		if(isPersisted())
		{
			preDelete();
			postDelete(getDb().executeDelete(this, connection));
		}
		return (T) this;
	}
	
	public <T extends JdbcModel> T sync(Jdbc db)
	{
		setDb(db);
		T presistedModel = (T) db.query(this);
		if(presistedModel != null)
		{
			persisted();
			EntityReflector entityReflector = db.getMapperReflector().getEntityReflector(getClass());
			for(String deserializedName : getDeserializedNames())
			{
				try
				{
					PropertyReflector propertyReflector = entityReflector.getPropertyReflector(deserializedName, getDb().getMapperType(), db.getMapperReflector());
					if(propertyReflector != null)
					{
						Field field = propertyReflector.getField();
						setDirty(deserializedName, field.get(presistedModel), field.get(this));
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			resetDeserializedNames();
		}
		return (T) save();
	}
	
	public <T extends JdbcModel> T set(String name, String value)
	{
		Jdbc db = getDb();
		EntityReflector entityReflector = db.getMapperReflector().getEntityReflector(getClass());
		PropertyReflector propertyReflector = entityReflector.getFieldReflector(name, db.getMapperType(), db.getMapperReflector());
		if(propertyReflector != null)
		{
			Deserializer<?> deserializer = propertyReflector.getDeserializer(db.getMapperType(), db.getMapperReflector(), MapperConfig.get());
			if(deserializer != null)
			{
				try
				{
					Field field = propertyReflector.getField();
					Object oldValue = field.get(this);
					Object newValue = deserializer.deserialize(value, propertyReflector.getTimeZone(), propertyReflector.getTimeFormat(db.getMapperType()), propertyReflector.getFieldClass());
					setDirty(name, oldValue, newValue);
					field.set(this, newValue);
				}
				catch(IllegalArgumentException | IllegalAccessException e)
				{
					throw new JdbcException(e);
				}
			}
		}
		return (T) this;
	}
	
	public boolean isDirty()
	{
		return !dirtyNames.isEmpty();
	}
	
	public Set<String> getDirtyNames()
	{
		return dirtyNames;
	}
	
	public Map<String, Object> getDirtyIdMap()
	{
		return dirtyIdMap;
	}
	
	public <T> T setDirtyId(String name, T oldValue, T value)
	{
		if(!Objects.equals(oldValue, value))
		{
			dirtyNames.add(name);
			dirtyIdMap.put(name, oldValue);
		}
		return value;
	}
	
	public <T> T setDirty(String name, T oldValue, T value)
	{
		if(!Objects.equals(oldValue, value))
		{
			dirtyNames.add(name);
		}
		return value;
	}
	
	public void resetDirty()
	{
		dirtyNames.clear();
		dirtyIdMap.clear();
	}
	
	protected static enum State
	{
		NEW,
		PERSISTED,
		DELETED,
		;
		
	}
	
}
