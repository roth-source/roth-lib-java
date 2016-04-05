package roth.lib.java.jdbc.mysql;

import java.sql.Blob;

import roth.lib.java.jdbc.JdbcBlob;

public class MysqlDbBlob extends JdbcBlob implements MysqlDbWrapper
{
	
	public MysqlDbBlob(Blob blob)
	{
		super(blob);
	}
	
}
