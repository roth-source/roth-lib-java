package roth.lib.db.maria;

import java.sql.SQLInput;

import roth.lib.db.DbSQLInput;

public class MariaDbSQLInput extends DbSQLInput implements MariaDbWrapper
{
	
	public MariaDbSQLInput(SQLInput sqlInput)
	{
		super(sqlInput);
	}
	
}
