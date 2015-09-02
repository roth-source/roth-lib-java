package roth.lib.db;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import roth.lib.Callback;
import roth.lib.db.sql.Column;
import roth.lib.db.sql.Columns;
import roth.lib.db.sql.Delete;
import roth.lib.db.sql.Insert;
import roth.lib.db.sql.Order;
import roth.lib.db.sql.Select;
import roth.lib.db.sql.Update;
import roth.lib.db.sql.Where;
import roth.lib.db.sql.Wheres;

public abstract class DbTable<T>
{
	protected Class<T> klass;
	
	protected DbTable(Class<T> klass)
	{
		this.klass = klass;
	}
	
	public abstract DbDataSource getDb();
	
	public String tableName()
	{
		return getDb().getReflector().getEntityName(klass);
	}
	
	public Select select()
	{
		return new Select(tableName());
	}
	
	public Select select(Column... columns)
	{
		return select(new Columns(columns));
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
	
	public T findBy(Where... wheres)
	{
		return findBy(new Wheres(wheres));
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
	
	public LinkedList<T> findAll()
	{
		return findAllBy(select());
	}
	
	public LinkedList<T> findAllBy(Order order)
	{
		return findAllBy(select().order(order));
	}
	
	public LinkedList<T> findAllBy(Where... wheres)
	{
		return findAllBy(new Wheres(wheres));
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
	
	public int executeInsert(Insert insert)
	{
		return getDb().executeInsert(insert);
	}
	
	public int executeInsert(String sql)
	{
		return getDb().executeInsert(sql);
	}
	
	public int executeInsert(String sql, Collection<Object> values)
	{
		return getDb().executeInsert(sql, values);
	}
	
	public int executeInsert(Insert insert, DbConnection connection) throws SQLException
	{
		return getDb().executeInsert(insert, connection);
	}
	
	public int executeInsert(String sql, DbConnection connection) throws SQLException
	{
		return getDb().executeInsert(sql, connection);
	}
	
	public int executeInsert(String sql, Collection<Object> values, DbConnection connection) throws SQLException
	{
		return getDb().executeInsert(sql, values, connection);
	}
	
	public int executeUpdate(Update update)
	{
		return getDb().executeUpdate(update);
	}
	
	public int executeUpdate(String sql)
	{
		return getDb().executeUpdate(sql);
	}
	
	public int executeUpdate(String sql, Collection<Object> values)
	{
		return getDb().executeUpdate(sql, values);
	}
	
	public int executeUpdate(Update update, DbConnection connection) throws SQLException
	{
		return getDb().executeUpdate(update, connection);
	}
	
	public int executeUpdate(String sql, DbConnection connection) throws SQLException
	{
		return getDb().executeUpdate(sql, connection);
	}
	
	public int executeUpdate(String sql, Collection<Object> values, DbConnection connection) throws SQLException
	{
		return getDb().executeUpdate(sql, values, connection);
	}
	
	public int executeDelete(Delete delete)
	{
		return getDb().executeDelete(delete);
	}
	
	public int executeDelete(String sql)
	{
		return getDb().executeDelete(sql);
	}
	
	public int executeDelete(String sql, Collection<Object> values)
	{
		return getDb().executeDelete(sql, values);
	}
	
	public int executeDelete(Delete delete, DbConnection connection) throws SQLException
	{
		return getDb().executeDelete(delete, connection);
	}
	
	public int executeDelete(String sql, DbConnection connection) throws SQLException
	{
		return getDb().executeDelete(sql, connection);
	}
	
	public int executeDelete(String sql, Collection<Object> values, DbConnection connection) throws SQLException
	{
		return getDb().executeDelete(sql, values, connection);
	}
	
	public int count(Select select)
	{
		int count = 0;
		select.columns(new Columns().add(Column.sqlAs("count(*)", "count")));
		LinkedHashMap<String, Object> results = getDb().query(select);
		Object object = results.get("count");
		if(object instanceof Number)
		{
			count = ((Number) object).intValue();
		}
		return count;
	}
	
}
