package roth.lib.db.maria;

import java.sql.Statement;

import roth.lib.db.DbStatement;

public class MariaDbStatement extends DbStatement implements MariaDbWrapper
{
	
	public MariaDbStatement(Statement statement)
	{
		super(statement);
	}
	
}
