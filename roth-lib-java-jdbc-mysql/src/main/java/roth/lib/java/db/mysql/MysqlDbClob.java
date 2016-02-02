package roth.lib.java.db.mysql;

import java.sql.Clob;

import roth.lib.java.db.DbClob;

public class MysqlDbClob extends DbClob implements MysqlDbWrapper
{
	
	public MysqlDbClob(Clob clob)
	{
		super(clob);
	}
	
}
