package roth.lib.java.db.orient.sql;

import java.util.Collection;

import roth.lib.java.db.sql.Where;

@SuppressWarnings("serial")
public class OrientWhere extends Where implements OrientSqlFactory
{
	
	public OrientWhere()
	{
		
	}
	
	public static OrientWhere sql(String sql)
	{
		return (OrientWhere) new OrientWhere().setSql(sql);
	}
	
	public static OrientWhere sql(String sql, Object...values)
	{
		return (OrientWhere) new OrientWhere().setSql(sql).addValues(values);
	}
	
	public static OrientWhere sql(String sql, Collection<?> values)
	{
		return (OrientWhere) new OrientWhere().setSql(sql).setValues(values);
	}
	
	public static OrientWhere equals(String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_EQ).addValues(value);
	}
	
	public static OrientWhere equals(String table, String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_EQ).addValues(value);
	}
	
	public static OrientWhere notEquals(String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_NE).addValues(value);
	}
	
	public static OrientWhere notEquals(String table, String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_NE).addValues(value);
	}
	
	public static OrientWhere lessThan(String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_LT).addValues(value);
	}
	
	public static OrientWhere lessThan(String table, String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_LT).addValues(value);
	}
	
	public static OrientWhere greaterThan(String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_GT).addValues(value);
	}
	
	public static OrientWhere greaterThan(String table, String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_GT).addValues(value);
	}
	
	public static OrientWhere lessThanOrEquals(String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_LE).addValues(value);
	}
	
	public static OrientWhere lessThanOrEquals(String table, String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_LE).addValues(value);
	}
	
	public static OrientWhere greaterThanOrEquals(String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_GE).addValues(value);
	}
	
	public static OrientWhere greaterThanOrEquals(String table, String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_GE).addValues(value);
	}
	
	public static OrientWhere in(String name, Object...values)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_IN).addValues(values);
	}
	
	public static OrientWhere in(String name, Collection<?> values)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_IN).setValues(values);
	}
	
	public static OrientWhere in(String table, String name, Object...values)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_IN).addValues(values);
	}
	
	public static OrientWhere in(String table, String name, Collection<?> values)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_IN).setValues(values);
	}
	
	public static OrientWhere notIn(String name, Object...values)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_NOT_IN).addValues(values);
	}
	
	public static OrientWhere notIn(String name, Collection<?> values)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_NOT_IN).setValues(values);
	}
	
	public static OrientWhere notIn(String table, String name, Object...values)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_NOT_IN).addValues(values);
	}
	
	public static OrientWhere notIn(String table, String name, Collection<?> values)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_NOT_IN).setValues(values);
	}
	
	public static OrientWhere like(String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_LIKE).addValues(value);
	}
	
	public static OrientWhere like(String table, String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_LIKE).addValues(value);
	}
	
	public static OrientWhere notLike(String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_NOT_LIKE).addValues(value);
	}
	
	public static OrientWhere notLike(String table, String name, Object value)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_NOT_LIKE).addValues(value);
	}
	
	public static OrientWhere between(String name, Object value1, Object value2)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_BETWEEN).addValues(value1, value2);
	}
	
	public static OrientWhere between(String table, String name, Object value1, Object value2)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_BETWEEN).addValues(value1, value2);
	}
	
	public static OrientWhere isNull(String name)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_IS_NULL);
	}
	
	public static OrientWhere isNull(String table, String name)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_IS_NULL);
	}
	
	public static OrientWhere isNotNull(String name)
	{
		return (OrientWhere) new OrientWhere().setName(name).setOpType(OP_IS_NOT_NULL);
	}
	
	public static OrientWhere isNotNull(String table, String name)
	{
		return (OrientWhere) new OrientWhere().setTable(table).setName(name).setOpType(OP_IS_NOT_NULL);
	}
	
}
