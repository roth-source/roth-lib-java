package roth.lib.java.db.mysql;

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

import roth.lib.java.db.DbArray;
import roth.lib.java.db.DbBlob;
import roth.lib.java.db.DbCallableStatement;
import roth.lib.java.db.DbClob;
import roth.lib.java.db.DbConnection;
import roth.lib.java.db.DbDatabaseMetaData;
import roth.lib.java.db.DbDriver;
import roth.lib.java.db.DbDriverAction;
import roth.lib.java.db.DbNClob;
import roth.lib.java.db.DbParameterMetaData;
import roth.lib.java.db.DbPreparedStatement;
import roth.lib.java.db.DbRef;
import roth.lib.java.db.DbResultSet;
import roth.lib.java.db.DbResultSetMetaData;
import roth.lib.java.db.DbRowId;
import roth.lib.java.db.DbSQLData;
import roth.lib.java.db.DbSQLInput;
import roth.lib.java.db.DbSQLOutput;
import roth.lib.java.db.DbSQLType;
import roth.lib.java.db.DbSQLXML;
import roth.lib.java.db.DbSavepoint;
import roth.lib.java.db.DbStatement;
import roth.lib.java.db.DbStruct;
import roth.lib.java.db.DbWrapper;

public interface MysqlDbWrapper extends DbWrapper
{
	
	@Override
	default DbArray wrap(Array array)
	{
		return array instanceof DbArray ? (DbArray) array : new MysqlDbArray(array);
	}
	
	@Override
	default DbBlob wrap(Blob blob)
	{
		return blob instanceof DbBlob ? (DbBlob) blob : new MysqlDbBlob(blob);
	}
	
	@Override
	default DbCallableStatement wrap(CallableStatement callableStatement)
	{
		return callableStatement instanceof DbCallableStatement ? (DbCallableStatement) callableStatement : new MysqlDbCallableStatement(callableStatement);
	}
	
	@Override
	default DbClob wrap(Clob clob)
	{
		return clob instanceof DbClob ? (DbClob) clob : new MysqlDbClob(clob);
	}
	
	@Override
	default DbConnection wrap(Connection connection)
	{
		return connection instanceof DbConnection ? (DbConnection) connection : new MysqlDbConnection(connection);
	}
	
	@Override
	default DbDatabaseMetaData wrap(DatabaseMetaData databaseMetaData)
	{
		return databaseMetaData instanceof DbDatabaseMetaData ? (DbDatabaseMetaData) databaseMetaData : new MysqlDbDatabaseMetaData(databaseMetaData);
	}
	
	@Override
	default DbDriver wrap(Driver driver)
	{
		return driver instanceof DbDriver ? (DbDriver) driver : new MysqlDbDriver(driver);
	}
	
	@Override
	default DbDriverAction wrap(DriverAction driverAction)
	{
		return driverAction instanceof DbDriverAction ? (DbDriverAction) driverAction : new MysqlDbDriverAction(driverAction);
	}
	
	@Override
	default DbNClob wrap(NClob nClob)
	{
		return nClob instanceof DbNClob ? (DbNClob) nClob : new MysqlDbNClob(nClob);
	}
	
	@Override
	default DbParameterMetaData wrap(ParameterMetaData parameterMetaData)
	{
		return parameterMetaData instanceof DbParameterMetaData ? (DbParameterMetaData) parameterMetaData : new MysqlDbParameterMetaData(parameterMetaData);
	}
	
	@Override
	default DbPreparedStatement wrap(PreparedStatement preparedStatement)
	{
		return preparedStatement instanceof DbPreparedStatement ? (DbPreparedStatement) preparedStatement : new MysqlDbPreparedStatement(preparedStatement);
	}
	
	@Override
	default DbRef wrap(Ref ref)
	{
		return ref instanceof DbRef ? (DbRef) ref : new MysqlDbRef(ref);
	}
	
	@Override
	default DbResultSet wrap(ResultSet resultSet)
	{
		return resultSet instanceof DbResultSet ? (DbResultSet) resultSet : new MysqlDbResultSet(resultSet);
	}
	
	@Override
	default DbResultSetMetaData wrap(ResultSetMetaData resultSetMetaData)
	{
		return resultSetMetaData instanceof DbResultSetMetaData ? (DbResultSetMetaData) resultSetMetaData : new MysqlDbResultSetMetaData(resultSetMetaData);
	}
	
	@Override
	default DbRowId wrap(RowId rowId)
	{
		return rowId instanceof DbRowId ? (DbRowId) rowId : new MysqlDbRowId(rowId);
	}
	
	@Override
	default DbSavepoint wrap(Savepoint savepoint)
	{
		return savepoint instanceof DbSavepoint ? (DbSavepoint) savepoint : new MysqlDbSavepoint(savepoint);
	}
	
	@Override
	default DbSQLData wrap(SQLData sqlData)
	{
		return sqlData instanceof DbSQLData ? (DbSQLData) sqlData : new MysqlDbSQLData(sqlData);
	}
	
	@Override
	default DbSQLInput wrap(SQLInput sqlInput)
	{
		return sqlInput instanceof DbSQLInput ? (DbSQLInput) sqlInput : new MysqlDbSQLInput(sqlInput);
	}
	
	@Override
	default DbSQLOutput wrap(SQLOutput sqlOutput)
	{
		return sqlOutput instanceof DbSQLOutput ? (DbSQLOutput) sqlOutput : new MysqlDbSQLOutput(sqlOutput);
	}
	
	@Override
	default DbSQLType wrap(SQLType sqlType)
	{
		return sqlType instanceof DbSQLType ? (DbSQLType) sqlType : new MysqlDbSQLType(sqlType);
	}
	
	@Override
	default DbSQLXML wrap(SQLXML sqlXml)
	{
		return sqlXml instanceof DbSQLXML ? (DbSQLXML) sqlXml : new MysqlDbSQLXML(sqlXml);
	}
	
	@Override
	default DbStatement wrap(Statement statement)
	{
		return statement instanceof DbStatement ? (DbStatement) statement : new MysqlDbStatement(statement);
	}
	
	@Override
	default DbStruct wrap(Struct struct)
	{
		return struct instanceof DbStruct ? (DbStruct) struct : new MysqlDbStruct(struct);
	}
	
}
