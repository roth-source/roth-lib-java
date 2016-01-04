package roth.lib.java.db.sql;

@SuppressWarnings("serial")
public abstract class Condition extends Values
{
	protected String logicType = LOGIC_AND;
	
	protected Condition()
	{
		
	}
	
	public abstract Condition setLogicType(String logicType);
	public abstract String toString(boolean nested);
	
}
