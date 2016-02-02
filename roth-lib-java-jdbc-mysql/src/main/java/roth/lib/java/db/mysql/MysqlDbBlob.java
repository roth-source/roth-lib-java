package roth.lib.java.db.mysql;

import java.sql.Blob;

import roth.lib.java.db.DbBlob;

public class MysqlDbBlob extends DbBlob implements MysqlDbWrapper
{
	
	public MysqlDbBlob(Blob blob)
	{
		super(blob);
	}
	
}
