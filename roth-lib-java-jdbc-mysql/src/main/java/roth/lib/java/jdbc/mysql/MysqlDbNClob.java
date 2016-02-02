package roth.lib.java.jdbc.mysql;

import java.sql.NClob;

import roth.lib.java.jdbc.DbNClob;

public class MysqlDbNClob extends DbNClob implements MysqlDbWrapper
{
	
	public MysqlDbNClob(NClob nClob)
	{
		super(nClob);
	}
	
}
