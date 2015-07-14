package roth.lib.map.rdb;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import roth.lib.Callback;
import roth.lib.map.rdb.sql.Column;
import roth.lib.map.rdb.sql.Columns;
import roth.lib.map.rdb.sql.Delete;
import roth.lib.map.rdb.sql.Insert;
import roth.lib.map.rdb.sql.Order;
import roth.lib.map.rdb.sql.Select;
import roth.lib.map.rdb.sql.Update;
import roth.lib.map.rdb.sql.Where;
import roth.lib.map.rdb.sql.Wheres;

public abstract class RdbTable<T extends RdbModel>
{
	protected Class<T> klass;
	
	protected RdbTable(Class<T> klass)
	{
		this.klass = klass;
	}
	
	public abstract Rdb getRdb();
	
	public Select select()
	{
		return new Select(tableName());
	}
	
	public Select select(Columns columns)
	{
		return new Select(columns, tableName());
	}
	
	public Insert insert(Collection<Object> values)
	{
		return new Insert(tableName(), values);
	}
	
	public Insert insert(Collection<String> names, Collection<Object> values)
	{
		return new Insert(tableName(), names, values);
	}
	
	public Insert insert(Map<String, Object> nameValues)
	{
		return new Insert(tableName(), nameValues);
	}
	
	public Update update()
	{
		return new Update(tableName());
	}
	
	public Update update(Map<String, Object> nameValues)
	{
		return new Update(tableName(), nameValues);
	}
	
	public Update update(Collection<String> names, Collection<Object> values)
	{
		return new Update(tableName(), names, values);
	}
	
	public Delete delete()
	{
		return new Delete(tableName());
	}
	
	public String tableName()
	{
		return getRdb().getMapper().getEntityName(klass);
	}
	
	public T findBy(Where where)
	{
		return findBy(new Wheres().and(where));
	}
	
	public T findBy(Wheres wheres)
	{
		return findBy(select().wheres(wheres));
	}
	
	public T findBy(Where where, Columns columns)
	{
		return findBy(new Wheres().and(where), columns);
	}
	
	public T findBy(Wheres wheres, Columns columns)
	{
		return findBy(select(columns).wheres(wheres));
	}
	
	public T findBy(Select select)
	{
		return getRdb().query(select, klass);
	}
	
	public T findBy(String sql)
	{
		return getRdb().query(sql, klass);
	}
	
	public T findBy(String sql, Collection<Object> values)
	{
		return getRdb().query(sql, values, klass);
	}
	
	public LinkedList<T> findAll()
	{
		return findAllBy(select());
	}
	
	public LinkedList<T> findAllBy(Order order)
	{
		return findAllBy(select().order(order));
	}
	
	public LinkedList<T> findAllBy(Where where)
	{
		return findAllBy(new Wheres().and(where));
	}
	
	public LinkedList<T> findAllBy(Wheres wheres)
	{
		return findAllBy(select().wheres(wheres));
	}
	
	public LinkedList<T> findAllBy(Where where, Order order)
	{
		return findAllBy(new Wheres().and(where), order);
	}
	
	public LinkedList<T> findAllBy(Wheres wheres, Order order)
	{
		return findAllBy(select().wheres(wheres).order(order));
	}
	
	public LinkedList<T> findAll(Columns columns)
	{
		return findAllBy(select(columns));
	}
	
	public LinkedList<T> findAllBy(Where where, Columns columns)
	{
		return findAllBy(new Wheres().and(where), columns);
	}
	
	public LinkedList<T> findAllBy(Wheres wheres, Columns columns)
	{
		return findAllBy(select(columns).wheres(wheres));
	}
	
	public LinkedList<T> findAllBy(Where where, Columns columns, Order order)
	{
		return findAllBy(new Wheres().and(where), columns, order);
	}
	
	public LinkedList<T> findAllBy(Wheres wheres, Columns columns, Order order)
	{
		return findAllBy(select(columns).wheres(wheres).order(order));
	}
	
	public LinkedList<T> findAllBy(Select select)
	{
		return getRdb().queryAll(select, klass);
	}
	
	public LinkedList<T> findAllBy(String sql)
	{
		return getRdb().queryAll(sql, klass);
	}
	
	public LinkedList<T> findAllBy(String sql, Collection<Object> values)
	{
		return getRdb().queryAll(sql, values, klass);
	}
	
	public void callback(Select select, Callback<T> callback)
	{
		getRdb().queryAll(select, callback.setKlass(klass));
	}
	
	public void callback(String sql, Callback<T> callback)
	{
		getRdb().queryAll(sql, callback.setKlass(klass));
	}
	
	public void callback(String sql, Collection<Object> values, Callback<T> callback)
	{
		getRdb().queryAll(sql, values, callback.setKlass(klass));
	}
	
	public int executeInsert(Insert insert)
	{
		return getRdb().executeInsert(insert);
	}
	
	public int executeInsert(String sql)
	{
		return getRdb().executeInsert(sql);
	}
	
	public int executeInsert(String sql, Collection<Object> values)
	{
		return getRdb().executeInsert(sql, values);
	}
	
	public int executeInsert(Insert insert, RdbConnection connection) throws SQLException
	{
		return getRdb().executeInsert(insert, connection);
	}
	
	public int executeInsert(String sql, RdbConnection connection) throws SQLException
	{
		return getRdb().executeInsert(sql, connection);
	}
	
	public int executeInsert(String sql, Collection<Object> values, RdbConnection connection) throws SQLException
	{
		return getRdb().executeInsert(sql, values, connection);
	}
	
	public int executeUpdate(Update update)
	{
		return getRdb().executeUpdate(update);
	}
	
	public int executeUpdate(String sql)
	{
		return getRdb().executeUpdate(sql);
	}
	
	public int executeUpdate(String sql, Collection<Object> values)
	{
		return getRdb().executeUpdate(sql, values);
	}
	
	public int executeUpdate(Update update, RdbConnection connection) throws SQLException
	{
		return getRdb().executeUpdate(update, connection);
	}
	
	public int executeUpdate(String sql, RdbConnection connection) throws SQLException
	{
		return getRdb().executeUpdate(sql, connection);
	}
	
	public int executeUpdate(String sql, Collection<Object> values, RdbConnection connection) throws SQLException
	{
		return getRdb().executeUpdate(sql, values, connection);
	}
	
	public int executeDelete(Delete delete)
	{
		return getRdb().executeDelete(delete);
	}
	
	public int executeDelete(String sql)
	{
		return getRdb().executeDelete(sql);
	}
	
	public int executeDelete(String sql, Collection<Object> values)
	{
		return getRdb().executeDelete(sql, values);
	}
	
	public int executeDelete(Delete delete, RdbConnection connection) throws SQLException
	{
		return getRdb().executeDelete(delete, connection);
	}
	
	public int executeDelete(String sql, RdbConnection connection) throws SQLException
	{
		return getRdb().executeDelete(sql, connection);
	}
	
	public int executeDelete(String sql, Collection<Object> values, RdbConnection connection) throws SQLException
	{
		return getRdb().executeDelete(sql, values, connection);
	}
	
	public int count(Select select)
	{
		int count = 0;
		select.columns(new Columns().add(Column.sqlAs("count(*)", "count")));
		LinkedHashMap<String, Object> results = getRdb().query(select);
		Object object = results.get("count");
		if(object instanceof Number)
		{
			count = ((Number) object).intValue();
		}
		return count;
	}
	
}
