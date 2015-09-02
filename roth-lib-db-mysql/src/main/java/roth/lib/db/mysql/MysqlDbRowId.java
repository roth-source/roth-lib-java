package roth.lib.db.mysql;

import java.sql.RowId;

import roth.lib.db.DbRowId;

public class MysqlDbRowId extends DbRowId implements MysqlDbWrapper
{
	
	public MysqlDbRowId(RowId rowId)
	{
		super(rowId);
	}
	
}
