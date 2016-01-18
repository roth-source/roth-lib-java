package roth.lib.java.db;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import roth.lib.java.Callback;
import roth.lib.java.db.sql.Column;
import roth.lib.java.db.sql.Columns;
import roth.lib.java.db.sql.Delete;
import roth.lib.java.db.sql.Insert;
import roth.lib.java.db.sql.Order;
import roth.lib.java.db.sql.Select;
import roth.lib.java.db.sql.SqlFactory;
import roth.lib.java.db.sql.Update;
import roth.lib.java.db.sql.Where;
import roth.lib.java.db.sql.Wheres;

public abstract class DbTable<T> implements SqlFactory
{
	protected Class<T> klass;
	
	protected DbTable(Class<T> klass)
	{
		this.klass = klass;
	}
	
	public abstract DbDataSource getDb();
	
	public String tableName()
	{
		return getDb().table(klass);
	}
	
	public Select select()
	{
		return newSelect().from(tableName());
	}
	
	public Select select(Column...columns)
	{
		return select().column(columns);
	}
	
	public Select select(Columns columns)
	{
		return select().columns(columns);
	}
	
	public Insert insert(Collection<Object> values)
	{
		return newInsert();
	}
	
	public Insert insert(Collection<String> names, Collection<Object> values)
	{
		return newInsert().setTable(tableName()).setNameValues(names, values);
	}
	
	public Insert insert(Map<String, Object> nameValues)
	{
		return newInsert().setTable(tableName()).setNameValues(nameValues);
	}
	
	public Update update()
	{
		return newUpdate().setTable(tableName());
	}
	
	public Update update(Map<String, Object> nameValues)
	{
		return newUpdate().setTable(tableName()).setNameValues(nameValues);
	}
	
	public Update update(Collection<String> names, Collection<Object> values)
	{
		return newUpdate().setTable(tableName()).setNameValues(names, values);
	}
	
	public Delete delete()
	{
		return newDelete().setTable(tableName());
	}
	
	public T findById(String id)
	{
		return id != null ? findBy(getDb().toIdWheres(klass, id)) : null;
	}
	
	public T findById(Integer id)
	{
		return id != null ? findBy(getDb().toIdWheres(klass, id)) : null;
	}
	
	public T findBy(Where... wheres)
	{
		return findBy(newWheres().andConditions(wheres));
	}
	
	public T findBy(Wheres wheres)
	{
		return findBy(select().wheres(wheres));
	}
	
	public T findBy(Where where, Columns columns)
	{
		return findBy(newWheres().andWhere(where), columns);
	}
	
	public T findBy(Wheres wheres, Columns columns)
	{
		return findBy(select(columns).wheres(wheres));
	}
	
	public T findBy(Select select)
	{
		return findBy(select, klass);
	}
	
	public T findBy(String sql)
	{
		return findBy(sql, klass);
	}
	
	public T findBy(String sql, Collection<Object> values)
	{
		return findBy(sql, values, klass);
	}
	
	public T findBy(String sql, Map<String, Object> valueMap)
	{
		return findBy(sql, valueMap, klass);
	}
	
	public <C> C findBy(Select select, Class<C> klass)
	{
		return getDb().query(select, klass);
	}
	
	public <C> C findBy(String sql, Class<C> klass)
	{
		return getDb().query(sql, klass);
	}
	
	public <C> C findBy(String sql, Collection<Object> values, Class<C> klass)
	{
		return getDb().query(sql, values, klass);
	}
	
	public <C> C findBy(String sql, Map<String, Object> valueMap, Class<C> klass)
	{
		return getDb().query(sql, valueMap, klass);
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
		return findAllBy(newWheres().andConditions(wheres));
	}
	
	public LinkedList<T> findAllBy(Wheres wheres)
	{
		return findAllBy(select().wheres(wheres));
	}
	
	public LinkedList<T> findAllBy(Where where, Order order)
	{
		return findAllBy(newWheres().andWhere(where), order);
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
		return findAllBy(newWheres().andWhere(where), columns);
	}
	
	public LinkedList<T> findAllBy(Wheres wheres, Columns columns)
	{
		return findAllBy(select(columns).wheres(wheres));
	}
	
	public LinkedList<T> findAllBy(Where where, Columns columns, Order order)
	{
		return findAllBy(newWheres().andWhere(where), columns, order);
	}
	
	public LinkedList<T> findAllBy(Wheres wheres, Columns columns, Order order)
	{
		return findAllBy(select(columns).wheres(wheres).order(order));
	}
	
	public LinkedList<T> findAllBy(Select select)
	{
		return findAllBy(select, klass);
	}
	
	public LinkedList<T> findAllBy(String sql)
	{
		return findAllBy(sql, klass);
	}
	
	public LinkedList<T> findAllBy(String sql, Collection<Object> values)
	{
		return findAllBy(sql, values, klass);
	}
	
	public LinkedList<T> findAllBy(String sql, Map<String, Object> valueMap)
	{
		return findAllBy(sql, valueMap, klass);
	}
	
	public <C> LinkedList<C> findAllBy(Select select, Class<C> klass)
	{
		return getDb().queryAll(select, klass);
	}
	
	public <C> LinkedList<C> findAllBy(String sql, Class<C> klass)
	{
		return getDb().queryAll(sql, klass);
	}
	
	public <C> LinkedList<C> findAllBy(String sql, Collection<Object> values, Class<C> klass)
	{
		return getDb().queryAll(sql, values, klass);
	}
	
	public <C> LinkedList<C> findAllBy(String sql, Map<String, Object> valueMap, Class<C> klass)
	{
		return getDb().queryAll(sql, valueMap, klass);
	}
	
	public void callback(Select select, Callback<T> callback)
	{
		callback(select, callback.setKlass(klass));
	}
	
	public void callback(String sql, Callback<T> callback)
	{
		callback(sql, callback.setKlass(klass));
	}
	
	public void callback(String sql, Collection<Object> values, Callback<T> callback)
	{
		callback(sql, values, callback.setKlass(klass));
	}
	
	public void callback(String sql, Map<String, Object> valueMap, Callback<T> callback)
	{
		callback(sql, valueMap, callback.setKlass(klass));
	}
	
	public <C> void callback(Select select, Callback<C> callback, Class<C> klass)
	{
		getDb().queryAll(select, callback.setKlass(klass));
	}
	
	public <C> void callback(String sql, Callback<C> callback, Class<C> klass)
	{
		getDb().queryAll(sql, callback.setKlass(klass));
	}
	
	public <C> void callback(String sql, Collection<Object> values, Callback<C> callback, Class<C> klass)
	{
		getDb().queryAll(sql, values, callback.setKlass(klass));
	}
	
	public <C> void callback(String sql, Map<String, Object> valueMap, Callback<C> callback, Class<C> klass)
	{
		getDb().queryAll(sql, valueMap, callback.setKlass(klass));
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
		select.columns(newColumns().addColumns(newColumn().setSql("count(*)").setAlias("count")));
		LinkedHashMap<String, Object> results = getDb().query(select);
		Object object = results.get("count");
		if(object instanceof Number)
		{
			count = ((Number) object).intValue();
		}
		return count;
	}
	
}
