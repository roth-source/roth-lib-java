package roth.lib.type;

import static roth.lib.type.AlgorithmModeType.CBC;
import static roth.lib.type.AlgorithmModeType.ECB;
import static roth.lib.type.AlgorithmNameType.AES;
import static roth.lib.type.AlgorithmNameType.DES;
import static roth.lib.type.AlgorithmNameType.DESEDE;

public enum AlgorithmType
{
	AES_CBC		(AES, CBC),
	AES_ECB		(AES, ECB),
	DES_CBC		(DES, CBC),
	DES_ECB		(DES, ECB),
	DESEDE_CBC	(DESEDE, CBC),
	DESEDE_ECB	(DESEDE, ECB),
	;
	
	protected AlgorithmNameType algorithmNameType;
	protected AlgorithmModeType algorithmModeType;
	
	AlgorithmType(AlgorithmNameType algorithmNameType, AlgorithmModeType algorithmModeType)
	{
		this.algorithmNameType = algorithmNameType;
		this.algorithmModeType = algorithmModeType;
	}
	
	public AlgorithmNameType getAlgorithmNameType()
	{
		return algorithmNameType;
	}
	
	public AlgorithmModeType getAlgorithmModeType()
	{
		return algorithmModeType;
	}
	
	public String getName()
	{
		return algorithmNameType.name();
	}
	
	public String getMode()
	{
		return algorithmModeType.name();
	}
	
	public int getKeyLength()
	{
		return algorithmNameType.getKeyLength();
	}
	
	public int getIvLength()
	{
		return algorithmNameType.getIvLength();
	}
	
	public String getAlgorithm(AlgorithmPaddingType algorithmPaddingType)
	{
		return algorithmNameType.name() + "/" + algorithmModeType.name() + "/" + algorithmPaddingType.name();
	}
	
}
