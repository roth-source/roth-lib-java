package roth.lib.java.jdbc.mysql;

import java.sql.Blob;

import roth.lib.java.jdbc.DbBlob;

public class MysqlDbBlob extends DbBlob implements MysqlDbWrapper
{
	
	public MysqlDbBlob(Blob blob)
	{
		super(blob);
	}
	
}
