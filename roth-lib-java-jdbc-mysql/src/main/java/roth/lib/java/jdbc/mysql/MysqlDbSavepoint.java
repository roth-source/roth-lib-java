package roth.lib.java.jdbc.mysql;

import java.sql.Savepoint;

import roth.lib.java.jdbc.JdbcSavepoint;

public class MysqlDbSavepoint extends JdbcSavepoint implements MysqlDbWrapper
{
	
	public MysqlDbSavepoint(Savepoint savepoint)
	{
		super(savepoint);
	}
	
}
