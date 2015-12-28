package roth.lib.java.db.sql;

@SuppressWarnings("serial")
public class Table extends Sql
{
	protected static final String FROM = "   FROM ";
	
	protected String name;
	protected String alias;
	protected Index index;
	
	protected Table(String name, String alias, Index index)
	{
		this.name = name;
		this.alias = alias;
		this.index = index;
	}
	
	public static Table name(String name)
	{
		return new Table(name, null, null);
	}
	
	public static Table nameAs(String name, String alias)
	{
		return new Table(name, alias, null);
	}
	
	public static Table name(String name, Index index)
	{
		return new Table(name, null, index);
	}
	
	public static Table nameAs(String name, String alias, Index index)
	{
		return new Table(name, alias, index);
	}
	
	public String alias()
	{
		return alias != null ? alias : name;
	}
	
	@Override
	public String toString()
	{
		return LF + FROM + tick(name) + (alias != null ? AS + tick(alias) : "") + (index != null ? index.toString() : "");
	}
	
}
