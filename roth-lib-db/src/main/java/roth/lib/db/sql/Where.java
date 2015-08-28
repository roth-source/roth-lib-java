package roth.lib.db.sql;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Where extends Condition
{
	protected String sql;
	protected LinkedList<Object> values = new LinkedList<Object>();
	
	protected Where(String sql)
	{
		this.sql = sql;
	}
	
	protected Where(String sql, Object value)
	{
		this.sql = sql;
		values.add(value);
	}
	
	protected Where(String sql, Collection<Object> values)
	{
		this.sql = sql;
		this.values.addAll(values);
	}
	
	public static Where sql(String sql)
	{
		return new Where(sql);
	}
	
	public static Where sql(String sql, Object value)
	{
		return new Where(sql, value);
	}
	
	public static Where sql(String sql, Collection<Object> values)
	{
		return new Where(sql, values);
	}
	
	public static Where equals(String name, Object value)
	{
		return new Where(tick(name) + Op.EQ.get(), value);
	}
	
	public static Where equals(String table, String name, Object value)
	{
		return new Where(tick(table) + DOT + tick(name) + Op.EQ.get(), value);
	}
	
	public static Where notEquals(String name, Object value)
	{
		return new Where(tick(name) + Op.NE.get(), value);
	}
	
	public static Where notEquals(String table, String name, Object value)
	{
		return new Where(tick(table) + DOT + tick(name) + Op.NE.get(), value);
	}
	
	public static Where lessThan(String name, Object value)
	{
		return new Where(tick(name) + Op.LT.get(), value);
	}
	
	public static Where lessThan(String table, String name, Object value)
	{
		return new Where(tick(table) + DOT + tick(name) + Op.LT.get(), value);
	}
	
	public static Where greaterThan(String name, Object value)
	{
		return new Where(tick(name) + Op.GT.get(), value);
	}
	
	public static Where greaterThan(String table, String name, Object value)
	{
		return new Where(tick(table) + DOT + tick(name) + Op.GT.get(), value);
	}
	
	public static Where lessThanOrEquals(String name, Object value)
	{
		return new Where(tick(name) + Op.LE.get(), value);
	}
	
	public static Where lessThanOrEquals(String table, String name, Object value)
	{
		return new Where(tick(table) + DOT + tick(name) + Op.LE.get(), value);
	}
	
	public static Where greaterThanOrEquals(String name, Object value)
	{
		return new Where(tick(name) + Op.GE.get(), value);
	}
	
	public static Where greaterThanOrEquals(String table, String name, Object value)
	{
		return new Where(tick(table) + DOT + tick(name) + Op.GE.get(), value);
	}
	
	public static Where in(String name, Collection<Object> values)
	{
		return new Where(tick(name) + String.format(Op.IN.get(), param(values.size())), values);
	}
	
	public static Where in(String table, String name, Collection<Object> values)
	{
		return new Where(tick(table) + DOT + tick(name) + String.format(Op.IN.get(), param(values.size())), values);
	}
	
	public static Where notIn(String name, Collection<Object> values)
	{
		return new Where(tick(name) + String.format(Op.NOT_IN.get(), param(values.size())), values);
	}
	
	public static Where notIn(String table, String name, Collection<Object> values)
	{
		return new Where(tick(table) + DOT + tick(name) + String.format(Op.NOT_IN.get(), param(values.size())), values);
	}
	
	public static Where like(String name, Object value)
	{
		return new Where(tick(name) + Op.LIKE.get(), value);
	}
	
	public static Where like(String table, String name, Object value)
	{
		return new Where(tick(table) + DOT + tick(name) + Op.LIKE.get(), value);
	}
	
	public static Where notLike(String name, Object value)
	{
		return new Where(tick(name) + Op.NOT_LIKE.get(), value);
	}
	
	public static Where notLike(String table, String name, Object value)
	{
		return new Where(tick(table) + DOT + tick(name) + Op.NOT_LIKE.get(), value);
	}
	
	public static Where between(String name, Object value1, Object value2)
	{
		return new Where(tick(name) + Op.BETWEEN.get(), Arrays.asList(value1, value2));
	}
	
	public static Where between(String table, String name, Object value1, Object value2)
	{
		return new Where(tick(table) + DOT + tick(name) + Op.BETWEEN.get(), Arrays.asList(value1, value2));
	}
	
	public static Where isNull(String name)
	{
		return new Where(tick(name) + Op.IS_NULL.get());
	}
	
	public static Where isNull(String table, String name)
	{
		return new Where(tick(table) + DOT + tick(name) + Op.IS_NULL.get());
	}
	
	public static Where isNotNull(String name)
	{
		return new Where(tick(name) + Op.IS_NOT_NULL.get());
	}
	
	public static Where isNotNull(String table, String name)
	{
		return new Where(tick(table) + DOT + tick(name) + Op.IS_NOT_NULL.get());
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
