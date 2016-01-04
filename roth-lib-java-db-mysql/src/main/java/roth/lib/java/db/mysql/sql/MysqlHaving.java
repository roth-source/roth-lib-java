package roth.lib.java.db.mysql.sql;

import java.util.Collection;

import roth.lib.java.db.sql.Having;

@SuppressWarnings("serial")
public class MysqlHaving extends Having implements MysqlSqlFactory
{
	
	public MysqlHaving()
	{
		
	}
	
	public static MysqlHaving sql(String sql)
	{
		return (MysqlHaving) new MysqlHaving().setSql(sql);
	}
	
	public static MysqlHaving sql(String sql, Object...values)
	{
		return (MysqlHaving) new MysqlHaving().setSql(sql).addValues(values);
	}
	
	public static MysqlHaving sql(String sql, Collection<?> values)
	{
		return (MysqlHaving) new MysqlHaving().setSql(sql).setValues(values);
	}
	
	public static MysqlHaving equals(String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_EQ).addValues(value);
	}
	
	public static MysqlHaving equals(String table, String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_EQ).addValues(value);
	}
	
	public static MysqlHaving notEquals(String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_NE).addValues(value);
	}
	
	public static MysqlHaving notEquals(String table, String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_NE).addValues(value);
	}
	
	public static MysqlHaving lessThan(String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_LT).addValues(value);
	}
	
	public static MysqlHaving lessThan(String table, String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_LT).addValues(value);
	}
	
	public static MysqlHaving greaterThan(String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_GT).addValues(value);
	}
	
	public static MysqlHaving greaterThan(String table, String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_GT).addValues(value);
	}
	
	public static MysqlHaving lessThanOrEquals(String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_LE).addValues(value);
	}
	
	public static MysqlHaving lessThanOrEquals(String table, String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_LE).addValues(value);
	}
	
	public static MysqlHaving greaterThanOrEquals(String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_GE).addValues(value);
	}
	
	public static MysqlHaving greaterThanOrEquals(String table, String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_GE).addValues(value);
	}
	
	public static MysqlHaving in(String name, Object...values)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_IN).addValues(values);
	}
	
	public static MysqlHaving in(String name, Collection<?> values)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_IN).setValues(values);
	}
	
	public static MysqlHaving in(String table, String name, Object...values)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_IN).addValues(values);
	}
	
	public static MysqlHaving in(String table, String name, Collection<?> values)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_IN).setValues(values);
	}
	
	public static MysqlHaving notIn(String name, Object...values)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_NOT_IN).addValues(values);
	}
	
	public static MysqlHaving notIn(String name, Collection<?> values)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_NOT_IN).setValues(values);
	}
	
	public static MysqlHaving notIn(String table, String name, Object...values)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_NOT_IN).addValues(values);
	}
	
	public static MysqlHaving notIn(String table, String name, Collection<?> values)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_NOT_IN).setValues(values);
	}
	
	public static MysqlHaving like(String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_LIKE).addValues(value);
	}
	
	public static MysqlHaving like(String table, String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_LIKE).addValues(value);
	}
	
	public static MysqlHaving notLike(String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_NOT_LIKE).addValues(value);
	}
	
	public static MysqlHaving notLike(String table, String name, Object value)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_NOT_LIKE).addValues(value);
	}
	
	public static MysqlHaving between(String name, Object value1, Object value2)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_BETWEEN).addValues(value1, value2);
	}
	
	public static MysqlHaving between(String table, String name, Object value1, Object value2)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_BETWEEN).addValues(value1, value2);
	}
	
	public static MysqlHaving isNull(String name)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_IS_NULL);
	}
	
	public static MysqlHaving isNull(String table, String name)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_IS_NULL);
	}
	
	public static MysqlHaving isNotNull(String name)
	{
		return (MysqlHaving) new MysqlHaving().setName(name).setOpType(OP_IS_NOT_NULL);
	}
	
	public static MysqlHaving isNotNull(String table, String name)
	{
		return (MysqlHaving) new MysqlHaving().setTable(table).setName(name).setOpType(OP_IS_NOT_NULL);
	}
	
}
