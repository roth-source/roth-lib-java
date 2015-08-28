package roth.lib.db.maria;

import java.sql.SQLXML;

import roth.lib.db.DbSQLXML;

public class MariaDbSQLXML extends DbSQLXML implements MariaDbWrapper
{
	
	public MariaDbSQLXML(SQLXML sqlXml)
	{
		super(sqlXml);
	}
	
}
