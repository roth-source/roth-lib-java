package roth.lib.java.jdbc.mysql;

import java.sql.ParameterMetaData;

import roth.lib.java.jdbc.JdbcParameterMetaData;

public class MysqlDbParameterMetaData extends JdbcParameterMetaData implements MysqlDbWrapper
{
	
	public MysqlDbParameterMetaData(ParameterMetaData parameterMetaData)
	{
		super(parameterMetaData);
	}
	
}
