package roth.lib.db.mysql;

import java.sql.Statement;

import roth.lib.db.DbStatement;

public class MysqlDbStatement extends DbStatement implements MysqlDbWrapper
{
	
	public MysqlDbStatement(Statement statement)
	{
		super(statement);
	}
	
}
