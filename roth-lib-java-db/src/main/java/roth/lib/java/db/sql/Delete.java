package roth.lib.java.db.sql;

import java.util.Collection;
import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Delete extends Sql
{
	protected String table;
	protected Wheres wheres;
	
	protected List<Wheres> nestedWheres = new List<Wheres>();
	
	public Delete()
	{
		
	}
	
	public Delete setTable(String table)
	{
		this.table = table;
		return this;
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
			wheres = newWheres();
			wheres(wheres);
		}
		wheres.andWhere(where);
		return this;
	}
	
	public Delete whereOpenParen()
	{
		Wheres wheres = newWheres();
		Wheres lastWheres = nestedWheres.peekLast();
		if(lastWheres == null)
		{
			lastWheres = newWheres();
			wheres(lastWheres);
		}
		nestedWheres.add(wheres);
		lastWheres.andWheres(wheres);
		return this;
	}
	
	public Delete whereCloseParen()
	{
		nestedWheres.pollLast();
		return this;
	}
	
	public Delete whereSql(String sql)
	{
		return where(newWhere().setSql(sql));
	}
	
	public Delete whereSql(String sql, Object...values)
	{
		return where(newWhere().setSql(sql).addValues(values));
	}
	
	public Delete whereSql(String sql, Collection<?> values)
	{
		return where(newWhere().setSql(sql).setValues(values));
	}
	
	public Delete whereEquals(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_EQ).addValues(value));
	}
	
	public Delete whereEquals(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_EQ).addValues(value));
	}
	
	public Delete whereNotEquals(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_NE).addValues(value));
	}
	
	public Delete whereNotEquals(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_NE).addValues(value));
	}
	
	public Delete whereLessThan(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_LT).addValues(value));
	}
	
	public Delete whereLessThan(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_LT).addValues(value));
	}
	
	public Delete whereGreaterThan(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_GT).addValues(value));
	}
	
	public Delete whereGreaterThan(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_GT).addValues(value));
	}
	
	public Delete whereLessThanOrEquals(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_LE).addValues(value));
	}
	
	public Delete whereLessThanOrEquals(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_LE).addValues(value));
	}
	
	public Delete whereGreaterThanOrEquals(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_GE).addValues(value));
	}
	
	public Delete whereGreaterThanOrEquals(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_GE).addValues(value));
	}
	
	public Delete whereIn(String name, Object...values)
	{
		return where(newWhere().setName(name).setOpType(OP_IN).addValues(values));
	}
	
	public Delete whereIn(String name, Collection<?> values)
	{
		return where(newWhere().setName(name).setOpType(OP_IN).setValues(values));
	}
	
	public Delete whereIn(String table, String name, Object...values)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_IN).addValues(values));
	}
	
	public Delete whereIn(String table, String name, Collection<?> values)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_IN).setValues(values));
	}
	
	public Delete whereNotIn(String name, Object...values)
	{
		return where(newWhere().setName(name).setOpType(OP_NOT_IN).addValues(values));
	}
	
	public Delete whereNotIn(String name, Collection<?> values)
	{
		return where(newWhere().setName(name).setOpType(OP_NOT_IN).setValues(values));
	}
	
	public Delete whereNotIn(String table, String name, Object...values)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_NOT_IN).addValues(values));
	}
	
	public Delete whereNotIn(String table, String name, Collection<?> values)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_NOT_IN).setValues(values));
	}
	
	public Delete whereLike(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_LIKE).addValues(value));
	}
	
	public Delete whereLike(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_LIKE).addValues(value));
	}
	
	public Delete whereNotLike(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_NOT_LIKE).addValues(value));
	}
	
	public Delete whereNotLike(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_NOT_LIKE).addValues(value));
	}
	
	public Delete whereBetween(String name, Object value1, Object value2)
	{
		return where(newWhere().setName(name).setOpType(OP_BETWEEN).addValues(value1, value2));
	}
	
	public Delete whereBetween(String table, String name, Object value1, Object value2)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_BETWEEN).addValues(value1, value2));
	}
	
	public Delete whereIsNull(String name)
	{
		return where(newWhere().setName(name).setOpType(OP_IS_NULL));
	}
	
	public Delete whereIsNull(String table, String name)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_IS_NULL));
	}
	
	public Delete whereIsNotNull(String name)
	{
		return where(newWhere().setName(name).setOpType(OP_IS_NOT_NULL));
	}
	
	public Delete whereIsNotNull(String table, String name)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_IS_NOT_NULL));
	}
	
	public Delete orWhere(Where where)
	{
		Wheres wheres = nestedWheres.peekLast();
		if(wheres == null)
		{
			wheres = newWheres();
			wheres(wheres);
		}
		wheres.orWhere(where);
		return this;
	}
	
	public Delete orWhereOpenParen()
	{
		Wheres wheres = newWheres();
		Wheres lastWheres = nestedWheres.peekLast();
		if(lastWheres == null)
		{
			lastWheres = newWheres();
			wheres(lastWheres);
		}
		nestedWheres.add(wheres);
		lastWheres.orWheres(wheres);
		return this;
	}
	
	public Delete orWhereSql(String sql)
	{
		return orWhere(newWhere().setSql(sql));
	}
	
	public Delete orWhereSql(String sql, Object...values)
	{
		return orWhere(newWhere().setSql(sql).addValues(values));
	}
	
	public Delete orWhereSql(String sql, Collection<?> values)
	{
		return orWhere(newWhere().setSql(sql).setValues(values));
	}
	
	public Delete orWhereEquals(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_EQ).addValues(value));
	}
	
	public Delete orWhereEquals(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_EQ).addValues(value));
	}
	
	public Delete orWhereNotEquals(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_NE).addValues(value));
	}
	
	public Delete orWhereNotEquals(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_NE).addValues(value));
	}
	
	public Delete orWhereLessThan(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_LT).addValues(value));
	}
	
	public Delete orWhereLessThan(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_LT).addValues(value));
	}
	
	public Delete orWhereGreaterThan(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_GT).addValues(value));
	}
	
	public Delete orWhereGreaterThan(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_GT).addValues(value));
	}
	
	public Delete orWhereLessThanOrEquals(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_LE).addValues(value));
	}
	
	public Delete orWhereLessThanOrEquals(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_LE).addValues(value));
	}
	
	public Delete orWhereGreaterThanOrEquals(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_GE).addValues(value));
	}
	
	public Delete orWhereGreaterThanOrEquals(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_GE).addValues(value));
	}
	
	public Delete orWhereIn(String name, Object...values)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_IN).addValues(values));
	}
	
	public Delete orWhereIn(String name, Collection<?> values)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_IN).setValues(values));
	}
	
	public Delete orWhereIn(String table, String name, Object...values)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_IN).addValues(values));
	}
	
	public Delete orWhereIn(String table, String name, Collection<?> values)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_IN).setValues(values));
	}
	
	public Delete orWhereNotIn(String name, Object...values)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_NOT_IN).addValues(values));
	}
	
	public Delete orWhereNotIn(String name, Collection<?> values)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_NOT_IN).setValues(values));
	}
	
	public Delete orWhereNotIn(String table, String name, Object...values)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_NOT_IN).addValues(values));
	}
	
	public Delete orWhereNotIn(String table, String name, Collection<?> values)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_NOT_IN).setValues(values));
	}
	
	public Delete orWhereLike(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_LIKE).addValues(value));
	}
	
	public Delete orWhereLike(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_LIKE).addValues(value));
	}
	
	public Delete orWhereNotLike(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_NOT_LIKE).addValues(value));
	}
	
	public Delete orWhereNotLike(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_NOT_LIKE).addValues(value));
	}
	
	public Delete orWhereBetween(String name, Object value1, Object value2)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_BETWEEN).addValues(value1, value2));
	}
	
	public Delete orWhereBetween(String table, String name, Object value1, Object value2)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_BETWEEN).addValues(value1, value2));
	}
	
	public Delete orWhereIsNull(String name)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_IS_NULL));
	}
	
	public Delete orWhereIsNull(String table, String name)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_IS_NULL));
	}
	
	public Delete orWhereIsNotNull(String name)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_IS_NOT_NULL));
	}
	
	public Delete orWhereIsNotNull(String table, String name)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_IS_NOT_NULL));
	}
	
	public List<Object> getValues()
	{
		List<Object> values = new List<Object>().allowNull();
		if(wheres != null)
		{
			values.addAll(wheres.getValues());
		}
		return values;
	}
	
	@Override
	public String toString()
	{
		if(wheres == null)
		{
			throw new IllegalArgumentException("wheres cannot be null");
		}
		String wheresValue = wheres.toString();
		if(wheresValue == null || wheresValue.isEmpty())
		{
			throw new IllegalArgumentException("wheres cannot be empty");
		}
		StringBuilder builder = new StringBuilder();
		builder.append(DELETE + tick(table));
		builder.append(wheresValue);
		builder.append(END);
		return builder.toString();
	}
	
}
