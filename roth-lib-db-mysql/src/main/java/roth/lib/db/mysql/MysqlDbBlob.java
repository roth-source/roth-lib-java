package roth.lib.db.mysql;

import java.sql.Blob;

import roth.lib.db.DbBlob;

public class MysqlDbBlob extends DbBlob implements MysqlDbWrapper
{
	
	public MysqlDbBlob(Blob blob)
	{
		super(blob);
	}
	
}
