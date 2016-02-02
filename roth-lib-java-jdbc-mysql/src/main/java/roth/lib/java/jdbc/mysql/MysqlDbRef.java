package roth.lib.java.jdbc.mysql;

import java.sql.Ref;

import roth.lib.java.jdbc.DbRef;

public class MysqlDbRef extends DbRef implements MysqlDbWrapper
{
	
	public MysqlDbRef(Ref ref)
	{
		super(ref);
	}
	
}
