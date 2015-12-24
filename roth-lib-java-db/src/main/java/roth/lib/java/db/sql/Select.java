package roth.lib.java.db.sql;

import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Select extends Sql
{
	protected Columns columns;
	protected Table table;
	protected Joins joins;
	protected Wheres wheres;
	protected Groups groups;
	protected Havings havings;
	protected Orders orders;
	protected Page page;
	
	protected LinkedList<Wheres> nestedWheres = new LinkedList<Wheres>();
	protected LinkedList<Havings> nestedHavings = new LinkedList<Havings>();
	
	public Select()
	{
		
	}
	
	public Select(Columns columns, Table table)
	{
		columns(columns);
		table(table);
	}
	
	public Select(Columns columns, String table)
	{
		columns(columns);
		table(table);
	}
	
	public Select(Columns columns, String table, String alias)
	{
		columns(columns);
		tableAs(table, alias);
	}
	
	public Select(Table table)
	{
		table(table);
	}
	
	public Select(String table)
	{
		table(table);
	}
	
	public Select(String table, String alias)
	{
		tableAs(table, alias);
	}
	
	public Select columns(Columns columns)
	{
		this.columns = columns;
		return this;
	}
	
	public Select columnSql(String sql)
	{
		return column(Column.sql(sql));
	}
	
	public Select columnSqlAs(String sql, String alias)
	{
		return column(Column.sqlAs(sql, alias));
	}
	
	public Select column(String name)
	{
		return column(Column.name(name));
	}
	
	public Select columnAs(String name, String alias)
	{
		return column(Column.nameAs(name, alias));
	}
	
	public Select column(String table, String name)
	{
		return column(Column.tableName(table, name));
	}
	
	public Select columnAs(String table, String name, String alias)
	{
		return column(Column.tableNameAs(table, name, alias));
	}
	
	public Select column(Column column)
	{
		if(columns == null)
		{
			columns = new Columns();
		}
		columns.add(column);
		return this;
	}
	
	public boolean hasTable()
	{
		return table != null;
	}
	
	public Select table(Table table)
	{
		this.table = table;
		return this;
	}
	
	public Select table(String name)
	{
		return table(Table.name(name));
	}
	
	public Select tableAs(String name, String alias)
	{
		return table(Table.nameAs(name, alias));
	}
	
	public Select joins(Joins joins)
	{
		this.joins = joins;
		return this;
	}
	
	public Select join(Join join)
	{
		joins = joins != null ? joins : new Joins();
		joins.add(join);
		return this;
	}
	
	public Select join(String sql)
	{
		return join(Join.sql(sql));
	}
	
	public Select join(String table1, String name1, String table2, String name2)
	{
		return join(Join.join(table1, Join.on(table1, name1, table2, name2)));
	}
	
	public Select joinAs(String table1, String alias, String name1, String table2, String name2)
	{
		return join(Join.joinAs(table1, alias, Join.on(alias, name1, table2, name2)));
	}
	
	public Select joinInner(String table1, String name1, String table2, String name2)
	{
		return join(Join.inner(table1, Join.on(table1, name1, table2, name2)));
	}
	
	public Select joinInnerAs(String table1, String alias, String name1, String table2, String name2)
	{
		return join(Join.innerAs(table1, alias, Join.on(alias, name1, table2, name2)));
	}
	
	public Select joinLeft(String table1, String name1, String table2, String name2)
	{
		return join(Join.left(table1, Join.on(table1, name1, table2, name2)));
	}
	
	public Select joinLeftAs(String table1, String alias, String name1, String table2, String name2)
	{
		return join(Join.leftAs(table1, alias, Join.on(alias, name1, table2, name2)));
	}
	
	public Select joinRight(String table1, String name1, String table2, String name2)
	{
		return join(Join.right(table1, Join.on(table1, name1, table2, name2)));
	}
	
	public Select joinRightAs(String table1, String alias, String name1, String table2, String name2)
	{
		return join(Join.rightAs(table1, alias, Join.on(alias, name1, table2, name2)));
	}
	
	public Select joinOuter(String table1, String name1, String table2, String name2)
	{
		return join(Join.outer(table1, Join.on(table1, name1, table2, name2)));
	}
	
	public Select joinOuterAs(String table1, String alias, String name1, String table2, String name2)
	{
		return join(Join.outerAs(table1, alias, Join.on(alias, name1, table2, name2)));
	}
	
	public Select wheres(Wheres wheres)
	{
		this.wheres = wheres;
		nestedWheres.clear();
		nestedWheres.add(wheres);
		return this;
	}
	
	public Select where(Where where)
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
	
	public Select whereOpenParen()
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
	
	public Select whereCloseParen()
	{
		if(!nestedWheres.isEmpty())
		{
			nestedWheres.removeLast();
		}
		return this;
	}
	
	public Select where(String sql)
	{
		return where(Where.sql(sql));
	}
	
	public Select where(String sql, Object value)
	{
		return where(Where.sql(sql, value));
	}
	
	public Select where(String sql, Collection<?> values)
	{
		return where(Where.sql(sql, values));
	}
	
	public Select whereEquals(String name, Object value)
	{
		return where(Where.equals(name, value));
	}
	
	public Select whereEquals(String table, String name, Object value)
	{
		return where(Where.equals(table, name, value));
	}
	
	public Select whereNotEquals(String name, Object value)
	{
		return where(Where.notEquals(name, value));
	}
	
	public Select whereNotEquals(String table, String name, Object value)
	{
		return where(Where.notEquals(table, name, value));
	}
	
	public Select whereLessThan(String name, Object value)
	{
		return where(Where.lessThan(name, value));
	}
	
	public Select whereLessThan(String table, String name, Object value)
	{
		return where(Where.lessThan(table, name, value));
	}
	
	public Select whereGreaterThan(String name, Object value)
	{
		return where(Where.greaterThan(name, value));
	}
	
	public Select whereGreaterThan(String table, String name, Object value)
	{
		return where(Where.greaterThan(table, name, value));
	}
	
	public Select whereLessThanOrEquals(String name, Object value)
	{
		return where(Where.lessThanOrEquals(name, value));
	}
	
	public Select whereLessThanOrEquals(String table, String name, Object value)
	{
		return where(Where.lessThanOrEquals(table, name, value));
	}
	
	public Select whereGreaterThanOrEquals(String name, Object value)
	{
		return where(Where.greaterThanOrEquals(name, value));
	}
	
	public Select whereGreaterThanOrEquals(String table, String name, Object value)
	{
		return where(Where.greaterThanOrEquals(table, name, value));
	}
	
	public Select whereIn(String name, Collection<?> values)
	{
		return where(Where.in(name, values));
	}
	
	public Select whereIn(String table, String name, Collection<?> values)
	{
		return where(Where.in(table, name, values));
	}
	
	public Select whereNotIn(String name, Collection<?> values)
	{
		return where(Where.notIn(name, values));
	}
	
	public Select whereNotIn(String table, String name, Collection<?> values)
	{
		return where(Where.notIn(table, name, values));
	}
	
	public Select whereLike(String name, Object value)
	{
		return where(Where.like(name, value));
	}
	
	public Select whereLike(String table, String name, Object value)
	{
		return where(Where.like(table, name, value));
	}
	
	public Select whereNotLike(String name, Object value)
	{
		return where(Where.notLike(name, value));
	}
	
	public Select whereNotLike(String table, String name, Object value)
	{
		return where(Where.notLike(table, name, value));
	}
	
	public Select whereBetween(String name, Object value1, Object value2)
	{
		return where(Where.between(name, value1, value2));
	}
	
	public Select whereBetween(String table, String name, Object value1, Object value2)
	{
		return where(Where.between(table, name, value1, value2));
	}
	
	public Select whereIsNull(String name)
	{
		return where(Where.isNull(name));
	}
	
	public Select whereIsNull(String table, String name)
	{
		return where(Where.isNull(table, name));
	}
	
	public Select whereIsNotNull(String name)
	{
		return where(Where.isNotNull(name));
	}
	
	public Select whereIsNotNull(String table, String name)
	{
		return where(Where.isNotNull(table, name));
	}
	
	public Select orWhere(Where where)
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
	
	public Select orWhereOpenParen()
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
	
	public Select orWhere(String sql)
	{
		return orWhere(Where.sql(sql));
	}
	
	public Select orWhere(String sql, Object value)
	{
		return orWhere(Where.sql(sql, value));
	}
	
	public Select orWhere(String sql, Collection<?> values)
	{
		return orWhere(Where.sql(sql, values));
	}
	
	public Select orWhereEquals(String name, Object value)
	{
		return orWhere(Where.equals(name, value));
	}
	
	public Select orWhereEquals(String table, String name, Object value)
	{
		return orWhere(Where.equals(table, name, value));
	}
	
	public Select orWhereNotEquals(String name, Object value)
	{
		return orWhere(Where.notEquals(name, value));
	}
	
	public Select orWhereNotEquals(String table, String name, Object value)
	{
		return orWhere(Where.notEquals(table, name, value));
	}
	
	public Select orWhereLessThan(String name, Object value)
	{
		return orWhere(Where.lessThan(name, value));
	}
	
	public Select orWhereLessThan(String table, String name, Object value)
	{
		return orWhere(Where.lessThan(table, name, value));
	}
	
	public Select orWhereGreaterThan(String name, Object value)
	{
		return orWhere(Where.greaterThan(name, value));
	}
	
	public Select orWhereGreaterThan(String table, String name, Object value)
	{
		return orWhere(Where.greaterThan(table, name, value));
	}
	
	public Select orWhereLessThanOrEquals(String name, Object value)
	{
		return orWhere(Where.lessThanOrEquals(name, value));
	}
	
	public Select orWhereLessThanOrEquals(String table, String name, Object value)
	{
		return orWhere(Where.lessThanOrEquals(table, name, value));
	}
	
	public Select orWhereGreaterThanOrEquals(String name, Object value)
	{
		return orWhere(Where.greaterThanOrEquals(name, value));
	}
	
	public Select orWhereGreaterThanOrEquals(String table, String name, Object value)
	{
		return orWhere(Where.greaterThanOrEquals(table, name, value));
	}
	
	public Select orWhereIn(String name, Collection<?> values)
	{
		return orWhere(Where.in(name, values));
	}
	
	public Select orWhereIn(String table, String name, Collection<?> values)
	{
		return orWhere(Where.in(table, name, values));
	}
	
	public Select orWhereLike(String name, Object value)
	{
		return orWhere(Where.like(name, value));
	}
	
	public Select orWhereLike(String table, String name, Object value)
	{
		return orWhere(Where.like(table, name, value));
	}
	
	public Select orWhereNotLike(String name, Object value)
	{
		return orWhere(Where.notLike(name, value));
	}
	
	public Select orWhereNotLike(String table, String name, Object value)
	{
		return orWhere(Where.notLike(table, name, value));
	}
	
	public Select orWhereBetween(String name, Object value1, Object value2)
	{
		return orWhere(Where.between(name, value1, value2));
	}
	
	public Select orWhereBetween(String table, String name, Object value1, Object value2)
	{
		return orWhere(Where.between(table, name, value1, value2));
	}
	
	public Select orWhereIsNull(String name)
	{
		return orWhere(Where.isNull(name));
	}
	
	public Select orWhereIsNull(String table, String name)
	{
		return orWhere(Where.isNull(table, name));
	}
	
	public Select orWhereIsNotNull(String name)
	{
		return orWhere(Where.isNotNull(name));
	}
	
	public Select orWhereIsNotNull(String table, String name)
	{
		return orWhere(Where.isNotNull(table, name));
	}
	
	public Select groups(Groups groups)
	{
		this.groups = groups;
		return this;
	}
	
	public Select group(Group group)
	{
		groups = groups != null ? groups : new Groups();
		groups.add(group);
		return this;
	}
	
	public Select groupBy(String name)
	{
		return group(Group.byName(name));
	}
	
	public Select groupBy(String table, String name)
	{
		return group(Group.byTableName(table, name));
	}
	
	public Select havings(Havings havings)
	{
		this.havings = havings;
		nestedHavings.clear();
		nestedHavings.add(havings);
		return this;
	}
	
	public Select having(Having having)
	{
		Havings havings = nestedHavings.peekLast();
		if(havings == null)
		{
			havings = new Havings();
			havings(havings);
		}
		havings.and(having);
		return this;
	}
	
	public Select havingOpenParen()
	{
		Havings havings = new Havings();
		Havings lastHavings = nestedHavings.peekLast();
		if(lastHavings == null)
		{
			lastHavings = new Havings();
			havings(lastHavings);
		}
		nestedHavings.add(havings);
		lastHavings.and(havings);
		return this;
	}
	
	public Select havingCloseParen()
	{
		if(!nestedHavings.isEmpty())
		{
			nestedHavings.removeLast();
		}
		return this;
	}
	
	public Select having(String sql)
	{
		return having(Having.sql(sql));
	}
	
	public Select having(String sql, Object value)
	{
		return having(Having.sql(sql, value));
	}
	
	public Select having(String sql, Collection<?> values)
	{
		return having(Having.sql(sql, values));
	}
	
	public Select havingEquals(String name, Object value)
	{
		return having(Having.equals(name, value));
	}
	
	public Select havingEquals(String table, String name, Object value)
	{
		return having(Having.equals(table, name, value));
	}
	
	public Select havingNotEquals(String name, Object value)
	{
		return having(Having.notEquals(name, value));
	}
	
	public Select havingNotEquals(String table, String name, Object value)
	{
		return having(Having.notEquals(table, name, value));
	}
	
	public Select havingLessThan(String name, Object value)
	{
		return having(Having.lessThan(name, value));
	}
	
	public Select havingLessThan(String table, String name, Object value)
	{
		return having(Having.lessThan(table, name, value));
	}
	
	public Select havingGreaterThan(String name, Object value)
	{
		return having(Having.greaterThan(name, value));
	}
	
	public Select havingGreaterThan(String table, String name, Object value)
	{
		return having(Having.greaterThan(table, name, value));
	}
	
	public Select havingLessThanOrEquals(String name, Object value)
	{
		return having(Having.lessThanOrEquals(name, value));
	}
	
	public Select havingLessThanOrEquals(String table, String name, Object value)
	{
		return having(Having.lessThanOrEquals(table, name, value));
	}
	
	public Select havingGreaterThanOrEquals(String name, Object value)
	{
		return having(Having.greaterThanOrEquals(name, value));
	}
	
	public Select havingGreaterThanOrEquals(String table, String name, Object value)
	{
		return having(Having.greaterThanOrEquals(table, name, value));
	}
	
	public Select havingIn(String name, Collection<?> values)
	{
		return having(Having.in(name, values));
	}
	
	public Select havingIn(String table, String name, Collection<?> values)
	{
		return having(Having.in(table, name, values));
	}
	
	public Select havingLike(String name, Object value)
	{
		return having(Having.like(name, value));
	}
	
	public Select havingLike(String table, String name, Object value)
	{
		return having(Having.like(table, name, value));
	}
	
	public Select havingBetween(String name, Object value1, Object value2)
	{
		return having(Having.between(name, value1, value2));
	}
	
	public Select havingBetween(String table, String name, Object value1, Object value2)
	{
		return having(Having.between(table, name, value1, value2));
	}
	
	public Select havingIsNull(String name)
	{
		return having(Having.isNull(name));
	}
	
	public Select havingIsNull(String table, String name)
	{
		return having(Having.isNull(table, name));
	}
	
	public Select havingIsNotNull(String name)
	{
		return having(Having.isNotNull(name));
	}
	
	public Select havingIsNotNull(String table, String name)
	{
		return having(Having.isNotNull(table, name));
	}
	
	public Select orHaving(Having having)
	{
		Havings havings = nestedHavings.peekLast();
		if(havings == null)
		{
			havings = new Havings();
			havings(havings);
		}
		havings.or(having);
		return this;
	}
	
	public Select orHavingOpenParen()
	{
		Havings havings = new Havings();
		Havings lastHavings = nestedHavings.peekLast();
		if(lastHavings == null)
		{
			lastHavings = new Havings();
			havings(lastHavings);
		}
		nestedHavings.add(havings);
		lastHavings.or(havings);
		return this;
	}
	
	public Select orHaving(String sql)
	{
		return orHaving(Having.sql(sql));
	}
	
	public Select orHaving(String sql, Object value)
	{
		return orHaving(Having.sql(sql, value));
	}
	
	public Select orHaving(String sql, Collection<?> values)
	{
		return orHaving(Having.sql(sql, values));
	}
	
	public Select orHavingEquals(String name, Object value)
	{
		return orHaving(Having.equals(name, value));
	}
	
	public Select orHavingEquals(String table, String name, Object value)
	{
		return orHaving(Having.equals(table, name, value));
	}
	
	public Select orHavingNotEquals(String name, Object value)
	{
		return orHaving(Having.notEquals(name, value));
	}
	
	public Select orHavingNotEquals(String table, String name, Object value)
	{
		return orHaving(Having.notEquals(table, name, value));
	}
	
	public Select orHavingLessThan(String name, Object value)
	{
		return orHaving(Having.lessThan(name, value));
	}
	
	public Select orHavingLessThan(String table, String name, Object value)
	{
		return orHaving(Having.lessThan(table, name, value));
	}
	
	public Select orHavingGreaterThan(String name, Object value)
	{
		return orHaving(Having.greaterThan(name, value));
	}
	
	public Select orHavingGreaterThan(String table, String name, Object value)
	{
		return orHaving(Having.greaterThan(table, name, value));
	}
	
	public Select orHavingLessThanOrEquals(String name, Object value)
	{
		return orHaving(Having.lessThanOrEquals(name, value));
	}
	
	public Select orHavingLessThanOrEquals(String table, String name, Object value)
	{
		return orHaving(Having.lessThanOrEquals(table, name, value));
	}
	
	public Select orHavingGreaterThanOrEquals(String name, Object value)
	{
		return orHaving(Having.greaterThanOrEquals(name, value));
	}
	
	public Select orHavingGreaterThanOrEquals(String table, String name, Object value)
	{
		return orHaving(Having.greaterThanOrEquals(table, name, value));
	}
	
	public Select orHavingIn(String name, Collection<?> values)
	{
		return orHaving(Having.in(name, values));
	}
	
	public Select orHavingIn(String table, String name, Collection<?> values)
	{
		return orHaving(Having.in(table, name, values));
	}
	
	public Select orHavingLike(String name, Object value)
	{
		return orHaving(Having.like(name, value));
	}
	
	public Select orHavingLike(String table, String name, Object value)
	{
		return orHaving(Having.like(table, name, value));
	}
	
	public Select orHavingBetween(String name, Object value1, Object value2)
	{
		return orHaving(Having.between(name, value1, value2));
	}
	
	public Select orHavingBetween(String table, String name, Object value1, Object value2)
	{
		return orHaving(Having.between(table, name, value1, value2));
	}
	
	public Select orHavingIsNull(String name)
	{
		return orHaving(Having.isNull(name));
	}
	
	public Select orHavingIsNull(String table, String name)
	{
		return orHaving(Having.isNull(table, name));
	}
	
	public Select orHavingIsNotNull(String name)
	{
		return orHaving(Having.isNotNull(name));
	}
	
	public Select orHavingIsNotNull(String table, String name)
	{
		return orHaving(Having.isNotNull(table, name));
	}
	
	public Select orders(Orders orders)
	{
		this.orders = orders;
		return this;
	}
	
	public Select order(Order order)
	{
		orders = orders != null ? orders : new Orders();
		orders.add(order);
		return this;
	}
	
	public Select orderByAsc(String name)
	{
		return order(Order.byAsc(name));
	}
	
	public Select orderByAsc(String table, String name)
	{
		return order(Order.byAsc(table, name));
	}
	
	public Select orderByDesc(String name)
	{
		return order(Order.byDesc(name));
	}
	
	public Select orderByDesc(String table, String name)
	{
		return order(Order.byDesc(table, name));
	}
	
	public Select orderBy(String sql)
	{
		return order(Order.bySql(sql));
	}
	
	public Select page(Page page)
	{
		this.page = page;
		return this;
	}
	
	public Select limit(int limit)
	{
		return page(Page.limit(limit));
	}
	
	public Select offset(int offset)
	{
		return page(Page.offset(offset));
	}
	
	public Select limitOffset(int limit, int offset)
	{
		return page(Page.limitOffset(limit, offset));
	}
	
	@Override
	public LinkedList<Object> values()
	{
		LinkedList<Object> values = new LinkedList<Object>();
		if(joins != null)
		{
			values.addAll(joins.values());
		}
		if(wheres != null)
		{
			values.addAll(wheres.values());
		}
		if(havings != null)
		{
			values.addAll(havings.values());
		}
		return values;
	}
	
	@Override
	public String toString()
	{
		return toString(true);
	}
	
	public String toString(boolean end)
	{
		if(table == null) throw new IllegalArgumentException("table cannot be null");
		columns = columns != null ? columns : new Columns(table.alias());
		StringBuilder builder = new StringBuilder();
		builder.append(columns);
		builder.append(table);
		builder.append(joins != null ? joins : "");
		builder.append(wheres != null ? wheres : "");
		builder.append(groups != null ? groups : "");
		builder.append(havings != null ? havings : "");
		builder.append(orders != null ? orders : "");
		builder.append(page != null ? page : "");
		builder.append(end ? END : "");
		return builder.toString();
	}
	
}
