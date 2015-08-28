package roth.lib.api.digitalocean.type;


public enum RegionType
{
	NEW_YORK_1			("nyc1"),
	NEW_YORK_2			("nyc2"),
	SAN_FRANCISCO_1		("sfo1"),
	AMSTERDAM_1			("ams1"),
	AMSTERDAM_2			("ams2"),
	SINGAPORE_1			("spg1"),
	;
	
	private String slug;
	
	RegionType(String slug)
	{
		this.slug = slug;
	}
	
	public String get()
	{
		return slug;
	}
	
}
