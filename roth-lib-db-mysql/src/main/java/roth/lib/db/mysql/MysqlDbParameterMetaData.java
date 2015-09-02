package roth.lib.db.mysql;

import java.sql.ParameterMetaData;

import roth.lib.db.DbParameterMetaData;

public class MysqlDbParameterMetaData extends DbParameterMetaData implements MysqlDbWrapper
{
	
	public MysqlDbParameterMetaData(ParameterMetaData parameterMetaData)
	{
		super(parameterMetaData);
	}
	
}
