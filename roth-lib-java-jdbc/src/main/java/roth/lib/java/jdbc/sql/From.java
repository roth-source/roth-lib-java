package roth.lib.java.jdbc.sql;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class From extends Sql
{
	protected String name;
	protected String alias;
	protected Select select;
	
	protected From()
	{
		
	}
	
	public From setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public From setAlias(String alias)
	{
		this.alias = alias;
		return this;
	}
	
	public From setSelect(Select select)
	{
		this.select = select;
		return this;
	}
	
	public String alias()
	{
		return alias != null ? alias : name;
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
		if(select != null)
		{
			return LF + FROM + "(" + LF + select.toString(false) + ")" + AS + tick(alias);
		}
		else
		{
			return LF + FROM + tick(name) + (alias != null ? AS + tick(alias) : "");
		}
	}
	
}
