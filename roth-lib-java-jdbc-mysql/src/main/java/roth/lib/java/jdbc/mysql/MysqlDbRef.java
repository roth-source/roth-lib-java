package roth.lib.java.jdbc.mysql;

import java.sql.Ref;

import roth.lib.java.jdbc.JdbcRef;

public class MysqlDbRef extends JdbcRef implements MysqlDbWrapper
{
	
	public MysqlDbRef(Ref ref)
	{
		super(ref);
	}
	
}
