package roth.lib.api.cloudflare.data.type;

public enum MinificationType
{
	OFF						(0),
	JAVASCRIPT				(1),
	CSS						(2),
	JAVASCRIPT_CSS			(3),
	HTML					(4),
	JAVASCRIPT_HTML			(5),
	CSS_HTML				(6),
	CSS_JAVASCRIPT_HTML		(7),
	;
	
	private int code;
	
	MinificationType(int code)
	{
		this.code = code;
	}
	
	public String get()
	{
		return String.valueOf(code);
	}
	
}
