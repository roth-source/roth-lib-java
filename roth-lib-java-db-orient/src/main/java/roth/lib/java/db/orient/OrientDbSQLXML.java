package roth.lib.java.db.orient;

import java.sql.SQLXML;

import roth.lib.java.db.DbSQLXML;

public class OrientDbSQLXML extends DbSQLXML implements OrientDbWrapper
{
	
	public OrientDbSQLXML(SQLXML sqlXml)
	{
		super(sqlXml);
	}
	
}
