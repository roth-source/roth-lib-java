package roth.lib.java.lang;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

@SuppressWarnings("serial")
public class Number extends BigDecimal
{
	
	public Number(char[] in)
	{
		super(in);
	}
	
	public Number(String val)
	{
		super(val);
	}
	
	public Number(double val)
	{
		super(val);
	}
	
	public Number(BigInteger val)
	{
		super(val);
	}
	
	public Number(int val)
	{
		super(val);
	}
	
	public Number(long val)
	{
		super(val);
	}
	
	public Number(char[] in, MathContext mc)
	{
		super(in, mc);
	}
	
	public Number(String val, MathContext mc)
	{
		super(val, mc);
	}
	
	public Number(double val, MathContext mc)
	{
		super(val, mc);
	}
	
	public Number(BigInteger val, MathContext mc)
	{
		super(val, mc);
	}
	
	public Number(BigInteger unscaledVal, int scale)
	{
		super(unscaledVal, scale);
	}
	
	public Number(int val, MathContext mc)
	{
		super(val, mc);
	}
	
	public Number(long val, MathContext mc)
	{
		super(val, mc);
	}
	
	public Number(char[] in, int offset, int len)
	{
		super(in, offset, len);
	}
	
	public Number(BigInteger unscaledVal, int scale, MathContext mc)
	{
		super(unscaledVal, scale, mc);
	}
	
	public Number(char[] in, int offset, int len, MathContext mc)
	{
		super(in, offset, len, mc);
	}
	
	@Override
	public BigDecimal add(BigDecimal augend)
	{
		return super.add(augend);
	}
	
	@Override
	public BigDecimal add(BigDecimal augend, MathContext mc)
	{
		return super.add(augend, mc);
	}
	
	@Override
	public BigDecimal subtract(BigDecimal subtrahend)
	{
		return super.subtract(subtrahend);
	}
	
	@Override
	public BigDecimal subtract(BigDecimal subtrahend, MathContext mc)
	{
		return super.subtract(subtrahend, mc);
	}
	
	@Override
	public BigDecimal multiply(BigDecimal multiplicand)
	{
		return super.multiply(multiplicand);
	}
	
	@Override
	public BigDecimal multiply(BigDecimal multiplicand, MathContext mc)
	{
		return super.multiply(multiplicand, mc);
	}
	
	@Override
	public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)
	{
		return super.divide(divisor, scale, roundingMode);
	}
	
	@Override
	public BigDecimal divide(BigDecimal divisor, int scale, RoundingMode roundingMode)
	{
		return super.divide(divisor, scale, roundingMode);
	}
	
	@Override
	public BigDecimal divide(BigDecimal divisor, int roundingMode)
	{
		return super.divide(divisor, roundingMode);
	}
	
	@Override
	public BigDecimal divide(BigDecimal divisor, RoundingMode roundingMode)
	{
		return super.divide(divisor, roundingMode);
	}
	
	@Override
	public BigDecimal divide(BigDecimal divisor)
	{
		return super.divide(divisor);
	}
	
	@Override
	public BigDecimal divide(BigDecimal divisor, MathContext mc)
	{
		return super.divide(divisor, mc);
	}
	
	@Override
	public BigDecimal divideToIntegralValue(BigDecimal divisor)
	{
		return super.divideToIntegralValue(divisor);
	}
	
	@Override
	public BigDecimal divideToIntegralValue(BigDecimal divisor, MathContext mc)
	{
		return super.divideToIntegralValue(divisor, mc);
	}
	
	@Override
	public BigDecimal remainder(BigDecimal divisor)
	{
		return super.remainder(divisor);
	}
	
	@Override
	public BigDecimal remainder(BigDecimal divisor, MathContext mc)
	{
		return super.remainder(divisor, mc);
	}
	
	@Override
	public BigDecimal[] divideAndRemainder(BigDecimal divisor)
	{
		return super.divideAndRemainder(divisor);
	}
	
	@Override
	public BigDecimal[] divideAndRemainder(BigDecimal divisor, MathContext mc)
	{
		return super.divideAndRemainder(divisor, mc);
	}
	
	@Override
	public BigDecimal pow(int n)
	{
		return super.pow(n);
	}
	
	@Override
	public BigDecimal pow(int n, MathContext mc)
	{
		return super.pow(n, mc);
	}
	
	@Override
	public BigDecimal abs()
	{
		return super.abs();
	}
	
	@Override
	public BigDecimal abs(MathContext mc)
	{
		return super.abs(mc);
	}
	
	@Override
	public BigDecimal negate()
	{
		return super.negate();
	}
	
	@Override
	public BigDecimal negate(MathContext mc)
	{
		return super.negate(mc);
	}
	
	@Override
	public BigDecimal plus()
	{
		return super.plus();
	}
	
	@Override
	public BigDecimal plus(MathContext mc)
	{
		return super.plus(mc);
	}
	
	@Override
	public int signum()
	{
		return super.signum();
	}
	
	@Override
	public int scale()
	{
		return super.scale();
	}
	
	@Override
	public int precision()
	{
		return super.precision();
	}
	
	@Override
	public BigInteger unscaledValue()
	{
		return super.unscaledValue();
	}
	
	@Override
	public BigDecimal round(MathContext mc)
	{
		return super.round(mc);
	}
	
	@Override
	public BigDecimal setScale(int newScale, RoundingMode roundingMode)
	{
		return super.setScale(newScale, roundingMode);
	}
	
	@Override
	public BigDecimal setScale(int newScale, int roundingMode)
	{
		return super.setScale(newScale, roundingMode);
	}
	
	@Override
	public BigDecimal setScale(int newScale)
	{
		return super.setScale(newScale);
	}
	
	@Override
	public BigDecimal movePointLeft(int n)
	{
		return super.movePointLeft(n);
	}
	
	@Override
	public BigDecimal movePointRight(int n)
	{
		return super.movePointRight(n);
	}
	
	@Override
	public BigDecimal scaleByPowerOfTen(int n)
	{
		return super.scaleByPowerOfTen(n);
	}
	
	@Override
	public BigDecimal stripTrailingZeros()
	{
		return super.stripTrailingZeros();
	}
	
	@Override
	public int compareTo(BigDecimal val)
	{
		return super.compareTo(val);
	}
	
	@Override
	public boolean equals(Object x)
	{
		return super.equals(x);
	}
	
	@Override
	public BigDecimal min(BigDecimal val)
	{
		return super.min(val);
	}
	
	@Override
	public BigDecimal max(BigDecimal val)
	{
		return super.max(val);
	}
	
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	@Override
	public String toString()
	{
		return super.toString();
	}
	
	@Override
	public String toEngineeringString()
	{
		return super.toEngineeringString();
	}
	
	@Override
	public String toPlainString()
	{
		return super.toPlainString();
	}
	
	@Override
	public BigInteger toBigInteger()
	{
		return super.toBigInteger();
	}
	
	@Override
	public BigInteger toBigIntegerExact()
	{
		return super.toBigIntegerExact();
	}
	
	@Override
	public long longValue()
	{
		return super.longValue();
	}
	
	@Override
	public long longValueExact()
	{
		return super.longValueExact();
	}
	
	@Override
	public int intValue()
	{
		return super.intValue();
	}
	
	@Override
	public int intValueExact()
	{
		return super.intValueExact();
	}
	
	@Override
	public short shortValueExact()
	{
		return super.shortValueExact();
	}
	
	@Override
	public byte byteValueExact()
	{
		return super.byteValueExact();
	}
	
	@Override
	public float floatValue()
	{
		return super.floatValue();
	}
	
	@Override
	public double doubleValue()
	{
		return super.doubleValue();
	}
	
	@Override
	public BigDecimal ulp()
	{
		return super.ulp();
	}
	
	@Override
	public byte byteValue()
	{
		return super.byteValue();
	}
	
	@Override
	public short shortValue()
	{
		return super.shortValue();
	}
	
	@Override
	protected Number clone()
	{
		try
		{
			return (Number) super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
	
}
