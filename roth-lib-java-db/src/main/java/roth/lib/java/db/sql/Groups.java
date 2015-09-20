package roth.lib.java.db.sql;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class Groups extends Sql
{
	public static final String GROUP_BY = "  GROUP BY ";
	
	protected LinkedList<Group> groups = new LinkedList<Group>();
	
	public Groups() {}
	
	public void add(Group group)
	{
		groups.add(group);
	}
	
	@Override
	public String toString()
	{
		return !groups.isEmpty() ? LF + GROUP_BY + list(groups) : "";
	}
	
}
