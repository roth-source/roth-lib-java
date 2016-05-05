package roth.lib.java.jdbc.sql;

import java.util.Collection;
import roth.lib.java.lang.Map;
import java.util.Map.Entry;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Update extends Sql
{
	protected String table;
	protected Joins joins;
	protected List<Set> sets = new List<Set>();
	protected Wheres wheres;
	
	protected List<Wheres> nestedWheres = new List<Wheres>();
	
	public Update()
	{
		
	}
	
	public Update setTable(String table)
	{
		this.table = table;
		return this;
	}

	public Update joins(Joins joins)
	{
		this.joins = joins;
		return this;
	}
	
	public Update join(Join join)
	{
		joins = joins != null ? joins : newJoins();
		joins.addJoins(join);
		return this;
	}
	
	public Update join(String table1, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(JOIN).setTable(table1).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinAs(String table1, String alias, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(JOIN).setTable(table1).setAlias(alias).addOns(newOn().setTable1(alias).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinAs(Select select, String alias, On...ons)
	{
		return join(newJoin().setJoinType(JOIN).setSelect(select).setAlias(alias).addOns(ons));
	}
	
	public Update joinAs(Select select, String alias, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(JOIN).setSelect(select).setAlias(alias).addOns(newOn().setTable1(alias).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update join(String table, On...ons)
	{
		return join(newJoin().setJoinType(JOIN).setTable(table).addOns(ons));
	}
	
	public Update joinAs(String table, String alias, On...ons)
	{
		return join(newJoin().setJoinType(JOIN).setTable(table).setAlias(alias).addOns(ons));
	}
	
	public Update joinInner(String table1, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(INNER_JOIN).setTable(table1).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinInnerAs(String table1, String alias, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(INNER_JOIN).setTable(table1).setAlias(alias).addOns(newOn().setTable1(alias).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinInnerAs(Select select, String alias, On...ons)
	{
		return join(newJoin().setJoinType(INNER_JOIN).setSelect(select).setAlias(alias).addOns(ons));
	}
	
	public Update joinInnerAs(Select select, String alias, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(INNER_JOIN).setSelect(select).setAlias(alias).addOns(newOn().setTable1(alias).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinInner(String table, On...ons)
	{
		return join(newJoin().setJoinType(INNER_JOIN).setTable(table).addOns(ons));
	}
	
	public Update joinInnerAs(String table, String alias, On...ons)
	{
		return join(newJoin().setJoinType(INNER_JOIN).setTable(table).setAlias(alias).addOns(ons));
	}
	
	public Update joinLeft(String table1, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(LEFT_JOIN).setTable(table1).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinLeftAs(String table1, String alias, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(LEFT_JOIN).setTable(table1).setAlias(alias).addOns(newOn().setTable1(alias).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinLeftAs(Select select, String alias, On...ons)
	{
		return join(newJoin().setJoinType(LEFT_JOIN).setSelect(select).setAlias(alias).addOns(ons));
	}
	
	public Update joinLeftAs(Select select, String alias, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(LEFT_JOIN).setSelect(select).setAlias(alias).addOns(newOn().setTable1(alias).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinLeft(String table, On...ons)
	{
		return join(newJoin().setJoinType(LEFT_JOIN).setTable(table).addOns(ons));
	}
	
	public Update joinLeftAs(String table, String alias, On...ons)
	{
		return join(newJoin().setJoinType(LEFT_JOIN).setTable(table).setAlias(alias).addOns(ons));
	}
	
	public Update joinRight(String table1, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(RIGHT_JOIN).setTable(table1).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinRightAs(String table1, String alias, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(RIGHT_JOIN).setTable(table1).setAlias(alias).addOns(newOn().setTable1(alias).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinRightAs(Select select, String alias, On...ons)
	{
		return join(newJoin().setJoinType(RIGHT_JOIN).setSelect(select).setAlias(alias).addOns(ons));
	}
	
	public Update joinRightAs(Select select, String alias, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(RIGHT_JOIN).setSelect(select).setAlias(alias).addOns(newOn().setTable1(alias).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinRight(String table, On...ons)
	{
		return join(newJoin().setJoinType(RIGHT_JOIN).setTable(table).addOns(ons));
	}
	
	public Update joinRightAs(String table, String alias, On...ons)
	{
		return join(newJoin().setJoinType(RIGHT_JOIN).setTable(table).setAlias(alias).addOns(ons));
	}
	
	public Update joinOuter(String table1, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(OUTER_JOIN).setTable(table1).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinOuterAs(String table1, String alias, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(OUTER_JOIN).setTable(table1).setAlias(alias).addOns(newOn().setTable1(alias).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinOuterAs(Select select, String alias, On...ons)
	{
		return join(newJoin().setJoinType(OUTER_JOIN).setSelect(select).setAlias(alias).addOns(ons));
	}
	
	public Update joinOuterAs(Select select, String alias, String name1, String table2, String name2)
	{
		return join(newJoin().setJoinType(OUTER_JOIN).setSelect(select).setAlias(alias).addOns(newOn().setTable1(alias).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Update joinOuter(String table, On...ons)
	{
		return join(newJoin().setJoinType(OUTER_JOIN).setTable(table).addOns(ons));
	}
	
	public Update joinOuterAs(String table, String alias, On...ons)
	{
		return join(newJoin().setJoinType(OUTER_JOIN).setTable(table).setAlias(alias).addOns(ons));
	}
	
	public Update setSets(Collection<Set> sets)
	{
		this.sets = new List<Set>().collection(sets);
		return this;
	}
	
	public Update addSets(Set...sets)
	{
		this.sets.array(sets);
		return this;
	}
	
	public Update setNameValues(Map<String, Object> nameValues)
	{
		for(Entry<String, Object> nameValueEntry : nameValues.entrySet())
		{
			sets.add(newSet().setName(nameValueEntry.getKey()).setValue(nameValueEntry.getValue()));
		}
		return this;
	}
	
	public Update setNameValues(Collection<String> names, Collection<?> values)
	{
		if(names.size() != values.size())
		{
			throw new IllegalArgumentException("names is different size than values");
		}
		List<String> nameList = new List<String>().collection(names);
		List<Object> valueList = new List<Object>().allowNull().collection(values);
		for(int i = 0; i < nameList.size(); i++)
		{
			sets.add(newSet().setName(nameList.get(i)).setValue(valueList.get(i)));
		}
		return this;
	}
	
	public Update setSqlValues(Map<String, Object> sqlValues)
	{
		for(Entry<String, Object> sqlValueEntry : sqlValues.entrySet())
		{
			sets.add(newSet().setSql(sqlValueEntry.getKey()).setValue(sqlValueEntry.getValue()));
		}
		return this;
	}
	
	public Update setSqlValues(Collection<String> sqls, Collection<?> values)
	{
		if(sqls.size() != values.size())
		{
			throw new IllegalArgumentException("sqls is different size than values");
		}
		List<String> sqlList = new List<String>().collection(sqls);
		List<Object> valueList = new List<Object>().allowNull().collection(values);
		for(int i = 0; i < sqlList.size(); i++)
		{
			sets.add(newSet().setSql(sqlList.get(i)).setValue(valueList.get(i)));
		}
		return this;
	}
	
	public Update setEqual(String name, Object value)
	{
		sets.add(newSet().setName(name).setValue(value));
		return this;
	}
	
	public Update set(String sql, Object value)
	{
		sets.add(newSet().setSql(sql).setValue(value));
		return this;
	}
	
	public Update set(String sql)
	{
		sets.add(newSet().setSql(sql));
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
			wheres = newWheres();
			wheres(wheres);
		}
		wheres.andWhere(where);
		return this;
	}
	
	public Update whereOpenParen()
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
	
	public Update whereCloseParen()
	{
		nestedWheres.pollLast();
		return this;
	}
	
	public Update whereSql(String sql)
	{
		return where(newWhere().setSql(sql));
	}
	
	public Update whereSql(String sql, Object...values)
	{
		return where(newWhere().setSql(sql).addValues(values));
	}
	
	public Update whereSql(String sql, Collection<?> values)
	{
		return where(newWhere().setSql(sql).setValues(values));
	}
	
	public Update whereEquals(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_EQ).addValues(value));
	}
	
	public Update whereEquals(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_EQ).addValues(value));
	}
	
	public Update whereNotEquals(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_NE).addValues(value));
	}
	
	public Update whereNotEquals(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_NE).addValues(value));
	}
	
	public Update whereLessThan(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_LT).addValues(value));
	}
	
	public Update whereLessThan(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_LT).addValues(value));
	}
	
	public Update whereGreaterThan(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_GT).addValues(value));
	}
	
	public Update whereGreaterThan(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_GT).addValues(value));
	}
	
	public Update whereLessThanOrEquals(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_LE).addValues(value));
	}
	
	public Update whereLessThanOrEquals(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_LE).addValues(value));
	}
	
	public Update whereGreaterThanOrEquals(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_GE).addValues(value));
	}
	
	public Update whereGreaterThanOrEquals(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_GE).addValues(value));
	}
	
	public Update whereIn(String name, Object...values)
	{
		return where(newWhere().setName(name).setOpType(OP_IN).addValues(values));
	}
	
	public Update whereIn(String name, Collection<?> values)
	{
		return where(newWhere().setName(name).setOpType(OP_IN).setValues(values));
	}
	
	public Update whereIn(String table, String name, Object...values)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_IN).addValues(values));
	}
	
	public Update whereIn(String table, String name, Collection<?> values)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_IN).setValues(values));
	}
	
	public Update whereNotIn(String name, Object...values)
	{
		return where(newWhere().setName(name).setOpType(OP_NOT_IN).addValues(values));
	}
	
	public Update whereNotIn(String name, Collection<?> values)
	{
		return where(newWhere().setName(name).setOpType(OP_NOT_IN).setValues(values));
	}
	
	public Update whereNotIn(String table, String name, Object...values)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_NOT_IN).addValues(values));
	}
	
	public Update whereNotIn(String table, String name, Collection<?> values)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_NOT_IN).setValues(values));
	}
	
	public Update whereLike(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_LIKE).addValues(value));
	}
	
	public Update whereLike(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_LIKE).addValues(value));
	}
	
	public Update whereNotLike(String name, Object value)
	{
		return where(newWhere().setName(name).setOpType(OP_NOT_LIKE).addValues(value));
	}
	
	public Update whereNotLike(String table, String name, Object value)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_NOT_LIKE).addValues(value));
	}
	
	public Update whereBetween(String name, Object value1, Object value2)
	{
		return where(newWhere().setName(name).setOpType(OP_BETWEEN).addValues(value1, value2));
	}
	
	public Update whereBetween(String table, String name, Object value1, Object value2)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_BETWEEN).addValues(value1, value2));
	}
	
	public Update whereIsNull(String name)
	{
		return where(newWhere().setName(name).setOpType(OP_IS_NULL));
	}
	
	public Update whereIsNull(String table, String name)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_IS_NULL));
	}
	
	public Update whereIsNotNull(String name)
	{
		return where(newWhere().setName(name).setOpType(OP_IS_NOT_NULL));
	}
	
	public Update whereIsNotNull(String table, String name)
	{
		return where(newWhere().setTable(table).setName(name).setOpType(OP_IS_NOT_NULL));
	}
	
	public Update orWhere(Where where)
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
	
	public Update orWhereOpenParen()
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
	
	public Update orWhereSql(String sql)
	{
		return orWhere(newWhere().setSql(sql));
	}
	
	public Update orWhereSql(String sql, Object...values)
	{
		return orWhere(newWhere().setSql(sql).addValues(values));
	}
	
	public Update orWhereSql(String sql, Collection<?> values)
	{
		return orWhere(newWhere().setSql(sql).setValues(values));
	}
	
	public Update orWhereEquals(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_EQ).addValues(value));
	}
	
	public Update orWhereEquals(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_EQ).addValues(value));
	}
	
	public Update orWhereNotEquals(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_NE).addValues(value));
	}
	
	public Update orWhereNotEquals(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_NE).addValues(value));
	}
	
	public Update orWhereLessThan(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_LT).addValues(value));
	}
	
	public Update orWhereLessThan(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_LT).addValues(value));
	}
	
	public Update orWhereGreaterThan(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_GT).addValues(value));
	}
	
	public Update orWhereGreaterThan(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_GT).addValues(value));
	}
	
	public Update orWhereLessThanOrEquals(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_LE).addValues(value));
	}
	
	public Update orWhereLessThanOrEquals(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_LE).addValues(value));
	}
	
	public Update orWhereGreaterThanOrEquals(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_GE).addValues(value));
	}
	
	public Update orWhereGreaterThanOrEquals(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_GE).addValues(value));
	}
	
	public Update orWhereIn(String name, Object...values)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_IN).addValues(values));
	}
	
	public Update orWhereIn(String name, Collection<?> values)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_IN).setValues(values));
	}
	
	public Update orWhereIn(String table, String name, Object...values)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_IN).addValues(values));
	}
	
	public Update orWhereIn(String table, String name, Collection<?> values)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_IN).setValues(values));
	}
	
	public Update orWhereNotIn(String name, Object...values)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_NOT_IN).addValues(values));
	}
	
	public Update orWhereNotIn(String name, Collection<?> values)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_NOT_IN).setValues(values));
	}
	
	public Update orWhereNotIn(String table, String name, Object...values)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_NOT_IN).addValues(values));
	}
	
	public Update orWhereNotIn(String table, String name, Collection<?> values)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_NOT_IN).setValues(values));
	}
	
	public Update orWhereLike(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_LIKE).addValues(value));
	}
	
	public Update orWhereLike(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_LIKE).addValues(value));
	}
	
	public Update orWhereNotLike(String name, Object value)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_NOT_LIKE).addValues(value));
	}
	
	public Update orWhereNotLike(String table, String name, Object value)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_NOT_LIKE).addValues(value));
	}
	
	public Update orWhereBetween(String name, Object value1, Object value2)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_BETWEEN).addValues(value1, value2));
	}
	
	public Update orWhereBetween(String table, String name, Object value1, Object value2)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_BETWEEN).addValues(value1, value2));
	}
	
	public Update orWhereIsNull(String name)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_IS_NULL));
	}
	
	public Update orWhereIsNull(String table, String name)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_IS_NULL));
	}
	
	public Update orWhereIsNotNull(String name)
	{
		return orWhere(newWhere().setName(name).setOpType(OP_IS_NOT_NULL));
	}
	
	public Update orWhereIsNotNull(String table, String name)
	{
		return orWhere(newWhere().setTable(table).setName(name).setOpType(OP_IS_NOT_NULL));
	}
	
	public List<Object> getValues()
	{
		List<Object> values = new List<Object>().allowNull();
		for(Set set : sets)
		{
			values.add(set.value);
		}
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
		builder.append(UPDATE + tick(table));
		builder.append(joins != null ? joins : "");
		builder.append(LF + SET + list(sets));
		builder.append(wheresValue);
		builder.append(END);
		return builder.toString();
	}
	
}
