package roth.lib.java.jdbc.sql;

@SuppressWarnings("serial")
public abstract class Column extends Sql
{
	protected String sql;
	protected String table;
	protected String name;
	protected String alias;
	
	protected Column()
	{
		
	}

	public Column setSql(String sql)
	{
		this.sql = sql;
		return this;
	}
	
	public Column setTable(String table)
	{
		this.table = table;
		return this;
	}
	
	public Column setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Column setAlias(String alias)
	{
		this.alias = alias;
		return this;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		if(sql != null)
		{
			builder.append(sql);
		}
		else if(name != null)
		{
			if(table != null)
			{
				builder.append(tick(table));
				builder.append(DOT);
			}
			builder.append(ALL.equals(name) ? ALL : tick(name));
		}
		if(alias != null)
		{
			builder.append(AS);
			builder.append(tick(alias));
		}
		return builder.toString();
	}
	
}
