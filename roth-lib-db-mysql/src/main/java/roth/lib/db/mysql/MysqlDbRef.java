package roth.lib.db.mysql;

import java.sql.Ref;

import roth.lib.db.DbRef;

public class MysqlDbRef extends DbRef implements MysqlDbWrapper
{
	
	public MysqlDbRef(Ref ref)
	{
		super(ref);
	}
	
}
