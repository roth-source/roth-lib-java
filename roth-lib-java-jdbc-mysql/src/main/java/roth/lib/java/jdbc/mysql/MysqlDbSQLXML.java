package roth.lib.java.jdbc.mysql;

import java.sql.SQLXML;

import roth.lib.java.jdbc.DbSQLXML;

public class MysqlDbSQLXML extends DbSQLXML implements MysqlDbWrapper
{
	
	public MysqlDbSQLXML(SQLXML sqlXml)
	{
		super(sqlXml);
	}
	
}
