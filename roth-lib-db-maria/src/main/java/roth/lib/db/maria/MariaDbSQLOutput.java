package roth.lib.db.maria;

import java.sql.SQLOutput;

import roth.lib.db.DbSQLOutput;

public class MariaDbSQLOutput extends DbSQLOutput implements MariaDbWrapper
{
	
	public MariaDbSQLOutput(SQLOutput sqlOutput)
	{
		super(sqlOutput);
	}
	
}
