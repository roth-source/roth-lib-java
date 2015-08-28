package roth.lib.api.digitalocean.type;

public enum SizeType
{
	_512MB,
	_1GB,
	_2GB,
	_4GB,
	_8GB,
	_16GB,
	;
	
	public String get()
	{
		return name().toLowerCase().replaceFirst("^_", "");
	}
	
}
