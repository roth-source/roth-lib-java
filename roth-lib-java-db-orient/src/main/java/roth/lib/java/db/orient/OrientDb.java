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
import java.util.Properties;

import roth.lib.java.db.DbArray;
import roth.lib.java.db.DbBlob;
import roth.lib.java.db.DbCallableStatement;
import roth.lib.java.db.DbClob;
import roth.lib.java.db.DbConnection;
import roth.lib.java.db.DbDataSource;
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
import roth.lib.java.db.sql.Column;
import roth.lib.java.db.sql.Columns;
import roth.lib.java.db.sql.Delete;
import roth.lib.java.db.sql.From;
import roth.lib.java.db.sql.Group;
import roth.lib.java.db.sql.Groups;
import roth.lib.java.db.sql.Having;
import roth.lib.java.db.sql.Havings;
import roth.lib.java.db.sql.Insert;
import roth.lib.java.db.sql.Join;
import roth.lib.java.db.sql.Joins;
import roth.lib.java.db.sql.On;
import roth.lib.java.db.sql.Order;
import roth.lib.java.db.sql.Orders;
import roth.lib.java.db.sql.Page;
import roth.lib.java.db.sql.Select;
import roth.lib.java.db.sql.Set;
import roth.lib.java.db.sql.Update;
import roth.lib.java.db.sql.Where;
import roth.lib.java.db.sql.Wheres;
import roth.lib.java.mapper.MapperType;

public class OrientDb extends DbDataSource implements OrientDbWrapper
{
	
	public OrientDb()
	{
		super(MapperType.ORIENT);
	}
	
	public OrientDb(String driver, String url)
	{
		super(MapperType.ORIENT, driver, url);
	}
	
	public OrientDb(String driver, String url, String username, String password)
	{
		super(MapperType.ORIENT, driver, url, username, password);
	}
	
	public OrientDb(String driver, String url, Properties properties)
	{
		super(MapperType.ORIENT, driver, url, properties);
	}
	
	public OrientDb(String driver, String url, String username, String password, Properties properties)
	{
		super(MapperType.ORIENT, driver, url, username, password, properties);
	}

	@Override
	public DbArray wrap(Array array)
	{
		return null;
	}

	@Override
	public DbBlob wrap(Blob blob)
	{
		return null;
	}

	@Override
	public DbCallableStatement wrap(CallableStatement callableStatement)
	{
		return null;
	}

	@Override
	public DbClob wrap(Clob clob)
	{
		return null;
	}

	@Override
	public DbConnection wrap(Connection connection)
	{
		return null;
	}

	@Override
	public DbDatabaseMetaData wrap(DatabaseMetaData databaseMetaData)
	{
		return null;
	}

	@Override
	public DbDriver wrap(Driver driver)
	{
		return null;
	}

	@Override
	public DbDriverAction wrap(DriverAction driverAction)
	{
		return null;
	}

	@Override
	public DbNClob wrap(NClob nClob)
	{
		return null;
	}

	@Override
	public DbParameterMetaData wrap(ParameterMetaData parameterMetaData)
	{
		return null;
	}

	@Override
	public DbPreparedStatement wrap(PreparedStatement preparedStatement)
	{
		return null;
	}

	@Override
	public DbRef wrap(Ref ref)
	{
		return null;
	}

	@Override
	public DbResultSet wrap(ResultSet resultSet)
	{
		return null;
	}

	@Override
	public DbResultSetMetaData wrap(ResultSetMetaData resultSetMetaData)
	{
		return null;
	}

	@Override
	public DbRowId wrap(RowId rowId)
	{
		return null;
	}

	@Override
	public DbSavepoint wrap(Savepoint savepoint)
	{
		return null;
	}

	@Override
	public DbSQLData wrap(SQLData sqlData)
	{
		return null;
	}

	@Override
	public DbSQLInput wrap(SQLInput sqlInput)
	{
		return null;
	}

	@Override
	public DbSQLOutput wrap(SQLOutput sqlOutput)
	{
		return null;
	}

	@Override
	public DbSQLType wrap(SQLType sqlType)
	{
		return null;
	}

	@Override
	public DbSQLXML wrap(SQLXML sqlXml)
	{
		return null;
	}

	@Override
	public DbStatement wrap(Statement statement)
	{
		return null;
	}

	@Override
	public DbStruct wrap(Struct struct)
	{
		return null;
	}

	@Override
	public Select newSelect()
	{
		return null;
	}

	@Override
	public Columns newColumns()
	{
		return null;
	}

	@Override
	public Column newColumn()
	{
		return null;
	}

	@Override
	public From newFrom()
	{
		return null;
	}

	@Override
	public Joins newJoins()
	{
		return null;
	}

	@Override
	public Join newJoin()
	{
		return null;
	}

	@Override
	public On newOn()
	{
		return null;
	}

	@Override
	public Wheres newWheres()
	{
		return null;
	}

	@Override
	public Where newWhere()
	{
		return null;
	}

	@Override
	public Groups newGroups()
	{
		return null;
	}

	@Override
	public Group newGroup()
	{
		return null;
	}

	@Override
	public Havings newHavings()
	{
		return null;
	}

	@Override
	public Having newHaving()
	{
		return null;
	}

	@Override
	public Orders newOrders()
	{
		return null;
	}

	@Override
	public Order newOrder()
	{
		return null;
	}

	@Override
	public Page newPage()
	{
		return null;
	}

	@Override
	public Insert newInsert()
	{
		return null;
	}

	@Override
	public Update newUpdate()
	{
		return null;
	}

	@Override
	public Set newSet()
	{
		return null;
	}

	@Override
	public Delete newDelete()
	{
		return null;
	}
	
}
