package roth.lib.java.jdbc.sql;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Joins extends Sql
{
	protected List<Join> joins = new List<Join>();
	
	public Joins()
	{
		
	}
	
	public Joins setJoins(List<Join> joins)
	{
		this.joins = joins;
		return this;
	}
	
	public Joins addJoins(Join... joins)
	{
		this.joins.array(joins);
		return this;
	}
	
	@Override
	public List<Object> getValues()
	{
		List<Object> values = new List<Object>().allowNull();
		for(Join join : joins)
		{
			values.addAll(join.getValues());
		}
		return values;
	}
	
	@Override
	public String toString()
	{
		return !joins.isEmpty() ? LF + list(joins, LF) : "";
	}
	
}
