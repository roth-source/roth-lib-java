
package roth.lib.java.jdbc;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverAction;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLData;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;

public interface JdbcWrapper
{
	JdbcArray wrap(Array array);
	JdbcBlob wrap(Blob blob);
	JdbcCallableStatement wrap(CallableStatement callableStatement);
	JdbcClob wrap(Clob clob);
	JdbcConnection wrap(Connection connection);
	JdbcDatabaseMetaData wrap(DatabaseMetaData databaseMetaData);
	JdbcDriver wrap(Driver driver);
	JdbcDriverAction wrap(DriverAction driverAction);
	JdbcNClob wrap(NClob nClob);
	JdbcParameterMetaData wrap(ParameterMetaData parameterMetaData);
	JdbcPreparedStatement wrap(PreparedStatement preparedStatement);
	JdbcRef wrap(Ref ref);
	JdbcResultSet wrap(ResultSet resultSet);
	JdbcResultSetMetaData wrap(ResultSetMetaData resultSetMetaData);
	JdbcRowId wrap(RowId rowId);
	JdbcSavepoint wrap(Savepoint savepoint);
	JdbcSQLData wrap(SQLData sqlData);
	JdbcSQLInput wrap(SQLInput sqlInput);
	JdbcSQLOutput wrap(SQLOutput sqlOutput);
	JdbcSQLType wrap(SQLType sqlType);
	JdbcSQLXML wrap(SQLXML sqlXml);
	JdbcStatement wrap(Statement statement);
	JdbcStruct wrap(Struct struct);
	
}
