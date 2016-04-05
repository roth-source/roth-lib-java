package roth.lib.java.jdbc;

import java.sql.SQLException;
import java.sql.Struct;
import java.util.Map;

public abstract class JdbcStruct implements Struct, JdbcWrapper
{
	protected Struct struct;
	
	public JdbcStruct(Struct struct)
	{
		this.struct = struct;
	}
	
	public String getSQLTypeName() throws SQLException
	{
		return struct.getSQLTypeName();
	}
	
	public Object[] getAttributes() throws SQLException
	{
		return struct.getAttributes();
	}
	
	public Object[] getAttributes(Map<String, Class<?>> map) throws SQLException
	{
		return struct.getAttributes(map);
	}
	
}
