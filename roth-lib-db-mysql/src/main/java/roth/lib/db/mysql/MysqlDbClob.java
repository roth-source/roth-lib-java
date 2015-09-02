package roth.lib.db.mysql;

import java.sql.Clob;

import roth.lib.db.DbClob;

public class MysqlDbClob extends DbClob implements MysqlDbWrapper
{
	
	public MysqlDbClob(Clob clob)
	{
		super(clob);
	}
	
}
