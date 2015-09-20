package roth.lib.java.db;

import java.sql.RowId;

public abstract class DbRowId implements RowId, DbWrapper
{
	protected RowId rowId;
	
	public DbRowId(RowId rowId)
	{
		this.rowId = rowId;
	}
	
	@Override
	public byte[] getBytes()
	{
		return rowId.getBytes();
	}
	
	@Override
	public int hashCode()
	{
		return rowId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return rowId.equals(obj);
	}
	
	@Override
	public String toString()
	{
		return rowId.toString();
	}
	
}
