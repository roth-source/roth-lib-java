package roth.lib.java.db.mysql.sql;

import java.util.Collection;

import roth.lib.java.db.sql.Where;

@SuppressWarnings("serial")
public class MysqlWhere extends Where implements MysqlSqlFactory
{
	
	public MysqlWhere()
	{
		
	}
	
	public static MysqlWhere sql(String sql)
	{
		return (MysqlWhere) new MysqlWhere().setSql(sql);
	}
	
	public static MysqlWhere sql(String sql, Object...values)
	{
		return (MysqlWhere) new MysqlWhere().setSql(sql).addValues(values);
	}
	
	public static MysqlWhere sql(String sql, Collection<?> values)
	{
		return (MysqlWhere) new MysqlWhere().setSql(sql).setValues(values);
	}
	
	public static MysqlWhere equals(String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(OP_EQ).addValues(value);
	}
	
	public static MysqlWhere equals(String table, String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(OP_EQ).addValues(value);
	}
	
	public static MysqlWhere notEquals(String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(OP_NE).addValues(value);
	}
	
	public static MysqlWhere notEquals(String table, String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(OP_NE).addValues(value);
	}
	
	public static MysqlWhere lessThan(String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(OP_LT).addValues(value);
	}
	
	public static MysqlWhere lessThan(String table, String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(OP_LT).addValues(value);
	}
	
	public static MysqlWhere greaterThan(String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(OP_GT).addValues(value);
	}
	
	public static MysqlWhere greaterThan(String table, String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(OP_GT).addValues(value);
	}
	
	public static MysqlWhere lessThanOrEquals(String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(OP_LE).addValues(value);
	}
	
	public static MysqlWhere lessThanOrEquals(String table, String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(OP_LE).addValues(value);
	}
	
	public static MysqlWhere greaterThanOrEquals(String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(OP_GE).addValues(value);
	}
	
	public static MysqlWhere greaterThanOrEquals(String table, String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(OP_GE).addValues(value);
	}
	
	public static MysqlWhere in(String name, Object...values)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(String.format(OP_IN, param(values.length))).addValues(values);
	}
	
	public static MysqlWhere in(String name, Collection<?> values)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(String.format(OP_IN, param(values.size()))).setValues(values);
	}
	
	public static MysqlWhere in(String table, String name, Object...values)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(String.format(OP_IN, param(values.length))).addValues(values);
	}
	
	public static MysqlWhere in(String table, String name, Collection<?> values)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(String.format(OP_IN, param(values.size()))).setValues(values);
	}
	
	public static MysqlWhere notIn(String name, Object...values)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(String.format(OP_NOT_IN, param(values.length))).addValues(values);
	}
	
	public static MysqlWhere notIn(String name, Collection<?> values)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(String.format(OP_NOT_IN, param(values.size()))).setValues(values);
	}
	
	public static MysqlWhere notIn(String table, String name, Object...values)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(String.format(OP_NOT_IN, param(values.length))).addValues(values);
	}
	
	public static MysqlWhere notIn(String table, String name, Collection<?> values)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(String.format(OP_NOT_IN, param(values.size()))).setValues(values);
	}
	
	public static MysqlWhere like(String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(OP_LIKE).addValues(value);
	}
	
	public static MysqlWhere like(String table, String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(OP_LIKE).addValues(value);
	}
	
	public static MysqlWhere notLike(String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(OP_NOT_LIKE).addValues(value);
	}
	
	public static MysqlWhere notLike(String table, String name, Object value)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(OP_NOT_LIKE).addValues(value);
	}
	
	public static MysqlWhere between(String name, Object value1, Object value2)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(OP_BETWEEN).addValues(value1, value2);
	}
	
	public static MysqlWhere between(String table, String name, Object value1, Object value2)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(OP_BETWEEN).addValues(value1, value2);
	}
	
	public static MysqlWhere isNull(String name)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(OP_IS_NULL);
	}
	
	public static MysqlWhere isNull(String table, String name)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(OP_IS_NULL);
	}
	
	public static MysqlWhere isNotNull(String name)
	{
		return (MysqlWhere) new MysqlWhere().setName(name).setOpType(OP_IS_NOT_NULL);
	}
	
	public static MysqlWhere isNotNull(String table, String name)
	{
		return (MysqlWhere) new MysqlWhere().setTable(table).setName(name).setOpType(OP_IS_NOT_NULL);
	}
	
}
