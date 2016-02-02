package roth.lib.java.jdbc.mysql;

import java.sql.RowId;

import roth.lib.java.jdbc.DbRowId;

public class MysqlDbRowId extends DbRowId implements MysqlDbWrapper
{
	
	public MysqlDbRowId(RowId rowId)
	{
		super(rowId);
	}
	
}
