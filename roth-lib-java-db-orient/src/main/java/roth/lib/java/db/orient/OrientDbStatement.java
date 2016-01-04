package roth.lib.java.db.orient;

import java.sql.Statement;

import roth.lib.java.db.DbStatement;

public class OrientDbStatement extends DbStatement implements OrientDbWrapper
{
	
	public OrientDbStatement(Statement statement)
	{
		super(statement);
	}
	
}
