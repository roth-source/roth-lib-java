package roth.lib.java.db.sql;

import java.util.Collection;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class On extends Values
{
	protected String sql;
	protected String table1;
	protected String name1;
	protected String table2;
	protected String name2;
	
	public On()
	{
		
	}
	
	public On setSql(String sql)
	{
		this.sql = sql;
		return this;
	}
	
	public On setTable1(String table1)
	{
		this.table1 = table1;
		return this;
	}
	
	public On setName1(String name1)
	{
		this.name1 = name1;
		return this;
	}
	
	public On setTable2(String table2)
	{
		this.table2 = table2;
		return this;
	}
	
	public On setName2(String name2)
	{
		this.name2 = name2;
		return this;
	}
	
	public On setValues(Collection<?> values)
	{
		this.values = new List<Object>().allowNull().collection(values);
		return this;
	}
	
	public On addValues(Object...values)
	{
		this.values.array(values);
		return this;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		if(sql != null)
		{
			builder.append(sql);
		}
		else
		{
			builder.append(tick(table1));
			builder.append(DOT);
			builder.append(tick(name1));
			builder.append(EQ);
			builder.append(tick(table2));
			builder.append(DOT);
			builder.append(tick(name2));
		}
		return builder.toString();
	}
	
}
