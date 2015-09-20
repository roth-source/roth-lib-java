package roth.lib.java.db;

import java.sql.Array;
import java.sql.SQLException;
import java.util.Map;

public abstract class DbArray implements Array, DbWrapper
{
	protected Array array;
	
	public DbArray(Array array)
	{
		this.array = array;
	}
	
	@Override
	public String getBaseTypeName() throws SQLException
	{
		return array.getBaseTypeName();
	}
	
	@Override
	public int getBaseType() throws SQLException
	{
		return array.getBaseType();
	}
	
	@Override
	public Object getArray() throws SQLException
	{
		return array.getArray();
	}
	
	@Override
	public Object getArray(Map<String, Class<?>> map) throws SQLException
	{
		return array.getArray(map);
	}
	
	@Override
	public Object getArray(long index, int count) throws SQLException
	{
		return array.getArray(index, count);
	}
	
	@Override
	public Object getArray(long index, int count, Map<String, Class<?>> map) throws SQLException
	{
		return array.getArray(index, count, map);
	}
	
	@Override
	public DbResultSet getResultSet() throws SQLException
	{
		return wrap(array.getResultSet());
	}
	
	@Override
	public DbResultSet getResultSet(Map<String, Class<?>> map) throws SQLException
	{
		return wrap(array.getResultSet(map));
	}
	
	@Override
	public DbResultSet getResultSet(long index, int count) throws SQLException
	{
		return wrap(array.getResultSet(index, count));
	}
	
	@Override
	public DbResultSet getResultSet(long index, int count, Map<String, Class<?>> map) throws SQLException
	{
		return wrap(array.getResultSet(index, count, map));
	}
	
	@Override
	public void free() throws SQLException
	{
		array.free();
	}
	
}
