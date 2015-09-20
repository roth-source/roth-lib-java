package roth.lib.java.type;

public enum AlgorithmNameType
{
	AES			(16, 16),
	DES			(8, 8),
	DESEDE		(24, 8),
	;
	
	protected int keyLength;
	protected int ivLength;
	
	AlgorithmNameType(int keyLength, int ivLength)
	{
		this.keyLength = keyLength;
		this.ivLength = ivLength;
	}
	
	public int getKeyLength()
	{
		return keyLength;
	}
	
	public int getIvLength()
	{
		return ivLength;
	}
	
}
