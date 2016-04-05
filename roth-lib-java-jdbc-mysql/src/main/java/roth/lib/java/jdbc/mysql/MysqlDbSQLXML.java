package roth.lib.java.jdbc.mysql;

import java.sql.SQLXML;

import roth.lib.java.jdbc.JdbcSQLXML;

public class MysqlDbSQLXML extends JdbcSQLXML implements MysqlDbWrapper
{
	
	public MysqlDbSQLXML(SQLXML sqlXml)
	{
		super(sqlXml);
	}
	
}
