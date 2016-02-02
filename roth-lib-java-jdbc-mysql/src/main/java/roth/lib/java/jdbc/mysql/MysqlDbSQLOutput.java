package roth.lib.java.jdbc.mysql;

import java.sql.SQLOutput;

import roth.lib.java.jdbc.DbSQLOutput;

public class MysqlDbSQLOutput extends DbSQLOutput implements MysqlDbWrapper
{
	
	public MysqlDbSQLOutput(SQLOutput sqlOutput)
	{
		super(sqlOutput);
	}
	
}
