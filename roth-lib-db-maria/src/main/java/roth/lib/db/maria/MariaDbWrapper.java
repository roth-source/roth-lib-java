package roth.lib.db.maria;

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

import roth.lib.db.DbArray;
import roth.lib.db.DbBlob;
import roth.lib.db.DbCallableStatement;
import roth.lib.db.DbClob;
import roth.lib.db.DbCloseHandler;
import roth.lib.db.DbConnection;
import roth.lib.db.DbDatabaseMetaData;
import roth.lib.db.DbDriver;
import roth.lib.db.DbDriverAction;
import roth.lib.db.DbNClob;
import roth.lib.db.DbParameterMetaData;
import roth.lib.db.DbPreparedStatement;
import roth.lib.db.DbRef;
import roth.lib.db.DbResultSet;
import roth.lib.db.DbResultSetMetaData;
import roth.lib.db.DbRowId;
import roth.lib.db.DbSQLData;
import roth.lib.db.DbSQLInput;
import roth.lib.db.DbSQLOutput;
import roth.lib.db.DbSQLType;
import roth.lib.db.DbSQLXML;
import roth.lib.db.DbSavepoint;
import roth.lib.db.DbStatement;
import roth.lib.db.DbStruct;
import roth.lib.db.DbWrapper;

public interface MariaDbWrapper extends DbWrapper
{
	
	@Override
	default DbArray wrap(Array array)
	{
		return new MariaDbArray(array);
	}
	
	@Override
	default DbBlob wrap(Blob blob)
	{
		return new MariaDbBlob(blob);
	}
	
	@Override
	default DbCallableStatement wrap(CallableStatement callableStatement)
	{
		return new MariaDbCallableStatement(callableStatement);
	}
	
	@Override
	default DbClob wrap(Clob clob)
	{
		return new MariaDbClob(clob);
	}
	
	@Override
	default DbConnection wrap(Connection connection)
	{
		return new MariaDbConnection(connection);
	}
	
	@Override
	default DbConnection wrap(Connection connection, DbCloseHandler closeHandler)
	{
		return new MariaDbConnection(connection, closeHandler);
	}
	
	@Override
	default DbDatabaseMetaData wrap(DatabaseMetaData databaseMetaData)
	{
		return new MariaDbDatabaseMetaData(databaseMetaData);
	}
	
	@Override
	default DbDriver wrap(Driver driver)
	{
		return new MariaDbDriver(driver);
	}
	
	@Override
	default DbDriverAction wrap(DriverAction driverAction)
	{
		return new MariaDbDriverAction(driverAction);
	}
	
	@Override
	default DbNClob wrap(NClob nClob)
	{
		return new MariaDbNClob(nClob);
	}
	
	@Override
	default DbParameterMetaData wrap(ParameterMetaData parameterMetaData)
	{
		return new MariaDbParameterMetaData(parameterMetaData);
	}
	
	@Override
	default DbPreparedStatement wrap(PreparedStatement preparedStatement)
	{
		return new MariaDbPreparedStatement(preparedStatement);
	}
	
	@Override
	default DbRef wrap(Ref ref)
	{
		return new MariaDbRef(ref);
	}
	
	@Override
	default DbResultSet wrap(ResultSet resultSet)
	{
		return new MariaDbResultSet(resultSet);
	}
	
	@Override
	default DbResultSetMetaData wrap(ResultSetMetaData resultSetMetaData)
	{
		return new MariaDbResultSetMetaData(resultSetMetaData);
	}
	
	@Override
	default DbRowId wrap(RowId rowId)
	{
		return new MariaDbRowId(rowId);
	}
	
	@Override
	default DbSavepoint wrap(Savepoint savepoint)
	{
		return new MariaDbSavepoint(savepoint);
	}
	
	@Override
	default DbSQLData wrap(SQLData sqlData)
	{
		return new MariaDbSQLData(sqlData);
	}
	
	@Override
	default DbSQLInput wrap(SQLInput sqlInput)
	{
		return new MariaDbSQLInput(sqlInput);
	}
	
	@Override
	default DbSQLOutput wrap(SQLOutput sqlOutput)
	{
		return new MariaDbSQLOutput(sqlOutput);
	}
	
	@Override
	default DbSQLType wrap(SQLType sqlType)
	{
		return new MariaDbSQLType(sqlType);
	}
	
	@Override
	default DbSQLXML wrap(SQLXML sqlXml)
	{
		return new MariaDbSQLXML(sqlXml);
	}
	
	@Override
	default DbStatement wrap(Statement statement)
	{
		return new MariaDbStatement(statement);
	}
	
	@Override
	default DbStruct wrap(Struct struct)
	{
		return new MariaDbStruct(struct);
	}
	
}
