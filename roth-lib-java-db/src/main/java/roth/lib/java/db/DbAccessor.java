package roth.lib.java.db;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import roth.lib.java.Callback;
import roth.lib.java.db.sql.Select;

public abstract class DbAccessor<T>
{
	protected Class<T> klass;
	
	public DbAccessor(Class<T> klass)
	{
		this.klass = klass;
	}
	
	public abstract DbDataSource getDb();
	
	public T findBy(Select select)
	{
		return getDb().query(select, klass);
	}
	
	public T findBy(String sql)
	{
		return getDb().query(sql, klass);
	}
	
	public T findBy(String sql, Collection<Object> values)
	{
		return getDb().query(sql, values, klass);
	}
	
	public T findBy(String sql, Map<String, Object> valueMap)
	{
		return getDb().query(sql, valueMap, klass);
	}
	
	public LinkedList<T> findAllBy(Select select)
	{
		return getDb().queryAll(select, klass);
	}
	
	public LinkedList<T> findAllBy(String sql)
	{
		return getDb().queryAll(sql, klass);
	}
	
	public LinkedList<T> findAllBy(String sql, Collection<Object> values)
	{
		return getDb().queryAll(sql, values, klass);
	}
	
	public LinkedList<T> findAllBy(String sql, Map<String, Object> valueMap)
	{
		return getDb().queryAll(sql, valueMap, klass);
	}
	
	public void callback(Select select, Callback<T> callback)
	{
		getDb().queryAll(select, callback.setKlass(klass));
	}
	
	public void callback(String sql, Callback<T> callback)
	{
		getDb().queryAll(sql, callback.setKlass(klass));
	}
	
	public void callback(String sql, Collection<Object> values, Callback<T> callback)
	{
		getDb().queryAll(sql, values, callback.setKlass(klass));
	}
	
	public void callback(String sql, Map<String, Object> valueMap, Callback<T> callback)
	{
		getDb().queryAll(sql, valueMap, callback.setKlass(klass));
	}
	
}
