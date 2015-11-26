package roth.lib.java.db.sql;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class Joins extends Sql
{
	protected LinkedList<Join> joins = new LinkedList<Join>();
	
	public Joins() {}
	
	public void add(Join join)
	{
		joins.add(join);
	}
	
	@Override
	public LinkedList<Object> values()
	{
		LinkedList<Object> values = new LinkedList<Object>();
		for(Join join : joins)
		{
			values.addAll(join.values());
		}
		return values;
	}
	
	@Override
	public String toString()
	{
		return !joins.isEmpty() ? LF + list(joins, LF) : "";
	}
	
}
