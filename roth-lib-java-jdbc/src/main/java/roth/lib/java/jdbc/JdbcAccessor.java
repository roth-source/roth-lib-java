package roth.lib.java.jdbc;

import java.util.Collection;
import roth.lib.java.lang.List;
import roth.lib.java.lang.Map;

import roth.lib.java.Callback;
import roth.lib.java.jdbc.sql.Select;

public abstract class JdbcAccessor
{
	
	public JdbcAccessor()
	{
		
	}
	
	public abstract Jdbc getDb();
	
	public <T> T findBy(Select select, Class<T> klass)
	{
		return getDb().query(select, klass);
	}
	
	public <T> T findBy(String sql, Class<T> klass)
	{
		return getDb().query(sql, klass);
	}
	
	public <T> T findBy(String sql, Collection<Object> values, Class<T> klass)
	{
		return getDb().query(sql, values, klass);
	}
	
	public <T> T findBy(String sql, Map<String, Object> valueMap, Class<T> klass)
	{
		return getDb().query(sql, valueMap, klass);
	}
	
	public <T> List<T> findAllBy(Select select, Class<T> klass)
	{
		return getDb().queryAll(select, klass);
	}
	
	public <T> List<T> findAllBy(String sql, Class<T> klass)
	{
		return getDb().queryAll(sql, klass);
	}
	
	public <T> List<T> findAllBy(String sql, Collection<Object> values, Class<T> klass)
	{
		return getDb().queryAll(sql, values, klass);
	}
	
	public <T> List<T> findAllBy(String sql, Map<String, Object> valueMap, Class<T> klass)
	{
		return getDb().queryAll(sql, valueMap, klass);
	}
	
	public <T> void callback(Select select, Callback<T> callback, Class<T> klass)
	{
		getDb().queryAll(select, callback.setKlass(klass));
	}
	
	public <T> void callback(String sql, Callback<T> callback, Class<T> klass)
	{
		getDb().queryAll(sql, callback.setKlass(klass));
	}
	
	public <T> void callback(String sql, Collection<Object> values, Callback<T> callback, Class<T> klass)
	{
		getDb().queryAll(sql, values, callback.setKlass(klass));
	}
	
	public <T> void callback(String sql, Map<String, Object> valueMap, Callback<T> callback, Class<T> klass)
	{
		getDb().queryAll(sql, valueMap, callback.setKlass(klass));
	}
	
}
