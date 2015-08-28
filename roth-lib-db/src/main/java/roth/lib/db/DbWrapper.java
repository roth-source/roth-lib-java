package roth.lib.db;

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

public interface DbWrapper
{
	DbArray wrap(Array array);
	DbBlob wrap(Blob blob);
	DbCallableStatement wrap(CallableStatement callableStatement);
	DbClob wrap(Clob clob);
	DbConnection wrap(Connection connection);
	DbConnection wrap(Connection connection, DbCloseHandler closeHandler);
	DbDatabaseMetaData wrap(DatabaseMetaData databaseMetaData);
	DbDriver wrap(Driver driver);
	DbDriverAction wrap(DriverAction driverAction);
	DbNClob wrap(NClob nClob);
	DbParameterMetaData wrap(ParameterMetaData parameterMetaData);
	DbPreparedStatement wrap(PreparedStatement preparedStatement);
	DbRef wrap(Ref ref);
	DbResultSet wrap(ResultSet resultSet);
	DbResultSetMetaData wrap(ResultSetMetaData resultSetMetaData);
	DbRowId wrap(RowId rowId);
	DbSavepoint wrap(Savepoint savepoint);
	DbSQLData wrap(SQLData sqlData);
	DbSQLInput wrap(SQLInput sqlInput);
	DbSQLOutput wrap(SQLOutput sqlOutput);
	DbSQLType wrap(SQLType sqlType);
	DbSQLXML wrap(SQLXML sqlXml);
	DbStatement wrap(Statement statement);
	DbStruct wrap(Struct struct);
	
}
