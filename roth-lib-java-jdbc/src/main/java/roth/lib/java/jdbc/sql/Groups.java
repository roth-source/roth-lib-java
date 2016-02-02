package roth.lib.java.jdbc.sql;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Groups extends Sql
{
	protected List<Group> groups = new List<Group>();
	
	protected Groups()
	{
		
	}
	
	public Groups setGroups(List<Group> groups)
	{
		this.groups = groups;
		return this;
	}
	
	public Groups addGroups(Group... groups)
	{
		this.groups.array(groups);
		return this;
	}
	
	@Override
	public String toString()
	{
		return !groups.isEmpty() ? LF + GROUP_BY + list(groups) : "";
	}
	
}
