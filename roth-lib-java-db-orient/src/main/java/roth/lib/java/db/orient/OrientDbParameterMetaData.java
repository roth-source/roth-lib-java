package roth.lib.java.db.orient;

import java.sql.ParameterMetaData;

import roth.lib.java.db.DbParameterMetaData;

public class OrientDbParameterMetaData extends DbParameterMetaData implements OrientDbWrapper
{
	
	public OrientDbParameterMetaData(ParameterMetaData parameterMetaData)
	{
		super(parameterMetaData);
	}
	
}
