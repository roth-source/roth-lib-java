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
	public String toString()
	{
		return !joins.isEmpty() ? LF + list(joins, LF) : "";
	}
	
}
