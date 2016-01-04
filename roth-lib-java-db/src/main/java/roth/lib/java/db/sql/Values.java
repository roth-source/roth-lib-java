package roth.lib.java.db.sql;

import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class Values extends Sql
{
	protected LinkedList<Object> values = new LinkedList<Object>();
	
	protected Values()
	{
		
	}
	
	@Override
	public LinkedList<Object> getValues()
	{
		return values;
	}
	
}
