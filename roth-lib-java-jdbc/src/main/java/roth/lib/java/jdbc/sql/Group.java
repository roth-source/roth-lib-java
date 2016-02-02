package roth.lib.java.jdbc.sql;

@SuppressWarnings("serial")
public abstract class Group extends Sql
{
	protected String sql;
	protected String table;
	protected String name;
	
	protected Group()
	{
		
	}
	
	public Group setSql(String sql)
	{
		this.sql = sql;
		return this;
	}
	
	public Group setTable(String table)
	{
		this.table = table;
		return this;
	}
	
	public Group setName(String name)
	{
		this.name = name;
		return this;
	}
	
	@Override
	public String toString()
	{
		if(sql != null)
		{
			return sql;
		}
		else
		{
			StringBuilder builder = new StringBuilder();
			if(table != null)
			{
				builder.append(tick(table));
				builder.append(DOT);
			}
			builder.append(tick(name));
			return builder.toString();
		}
	}
	
}
