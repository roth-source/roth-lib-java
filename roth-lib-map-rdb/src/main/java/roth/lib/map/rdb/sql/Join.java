package roth.lib.map.rdb.sql;

import java.util.Arrays;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Join extends Sql
{
	protected static final String ON = " ON ";
	protected static final String AND = " AND ";
	
	private String sql;
	
	protected Join(String sql)
	{
		this.sql = sql;
	}
	
	protected static Join join(JoinType joinType, String name, String alias, On...ons)
	{
		return new Join(joinType.get() + tick(name) + (alias != null ? AS + tick(alias) : "") + ON + list(Arrays.asList(values(ons)), AND));
	}
	
	protected static Join join(JoinType joinType, String name, String alias, String...ons)
	{
		return new Join(joinType.get() + tick(name) + (alias != null ? AS + tick(alias) : "") + ON + list(Arrays.asList(ons), AND));
	}
	
	public static Join join(String table, On...ons)
	{
		return join(JoinType.JOIN, table, null, ons);
	}
	
	public static Join joinAs(String table, String alias, On...ons)
	{
		return join(JoinType.JOIN, table, alias, ons);
	}
	
	public static Join join(String table, String...ons)
	{
		return join(JoinType.JOIN, table, null, ons);
	}
	
	public static Join joinAs(String table, String alias, String...ons)
	{
		return join(JoinType.JOIN, table, alias, ons);
	}
	
	public static Join inner(String table, On...ons)
	{
		return join(JoinType.INNER_JOIN, table, null, ons);
	}
	
	public static Join innerAs(String table, String alias, On...ons)
	{
		return join(JoinType.INNER_JOIN, table, alias, ons);
	}
	
	public static Join inner(String table, String...ons)
	{
		return join(JoinType.INNER_JOIN, table, null, ons);
	}
	
	public static Join innerAs(String table, String alias, String...ons)
	{
		return join(JoinType.INNER_JOIN, table, alias, ons);
	}
	
	public static Join left(String table, On...ons)
	{
		return join(JoinType.LEFT_JOIN, table, null, ons);
	}
	
	public static Join leftAs(String table, String alias, On...ons)
	{
		return join(JoinType.LEFT_JOIN, table, alias, ons);
	}
	
	public static Join left(String table, String...ons)
	{
		return join(JoinType.LEFT_JOIN, table, null, ons);
	}
	
	public static Join leftAs(String table, String alias, String...ons)
	{
		return join(JoinType.LEFT_JOIN, table, alias, ons);
	}
	
	public static Join right(String table, On...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, null, ons);
	}
	
	public static Join rightAs(String table, String alias, On...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, alias, ons);
	}
	
	public static Join right(String table, String...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, null, ons);
	}
	
	public static Join rightAs(String table, String alias, String...ons)
	{
		return join(JoinType.RIGHT_JOIN, table, alias, ons);
	}
	
	public static Join outer(String table, On...ons)
	{
		return join(JoinType.OUTER_JOIN, table, null, ons);
	}
	
	public static Join outerAs(String table, String alias, On...ons)
	{
		return join(JoinType.OUTER_JOIN, table, alias, ons);
	}
	
	public static Join outer(String table, String...ons)
	{
		return join(JoinType.OUTER_JOIN, table, null, ons);
	}
	
	public static Join outerAs(String table, String alias, String...ons)
	{
		return join(JoinType.OUTER_JOIN, table, alias, ons);
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
		
		private String sql;
		
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
