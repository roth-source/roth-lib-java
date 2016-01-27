package roth.lib.java.db.sql;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Values extends Sql
{
	protected List<Object> values = new List<Object>(true);
	
	protected Values()
	{
		
	}
	
	@Override
	public List<Object> getValues()
	{
		return values;
	}
	
}
