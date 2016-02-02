package roth.lib.java.jdbc.mysql;

import java.sql.Clob;

import roth.lib.java.jdbc.DbClob;

public class MysqlDbClob extends DbClob implements MysqlDbWrapper
{
	
	public MysqlDbClob(Clob clob)
	{
		super(clob);
	}
	
}
