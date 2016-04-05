package roth.lib.java.jdbc;

import java.sql.RowId;

public abstract class JdbcRowId implements RowId, JdbcWrapper
{
	protected RowId rowId;
	
	public JdbcRowId(RowId rowId)
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
