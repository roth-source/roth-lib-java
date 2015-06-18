package roth.lib.map.rdb.sql;

@SuppressWarnings("serial")
public class Table extends Sql
{
	protected static final String FROM = "   FROM ";
	
	private String name;
	private String alias;
	
	protected Table(String name, String alias)
	{
		this.name = name;
		this.alias = alias;
	}
	
	public static Table name(String name)
	{
		return new Table(name, null);
	}
	
	public static Table nameAs(String name, String alias)
	{
		return new Table(name, alias);
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
