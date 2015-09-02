package roth.lib.db.mysql;

import java.sql.Savepoint;

import roth.lib.db.DbSavepoint;

public class MysqlDbSavepoint extends DbSavepoint implements MysqlDbWrapper
{
	
	public MysqlDbSavepoint(Savepoint savepoint)
	{
		super(savepoint);
	}
	
}
