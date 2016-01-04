package roth.lib.java.db.orient.sql;

import java.util.Collection;

import roth.lib.java.db.sql.Having;

@SuppressWarnings("serial")
public class OrientHaving extends Having implements OrientSqlFactory
{
	
	public OrientHaving()
	{
		
	}
	
	public static OrientHaving sql(String sql)
	{
		return (OrientHaving) new OrientHaving().setSql(sql);
	}
	
	public static OrientHaving sql(String sql, Object...values)
	{
		return (OrientHaving) new OrientHaving().setSql(sql).addValues(values);
	}
	
	public static OrientHaving sql(String sql, Collection<?> values)
	{
		return (OrientHaving) new OrientHaving().setSql(sql).setValues(values);
	}
	
	public static OrientHaving equals(String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_EQ).addValues(value);
	}
	
	public static OrientHaving equals(String table, String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_EQ).addValues(value);
	}
	
	public static OrientHaving notEquals(String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_NE).addValues(value);
	}
	
	public static OrientHaving notEquals(String table, String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_NE).addValues(value);
	}
	
	public static OrientHaving lessThan(String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_LT).addValues(value);
	}
	
	public static OrientHaving lessThan(String table, String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_LT).addValues(value);
	}
	
	public static OrientHaving greaterThan(String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_GT).addValues(value);
	}
	
	public static OrientHaving greaterThan(String table, String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_GT).addValues(value);
	}
	
	public static OrientHaving lessThanOrEquals(String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_LE).addValues(value);
	}
	
	public static OrientHaving lessThanOrEquals(String table, String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_LE).addValues(value);
	}
	
	public static OrientHaving greaterThanOrEquals(String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_GE).addValues(value);
	}
	
	public static OrientHaving greaterThanOrEquals(String table, String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_GE).addValues(value);
	}
	
	public static OrientHaving in(String name, Object...values)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_IN).addValues(values);
	}
	
	public static OrientHaving in(String name, Collection<?> values)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_IN).setValues(values);
	}
	
	public static OrientHaving in(String table, String name, Object...values)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_IN).addValues(values);
	}
	
	public static OrientHaving in(String table, String name, Collection<?> values)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_IN).setValues(values);
	}
	
	public static OrientHaving notIn(String name, Object...values)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_NOT_IN).addValues(values);
	}
	
	public static OrientHaving notIn(String name, Collection<?> values)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_NOT_IN).setValues(values);
	}
	
	public static OrientHaving notIn(String table, String name, Object...values)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_NOT_IN).addValues(values);
	}
	
	public static OrientHaving notIn(String table, String name, Collection<?> values)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_NOT_IN).setValues(values);
	}
	
	public static OrientHaving like(String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_LIKE).addValues(value);
	}
	
	public static OrientHaving like(String table, String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_LIKE).addValues(value);
	}
	
	public static OrientHaving notLike(String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_NOT_LIKE).addValues(value);
	}
	
	public static OrientHaving notLike(String table, String name, Object value)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_NOT_LIKE).addValues(value);
	}
	
	public static OrientHaving between(String name, Object value1, Object value2)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_BETWEEN).addValues(value1, value2);
	}
	
	public static OrientHaving between(String table, String name, Object value1, Object value2)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_BETWEEN).addValues(value1, value2);
	}
	
	public static OrientHaving isNull(String name)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_IS_NULL);
	}
	
	public static OrientHaving isNull(String table, String name)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_IS_NULL);
	}
	
	public static OrientHaving isNotNull(String name)
	{
		return (OrientHaving) new OrientHaving().setName(name).setOpType(OP_IS_NOT_NULL);
	}
	
	public static OrientHaving isNotNull(String table, String name)
	{
		return (OrientHaving) new OrientHaving().setTable(table).setName(name).setOpType(OP_IS_NOT_NULL);
	}
	
}
