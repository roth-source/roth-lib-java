package roth.lib.java.db.mysql;

import java.sql.NClob;

import roth.lib.java.db.DbNClob;

public class MysqlDbNClob extends DbNClob implements MysqlDbWrapper
{
	
	public MysqlDbNClob(NClob nClob)
	{
		super(nClob);
	}
	
}
