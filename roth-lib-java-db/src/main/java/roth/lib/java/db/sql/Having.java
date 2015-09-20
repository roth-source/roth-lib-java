package roth.lib.java.db.sql;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Having extends Condition
{
	protected String sql;
	protected LinkedList<Object> values = new LinkedList<Object>();
	
	protected Having(String sql)
	{
		this.sql = sql;
	}
	
	protected Having(String sql, Object value)
	{
		this.sql = sql;
		values.add(value);
	}
	
	protected Having(String sql, Collection<?> values)
	{
		this.sql = sql;
		this.values.addAll(values);
	}
	
	public static Having sql(String sql)
	{
		return new Having(sql);
	}
	
	public static Having sql(String sql, Object value)
	{
		return new Having(sql, value);
	}
	
	public static Having sql(String sql, Collection<?> values)
	{
		return new Having(sql, values);
	}
	
	public static Having equals(String name, Object value)
	{
		return new Having(tick(name) + Op.EQ.get(), value);
	}
	
	public static Having equals(String table, String name, Object value)
	{
		return new Having(tick(table) + DOT + tick(name) + Op.EQ.get(), value);
	}
	
	public static Having notEquals(String name, Object value)
	{
		return new Having(tick(name) + Op.NE.get(), value);
	}
	
	public static Having notEquals(String table, String name, Object value)
	{
		return new Having(tick(table) + DOT + tick(name) + Op.NE.get(), value);
	}
	
	public static Having lessThan(String name, Object value)
	{
		return new Having(tick(name) + Op.LT.get(), value);
	}
	
	public static Having lessThan(String table, String name, Object value)
	{
		return new Having(tick(table) + DOT + tick(name) + Op.LT.get(), value);
	}
	
	public static Having greaterThan(String name, Object value)
	{
		return new Having(tick(name) + Op.GT.get(), value);
	}
	
	public static Having greaterThan(String table, String name, Object value)
	{
		return new Having(tick(table) + DOT + tick(name) + Op.GT.get(), value);
	}
	
	public static Having lessThanOrEquals(String name, Object value)
	{
		return new Having(tick(name) + Op.LE.get(), value);
	}
	
	public static Having lessThanOrEquals(String table, String name, Object value)
	{
		return new Having(tick(table) + DOT + tick(name) + Op.LE.get(), value);
	}
	
	public static Having greaterThanOrEquals(String name, Object value)
	{
		return new Having(tick(name) + Op.GE.get(), value);
	}
	
	public static Having greaterThanOrEquals(String table, String name, Object value)
	{
		return new Having(tick(table) + DOT + tick(name) + Op.GE.get(), value);
	}
	
	public static Having in(String name, Collection<?> values)
	{
		return new Having(tick(name) + String.format(Op.IN.get(), param(values.size())), values);
	}
	
	public static Having in(String table, String name, Collection<?> values)
	{
		return new Having(tick(table) + DOT + tick(name) + String.format(Op.IN.get(), param(values.size())), values);
	}
	
	public static Having like(String name, Object value)
	{
		return new Having(tick(name) + Op.LIKE.get(), value);
	}
	
	public static Having like(String table, String name, Object value)
	{
		return new Having(tick(table) + DOT + tick(name) + Op.LIKE.get(), value);
	}
	
	public static Having between(String name, Object value1, Object value2)
	{
		return new Having(tick(name) + Op.BETWEEN.get(), Arrays.asList(value1, value2));
	}
	
	public static Having between(String table, String name, Object value1, Object value2)
	{
		return new Having(tick(table) + DOT + tick(name) + Op.BETWEEN.get(), Arrays.asList(value1, value2));
	}
	
	public static Having isNull(String name)
	{
		return new Having(tick(name) + Op.IS_NULL.get());
	}
	
	public static Having isNull(String table, String name)
	{
		return new Having(tick(table) + DOT + tick(name) + Op.IS_NULL.get());
	}
	
	public static Having isNotNull(String name)
	{
		return new Having(tick(name) + Op.IS_NOT_NULL.get());
	}
	
	public static Having isNotNull(String table, String name)
	{
		return new Having(tick(table) + DOT + tick(name) + Op.IS_NOT_NULL.get());
	}
	
	@Override
	public LinkedList<Object> values()
	{
		return values;
	}
	
	@Override
	public String toString()
	{
		return toStringNested();
	}
	
	@Override
	public String toStringNested()
	{
		return sql;
	}
	
}
