package roth.lib.java.jdbc.mysql;

import java.sql.SQLInput;

import roth.lib.java.jdbc.JdbcSQLInput;

public class MysqlDbSQLInput extends JdbcSQLInput implements MysqlDbWrapper
{
	
	public MysqlDbSQLInput(SQLInput sqlInput)
	{
		super(sqlInput);
	}
	
}
