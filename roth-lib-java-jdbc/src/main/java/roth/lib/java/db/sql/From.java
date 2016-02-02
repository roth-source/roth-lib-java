package roth.lib.java.db.sql;

@SuppressWarnings("serial")
public abstract class From extends Sql
{
	protected String name;
	protected String alias;
	
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
	
	public String alias()
	{
		return alias != null ? alias : name;
	}
	
	@Override
	public String toString()
	{
		return LF + FROM + tick(name) + (alias != null ? AS + tick(alias) : "");
	}
	
}
