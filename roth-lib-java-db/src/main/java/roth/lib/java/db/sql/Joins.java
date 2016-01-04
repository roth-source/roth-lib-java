package roth.lib.java.db.sql;

import java.util.Arrays;
import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class Joins extends Sql
{
	protected LinkedList<Join> joins = new LinkedList<Join>();
	
	public Joins()
	{
		
	}
	
	public Joins setJoins(LinkedList<Join> joins)
	{
		this.joins = joins;
		return this;
	}
	
	public Joins addJoins(Join... joins)
	{
		this.joins.addAll(Arrays.asList(joins));
		return this;
	}
	
	@Override
	public LinkedList<Object> getValues()
	{
		LinkedList<Object> values = new LinkedList<Object>();
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
