package roth.lib.java.jdbc.mysql;

import java.sql.RowId;

import roth.lib.java.jdbc.JdbcRowId;

public class MysqlDbRowId extends JdbcRowId implements MysqlDbWrapper
{
	
	public MysqlDbRowId(RowId rowId)
	{
		super(rowId);
	}
	
}
