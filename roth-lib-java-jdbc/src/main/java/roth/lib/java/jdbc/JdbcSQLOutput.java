package roth.lib.java.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.SQLXML;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;

public abstract class JdbcSQLOutput implements SQLOutput, JdbcWrapper
{
	protected SQLOutput sqlOutput;
	
	public JdbcSQLOutput(SQLOutput sqlOutput)
	{
		this.sqlOutput = sqlOutput;
	}
	
	public void writeString(String x) throws SQLException
	{
		sqlOutput.writeString(x);
	}
	
	public void writeBoolean(boolean x) throws SQLException
	{
		sqlOutput.writeBoolean(x);
	}
	
	public void writeByte(byte x) throws SQLException
	{
		sqlOutput.writeByte(x);
	}
	
	public void writeShort(short x) throws SQLException
	{
		sqlOutput.writeShort(x);
	}
	
	public void writeInt(int x) throws SQLException
	{
		sqlOutput.writeInt(x);
	}
	
	public void writeLong(long x) throws SQLException
	{
		sqlOutput.writeLong(x);
	}
	
	public void writeFloat(float x) throws SQLException
	{
		sqlOutput.writeFloat(x);
	}
	
	public void writeDouble(double x) throws SQLException
	{
		sqlOutput.writeDouble(x);
	}
	
	public void writeBigDecimal(BigDecimal x) throws SQLException
	{
		sqlOutput.writeBigDecimal(x);
	}
	
	public void writeBytes(byte[] x) throws SQLException
	{
		sqlOutput.writeBytes(x);
	}
	
	public void writeDate(Date x) throws SQLException
	{
		sqlOutput.writeDate(x);
	}
	
	public void writeTime(Time x) throws SQLException
	{
		sqlOutput.writeTime(x);
	}
	
	public void writeTimestamp(Timestamp x) throws SQLException
	{
		sqlOutput.writeTimestamp(x);
	}
	
	public void writeCharacterStream(Reader x) throws SQLException
	{
		sqlOutput.writeCharacterStream(x);
	}
	
	public void writeAsciiStream(InputStream x) throws SQLException
	{
		sqlOutput.writeAsciiStream(x);
	}
	
	public void writeBinaryStream(InputStream x) throws SQLException
	{
		sqlOutput.writeBinaryStream(x);
	}
	
	public void writeObject(SQLData x) throws SQLException
	{
		sqlOutput.writeObject(x);
	}
	
	public void writeRef(Ref x) throws SQLException
	{
		sqlOutput.writeRef(x);
	}
	
	public void writeBlob(Blob x) throws SQLException
	{
		sqlOutput.writeBlob(x);
	}
	
	public void writeClob(Clob x) throws SQLException
	{
		sqlOutput.writeClob(x);
	}
	
	public void writeStruct(Struct x) throws SQLException
	{
		sqlOutput.writeStruct(x);
	}
	
	public void writeArray(Array x) throws SQLException
	{
		sqlOutput.writeArray(x);
	}
	
	public void writeURL(URL x) throws SQLException
	{
		sqlOutput.writeURL(x);
	}
	
	public void writeNString(String x) throws SQLException
	{
		sqlOutput.writeNString(x);
	}
	
	public void writeNClob(NClob x) throws SQLException
	{
		sqlOutput.writeNClob(x);
	}
	
	public void writeRowId(RowId x) throws SQLException
	{
		sqlOutput.writeRowId(x);
	}
	
	public void writeSQLXML(SQLXML x) throws SQLException
	{
		sqlOutput.writeSQLXML(x);
	}
	
}
