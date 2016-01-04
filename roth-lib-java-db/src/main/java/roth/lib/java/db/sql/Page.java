package roth.lib.java.db.sql;

@SuppressWarnings("serial")
public abstract class Page extends Sql
{
	protected Integer limit;
	protected Integer offset;
	
	protected Page()
	{
		
	}
	
	public Page setLimit(int limit)
	{
		this.limit = limit;
		return this;
	}
	
	public Page setOffset(int offset)
	{
		this.offset = offset;
		return this;
	}
	
	@Override
	public String toString()
	{
		return (limit != null ? LF + LIMIT + limit : "") + (offset != null ? LF + OFFSET + offset : "");
	}
	
}
