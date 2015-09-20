package roth.lib.java.db.mysql;

import java.sql.ParameterMetaData;

import roth.lib.java.db.DbParameterMetaData;

public class MysqlDbParameterMetaData extends DbParameterMetaData implements MysqlDbWrapper
{
	
	public MysqlDbParameterMetaData(ParameterMetaData parameterMetaData)
	{
		super(parameterMetaData);
	}
	
}
