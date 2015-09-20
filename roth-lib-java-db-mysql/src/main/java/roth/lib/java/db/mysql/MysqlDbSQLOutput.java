package roth.lib.java.db.mysql;

import java.sql.SQLOutput;

import roth.lib.java.db.DbSQLOutput;

public class MysqlDbSQLOutput extends DbSQLOutput implements MysqlDbWrapper
{
	
	public MysqlDbSQLOutput(SQLOutput sqlOutput)
	{
		super(sqlOutput);
	}
	
}
