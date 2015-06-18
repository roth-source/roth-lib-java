package roth.lib.map.rdb.sql;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public class Update extends Sql
{
	public static final String UPDATE 	= " UPDATE ";
	public static final String SET		= "    SET ";
	
	private String table;
	private LinkedList<String> sets = new LinkedList<String>();
	private LinkedList<Object> values = new LinkedList<Object>();
	private Wheres wheres;
	
	private LinkedList<Wheres> nestedWheres = new LinkedList<Wheres>();
	
	public Update(String table)
	{
		this.table = table;
	}
	
	public Update(String table, Map<String, Object> nameValues)
	{
		this.table = table;
		for(Entry<String, Object> nameValueEntry : nameValues.entrySet())
		{
			sets.add(tick(nameValueEntry.getKey()) + PARAM);
			values.add(nameValueEntry.getValue());
		}
	}
	
	public Update(String table, Collection<String> names, Collection<Object> values)
	{
		if(names.size() != values.size()) throw new IllegalArgumentException("names is different size than values");
		this.table = table;
		this.values = new LinkedList<Object>(values);
		for(String name : names)
		{
			sets.add(tick(name) + PARAM);
		}
	}
	
	public Update setEqual(String name, Object value)
	{
		set(tick(name) + PARAM, value);
		return this;
	}
	
	public Update set(String sql, Object value)
	{
		sets.add(sql);
		values.add(value);
		return this;
	}
	
	public Update wheres(Wheres wheres)
	{
		this.wheres = wheres;
		nestedWheres.clear();
		nestedWheres.add(wheres);
		return this;
	}
	
	public Update where(Where where)
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
	
	public Update whereOpenParen()
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
	
	public Update whereCloseParen()
	{
		if(!nestedWheres.isEmpty())
		{
			nestedWheres.removeLast();
		}
		return this;
	}
	
	public Update where(String sql)
	{
		return where(Where.sql(sql));
	}
	
	public Update where(String sql, Object value)
	{
		return where(Where.sql(sql, value));
	}
	
	public Update where(String sql, Collection<Object> values)
	{
		return where(Where.sql(sql, values));
	}
	
	public Update whereEquals(String name, Object value)
	{
		return where(Where.equals(name, value));
	}
	
	public Update whereEquals(String table, String name, Object value)
	{
		return where(Where.equals(table, name, value));
	}
	
	public Update whereNotEquals(String name, Object value)
	{
		return where(Where.notEquals(name, value));
	}
	
	public Update whereLessThan(String name, Object value)
	{
		return where(Where.lessThan(name, value));
	}
	
	public Update whereGreaterThan(String name, Object value)
	{
		return where(Where.greaterThan(name, value));
	}
	
	public Update whereLessThanOrEquals(String name, Object value)
	{
		return where(Where.lessThanOrEquals(name, value));
	}
	
	public Update whereGreaterThanOrEquals(String name, Object value)
	{
		return where(Where.greaterThanOrEquals(name, value));
	}
	
	public Update whereIn(String name, Collection<Object> values)
	{
		return where(Where.in(name, values));
	}
	
	public Update whereLike(String name, Object value)
	{
		return where(Where.like(name, value));
	}
	
	public Update whereBetween(String name, Object value1, Object value2)
	{
		return where(Where.between(name, value1, value2));
	}
	
	public Update whereIsNull(String name)
	{
		return where(Where.isNull(name));
	}
	
	public Update whereIsNotNull(String name)
	{
		return where(Where.isNotNull(name));
	}
	
	public Update orWhere(Where where)
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
	
	public Update orWhereOpenParen()
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
	
	public Update orWhere(String sql)
	{
		return orWhere(Where.sql(sql));
	}
	
	public Update orWhere(String sql, Object value)
	{
		return orWhere(Where.sql(sql, value));
	}
	
	public Update orWhere(String sql, Collection<Object> values)
	{
		return orWhere(Where.sql(sql, values));
	}
	
	public Update orWhereEquals(String name, Object value)
	{
		return orWhere(Where.equals(name, value));
	}
	
	public Update orWhereNotEquals(String name, Object value)
	{
		return orWhere(Where.notEquals(name, value));
	}
	
	public Update orWhereLessThan(String name, Object value)
	{
		return orWhere(Where.lessThan(name, value));
	}
	
	public Update orWhereGreaterThan(String name, Object value)
	{
		return orWhere(Where.greaterThan(name, value));
	}
	
	public Update orWhereLessThanOrEquals(String name, Object value)
	{
		return orWhere(Where.lessThanOrEquals(name, value));
	}
	
	public Update orWhereGreaterThanOrEquals(String name, Object value)
	{
		return orWhere(Where.greaterThanOrEquals(name, value));
	}
	
	public Update orWhereIn(String name, Collection<Object> values)
	{
		return orWhere(Where.in(name, values));
	}
	
	public Update orWhereLike(String name, Object value)
	{
		return orWhere(Where.like(name, value));
	}
	
	public Update orWhereBetween(String name, Object value1, Object value2)
	{
		return orWhere(Where.between(name, value1, value2));
	}
	
	public Update orWhereIsNull(String name)
	{
		return orWhere(Where.isNull(name));
	}
	
	public Update orWhereIsNotNull(String name)
	{
		return orWhere(Where.isNotNull(name));
	}
	
	public LinkedList<Object> values()
	{
		LinkedList<Object> values = new LinkedList<Object>();
		if(this.values != null)
		{
			values.addAll(this.values);
		}
		if(wheres != null)
		{
			values.addAll(wheres.values());
		}
		return values;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(UPDATE + tick(table));
		builder.append(LF + SET + list(sets));
		builder.append(wheres != null ? wheres : "");
		builder.append(END);
		return builder.toString();
	}
	
}
