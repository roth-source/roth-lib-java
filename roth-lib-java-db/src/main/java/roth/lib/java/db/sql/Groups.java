package roth.lib.java.db.sql;

import java.util.Arrays;
import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class Groups extends Sql
{
	protected LinkedList<Group> groups = new LinkedList<Group>();
	
	protected Groups()
	{
		
	}
	
	public Groups setGroups(LinkedList<Group> groups)
	{
		this.groups = groups;
		return this;
	}
	
	public Groups addGroups(Group... groups)
	{
		this.groups.addAll(Arrays.asList(groups));
		return this;
	}
	
	@Override
	public String toString()
	{
		return !groups.isEmpty() ? LF + GROUP_BY + list(groups) : "";
	}
	
}
