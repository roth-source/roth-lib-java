package roth.lib.map.rdb;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Objects;

@SuppressWarnings({"serial","unchecked"})
public abstract class RdbModel implements Serializable
{
	private transient LinkedHashSet<String> dirtyNames = new LinkedHashSet<String>();
	private transient State state = State.NEW;
	
	protected RdbModel() {}
	
	public abstract Rdb getRdb();
	public abstract boolean isGenerated();
	
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
	
	public <T extends RdbModel> T refresh()
	{
		T model = (T) this;
		if(isPersisted())
		{
			model = getRdb().query(model);
		}
		return model;
	}
	
	public <T extends RdbModel> T save()
	{
		return (T) (isNew() ? insert() : update());
	}
	
	public <T extends RdbModel> T save(RdbConnection connection) throws SQLException
	{
		return (T) (isNew() ? insert(connection) : update(connection));
	}
	
	public <T extends RdbModel> T insert()
	{
		if(isNew())
		{
			preInsert();
			postInsert(getRdb().executeInsert(this));
		}
		return (T) this;
	}
	
	public <T extends RdbModel> T insert(RdbConnection connection) throws SQLException
	{
		if(isNew())
		{
			preInsert();
			postInsert(getRdb().executeInsert(this, connection));
		}
		return (T) this;
	}
	
	public <T extends RdbModel> T update()
	{
		if(isPersisted() && isDirty())
		{
			preUpdate();
			postUpdate(getRdb().executeUpdate(this));
		}
		return (T) this;
	}
	
	public <T extends RdbModel> T update(RdbConnection connection) throws SQLException
	{
		if(isPersisted() && isDirty())
		{
			preUpdate();
			postUpdate(getRdb().executeUpdate(this, connection));
		}
		return (T) this;
	}
	
	public <T extends RdbModel> T delete()
	{
		if(isPersisted())
		{
			preDelete();
			postDelete(getRdb().executeDelete(this));
		}
		return (T) this;
	}
	
	public <T extends RdbModel> T delete(RdbConnection connection) throws SQLException
	{
		if(isPersisted())
		{
			preDelete();
			postDelete(getRdb().executeDelete(this, connection));
		}
		return (T) this;
	}
	
	public boolean isDirty()
	{
		return !dirtyNames.isEmpty();
	}
	
	public LinkedHashSet<String> getDirtyNames()
	{
		return dirtyNames;
	}
	
	public <T> T setDirty(String name, T oldValue, T value)
	{
		if(isPersisted() && !Objects.equals(oldValue, value))
		{
			dirtyNames.add(name);
		}
		return value;
	}
	
	public void resetDirty()
	{
		dirtyNames.clear();
	}
	
	protected static enum State
	{
		NEW,
		PERSISTED,
		DELETED,
		;
		
	}
	
}
