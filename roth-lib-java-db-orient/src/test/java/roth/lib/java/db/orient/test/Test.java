package roth.lib.java.db.orient.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.orientechnologies.orient.core.record.impl.ODocument;

import roth.lib.java.db.DbConnection;
import roth.lib.java.db.orient.OrientDb;

public class Test
{
	
	public static void main(String[] args) throws Exception
	{
		OrientDb db = new OrientDb("com.orientechnologies.orient.jdbc.OrientJdbcDriver", "jdbc:orient:remote:localhost/test", "root", "test");
		try(DbConnection dbConnection = db.getConnection())
		{
			Connection connection = dbConnection.unwrap();
			try(PreparedStatement statement = connection.prepareStatement("SELECT method FROM Payment"))
			{
				try(ResultSet resultSet = statement.executeQuery())
				{
					ResultSetMetaData metaData = resultSet.getMetaData();
					while(resultSet.next())
					{
						for(int i = 1; i <= metaData.getColumnCount(); i++)
						{
							String label = metaData.getColumnLabel(i);
							Object object = resultSet.getObject(label);
							if(object instanceof ODocument)
							{
								ODocument document = (ODocument) object;
								System.out.println(document.getOriginalValue("number"));
							}
							System.out.println(label + " : " + object);
						}
						System.out.println(resultSet.getString("@rid"));
					}
				}
			}
		}
		db.close();
	}
	
}
