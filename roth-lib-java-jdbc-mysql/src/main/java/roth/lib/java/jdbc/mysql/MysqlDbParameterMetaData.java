package roth.lib.java.jdbc.mysql;

import java.sql.ParameterMetaData;

import roth.lib.java.jdbc.DbParameterMetaData;

public class MysqlDbParameterMetaData extends DbParameterMetaData implements MysqlDbWrapper
{
	
	public MysqlDbParameterMetaData(ParameterMetaData parameterMetaData)
	{
		super(parameterMetaData);
	}
	
}
