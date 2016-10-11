package roth.lib.java.jdbc.mysql;

import roth.lib.java.jdbc.JdbcConnection;
import roth.lib.java.jdbc.JdbcTable;
import roth.lib.java.jdbc.mysql.sql.MysqlSqlFactory;

public abstract class MysqlDbTable<T> extends JdbcTable<T> implements MysqlSqlFactory
{
	
	protected MysqlDbTable(Class<T> klass)
	{
		super(klass);
	}
	
	protected MysqlDbTable(Class<T> klass, Object request)
	{
		super(klass, request);
	}
	
	protected MysqlDbTable(Class<T> klass, JdbcConnection connection)
	{
		super(klass, connection);
	}
	
}
