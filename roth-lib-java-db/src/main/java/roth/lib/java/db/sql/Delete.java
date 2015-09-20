package roth.lib.java.db.sql;

import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Delete extends Sql
{
	public static final String DELETE = " DELETE FROM ";
	
	protected String table;
	protected Wheres wheres;
	
	protected LinkedList<Wheres> nestedWheres = new LinkedList<Wheres>();
	
	public Delete(String table)
	{
		this.table = table;
	}
	
	public Delete wheres(Wheres wheres)
	{
		this.wheres = wheres;
		nestedWheres.clear();
		nestedWheres.add(wheres);
		return this;
	}
	
	public Delete where(Where where)
	{
		Wheres wheres = nestedWheres.peekLast();
		if(wheres == null)
		{
			wheres = new Wheres();
			wheres(wheres);
		}
		wheres.and(where);
		return this;
	}
	
	public Delete whereOpenParen()
	{
		Wheres wheres = new Wheres();
		Wheres lastWheres = nestedWheres.peekLast();
		if(lastWheres == null)
		{
			lastWheres = new Wheres();
			wheres(lastWheres);
		}
		nestedWheres.add(wheres);
		lastWheres.and(wheres);
		return this;
	}
	
	public Delete whereCloseParen()
	{
		if(!nestedWheres.isEmpty())
		{
			nestedWheres.removeLast();
		}
		return this;
	}
	
	public Delete where(String sql)
	{
		return where(Where.sql(sql));
	}
	
	public Delete where(String sql, Object value)
	{
		return where(Where.sql(sql, value));
	}
	
	public Delete where(String sql, Collection<?> values)
	{
		return where(Where.sql(sql, values));
	}
	
	public Delete whereEquals(String name, Object value)
	{
		return where(Where.equals(name, value));
	}
	
	public Delete whereEquals(String table, String name, Object value)
	{
		return where(Where.equals(table, name, value));
	}
	
	public Delete whereNotEquals(String name, Object value)
	{
		return where(Where.notEquals(name, value));
	}
	
	public Delete whereLessThan(String name, Object value)
	{
		return where(Where.lessThan(name, value));
	}
	
	public Delete whereGreaterThan(String name, Object value)
	{
		return where(Where.greaterThan(name, value));
	}
	
	public Delete whereLessThanOrEquals(String name, Object value)
	{
		return where(Where.lessThanOrEquals(name, value));
	}
	
	public Delete whereGreaterThanOrEquals(String name, Object value)
	{
		return where(Where.greaterThanOrEquals(name, value));
	}
	
	public Delete whereIn(String name, Collection<?> values)
	{
		return where(Where.in(name, values));
	}
	
	public Delete whereLike(String name, Object value)
	{
		return where(Where.like(name, value));
	}
	
	public Delete whereBetween(String name, Object value1, Object value2)
	{
		return where(Where.between(name, value1, value2));
	}
	
	public Delete whereIsNull(String name)
	{
		return where(Where.isNull(name));
	}
	
	public Delete whereIsNotNull(String name)
	{
		return where(Where.isNotNull(name));
	}
	
	public Delete orWhere(Where where)
	{
		Wheres wheres = nestedWheres.peekLast();
		if(wheres == null)
		{
			wheres = new Wheres();
			wheres(wheres);
		}
		wheres.or(where);
		return this;
	}
	
	public Delete orWhereOpenParen()
	{
		Wheres wheres = new Wheres();
		Wheres lastWheres = nestedWheres.peekLast();
		if(lastWheres == null)
		{
			lastWheres = new Wheres();
			wheres(lastWheres);
		}
		nestedWheres.add(wheres);
		lastWheres.or(wheres);
		return this;
	}
	
	public Delete orWhere(String sql)
	{
		return orWhere(Where.sql(sql));
	}
	
	public Delete orWhere(String sql, Object value)
	{
		return orWhere(Where.sql(sql, value));
	}
	
	public Delete orWhere(String sql, Collection<?> values)
	{
		return orWhere(Where.sql(sql, values));
	}
	
	public Delete orWhereEquals(String name, Object value)
	{
		return orWhere(Where.equals(name, value));
	}
	
	public Delete orWhereNotEquals(String name, Object value)
	{
		return orWhere(Where.notEquals(name, value));
	}
	
	public Delete orWhereLessThan(String name, Object value)
	{
		return orWhere(Where.lessThan(name, value));
	}
	
	public Delete orWhereGreaterThan(String name, Object value)
	{
		return orWhere(Where.greaterThan(name, value));
	}
	
	public Delete orWhereLessThanOrEquals(String name, Object value)
	{
		return orWhere(Where.lessThanOrEquals(name, value));
	}
	
	public Delete orWhereGreaterThanOrEquals(String name, Object value)
	{
		return orWhere(Where.greaterThanOrEquals(name, value));
	}
	
	public Delete orWhereIn(String name, Collection<?> values)
	{
		return orWhere(Where.in(name, values));
	}
	
	public Delete orWhereLike(String name, Object value)
	{
		return orWhere(Where.like(name, value));
	}
	
	public Delete orWhereBetween(String name, Object value1, Object value2)
	{
		return orWhere(Where.between(name, value1, value2));
	}
	
	public Delete orWhereIsNull(String name)
	{
		return orWhere(Where.isNull(name));
	}
	
	public Delete orWhereIsNotNull(String name)
	{
		return orWhere(Where.isNotNull(name));
	}
	
	public LinkedList<Object> values()
	{
		LinkedList<Object> values = new LinkedList<Object>();
		if(wheres != null)
		{
			values.addAll(wheres.values());
		}
		return values;
	}
	
	@Override
	public String toString()
	{
		if(wheres == null || wheres.toString().isEmpty()) throw new IllegalArgumentException("cannot delete without where conditions");
		StringBuilder builder = new StringBuilder();
		builder.append(DELETE + tick(table));
		builder.append(wheres != null ? wheres : "");
		builder.append(END);
		return builder.toString();
	}
	
}
