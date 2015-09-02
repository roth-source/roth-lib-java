package roth.lib.db.mysql;

import java.sql.SQLInput;

import roth.lib.db.DbSQLInput;

public class MysqlDbSQLInput extends DbSQLInput implements MysqlDbWrapper
{
	
	public MysqlDbSQLInput(SQLInput sqlInput)
	{
		super(sqlInput);
	}
	
}
