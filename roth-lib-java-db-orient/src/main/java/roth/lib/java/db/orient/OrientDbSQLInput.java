package roth.lib.java.db.orient;

import java.sql.SQLInput;

import roth.lib.java.db.DbSQLInput;

public class OrientDbSQLInput extends DbSQLInput implements OrientDbWrapper
{
	
	public OrientDbSQLInput(SQLInput sqlInput)
	{
		super(sqlInput);
	}
	
}
