package roth.lib.java.db.mysql;

import java.sql.Savepoint;

import roth.lib.java.db.DbSavepoint;

public class MysqlDbSavepoint extends DbSavepoint implements MysqlDbWrapper
{
	
	public MysqlDbSavepoint(Savepoint savepoint)
	{
		super(savepoint);
	}
	
}
