package roth.lib.java.time;

public class Interval
{
	protected Time start;
	protected Time end;
	
	public Interval()
	{
		
	}
	
	public Interval(Time start, Time end)
	{
		this.start = start;
		this.end = end;
	}
	
	public Time getStart()
	{
		return start;
	}
	
	public Time getEnd()
	{
		return end;
	}
	
	public void setStart(Time start)
	{
		this.start = start;
	}
	
	public void setEnd(Time end)
	{
		this.end = end;
	}
	
}
