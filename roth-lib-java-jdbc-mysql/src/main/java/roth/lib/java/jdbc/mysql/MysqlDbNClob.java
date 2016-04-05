package roth.lib.java.jdbc.mysql;

import java.sql.NClob;

import roth.lib.java.jdbc.JdbcNClob;

public class MysqlDbNClob extends JdbcNClob implements MysqlDbWrapper
{
	
	public MysqlDbNClob(NClob nClob)
	{
		super(nClob);
	}
	
}
