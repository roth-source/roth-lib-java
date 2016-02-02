package roth.lib.java.db.sql;

@SuppressWarnings("serial")
public abstract class Set extends Sql
{
	protected String sql;
	protected String name;
	protected Object value;
	
	public Set()
	{
		
	}

	public Set setSql(String sql)
	{
		this.sql = sql;
		return this;
	}
	
	public Set setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Set setValue(Object value)
	{
		this.value = value;
		return this;
	}

	@Override
	public String toString()
	{
		if(sql != null)
		{
			return sql;
		}
		else
		{
			StringBuilder builder = new StringBuilder();
			builder.append(tick(name));
			builder.append(PARAM);
			return builder.toString();
		}
	}
	
}
