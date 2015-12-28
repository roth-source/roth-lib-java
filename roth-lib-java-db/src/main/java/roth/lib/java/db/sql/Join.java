package roth.lib.java.db.sql;

import java.util.Arrays;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Join extends Sql
{
	protected static final String ON = " ON ";
	protected static final String AND = " AND ";
	
	protected String sql;
	protected LinkedList<Object> values = new LinkedList<Object>();
	
	protected Join(String sql)
	{
		this.sql = sql;
	}
	
	protected Join(String sql, LinkedList<Object> values)
	{
		this.sql = sql;
		this.values = values;
	}
	
	protected static Join join(JoinType joinType, String name, String alias, Index index, On...ons)
	{
		return new Join(joinType.get() + tick(name) + (alias != null ? AS + tick(alias) : "") + (index != null ? index.toString() : "") + ON + list(Arrays.asList(values(ons)), AND));
	}
	
	protected static Join join(JoinType joinType, String name, String alias, Index index, String...ons)
	{
		return new Join(joinType.get() + tick(name) + (alias != null ? AS + tick(alias) : "") + (index != null ? index.toString() : "") + ON + list(Arrays.asList(ons), AND));
	}
	
	protected static Join join(JoinType joinType, Select select, String alias, Index index, String...ons)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(joinType.get());
		builder.append("(");
		builder.append(LF);
		builder.append(select.toString(false));
		builder.append(LF);
		builder.append("        ) ");
		builder.append(AS);
		builder.append(tick(alias));
		builder.append(index != null ? index.toString() : "");
		builder.append(ON);
		builder.append(list(Arrays.asList(ons), AND));
		return new Join(builder.toString(), select.values());
	}
	
	public static Join join(String table, On...ons)
	{
		return join(JoinType.JOIN, table, null, null, ons);
	}
	
	public static Join joinAs(String table, String alias, On...ons)
	{
		return join(JoinType.JOIN, table, alias, null, ons);
	}
	
	public static Join join(String table, String...ons)
	{
		return join(JoinType.JOIN, table, null, null, ons);
	}
	
	public static Join joinAs(String table, String alias, String...ons)
	{
		return join(JoinType.JOIN, table, alias, null, ons);
	}
	
	public static Join joinAs(Select select, String alias, String...ons)
	{
		return join(JoinType.JOIN, select, alias, null, ons);
	}

	public static Join join(String table, Index index, On...ons)
	{
		return join(JoinType.JOIN, table, null, index, ons);
	}
	
	public static Join joinAs(String table, String alias, Index index, On...ons)
	{
		return join(JoinType.JOIN, table, alias, index, ons);
	}
	
	public static Join join(String table, Index index, String...ons)
	{
		return join(JoinType.JOIN, table, null, index, ons);
	}
	
	public static Join joinAs(String table, String alias, Index index, String...ons)
	{
		return join(JoinType.JOIN, table, alias, index, ons);
	}
	
	public static Join joinAs(Select select, String alias, Index index, String...ons)
	{
		return join(JoinType.JOIN, select, alias, index, ons);
	}
	
	public static Join inner(String table, On...ons)
	{
		return join(JoinType.INNER_JOIN, table, null, null, ons);
	}
	
	public static Join innerAs(String table, String alias, On...ons)
	{
		return join(JoinType.INNER_JOIN, table, alias, null, ons);
	}
	
	public static Join inner(String table, String...ons)
	{
		return join(JoinType.INNER_JOIN, table, null, null, ons);
	}
	
	public static Join innerAs(String table, String alias, String...ons)
	{
		return join(JoinType.INNER_JOIN, table, alias, null, ons);
	}
	
	public static Join innerAs(Select select, String alias, String...ons)
	{
		return join(JoinType.INNER_JOIN, select, alias, null, ons);
	}
	
	public static Join inner(String table, Index index, On...ons)
	{
		return join(JoinType.INNER_JOIN, table, null, index, ons);
	}
	
	public static Join innerAs(String table, String alias, Index index, On...ons)
	{
		return join(JoinType.INNER_JOIN, table, alias, index, ons);
	}
	
	public static Join inner(String table, Index index, String...ons)
	{
		return join(JoinType.INNER_JOIN, table, null, index, ons);
	}
	
	public static Join innerAs(String table, String alias, Index index, String...ons)
	{
		return join(JoinType.INNER_JOIN, table, alias, index, ons);
	}
	
	public static Join innerAs(Select select, String alias, Index index, String...ons)
	{
		return join(JoinType.INNER_JOIN, select, alias, index, ons);
	}
	
	public static Join left(String table, On...ons)
	{
		return join(JoinType.LEFT_JOIN, table, null, null, ons);
	}
	
	public static Join leftAs(String table, String alias, On...ons)
	{
		return join(JoinType.LEFT_JOIN, table, alias, null, ons);
	}
	
	public static Join left(String table, String...ons)
	{
		return join(JoinType.LEFT_JOIN, table, null, null, ons);
	}
	
	public static Join leftAs(String table, String alias, String...ons)
	{
		return join(JoinType.LEFT_JOIN, table, alias, null, ons);
	}
	
	public static Join leftAs(Select select, String alias, String...ons)
	{
		return join(JoinType.LEFT_JOIN, select, alias, null, ons);
	}
	
	public static Join left(String table, Index index, On...ons)
	{
		return join(JoinType.LEFT_JOIN, table, null, index, ons);
	}
	
	public static Join leftAs(String table, String alias, Index index, On...ons)
	{
		return join(JoinType.LEFT_JOIN, table, alias, index, ons);
	}
	
	public static Join left(String table, Index index, String...ons)
	{
		return join(JoinType.LEFT_JOIN, table, null, index, ons);
	}
	
	public static Join leftAs(String table, String alias, Index index, String...ons)
	{
		return join(JoinType.LEFT_JOIN, table, alias, index, ons);
	}
	
	public static Join leftAs(Select select, String alias, Index index, String...ons)
	{
		return join(JoinType.LEFT_JOIN, select, alias, index, ons);
	}
	
	public static Join right(String table, On...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, null, null, ons);
	}
	
	public static Join rightAs(String table, String alias, On...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, alias, null, ons);
	}
	
	public static Join right(String table, String...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, null, null, ons);
	}
	
	public static Join rightAs(String table, String alias, String...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, alias, null, ons);
	}
	
	public static Join rightAs(Select select, String alias, String...ons)
	{
		return join(JoinType.RIGHT_JOIN, select, alias, null, ons);
	}
	
	public static Join right(String table, Index index, On...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, null, index, ons);
	}
	
	public static Join rightAs(String table, String alias, Index index, On...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, alias, index, ons);
	}
	
	public static Join right(String table, Index index, String...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, null, index, ons);
	}
	
	public static Join rightAs(String table, String alias, Index index, String...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, alias, index, ons);
	}
	
	public static Join rightAs(Select select, String alias, Index index, String...ons)
	{
		return join(JoinType.RIGHT_JOIN, select, alias, index, ons);
	}
	
	public static Join outer(String table, On...ons)
	{
		return join(JoinType.OUTER_JOIN, table, null, null, ons);
	}
	
	public static Join outerAs(String table, String alias, On...ons)
	{
		return join(JoinType.OUTER_JOIN, table, alias, null, ons);
	}
	
	public static Join outer(String table, String...ons)
	{
		return join(JoinType.OUTER_JOIN, table, null, null, ons);
	}
	
	public static Join outerAs(String table, String alias, String...ons)
	{
		return join(JoinType.OUTER_JOIN, table, alias, null, ons);
	}
	
	public static Join outerAs(Select select, String alias, String...ons)
	{
		return join(JoinType.OUTER_JOIN, select, alias, null, ons);
	}
	
	public static Join outer(String table, Index index, On...ons)
	{
		return join(JoinType.OUTER_JOIN, table, null, index, ons);
	}
	
	public static Join outerAs(String table, String alias, Index index, On...ons)
	{
		return join(JoinType.OUTER_JOIN, table, alias, index, ons);
	}
	
	public static Join outer(String table, Index index, String...ons)
	{
		return join(JoinType.OUTER_JOIN, table, null, index, ons);
	}
	
	public static Join outerAs(String table, String alias, Index index, String...ons)
	{
		return join(JoinType.OUTER_JOIN, table, alias, index, ons);
	}
	
	public static Join outerAs(Select select, String alias, Index index, String...ons)
	{
		return join(JoinType.OUTER_JOIN, select, alias, index, ons);
	}
	
	public static Join sql(String sql)
	{
		return new Join(sql);
	}
	
	public static On on(String table1, String name1, String table2, String name2)
	{
		return On.fields(table1, name1, table2, name2);
	}
	
	protected static String[] values(On...ons)
	{
		LinkedList<String> values = new LinkedList<String>();
		if(ons != null && ons.length > 0);
		for(On on : ons)
		{
			values.add(on.toString());
		}
		return values.toArray(new String[0]);
	}
	
	public LinkedList<Object> values()
	{
		return values;
	}
	
	@Override
	public String toString()
	{
		return sql;
	}
	
	public static enum JoinType
	{
		JOIN			("   JOIN "),
		INNER_JOIN		("  INNER JOIN "),
		LEFT_JOIN		("   LEFT JOIN "),
		RIGHT_JOIN		("  RIGHT JOIN "),
		OUTER_JOIN		("  OUTER JOIN "),
		;
		
		final String name;
		
		JoinType(String name)
		{
			this.name = name;
		}
		
		public String get()
		{
			return name;
		}
		
	}
	
	public static class On extends Sql
	{
		protected static final String EQ = " = ";
		
		protected String sql;
		
		protected On(String sql)
		{
			this.sql = sql;
		}
		
		public static On fields(String table1, String name1, String table2, String name2)
		{
			return new On(tick(table1) + DOT + tick(name1) + EQ + tick(table2) + DOT + tick(name2));
		}
		
		@Override
		public String toString()
		{
			return sql;
		}
	}
	
}
