package roth.lib.java.jdbc.mysql;

import java.sql.SQLOutput;

import roth.lib.java.jdbc.JdbcSQLOutput;

public class MysqlDbSQLOutput extends JdbcSQLOutput implements MysqlDbWrapper
{
	
	public MysqlDbSQLOutput(SQLOutput sqlOutput)
	{
		super(sqlOutput);
	}
	
}
