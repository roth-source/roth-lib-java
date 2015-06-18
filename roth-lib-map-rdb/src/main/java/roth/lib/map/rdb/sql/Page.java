package roth.lib.map.rdb.sql;

@SuppressWarnings("serial")
public class Page extends Sql
{
	protected static final String LIMIT 	= "  LIMIT ";
	protected static final String OFFSET	= " OFFSET ";
	
	private Integer limit;
	private Integer offset;
	
	protected Page(Integer limit, Integer offset)
	{
		this.limit = limit;
		this.offset = offset;
	}
	
	public static Page limit(int limit)
	{
		return new Page(limit, null);
	}
	
	public static Page offset(int offset)
	{
		return new Page(null, offset);
	}
	
	public static Page limitOffset(int limit, int offset)
	{
		return new Page(limit, offset);
	}
	
	@Override
	public String toString()
	{
		return (limit != null ? LF + LIMIT + limit : "") + (offset != null ? LF + OFFSET + offset : "");
	}
	
}
