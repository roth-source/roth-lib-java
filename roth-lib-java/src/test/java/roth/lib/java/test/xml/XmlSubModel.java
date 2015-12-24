package roth.lib.java.test.xml;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity(propertyName = "test-sub")
public class XmlSubModel
{
	
	@Property(name = "test-boolean")
	private Boolean testBoolean = true;
	
	@Property(name = "test-byte")
	private Byte testByte = 123;
	
	@Property(name = "test-short")
	private Short testShort = 12345;
	
	@Property(name = "test-integer")
	private Integer testInteger = 12345678;
	
	@Property(name = "test-long")
	private Long testLong = 123456789L;
	
	@Property(name = "test-float")
	private Float testFloat = 1.23456789f;
	
	@Property(name = "test-double")
	private Double testDouble = 1.23456789d;
	
	@Property(name = "test-character")
	private Character testCharacter = 'a';
	
	@Property(name = "test-string")
	private String testString = "test";
	
	@Property(name = "test-date")
	private Date testDate = new Date();
	
	@Property(name = "test-calendar")
	private Calendar testCalendar = new GregorianCalendar();
	
	public XmlSubModel()
	{
		
	}
	
}