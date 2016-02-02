package roth.lib.java.db.mysql;

import java.sql.Statement;

import roth.lib.java.db.DbStatement;

public class MysqlDbStatement extends DbStatement implements MysqlDbWrapper
{
	
	public MysqlDbStatement(Statement statement)
	{
		super(statement);
	}
	
}
