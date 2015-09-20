package roth.lib.java.db.mysql;

import java.sql.RowId;

import roth.lib.java.db.DbRowId;

public class MysqlDbRowId extends DbRowId implements MysqlDbWrapper
{
	
	public MysqlDbRowId(RowId rowId)
	{
		super(rowId);
	}
	
}
