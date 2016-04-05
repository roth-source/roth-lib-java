package roth.lib.java.jdbc.mysql;

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

import roth.lib.java.jdbc.JdbcWrapper;

public interface MysqlDbWrapper extends JdbcWrapper
{
	
	@Override
	default MysqlDbArray wrap(Array array)
	{
		return array instanceof MysqlDbArray ? (MysqlDbArray) array : new MysqlDbArray(array);
	}
	
	@Override
	default MysqlDbBlob wrap(Blob blob)
	{
		return blob instanceof MysqlDbBlob ? (MysqlDbBlob) blob : new MysqlDbBlob(blob);
	}
	
	@Override
	default MysqlDbCallableStatement wrap(CallableStatement callableStatement)
	{
		return callableStatement instanceof MysqlDbCallableStatement ? (MysqlDbCallableStatement) callableStatement : new MysqlDbCallableStatement(callableStatement);
	}
	
	@Override
	default MysqlDbClob wrap(Clob clob)
	{
		return clob instanceof MysqlDbClob ? (MysqlDbClob) clob : new MysqlDbClob(clob);
	}
	
	@Override
	default MysqlDbConnection wrap(Connection connection)
	{
		return connection instanceof MysqlDbConnection ? (MysqlDbConnection) connection : new MysqlDbConnection(connection);
	}
	
	@Override
	default MysqlDbDatabaseMetaData wrap(DatabaseMetaData databaseMetaData)
	{
		return databaseMetaData instanceof MysqlDbDatabaseMetaData ? (MysqlDbDatabaseMetaData) databaseMetaData : new MysqlDbDatabaseMetaData(databaseMetaData);
	}
	
	@Override
	default MysqlDbDriver wrap(Driver driver)
	{
		return driver instanceof MysqlDbDriver ? (MysqlDbDriver) driver : new MysqlDbDriver(driver);
	}
	
	@Override
	default MysqlDbDriverAction wrap(DriverAction driverAction)
	{
		return driverAction instanceof MysqlDbDriverAction ? (MysqlDbDriverAction) driverAction : new MysqlDbDriverAction(driverAction);
	}
	
	@Override
	default MysqlDbNClob wrap(NClob nClob)
	{
		return nClob instanceof MysqlDbNClob ? (MysqlDbNClob) nClob : new MysqlDbNClob(nClob);
	}
	
	@Override
	default MysqlDbParameterMetaData wrap(ParameterMetaData parameterMetaData)
	{
		return parameterMetaData instanceof MysqlDbParameterMetaData ? (MysqlDbParameterMetaData) parameterMetaData : new MysqlDbParameterMetaData(parameterMetaData);
	}
	
	@Override
	default MysqlDbPreparedStatement wrap(PreparedStatement preparedStatement)
	{
		return preparedStatement instanceof MysqlDbPreparedStatement ? (MysqlDbPreparedStatement) preparedStatement : new MysqlDbPreparedStatement(preparedStatement);
	}
	
	@Override
	default MysqlDbRef wrap(Ref ref)
	{
		return ref instanceof MysqlDbRef ? (MysqlDbRef) ref : new MysqlDbRef(ref);
	}
	
	@Override
	default MysqlDbResultSet wrap(ResultSet resultSet)
	{
		return resultSet instanceof MysqlDbResultSet ? (MysqlDbResultSet) resultSet : new MysqlDbResultSet(resultSet);
	}
	
	@Override
	default MysqlDbResultSetMetaData wrap(ResultSetMetaData resultSetMetaData)
	{
		return resultSetMetaData instanceof MysqlDbResultSetMetaData ? (MysqlDbResultSetMetaData) resultSetMetaData : new MysqlDbResultSetMetaData(resultSetMetaData);
	}
	
	@Override
	default MysqlDbRowId wrap(RowId rowId)
	{
		return rowId instanceof MysqlDbRowId ? (MysqlDbRowId) rowId : new MysqlDbRowId(rowId);
	}
	
	@Override
	default MysqlDbSavepoint wrap(Savepoint savepoint)
	{
		return savepoint instanceof MysqlDbSavepoint ? (MysqlDbSavepoint) savepoint : new MysqlDbSavepoint(savepoint);
	}
	
	@Override
	default MysqlDbSQLData wrap(SQLData sqlData)
	{
		return sqlData instanceof MysqlDbSQLData ? (MysqlDbSQLData) sqlData : new MysqlDbSQLData(sqlData);
	}
	
	@Override
	default MysqlDbSQLInput wrap(SQLInput sqlInput)
	{
		return sqlInput instanceof MysqlDbSQLInput ? (MysqlDbSQLInput) sqlInput : new MysqlDbSQLInput(sqlInput);
	}
	
	@Override
	default MysqlDbSQLOutput wrap(SQLOutput sqlOutput)
	{
		return sqlOutput instanceof MysqlDbSQLOutput ? (MysqlDbSQLOutput) sqlOutput : new MysqlDbSQLOutput(sqlOutput);
	}
	
	@Override
	default MysqlDbSQLType wrap(SQLType sqlType)
	{
		return sqlType instanceof MysqlDbSQLType ? (MysqlDbSQLType) sqlType : new MysqlDbSQLType(sqlType);
	}
	
	@Override
	default MysqlDbSQLXML wrap(SQLXML sqlXml)
	{
		return sqlXml instanceof MysqlDbSQLXML ? (MysqlDbSQLXML) sqlXml : new MysqlDbSQLXML(sqlXml);
	}
	
	@Override
	default MysqlDbStatement wrap(Statement statement)
	{
		return statement instanceof MysqlDbStatement ? (MysqlDbStatement) statement : new MysqlDbStatement(statement);
	}
	
	@Override
	default MysqlDbStruct wrap(Struct struct)
	{
		return struct instanceof MysqlDbStruct ? (MysqlDbStruct) struct : new MysqlDbStruct(struct);
	}
	
}
