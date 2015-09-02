package roth.lib.db.mysql;

import java.sql.SQLOutput;

import roth.lib.db.DbSQLOutput;

public class MysqlDbSQLOutput extends DbSQLOutput implements MysqlDbWrapper
{
	
	public MysqlDbSQLOutput(SQLOutput sqlOutput)
	{
		super(sqlOutput);
	}
	
}
