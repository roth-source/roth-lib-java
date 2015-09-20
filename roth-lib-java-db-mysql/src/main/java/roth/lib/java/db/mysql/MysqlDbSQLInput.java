package roth.lib.java.db.mysql;

import java.sql.SQLInput;

import roth.lib.java.db.DbSQLInput;

public class MysqlDbSQLInput extends DbSQLInput implements MysqlDbWrapper
{
	
	public MysqlDbSQLInput(SQLInput sqlInput)
	{
		super(sqlInput);
	}
	
}
