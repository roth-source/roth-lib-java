package roth.lib.db.mysql;

import java.sql.NClob;

import roth.lib.db.DbNClob;

public class MysqlDbNClob extends DbNClob implements MysqlDbWrapper
{
	
	public MysqlDbNClob(NClob nClob)
	{
		super(nClob);
	}
	
}
