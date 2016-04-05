package roth.lib.java.jdbc.mysql;

import java.sql.Statement;

import roth.lib.java.jdbc.JdbcStatement;

public class MysqlDbStatement extends JdbcStatement implements MysqlDbWrapper
{
	
	public MysqlDbStatement(Statement statement)
	{
		super(statement);
	}
	
}
