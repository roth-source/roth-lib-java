package roth.lib.java.jdbc.mysql;

import java.sql.Savepoint;

import roth.lib.java.jdbc.DbSavepoint;

public class MysqlDbSavepoint extends DbSavepoint implements MysqlDbWrapper
{
	
	public MysqlDbSavepoint(Savepoint savepoint)
	{
		super(savepoint);
	}
	
}
