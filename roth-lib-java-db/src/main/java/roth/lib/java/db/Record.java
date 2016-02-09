package roth.lib.java.db;

import roth.lib.java.lang.Map;

public class Record
{
	protected Position position;
	protected Map<String, Field> fieldMap = new Map<String, Field>();
	
	public Record()
	{
		
	}
	
}
