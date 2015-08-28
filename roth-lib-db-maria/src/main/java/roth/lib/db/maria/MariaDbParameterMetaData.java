package roth.lib.db.maria;

import java.sql.ParameterMetaData;

import roth.lib.db.DbParameterMetaData;

public class MariaDbParameterMetaData extends DbParameterMetaData implements MariaDbWrapper
{
	
	public MariaDbParameterMetaData(ParameterMetaData parameterMetaData)
	{
		super(parameterMetaData);
	}
	
}
