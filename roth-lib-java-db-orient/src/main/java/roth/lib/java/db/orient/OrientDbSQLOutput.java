package roth.lib.java.db.orient;

import java.sql.SQLOutput;

import roth.lib.java.db.DbSQLOutput;

public class OrientDbSQLOutput extends DbSQLOutput implements OrientDbWrapper
{
	
	public OrientDbSQLOutput(SQLOutput sqlOutput)
	{
		super(sqlOutput);
	}
	
}
