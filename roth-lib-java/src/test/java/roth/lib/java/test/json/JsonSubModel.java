package roth.lib.java.test.json;

import java.util.Calendar;
import java.util.Date;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
public class JsonSubModel
{
	
	@Property(name = "test_boolean")
	private Boolean testBoolean;
	//private Boolean testBoolean = true;
	
	@Property(name = "test_byte")
	private Byte testByte;
	//private Byte testByte = 123;
	
	@Property(name = "test_short")
	private Short testShort;
	//private Short testShort = 12345;
	
	@Property(name = "test_integer")
	private Integer testInteger;
	//private Integer testInteger = 12345678;
	
	@Property(name = "test_long")
	private Long testLong;
	//private Long testLong = 123456789L;
	
	@Property(name = "test_float")
	private Float testFloat;
	//private Float testFloat = 1.23456789f;
	
	@Property(name = "test_double")
	private Double testDouble;
	//private Double testDouble = 1.23456789d;
	
	@Property(name = "test_character")
	private Character testCharacter;
	//private Character testCharacter = 'a';
	
	@Property(name = "test_string")
	private String testString;
	//private String testString = "test";
	
	@Property(name = "test_date")
	private Date testDate;
	//private Date testDate = new Date();
	
	@Property(name = "test_calendar")
	private Calendar testCalendar;
	//private Calendar testCalendar = new GregorianCalendar();
	
	public JsonSubModel()
	{
		
	}

	public Boolean getTestBoolean()
	{
		return testBoolean;
	}

	public Byte getTestByte()
	{
		return testByte;
	}

	public Short getTestShort()
	{
		return testShort;
	}

	public Integer getTestInteger()
	{
		return testInteger;
	}

	public Long getTestLong()
	{
		return testLong;
	}

	public Float getTestFloat()
	{
		return testFloat;
	}

	public Double getTestDouble()
	{
		return testDouble;
	}

	public Character getTestCharacter()
	{
		return testCharacter;
	}

	public String getTestString()
	{
		return testString;
	}

	public Date getTestDate()
	{
		return testDate;
	}

	public Calendar getTestCalendar()
	{
		return testCalendar;
	}

	public void setTestBoolean(Boolean testBoolean)
	{
		this.testBoolean = testBoolean;
	}

	public void setTestByte(Byte testByte)
	{
		this.testByte = testByte;
	}

	public void setTestShort(Short testShort)
	{
		this.testShort = testShort;
	}

	public void setTestInteger(Integer testInteger)
	{
		this.testInteger = testInteger;
	}

	public void setTestLong(Long testLong)
	{
		this.testLong = testLong;
	}

	public void setTestFloat(Float testFloat)
	{
		this.testFloat = testFloat;
	}

	public void setTestDouble(Double testDouble)
	{
		this.testDouble = testDouble;
	}

	public void setTestCharacter(Character testCharacter)
	{
		this.testCharacter = testCharacter;
	}

	public void setTestString(String testString)
	{
		this.testString = testString;
	}

	public void setTestDate(Date testDate)
	{
		this.testDate = testDate;
	}

	public void setTestCalendar(Calendar testCalendar)
	{
		this.testCalendar = testCalendar;
	}
	
}