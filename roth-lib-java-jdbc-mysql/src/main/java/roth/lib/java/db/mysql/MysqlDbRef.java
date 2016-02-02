package roth.lib.java.db.mysql;

import java.sql.Ref;

import roth.lib.java.db.DbRef;

public class MysqlDbRef extends DbRef implements MysqlDbWrapper
{
	
	public MysqlDbRef(Ref ref)
	{
		super(ref);
	}
	
}
