package roth.lib.api.cloudflare.data.type;


public enum IntervalType
{
	PAST_6_HOURS	(120),
	PAST_12_HOURS	(110),
	PAST_24_HOURS	(100),
	PAST_1_DAY		(40),
	PAST_7_DAYS		(30),
	PAST_30_DAYS	(20),
	;
	
	private int code;
	
	IntervalType(int code)
	{
		this.code = code;
	}
	
	public Integer get()
	{
		return code;
	}
	
}
