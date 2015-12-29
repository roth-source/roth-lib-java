package roth.lib.java.db.sql;

import java.util.Arrays;

@SuppressWarnings("serial")
public class Index extends Sql
{
	protected static final String INDEX = " INDEX ";
	
	protected String sql;
	
	protected Index(String sql)
	{
		this.sql = sql;
	}
	
	protected static Index index(IndexType indexType, String...names)
	{
		return new Index(indexType.get() + INDEX + "(" + tick(Arrays.asList(names)) + ")");
	}
	
	public static Index force(String...names)
	{
		return index(IndexType.FORCE, names);
	}
	
	public static Index use(String...names)
	{
		return index(IndexType.USE, names);
	}
	
	public static Index ignore(String...names)
	{
		return index(IndexType.IGNORE, names);
	}
	
	@Override
	public String toString()
	{
		return sql;
	}
	
	public static enum IndexType
	{
		FORCE		(" FORCE"),
		USE			(" USE"),
		IGNORE  	(" IGNORE"),
		;
		
		final String name;
		
		IndexType(String name)
		{
			this.name = name;
		}
		
		public String get()
		{
			return name;
		}
		
	}
	
}
