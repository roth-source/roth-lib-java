package roth.lib.java.db.orient;

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

import roth.lib.java.db.DbWrapper;

public interface OrientDbWrapper extends DbWrapper
{
	
	@Override
	default OrientDbArray wrap(Array array)
	{
		return array instanceof OrientDbArray ? (OrientDbArray) array : new OrientDbArray(array);
	}
	
	@Override
	default OrientDbBlob wrap(Blob blob)
	{
		return blob instanceof OrientDbBlob ? (OrientDbBlob) blob : new OrientDbBlob(blob);
	}
	
	@Override
	default OrientDbCallableStatement wrap(CallableStatement callableStatement)
	{
		return callableStatement instanceof OrientDbCallableStatement ? (OrientDbCallableStatement) callableStatement : new OrientDbCallableStatement(callableStatement);
	}
	
	@Override
	default OrientDbClob wrap(Clob clob)
	{
		return clob instanceof OrientDbClob ? (OrientDbClob) clob : new OrientDbClob(clob);
	}
	
	@Override
	default OrientDbConnection wrap(Connection connection)
	{
		return connection instanceof OrientDbConnection ? (OrientDbConnection) connection : new OrientDbConnection(connection);
	}
	
	@Override
	default OrientDbDatabaseMetaData wrap(DatabaseMetaData databaseMetaData)
	{
		return databaseMetaData instanceof OrientDbDatabaseMetaData ? (OrientDbDatabaseMetaData) databaseMetaData : new OrientDbDatabaseMetaData(databaseMetaData);
	}
	
	@Override
	default OrientDbDriver wrap(Driver driver)
	{
		return driver instanceof OrientDbDriver ? (OrientDbDriver) driver : new OrientDbDriver(driver);
	}
	
	@Override
	default OrientDbDriverAction wrap(DriverAction driverAction)
	{
		return driverAction instanceof OrientDbDriverAction ? (OrientDbDriverAction) driverAction : new OrientDbDriverAction(driverAction);
	}
	
	@Override
	default OrientDbNClob wrap(NClob nClob)
	{
		return nClob instanceof OrientDbNClob ? (OrientDbNClob) nClob : new OrientDbNClob(nClob);
	}
	
	@Override
	default OrientDbParameterMetaData wrap(ParameterMetaData parameterMetaData)
	{
		return parameterMetaData instanceof OrientDbParameterMetaData ? (OrientDbParameterMetaData) parameterMetaData : new OrientDbParameterMetaData(parameterMetaData);
	}
	
	@Override
	default OrientDbPreparedStatement wrap(PreparedStatement preparedStatement)
	{
		return preparedStatement instanceof OrientDbPreparedStatement ? (OrientDbPreparedStatement) preparedStatement : new OrientDbPreparedStatement(preparedStatement);
	}
	
	@Override
	default OrientDbRef wrap(Ref ref)
	{
		return ref instanceof OrientDbRef ? (OrientDbRef) ref : new OrientDbRef(ref);
	}
	
	@Override
	default OrientDbResultSet wrap(ResultSet resultSet)
	{
		return resultSet instanceof OrientDbResultSet ? (OrientDbResultSet) resultSet : new OrientDbResultSet(resultSet);
	}
	
	@Override
	default OrientDbResultSetMetaData wrap(ResultSetMetaData resultSetMetaData)
	{
		return resultSetMetaData instanceof OrientDbResultSetMetaData ? (OrientDbResultSetMetaData) resultSetMetaData : new OrientDbResultSetMetaData(resultSetMetaData);
	}
	
	@Override
	default OrientDbRowId wrap(RowId rowId)
	{
		return rowId instanceof OrientDbRowId ? (OrientDbRowId) rowId : new OrientDbRowId(rowId);
	}
	
	@Override
	default OrientDbSavepoint wrap(Savepoint savepoint)
	{
		return savepoint instanceof OrientDbSavepoint ? (OrientDbSavepoint) savepoint : new OrientDbSavepoint(savepoint);
	}
	
	@Override
	default OrientDbSQLData wrap(SQLData sqlData)
	{
		return sqlData instanceof OrientDbSQLData ? (OrientDbSQLData) sqlData : new OrientDbSQLData(sqlData);
	}
	
	@Override
	default OrientDbSQLInput wrap(SQLInput sqlInput)
	{
		return sqlInput instanceof OrientDbSQLInput ? (OrientDbSQLInput) sqlInput : new OrientDbSQLInput(sqlInput);
	}
	
	@Override
	default OrientDbSQLOutput wrap(SQLOutput sqlOutput)
	{
		return sqlOutput instanceof OrientDbSQLOutput ? (OrientDbSQLOutput) sqlOutput : new OrientDbSQLOutput(sqlOutput);
	}
	
	@Override
	default OrientDbSQLType wrap(SQLType sqlType)
	{
		return sqlType instanceof OrientDbSQLType ? (OrientDbSQLType) sqlType : new OrientDbSQLType(sqlType);
	}
	
	@Override
	default OrientDbSQLXML wrap(SQLXML sqlXml)
	{
		return sqlXml instanceof OrientDbSQLXML ? (OrientDbSQLXML) sqlXml : new OrientDbSQLXML(sqlXml);
	}
	
	@Override
	default OrientDbStatement wrap(Statement statement)
	{
		return statement instanceof OrientDbStatement ? (OrientDbStatement) statement : new OrientDbStatement(statement);
	}
	
	@Override
	default OrientDbStruct wrap(Struct struct)
	{
		return struct instanceof OrientDbStruct ? (OrientDbStruct) struct : new OrientDbStruct(struct);
	}
	
}
