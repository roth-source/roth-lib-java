package roth.lib.java.jdbc.mysql;

import java.sql.Clob;

import roth.lib.java.jdbc.JdbcClob;

public class MysqlDbClob extends JdbcClob implements MysqlDbWrapper
{
	
	public MysqlDbClob(Clob clob)
	{
		super(clob);
	}
	
}
