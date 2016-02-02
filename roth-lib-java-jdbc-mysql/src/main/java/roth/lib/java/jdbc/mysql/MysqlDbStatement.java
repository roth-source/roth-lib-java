package roth.lib.java.jdbc.mysql;

import java.sql.Statement;

import roth.lib.java.jdbc.DbStatement;

public class MysqlDbStatement extends DbStatement implements MysqlDbWrapper
{
	
	public MysqlDbStatement(Statement statement)
	{
		super(statement);
	}
	
}
