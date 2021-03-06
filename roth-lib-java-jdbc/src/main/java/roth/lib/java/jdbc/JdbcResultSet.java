package roth.lib.java.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import roth.lib.java.time.Day;
import roth.lib.java.time.Hour;
import roth.lib.java.time.Millisecond;
import roth.lib.java.time.Minute;
import roth.lib.java.time.Month;
import roth.lib.java.time.Second;
import roth.lib.java.time.Year;
import roth.lib.java.util.EnumUtil;

public abstract class JdbcResultSet implements ResultSet, JdbcWrapper
{
	protected ResultSet resultSet;
	
	public JdbcResultSet(ResultSet resultSet)
	{
		this.resultSet = resultSet;
	}
	
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException
	{
		return resultSet.unwrap(iface);
	}
	
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException
	{
		return resultSet.isWrapperFor(iface);
	}
	
	@Override
	public boolean next() throws SQLException
	{
		return resultSet.next();
	}
	
	@Override
	public void close() throws SQLException
	{
		resultSet.close();
	}
	
	@Override
	public boolean wasNull() throws SQLException
	{
		return resultSet.wasNull();
	}
	
	@Override
	public String getString(int columnIndex) throws SQLException
	{
		return resultSet.getString(columnIndex);
	}
	
	@Override
	public boolean getBoolean(int columnIndex) throws SQLException
	{
		return resultSet.getBoolean(columnIndex);
	}
	
	@Override
	public byte getByte(int columnIndex) throws SQLException
	{
		return resultSet.getByte(columnIndex);
	}
	
	@Override
	public short getShort(int columnIndex) throws SQLException
	{
		return resultSet.getShort(columnIndex);
	}
	
	@Override
	public int getInt(int columnIndex) throws SQLException
	{
		return resultSet.getInt(columnIndex);
	}
	
	@Override
	public long getLong(int columnIndex) throws SQLException
	{
		return resultSet.getLong(columnIndex);
	}
	
	@Override
	public float getFloat(int columnIndex) throws SQLException
	{
		return resultSet.getFloat(columnIndex);
	}
	
	@Override
	public double getDouble(int columnIndex) throws SQLException
	{
		return resultSet.getDouble(columnIndex);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException
	{
		return resultSet.getBigDecimal(columnIndex, scale);
	}
	
	@Override
	public byte[] getBytes(int columnIndex) throws SQLException
	{
		return resultSet.getBytes(columnIndex);
	}
	
	@Override
	public Date getDate(int columnIndex) throws SQLException
	{
		return resultSet.getDate(columnIndex);
	}
	
	@Override
	public Time getTime(int columnIndex) throws SQLException
	{
		return resultSet.getTime(columnIndex);
	}
	
	@Override
	public Timestamp getTimestamp(int columnIndex) throws SQLException
	{
		return resultSet.getTimestamp(columnIndex);
	}
	
	@Override
	public InputStream getAsciiStream(int columnIndex) throws SQLException
	{
		return resultSet.getAsciiStream(columnIndex);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public InputStream getUnicodeStream(int columnIndex) throws SQLException
	{
		return resultSet.getUnicodeStream(columnIndex);
	}
	
	@Override
	public InputStream getBinaryStream(int columnIndex) throws SQLException
	{
		return resultSet.getBinaryStream(columnIndex);
	}
	
	@Override
	public String getString(String columnLabel) throws SQLException
	{
		return resultSet.getString(columnLabel);
	}
	
	@Override
	public boolean getBoolean(String columnLabel) throws SQLException
	{
		return resultSet.getBoolean(columnLabel);
	}
	
	@Override
	public byte getByte(String columnLabel) throws SQLException
	{
		return resultSet.getByte(columnLabel);
	}
	
	@Override
	public short getShort(String columnLabel) throws SQLException
	{
		return resultSet.getShort(columnLabel);
	}
	
	@Override
	public int getInt(String columnLabel) throws SQLException
	{
		return resultSet.getInt(columnLabel);
	}
	
	@Override
	public long getLong(String columnLabel) throws SQLException
	{
		return resultSet.getLong(columnLabel);
	}
	
	@Override
	public float getFloat(String columnLabel) throws SQLException
	{
		return resultSet.getFloat(columnLabel);
	}
	
	@Override
	public double getDouble(String columnLabel) throws SQLException
	{
		return resultSet.getDouble(columnLabel);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException
	{
		return resultSet.getBigDecimal(columnLabel, scale);
	}
	
	@Override
	public byte[] getBytes(String columnLabel) throws SQLException
	{
		return resultSet.getBytes(columnLabel);
	}
	
	@Override
	public Date getDate(String columnLabel) throws SQLException
	{
		return resultSet.getDate(columnLabel);
	}
	
	@Override
	public Time getTime(String columnLabel) throws SQLException
	{
		return resultSet.getTime(columnLabel);
	}
	
	@Override
	public Timestamp getTimestamp(String columnLabel) throws SQLException
	{
		return resultSet.getTimestamp(columnLabel);
	}
	
	@Override
	public InputStream getAsciiStream(String columnLabel) throws SQLException
	{
		return resultSet.getAsciiStream(columnLabel);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public InputStream getUnicodeStream(String columnLabel) throws SQLException
	{
		return resultSet.getUnicodeStream(columnLabel);
	}
	
	@Override
	public InputStream getBinaryStream(String columnLabel) throws SQLException
	{
		return resultSet.getBinaryStream(columnLabel);
	}
	
	@Override
	public SQLWarning getWarnings() throws SQLException
	{
		return resultSet.getWarnings();
	}
	
	@Override
	public void clearWarnings() throws SQLException
	{
		resultSet.clearWarnings();
	}
	
	@Override
	public String getCursorName() throws SQLException
	{
		return resultSet.getCursorName();
	}
	
	@Override
	public JdbcResultSetMetaData getMetaData() throws SQLException
	{
		return wrap(resultSet.getMetaData());
	}
	
	@Override
	public Object getObject(int columnIndex) throws SQLException
	{
		return resultSet.getObject(columnIndex);
	}
	
	@Override
	public Object getObject(String columnLabel) throws SQLException
	{
		return resultSet.getObject(columnLabel);
	}
	
	@Override
	public int findColumn(String columnLabel) throws SQLException
	{
		return resultSet.findColumn(columnLabel);
	}
	
	@Override
	public Reader getCharacterStream(int columnIndex) throws SQLException
	{
		return resultSet.getCharacterStream(columnIndex);
	}
	
	@Override
	public Reader getCharacterStream(String columnLabel) throws SQLException
	{
		return resultSet.getCharacterStream(columnLabel);
	}
	
	public BigInteger getBigInteger(int columnIndex) throws SQLException
	{
		return resultSet.getBigDecimal(columnIndex).toBigInteger();
	}
	
	public BigInteger getBigInteger(String columnLabel) throws SQLException
	{
		return resultSet.getBigDecimal(columnLabel).toBigInteger();
	}
	
	@Override
	public BigDecimal getBigDecimal(int columnIndex) throws SQLException
	{
		return resultSet.getBigDecimal(columnIndex);
	}
	
	@Override
	public BigDecimal getBigDecimal(String columnLabel) throws SQLException
	{
		return resultSet.getBigDecimal(columnLabel);
	}
	
	@Override
	public boolean isBeforeFirst() throws SQLException
	{
		return resultSet.isBeforeFirst();
	}
	
	@Override
	public boolean isAfterLast() throws SQLException
	{
		return resultSet.isAfterLast();
	}
	
	@Override
	public boolean isFirst() throws SQLException
	{
		return resultSet.isFirst();
	}
	
	@Override
	public boolean isLast() throws SQLException
	{
		return resultSet.isLast();
	}
	
	@Override
	public void beforeFirst() throws SQLException
	{
		resultSet.beforeFirst();
	}
	
	@Override
	public void afterLast() throws SQLException
	{
		resultSet.afterLast();
	}
	
	@Override
	public boolean first() throws SQLException
	{
		return resultSet.first();
	}
	
	@Override
	public boolean last() throws SQLException
	{
		return resultSet.last();
	}
	
	@Override
	public int getRow() throws SQLException
	{
		return resultSet.getRow();
	}
	
	@Override
	public boolean absolute(int row) throws SQLException
	{
		return resultSet.absolute(row);
	}
	
	@Override
	public boolean relative(int rows) throws SQLException
	{
		return resultSet.relative(rows);
	}
	
	@Override
	public boolean previous() throws SQLException
	{
		return resultSet.previous();
	}
	
	@Override
	public void setFetchDirection(int direction) throws SQLException
	{
		resultSet.setFetchDirection(direction);
	}
	
	@Override
	public int getFetchDirection() throws SQLException
	{
		return resultSet.getFetchDirection();
	}
	
	@Override
	public void setFetchSize(int rows) throws SQLException
	{
		resultSet.setFetchSize(rows);
	}
	
	@Override
	public int getFetchSize() throws SQLException
	{
		return resultSet.getFetchSize();
	}
	
	@Override
	public int getType() throws SQLException
	{
		return resultSet.getType();
	}
	
	@Override
	public int getConcurrency() throws SQLException
	{
		return resultSet.getConcurrency();
	}
	
	@Override
	public boolean rowUpdated() throws SQLException
	{
		return resultSet.rowUpdated();
	}
	
	@Override
	public boolean rowInserted() throws SQLException
	{
		return resultSet.rowInserted();
	}
	
	@Override
	public boolean rowDeleted() throws SQLException
	{
		return resultSet.rowDeleted();
	}
	
	@Override
	public void updateNull(int columnIndex) throws SQLException
	{
		resultSet.updateNull(columnIndex);
	}
	
	@Override
	public void updateBoolean(int columnIndex, boolean x) throws SQLException
	{
		resultSet.updateBoolean(columnIndex, x);
	}
	
	@Override
	public void updateByte(int columnIndex, byte x) throws SQLException
	{
		resultSet.updateByte(columnIndex, x);
	}
	
	@Override
	public void updateShort(int columnIndex, short x) throws SQLException
	{
		resultSet.updateShort(columnIndex, x);
	}
	
	@Override
	public void updateInt(int columnIndex, int x) throws SQLException
	{
		resultSet.updateInt(columnIndex, x);
	}
	
	@Override
	public void updateLong(int columnIndex, long x) throws SQLException
	{
		resultSet.updateLong(columnIndex, x);
	}
	
	@Override
	public void updateFloat(int columnIndex, float x) throws SQLException
	{
		resultSet.updateFloat(columnIndex, x);
	}
	
	@Override
	public void updateDouble(int columnIndex, double x) throws SQLException
	{
		resultSet.updateDouble(columnIndex, x);
	}
	
	@Override
	public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException
	{
		resultSet.updateBigDecimal(columnIndex, x);
	}
	
	@Override
	public void updateString(int columnIndex, String x) throws SQLException
	{
		resultSet.updateString(columnIndex, x);
	}
	
	@Override
	public void updateBytes(int columnIndex, byte[] x) throws SQLException
	{
		resultSet.updateBytes(columnIndex, x);
	}
	
	@Override
	public void updateDate(int columnIndex, Date x) throws SQLException
	{
		resultSet.updateDate(columnIndex, x);
	}
	
	@Override
	public void updateTime(int columnIndex, Time x) throws SQLException
	{
		resultSet.updateTime(columnIndex, x);
	}
	
	@Override
	public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException
	{
		resultSet.updateTimestamp(columnIndex, x);
	}
	
	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException
	{
		resultSet.updateAsciiStream(columnIndex, x);
	}
	
	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException
	{
		resultSet.updateBinaryStream(columnIndex, x);
	}
	
	@Override
	public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException
	{
		resultSet.updateCharacterStream(columnIndex, x);
	}
	
	@Override
	public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException
	{
		resultSet.updateObject(columnIndex, x);
	}
	
	@Override
	public void updateObject(int columnIndex, Object x) throws SQLException
	{
		resultSet.updateObject(columnIndex, x);
	}
	
	@Override
	public void updateNull(String columnLabel) throws SQLException
	{
		resultSet.updateNull(columnLabel);
	}
	
	@Override
	public void updateBoolean(String columnLabel, boolean x) throws SQLException
	{
		resultSet.updateBoolean(columnLabel, x);
	}
	
	@Override
	public void updateByte(String columnLabel, byte x) throws SQLException
	{
		resultSet.updateByte(columnLabel, x);
	}
	
	@Override
	public void updateShort(String columnLabel, short x) throws SQLException
	{
		resultSet.updateShort(columnLabel, x);
	}
	
	@Override
	public void updateInt(String columnLabel, int x) throws SQLException
	{
		resultSet.updateInt(columnLabel, x);
	}
	
	@Override
	public void updateLong(String columnLabel, long x) throws SQLException
	{
		resultSet.updateLong(columnLabel, x);
	}
	
	@Override
	public void updateFloat(String columnLabel, float x) throws SQLException
	{
		resultSet.updateFloat(columnLabel, x);
	}
	
	@Override
	public void updateDouble(String columnLabel, double x) throws SQLException
	{
		resultSet.updateDouble(columnLabel, x);
	}
	
	@Override
	public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException
	{
		resultSet.updateBigDecimal(columnLabel, x);
	}
	
	@Override
	public void updateString(String columnLabel, String x) throws SQLException
	{
		resultSet.updateString(columnLabel, x);
	}
	
	@Override
	public void updateBytes(String columnLabel, byte[] x) throws SQLException
	{
		resultSet.updateBytes(columnLabel, x);
	}
	
	@Override
	public void updateDate(String columnLabel, Date x) throws SQLException
	{
		resultSet.updateDate(columnLabel, x);
	}
	
	@Override
	public void updateTime(String columnLabel, Time x) throws SQLException
	{
		resultSet.updateTime(columnLabel, x);
	}
	
	@Override
	public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException
	{
		resultSet.updateTimestamp(columnLabel, x);
	}
	
	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException
	{
		resultSet.updateAsciiStream(columnLabel, x, length);
	}
	
	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException
	{
		resultSet.updateBinaryStream(columnLabel, x, length);
	}
	
	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException
	{
		resultSet.updateCharacterStream(columnLabel, reader, length);
	}
	
	@Override
	public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException
	{
		resultSet.updateObject(columnLabel, x, scaleOrLength);
	}
	
	@Override
	public void updateObject(String columnLabel, Object x) throws SQLException
	{
		resultSet.updateObject(columnLabel, x);
	}
	
	@Override
	public void insertRow() throws SQLException
	{
		resultSet.insertRow();
	}
	
	@Override
	public void updateRow() throws SQLException
	{
		resultSet.updateRow();
	}
	
	@Override
	public void deleteRow() throws SQLException
	{
		resultSet.deleteRow();
	}
	
	@Override
	public void refreshRow() throws SQLException
	{
		resultSet.refreshRow();
	}
	
	@Override
	public void cancelRowUpdates() throws SQLException
	{
		resultSet.cancelRowUpdates();
	}
	
	@Override
	public void moveToInsertRow() throws SQLException
	{
		resultSet.moveToInsertRow();
	}
	
	@Override
	public void moveToCurrentRow() throws SQLException
	{
		resultSet.moveToCurrentRow();
	}
	
	@Override
	public JdbcStatement getStatement() throws SQLException
	{
		return wrap(resultSet.getStatement());
	}
	
	@Override
	public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException
	{
		return resultSet.getObject(columnIndex, map);
	}
	
	@Override
	public JdbcRef getRef(int columnIndex) throws SQLException
	{
		return wrap(resultSet.getRef(columnIndex));
	}
	
	@Override
	public JdbcBlob getBlob(int columnIndex) throws SQLException
	{
		return wrap(resultSet.getBlob(columnIndex));
	}
	
	@Override
	public JdbcClob getClob(int columnIndex) throws SQLException
	{
		return wrap(resultSet.getClob(columnIndex));
	}
	
	@Override
	public JdbcArray getArray(int columnIndex) throws SQLException
	{
		return wrap(resultSet.getArray(columnIndex));
	}
	
	@Override
	public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException
	{
		return resultSet.getObject(columnLabel, map);
	}
	
	@Override
	public JdbcRef getRef(String columnLabel) throws SQLException
	{
		return wrap(resultSet.getRef(columnLabel));
	}
	
	@Override
	public JdbcBlob getBlob(String columnLabel) throws SQLException
	{
		return wrap(resultSet.getBlob(columnLabel));
	}
	
	@Override
	public JdbcClob getClob(String columnLabel) throws SQLException
	{
		return wrap(resultSet.getClob(columnLabel));
	}
	
	@Override
	public JdbcArray getArray(String columnLabel) throws SQLException
	{
		return wrap(resultSet.getArray(columnLabel));
	}
	
	@Override
	public Date getDate(int columnIndex, Calendar cal) throws SQLException
	{
		return resultSet.getDate(columnIndex, cal);
	}
	
	@Override
	public Date getDate(String columnLabel, Calendar cal) throws SQLException
	{
		return resultSet.getDate(columnLabel, cal);
	}
	
	@Override
	public Time getTime(int columnIndex, Calendar cal) throws SQLException
	{
		return resultSet.getTime(columnIndex, cal);
	}
	
	@Override
	public Time getTime(String columnLabel, Calendar cal) throws SQLException
	{
		return resultSet.getTime(columnLabel, cal);
	}
	
	@Override
	public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException
	{
		return resultSet.getTimestamp(columnIndex, cal);
	}
	
	@Override
	public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException
	{
		return resultSet.getTimestamp(columnLabel, cal);
	}
	
	@Override
	public URL getURL(int columnIndex) throws SQLException
	{
		return resultSet.getURL(columnIndex);
	}
	
	@Override
	public URL getURL(String columnLabel) throws SQLException
	{
		return resultSet.getURL(columnLabel);
	}
	
	@Override
	public void updateRef(int columnIndex, Ref x) throws SQLException
	{
		resultSet.updateRef(columnIndex, x);
	}
	
	@Override
	public void updateRef(String columnLabel, Ref x) throws SQLException
	{
		resultSet.updateRef(columnLabel, x);
	}
	
	@Override
	public void updateBlob(int columnIndex, Blob x) throws SQLException
	{
		resultSet.updateBlob(columnIndex, x);
	}
	
	@Override
	public void updateBlob(String columnLabel, Blob x) throws SQLException
	{
		resultSet.updateBlob(columnLabel, x);
	}
	
	@Override
	public void updateClob(int columnIndex, Clob x) throws SQLException
	{
		resultSet.updateClob(columnIndex, x);
	}
	
	@Override
	public void updateClob(String columnLabel, Clob x) throws SQLException
	{
		resultSet.updateClob(columnLabel, x);
	}
	
	@Override
	public void updateArray(int columnIndex, Array x) throws SQLException
	{
		resultSet.updateArray(columnIndex, x);
	}
	
	@Override
	public void updateArray(String columnLabel, Array x) throws SQLException
	{
		resultSet.updateArray(columnLabel, x);
	}
	
	@Override
	public JdbcRowId getRowId(int columnIndex) throws SQLException
	{
		return wrap(resultSet.getRowId(columnIndex));
	}
	
	@Override
	public JdbcRowId getRowId(String columnLabel) throws SQLException
	{
		return wrap(resultSet.getRowId(columnLabel));
	}
	
	@Override
	public void updateRowId(int columnIndex, RowId x) throws SQLException
	{
		resultSet.updateRowId(columnIndex, x);
	}
	
	@Override
	public void updateRowId(String columnLabel, RowId x) throws SQLException
	{
		resultSet.updateRowId(columnLabel, x);
	}
	
	@Override
	public int getHoldability() throws SQLException
	{
		return resultSet.getHoldability();
	}
	
	@Override
	public boolean isClosed() throws SQLException
	{
		return resultSet.isClosed();
	}
	
	@Override
	public void updateNString(int columnIndex, String nString) throws SQLException
	{
		resultSet.updateNString(columnIndex, nString);
	}
	
	@Override
	public void updateNString(String columnLabel, String nString) throws SQLException
	{
		resultSet.updateNString(columnLabel, nString);
	}
	
	@Override
	public void updateNClob(int columnIndex, NClob nClob) throws SQLException
	{
		resultSet.updateNClob(columnIndex, nClob);
	}
	
	@Override
	public void updateNClob(String columnLabel, NClob nClob) throws SQLException
	{
		resultSet.updateNClob(columnLabel, nClob);
	}
	
	@Override
	public JdbcNClob getNClob(int columnIndex) throws SQLException
	{
		return wrap(resultSet.getNClob(columnIndex));
	}
	
	@Override
	public JdbcNClob getNClob(String columnLabel) throws SQLException
	{
		return wrap(resultSet.getNClob(columnLabel));
	}
	
	@Override
	public JdbcSQLXML getSQLXML(int columnIndex) throws SQLException
	{
		return wrap(resultSet.getSQLXML(columnIndex));
	}
	
	@Override
	public JdbcSQLXML getSQLXML(String columnLabel) throws SQLException
	{
		return wrap(resultSet.getSQLXML(columnLabel));
	}
	
	@Override
	public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException
	{
		resultSet.updateSQLXML(columnIndex, xmlObject);
	}
	
	@Override
	public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException
	{
		resultSet.updateSQLXML(columnLabel, xmlObject);
	}
	
	@Override
	public String getNString(int columnIndex) throws SQLException
	{
		return resultSet.getNString(columnIndex);
	}
	
	@Override
	public String getNString(String columnLabel) throws SQLException
	{
		return resultSet.getNString(columnLabel);
	}
	
	@Override
	public Reader getNCharacterStream(int columnIndex) throws SQLException
	{
		return resultSet.getNCharacterStream(columnIndex);
	}
	
	@Override
	public Reader getNCharacterStream(String columnLabel) throws SQLException
	{
		return resultSet.getNCharacterStream(columnLabel);
	}
	
	@Override
	public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException
	{
		resultSet.updateNCharacterStream(columnIndex, x, length);
	}
	
	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException
	{
		resultSet.updateNCharacterStream(columnLabel, reader, length);
	}
	
	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException
	{
		resultSet.updateAsciiStream(columnIndex, x, length);
	}
	
	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException
	{
		resultSet.updateBinaryStream(columnIndex, x, length);
	}
	
	@Override
	public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException
	{
		resultSet.updateCharacterStream(columnIndex, x, length);
	}
	
	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException
	{
		resultSet.updateAsciiStream(columnLabel, x, length);
	}
	
	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException
	{
		resultSet.updateBinaryStream(columnLabel, x, length);
	}
	
	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException
	{
		resultSet.updateCharacterStream(columnLabel, reader, length);
	}
	
	@Override
	public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException
	{
		resultSet.updateBlob(columnIndex, inputStream, length);
	}
	
	@Override
	public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException
	{
		resultSet.updateBlob(columnLabel, inputStream, length);
	}
	
	@Override
	public void updateClob(int columnIndex, Reader reader, long length) throws SQLException
	{
		resultSet.updateClob(columnIndex, reader, length);
	}
	
	@Override
	public void updateClob(String columnLabel, Reader reader, long length) throws SQLException
	{
		resultSet.updateClob(columnLabel, reader, length);
	}
	
	@Override
	public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException
	{
		resultSet.updateNClob(columnIndex, reader, length);
	}
	
	@Override
	public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException
	{
		resultSet.updateNClob(columnLabel, reader, length);
	}
	
	@Override
	public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException
	{
		resultSet.updateNCharacterStream(columnIndex, x);
	}
	
	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException
	{
		resultSet.updateNCharacterStream(columnLabel, reader);
	}
	
	@Override
	public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException
	{
		resultSet.updateAsciiStream(columnIndex, x);
	}
	
	@Override
	public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException
	{
		resultSet.updateBinaryStream(columnIndex, x);
	}
	
	@Override
	public void updateCharacterStream(int columnIndex, Reader x) throws SQLException
	{
		resultSet.updateCharacterStream(columnIndex, x);
	}
	
	@Override
	public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException
	{
		resultSet.updateAsciiStream(columnLabel, x);
	}
	
	@Override
	public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException
	{
		resultSet.updateBinaryStream(columnLabel, x);
	}
	
	@Override
	public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException
	{
		resultSet.updateCharacterStream(columnLabel, reader);
	}
	
	@Override
	public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException
	{
		resultSet.updateBlob(columnIndex, inputStream);
	}
	
	@Override
	public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException
	{
		resultSet.updateBlob(columnLabel, inputStream);
	}
	
	@Override
	public void updateClob(int columnIndex, Reader reader) throws SQLException
	{
		resultSet.updateClob(columnIndex, reader);
	}
	
	@Override
	public void updateClob(String columnLabel, Reader reader) throws SQLException
	{
		resultSet.updateClob(columnLabel, reader);
	}
	
	@Override
	public void updateNClob(int columnIndex, Reader reader) throws SQLException
	{
		resultSet.updateNClob(columnIndex, reader);
	}
	
	@Override
	public void updateNClob(String columnLabel, Reader reader) throws SQLException
	{
		resultSet.updateNClob(columnLabel, reader);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getObject(int columnIndex, Class<T> type) throws SQLException
	{
		if(Year.class.isAssignableFrom(type))
		{
			return (T) getYear(columnIndex);
		}
		else if(Month.class.isAssignableFrom(type))
		{
			return (T) getMonth(columnIndex);
		}
		else if(Day.class.isAssignableFrom(type))
		{
			return (T) getDay(columnIndex);
		}
		else if(Hour.class.isAssignableFrom(type))
		{
			return (T) getHour(columnIndex);
		}
		else if(Minute.class.isAssignableFrom(type))
		{
			return (T) getMinute(columnIndex);
		}
		else if(Second.class.isAssignableFrom(type))
		{
			return (T) getSecond(columnIndex);
		}
		else if(Millisecond.class.isAssignableFrom(type))
		{
			return (T) getMillisecond(columnIndex);
		}
		else if(roth.lib.java.time.Time.class.isAssignableFrom(type))
		{
			return (T) getRothTime(columnIndex);
		}
		else if(Calendar.class.isAssignableFrom(type))
		{
			return (T) getCalendar(columnIndex);
		}
		else if(BigInteger.class.isAssignableFrom(type))
		{
			return (T) getBigInteger(columnIndex);
		}
		else if(Enum.class.isAssignableFrom(type))
		{
			return (T) getEnum(columnIndex, type);
		}
		else
		{
			T object = resultSet.getObject(columnIndex, type);
			return !wasNull() ? object : null;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getObject(String columnLabel, Class<T> type) throws SQLException
	{
		if(Year.class.isAssignableFrom(type))
		{
			return (T) getYear(columnLabel);
		}
		else if(Month.class.isAssignableFrom(type))
		{
			return (T) getMonth(columnLabel);
		}
		else if(Day.class.isAssignableFrom(type))
		{
			return (T) getDay(columnLabel);
		}
		else if(Hour.class.isAssignableFrom(type))
		{
			return (T) getHour(columnLabel);
		}
		else if(Minute.class.isAssignableFrom(type))
		{
			return (T) getMinute(columnLabel);
		}
		else if(Second.class.isAssignableFrom(type))
		{
			return (T) getSecond(columnLabel);
		}
		else if(Millisecond.class.isAssignableFrom(type))
		{
			return (T) getMillisecond(columnLabel);
		}
		else if(roth.lib.java.time.Time.class.isAssignableFrom(type))
		{
			return (T) getRothTime(columnLabel);
		}
		else if(Calendar.class.isAssignableFrom(type))
		{
			return (T) getCalendar(columnLabel);
		}
		else if(BigInteger.class.isAssignableFrom(type))
		{
			return (T) getBigInteger(columnLabel);
		}
		else if(Enum.class.isAssignableFrom(type))
		{
			return (T) getEnum(columnLabel, type);
		}
		else
		{
			T object = resultSet.getObject(columnLabel, type);
			return !wasNull() ? object : null;
		}
	}
	
	protected Year getYear(Date date)
	{
		Year year = null;
		if(date != null)
		{
			year = new Year(date); 
		}
		return year;
	}
	
	public Year getYear(int columnIndex) throws SQLException
	{
		return getYear(resultSet.getDate(columnIndex));
	}
	
	public Year getYear(String columnLabel) throws SQLException
	{
		return getYear(resultSet.getDate(columnLabel));
	}
	
	protected Month getMonth(Date date)
	{
		Month month = null;
		if(date != null)
		{
			month = new Month(date); 
		}
		return month;
	}
	
	public Month getMonth(int columnIndex) throws SQLException
	{
		return getMonth(resultSet.getDate(columnIndex));
	}
	
	public Month getMonth(String columnLabel) throws SQLException
	{
		return getMonth(resultSet.getDate(columnLabel));
	}
	
	protected Day getDay(Date date)
	{
		Day day = null;
		if(date != null)
		{
			day = new Day(date); 
		}
		return day;
	}
	
	public Day getDay(int columnIndex) throws SQLException
	{
		return getDay(resultSet.getDate(columnIndex));
	}
	
	public Day getDay(String columnLabel) throws SQLException
	{
		return getDay(resultSet.getDate(columnLabel));
	}
	
	protected Hour getHour(Timestamp timestamp)
	{
		Hour hour = null;
		if(timestamp != null)
		{
			hour = new Hour(timestamp); 
		}
		return hour;
	}
	
	public Hour getHour(int columnIndex) throws SQLException
	{
		return getHour(resultSet.getTimestamp(columnIndex));
	}
	
	public Hour getHour(String columnLabel) throws SQLException
	{
		return getHour(resultSet.getTimestamp(columnLabel));
	}
	
	protected Minute getMinute(Timestamp timestamp)
	{
		Minute minute = null;
		if(timestamp != null)
		{
			minute = new Minute(timestamp); 
		}
		return minute;
	}
	
	public Minute getMinute(int columnIndex) throws SQLException
	{
		return getMinute(resultSet.getTimestamp(columnIndex));
	}
	
	public Minute getMinute(String columnLabel) throws SQLException
	{
		return getMinute(resultSet.getTimestamp(columnLabel));
	}
	
	protected Second getSecond(Timestamp timestamp)
	{
		Second second = null;
		if(timestamp != null)
		{
			second = new Second(timestamp); 
		}
		return second;
	}
	
	public Second getSecond(int columnIndex) throws SQLException
	{
		return getSecond(resultSet.getTimestamp(columnIndex));
	}
	
	public Second getSecond(String columnLabel) throws SQLException
	{
		return getSecond(resultSet.getTimestamp(columnLabel));
	}
	
	protected Millisecond getMillisecond(Timestamp timestamp)
	{
		Millisecond millisecond = null;
		if(timestamp != null)
		{
			millisecond = new Millisecond(timestamp); 
		}
		return millisecond;
	}
	
	public Millisecond getMillisecond(int columnIndex) throws SQLException
	{
		return getMillisecond(resultSet.getTimestamp(columnIndex));
	}
	
	public Millisecond getMillisecond(String columnLabel) throws SQLException
	{
		return getMillisecond(resultSet.getTimestamp(columnLabel));
	}
	
	protected roth.lib.java.time.Time getRothTime(Timestamp timestamp)
	{
		roth.lib.java.time.Time time = null;
		if(timestamp != null)
		{
			time = new roth.lib.java.time.Time(timestamp); 
		}
		return time;
	}
	
	public roth.lib.java.time.Time getRothTime(int columnIndex) throws SQLException
	{
		return getRothTime(resultSet.getTimestamp(columnIndex));
	}
	
	public roth.lib.java.time.Time getRothTime(String columnLabel) throws SQLException
	{
		return getRothTime(resultSet.getTimestamp(columnLabel));
	}
	
	protected Calendar getCalendar(Timestamp timestamp)
	{
		Calendar calendar = null;
		if(timestamp != null)
		{
			calendar = new GregorianCalendar();
			calendar.setTime(timestamp);
		}
		return calendar;
	}
	
	public Calendar getCalendar(int columnIndex) throws SQLException
	{
		return getCalendar(resultSet.getTimestamp(columnIndex));
	}
	
	public Calendar getCalendar(String columnLabel) throws SQLException
	{
		return getCalendar(resultSet.getTimestamp(columnLabel));
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getEnum(int columnIndex, Class<T> klass) throws SQLException
	{
		return (T) EnumUtil.fromString(resultSet.getString(columnIndex), (Class<Enum<?>>) klass);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getEnum(String columnLabel, Class<T> klass) throws SQLException
	{
		return (T) EnumUtil.fromString(resultSet.getString(columnLabel), (Class<Enum<?>>) klass);
	}
	
	public Boolean getBooleanWrapper(String columnLabel) throws SQLException
	{
		Boolean value = resultSet.getBoolean(columnLabel);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Byte getByteWrapper(String columnLabel) throws SQLException
	{
		Byte value = resultSet.getByte(columnLabel);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Short getShortWrapper(String columnLabel) throws SQLException
	{
		Short value = resultSet.getShort(columnLabel);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Integer getIntegerWrapper(String columnLabel) throws SQLException
	{
		Integer value = resultSet.getInt(columnLabel);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Long getLongWrapper(String columnLabel) throws SQLException
	{
		Long value = resultSet.getLong(columnLabel);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Float getFloatWrapper(String columnLabel) throws SQLException
	{
		Float value = resultSet.getFloat(columnLabel);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Double getDoubleWrapper(String columnLabel) throws SQLException
	{
		Double value = resultSet.getDouble(columnLabel);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Boolean getBooleanWrapper(int columnIndex) throws SQLException
	{
		Boolean value = resultSet.getBoolean(columnIndex);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Byte getByteWrapper(int columnIndex) throws SQLException
	{
		Byte value = resultSet.getByte(columnIndex);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Short getShortWrapper(int columnIndex) throws SQLException
	{
		Short value = resultSet.getShort(columnIndex);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Integer getIntegerWrapper(int columnIndex) throws SQLException
	{
		Integer value = resultSet.getInt(columnIndex);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Long getLongWrapper(int columnIndex) throws SQLException
	{
		Long value = resultSet.getLong(columnIndex);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Float getFloatWrapper(int columnIndex) throws SQLException
	{
		Float value = resultSet.getFloat(columnIndex);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Double getDoubleWrapper(int columnIndex) throws SQLException
	{
		Double value = resultSet.getDouble(columnIndex);
		if(resultSet.wasNull())
		{
			value = null;
		}
		return value;
	}
	
	public Object getValue(String columnLabel, Class<?> klass) throws SQLException
	{
		Object value = null;
		if(String.class.isAssignableFrom(klass))
		{
			value = getString(columnLabel);
		}
		else if(Enum.class.isAssignableFrom(klass))
		{
			value = getEnum(columnLabel, klass);
		}
		else if(Year.class.isAssignableFrom(klass))
		{
			value = getYear(columnLabel);
		}
		else if(Month.class.isAssignableFrom(klass))
		{
			value = getMonth(columnLabel);
		}
		else if(Day.class.isAssignableFrom(klass))
		{
			value = getDay(columnLabel);
		}
		else if(Hour.class.isAssignableFrom(klass))
		{
			value = getHour(columnLabel);
		}
		else if(Minute.class.isAssignableFrom(klass))
		{
			value = getMinute(columnLabel);
		}
		else if(Second.class.isAssignableFrom(klass))
		{
			value = getSecond(columnLabel);
		}
		else if(Millisecond.class.isAssignableFrom(klass))
		{
			value = getMillisecond(columnLabel);
		}
		else if(roth.lib.java.time.Time.class.isAssignableFrom(klass))
		{
			value = getRothTime(columnLabel);
		}
		else if(Calendar.class.isAssignableFrom(klass))
		{
			value = getCalendar(columnLabel);
		}
		else if(Timestamp.class.isAssignableFrom(klass))
		{
			value = getTimestamp(columnLabel);
		}
		else if(Time.class.isAssignableFrom(klass))
		{
			value = getTime(columnLabel);
		}
		else if(Date.class.isAssignableFrom(klass))
		{
			value = getDate(columnLabel);
		}
		else if(Boolean.class.isAssignableFrom(klass))
		{
			value = getBooleanWrapper(columnLabel);
		}
		else if(Byte.class.isAssignableFrom(klass))
		{
			value = getByteWrapper(columnLabel);
		}
		else if(Short.class.isAssignableFrom(klass))
		{
			value = getShortWrapper(columnLabel);
		}
		else if(Integer.class.isAssignableFrom(klass))
		{
			value = getIntegerWrapper(columnLabel);
		}
		else if(Long.class.isAssignableFrom(klass))
		{
			value = getLongWrapper(columnLabel);
		}
		else if(BigInteger.class.isAssignableFrom(klass))
		{
			value = getBigInteger(columnLabel);
		}
		else if(Float.class.isAssignableFrom(klass))
		{
			value = getFloatWrapper(columnLabel);
		}
		else if(Double.class.isAssignableFrom(klass))
		{
			value = getDoubleWrapper(columnLabel);
		}
		else if(BigDecimal.class.isAssignableFrom(klass))
		{
			value = getBigDecimal(columnLabel);
		}
		else if(boolean.class.isAssignableFrom(klass))
		{
			value = getBoolean(columnLabel);
		}
		else if(byte.class.isAssignableFrom(klass))
		{
			value = getByte(columnLabel);
		}
		else if(short.class.isAssignableFrom(klass))
		{
			value = getShort(columnLabel);
		}
		else if(int.class.isAssignableFrom(klass))
		{
			value = getInt(columnLabel);
		}
		else if(long.class.isAssignableFrom(klass))
		{
			value = getLong(columnLabel);
		}
		else if(float.class.isAssignableFrom(klass))
		{
			value = getFloat(columnLabel);
		}
		else if(double.class.isAssignableFrom(klass))
		{
			value = getDouble(columnLabel);
		}
		else
		{
			value = getObject(columnLabel, klass);
		}
		return value;
	}
	
	public Object getValue(int columnIndex, Class<?> klass) throws SQLException
	{
		Object value = null;
		if(String.class.isAssignableFrom(klass))
		{
			value = getString(columnIndex);
		}
		else if(Enum.class.isAssignableFrom(klass))
		{
			value = getEnum(columnIndex, klass);
		}
		else if(Year.class.isAssignableFrom(klass))
		{
			value = getYear(columnIndex);
		}
		else if(Month.class.isAssignableFrom(klass))
		{
			value = getMonth(columnIndex);
		}
		else if(Day.class.isAssignableFrom(klass))
		{
			value = getDay(columnIndex);
		}
		else if(Hour.class.isAssignableFrom(klass))
		{
			value = getHour(columnIndex);
		}
		else if(Minute.class.isAssignableFrom(klass))
		{
			value = getMinute(columnIndex);
		}
		else if(Second.class.isAssignableFrom(klass))
		{
			value = getSecond(columnIndex);
		}
		else if(Millisecond.class.isAssignableFrom(klass))
		{
			value = getMillisecond(columnIndex);
		}
		else if(roth.lib.java.time.Time.class.isAssignableFrom(klass))
		{
			value = getRothTime(columnIndex);
		}
		else if(Calendar.class.isAssignableFrom(klass))
		{
			value = getCalendar(columnIndex);
		}
		else if(Timestamp.class.isAssignableFrom(klass))
		{
			value = getTimestamp(columnIndex);
		}
		else if(Time.class.isAssignableFrom(klass))
		{
			value = getTime(columnIndex);
		}
		else if(Date.class.isAssignableFrom(klass))
		{
			value = getDate(columnIndex);
		}
		else if(Boolean.class.isAssignableFrom(klass))
		{
			value = getBooleanWrapper(columnIndex);
		}
		else if(Byte.class.isAssignableFrom(klass))
		{
			value = getByteWrapper(columnIndex);
		}
		else if(Short.class.isAssignableFrom(klass))
		{
			value = getShortWrapper(columnIndex);
		}
		else if(Integer.class.isAssignableFrom(klass))
		{
			value = getIntegerWrapper(columnIndex);
		}
		else if(Long.class.isAssignableFrom(klass))
		{
			value = getLongWrapper(columnIndex);
		}
		else if(Float.class.isAssignableFrom(klass))
		{
			value = getFloatWrapper(columnIndex);
		}
		else if(Double.class.isAssignableFrom(klass))
		{
			value = getDoubleWrapper(columnIndex);
		}
		else if(BigInteger.class.isAssignableFrom(klass))
		{
			value = getBigInteger(columnIndex);
		}
		else if(BigDecimal.class.isAssignableFrom(klass))
		{
			value = getBigDecimal(columnIndex);
		}
		else if(boolean.class.isAssignableFrom(klass))
		{
			value = getBoolean(columnIndex);
		}
		else if(byte.class.isAssignableFrom(klass))
		{
			value = getByte(columnIndex);
		}
		else if(short.class.isAssignableFrom(klass))
		{
			value = getShort(columnIndex);
		}
		else if(int.class.isAssignableFrom(klass))
		{
			value = getInt(columnIndex);
		}
		else if(long.class.isAssignableFrom(klass))
		{
			value = getLong(columnIndex);
		}
		else if(float.class.isAssignableFrom(klass))
		{
			value = getFloat(columnIndex);
		}
		else if(double.class.isAssignableFrom(klass))
		{
			value = getDouble(columnIndex);
		}
		else
		{
			value = getObject(columnIndex, klass);
		}
		return value;
	}
	
}
