package roth.lib.java.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.Time;
import java.sql.Timestamp;

public abstract class JdbcSQLInput implements SQLInput, JdbcWrapper
{
	protected SQLInput sqlInput;
	
	public JdbcSQLInput(SQLInput sqlInput)
	{
		this.sqlInput = sqlInput;
	}
	
	public String readString() throws SQLException
	{
		return sqlInput.readString();
	}
	
	public boolean readBoolean() throws SQLException
	{
		return sqlInput.readBoolean();
	}
	
	public byte readByte() throws SQLException
	{
		return sqlInput.readByte();
	}
	
	public short readShort() throws SQLException
	{
		return sqlInput.readShort();
	}
	
	public int readInt() throws SQLException
	{
		return sqlInput.readInt();
	}
	
	public long readLong() throws SQLException
	{
		return sqlInput.readLong();
	}
	
	public float readFloat() throws SQLException
	{
		return sqlInput.readFloat();
	}

	public double readDouble() throws SQLException
	{
		return sqlInput.readDouble();
	}
	
	public BigDecimal readBigDecimal() throws SQLException
	{
		return sqlInput.readBigDecimal();
	}
	
	public byte[] readBytes() throws SQLException
	{
		return sqlInput.readBytes();
	}
	
	public Date readDate() throws SQLException
	{
		return sqlInput.readDate();
	}
	
	public Time readTime() throws SQLException
	{
		return sqlInput.readTime();
	}
	
	public Timestamp readTimestamp() throws SQLException
	{
		return sqlInput.readTimestamp();
	}
	
	public Reader readCharacterStream() throws SQLException
	{
		return sqlInput.readCharacterStream();
	}
	
	public InputStream readAsciiStream() throws SQLException
	{
		return sqlInput.readAsciiStream();
	}
	
	public InputStream readBinaryStream() throws SQLException
	{
		return sqlInput.readBinaryStream();
	}
	
	public Object readObject() throws SQLException
	{
		return sqlInput.readObject();
	}
	
	public JdbcRef readRef() throws SQLException
	{
		return wrap(sqlInput.readRef());
	}
	
	public JdbcBlob readBlob() throws SQLException
	{
		return wrap(sqlInput.readBlob());
	}
	
	public JdbcClob readClob() throws SQLException
	{
		return wrap(sqlInput.readClob());
	}
	
	public JdbcArray readArray() throws SQLException
	{
		return wrap(sqlInput.readArray());
	}
	
	public boolean wasNull() throws SQLException
	{
		return sqlInput.wasNull();
	}
	
	public URL readURL() throws SQLException
	{
		return sqlInput.readURL();
	}
	
	public JdbcNClob readNClob() throws SQLException
	{
		return wrap(sqlInput.readNClob());
	}
	
	public String readNString() throws SQLException
	{
		return sqlInput.readNString();
	}
	
	public JdbcSQLXML readSQLXML() throws SQLException
	{
		return wrap(sqlInput.readSQLXML());
	}
	
	public JdbcRowId readRowId() throws SQLException
	{
		return wrap(sqlInput.readRowId());
	}
	
}
