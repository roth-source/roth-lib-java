package roth.lib.db.mysql;

import java.sql.SQLXML;

import roth.lib.db.DbSQLXML;

public class MysqlDbSQLXML extends DbSQLXML implements MysqlDbWrapper
{
	
	public MysqlDbSQLXML(SQLXML sqlXml)
	{
		super(sqlXml);
	}
	
}
