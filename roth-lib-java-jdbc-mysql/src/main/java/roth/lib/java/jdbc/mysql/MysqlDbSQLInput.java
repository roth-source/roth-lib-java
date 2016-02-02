package roth.lib.java.jdbc.mysql;

import java.sql.SQLInput;

import roth.lib.java.jdbc.DbSQLInput;

public class MysqlDbSQLInput extends DbSQLInput implements MysqlDbWrapper
{
	
	public MysqlDbSQLInput(SQLInput sqlInput)
	{
		super(sqlInput);
	}
	
}
