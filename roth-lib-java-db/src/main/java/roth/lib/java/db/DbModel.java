package roth.lib.java.db;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Objects;

import roth.lib.java.Model;
import roth.lib.java.reflector.PropertyReflector;

@SuppressWarnings({"serial","unchecked"})
public abstract class DbModel extends Model
{
	protected transient LinkedHashMap<String, Object> dirtyIdMap = new LinkedHashMap<String, Object>();
	protected transient LinkedHashSet<String> dirtyNames = new LinkedHashSet<String>();
	protected transient State state = State.NEW;
	
	protected DbModel()
	{
		
	}
	
	public abstract DbDataSource getDb();
	public abstract DbModel setDb(DbDataSource db);
	
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
	
	public <T extends DbModel> T refresh()
	{
		T model = (T) this;
		if(isPersisted())
		{
			model = getDb().query(model);
		}
		return model;
	}
	
	public <T extends DbModel> T save()
	{
		return (T) (isNew() ? insert() : update());
	}
	
	public <T extends DbModel> T save(DbConnection connection) throws SQLException
	{
		return (T) (isNew() ? insert(connection) : update(connection));
	}
	
	public <T extends DbModel> T insert()
	{
		if(isNew())
		{
			preInsert();
			postInsert(getDb().executeInsert(this));
		}
		return (T) this;
	}
	
	public <T extends DbModel> T insert(DbConnection connection) throws SQLException
	{
		if(isNew())
		{
			preInsert();
			postInsert(getDb().executeInsert(this, connection));
		}
		return (T) this;
	}
	
	public <T extends DbModel> T update()
	{
		if(isPersisted() && isDirty())
		{
			preUpdate();
			postUpdate(getDb().executeUpdate(this));
		}
		return (T) this;
	}
	
	public <T extends DbModel> T update(DbConnection connection) throws SQLException
	{
		if(isPersisted() && isDirty())
		{
			preUpdate();
			postUpdate(getDb().executeUpdate(this, connection));
		}
		return (T) this;
	}
	
	public <T extends DbModel> T delete()
	{
		if(isPersisted())
		{
			preDelete();
			postDelete(getDb().executeDelete(this));
		}
		return (T) this;
	}
	
	public <T extends DbModel> T delete(DbConnection connection) throws SQLException
	{
		if(isPersisted())
		{
			preDelete();
			postDelete(getDb().executeDelete(this, connection));
		}
		return (T) this;
	}
	
	public <T extends DbModel> T sync(DbDataSource db)
	{
		setDb(db);
		T presistedModel = (T) db.query(this);
		if(presistedModel != null)
		{
			persisted();
			LinkedHashMap<String, PropertyReflector> fieldPropertyReflectorMap = db.getReflector().getFieldPropertyReflectorMap(getClass());
			for(String deserializedName : getDeserializedNames())
			{
				try
				{
					PropertyReflector propertyReflector = fieldPropertyReflectorMap.get(deserializedName);
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
	
	public boolean isDirty()
	{
		return !dirtyNames.isEmpty();
	}
	
	public LinkedHashSet<String> getDirtyNames()
	{
		return dirtyNames;
	}
	
	public LinkedHashMap<String, Object> getDirtyIdMap()
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
