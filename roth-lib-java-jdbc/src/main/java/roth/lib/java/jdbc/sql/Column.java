package roth.lib.java.jdbc.sql;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Column extends Sql
{
	protected String sql;
	protected Select select;
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
	
	public Column setSelect(Select select)
	{
		this.select = select;
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
	public List<Object> getValues()
	{
		if(select != null)
		{
			return select.getValues();
		}
		else
		{
			return super.getValues();
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		if(sql != null)
		{
			if(select != null)
			{
				builder.append(String.format(sql, select.toString(false)));
			}
			else
			{
				builder.append(sql);
			}
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
