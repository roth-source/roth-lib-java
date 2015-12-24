package roth.lib.java.test.xml;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.xml.XmlMapper;
import roth.lib.java.xml.XmlModel;

@Entity(name = "test")
@SuppressWarnings("serial")
public class XmlTestModel extends XmlModel
{
	
	@Property(name = "attribute", attribute = true)
	private String attribute = "test";
	
	@Property(name = "null-primitive-boolean")
	private boolean nullPrimitiveBoolean;
	
	@Property(name = "null-primitive-byte")
	private byte nullPrimitiveByte;
	
	@Property(name = "null-primitive-short")
	private short nullPrimitiveShort;
	
	@Property(name = "null-primitive-integer")
	private int nullPrimitiveInteger;
	
	@Property(name = "null-primitive-long")
	private long nullPrimitiveLong;
	
	@Property(name = "null-primitive-float")
	private float nullPrimitiveFloat;
	
	@Property(name = "null-primitive-double")
	private double nullPrimitiveDouble;
	
	@Property(name = "null-primitive-character")
	private char nullPrimitiveCharacter;
	
	@Property(name = "null-wrapper-boolean")
	private Boolean nullWrapperBoolean = null;
	
	@Property(name = "null-wrapper-byte")
	private Byte nullWrapperByte;
	
	@Property(name = "null-wrapper-short")
	private Short nullWrapperShort;
	
	@Property(name = "null-wrapper-integer")
	private Integer nullWrapperInteger;
	
	@Property(name = "null-wrapper-long")
	private Long nullWrapperLong;
	
	@Property(name = "null-wrapper-float")
	private Float nullWrapperFloat;
	
	@Property(name = "null-wrapper-double")
	private Double nullWrapperDouble;
	
	@Property(name = "null-wrapper-character")
	private Character nullWrapperCharacter;
	
	@Property(name = "null-string")
	private String nullString;
	
	@Property(name = "null-date")
	private Date nullDate;
	
	@Property(name = "null-calendar")
	private Calendar nullCalendar;
	
	@Property(name = "test-boolean")
	//private Boolean testBoolean = true;
	private Boolean testBoolean;
	
	@Property(name = "test-byte")
	//private Byte testByte = 123;
	private Byte testByte;
	
	@Property(name = "test-short")
	//private Short testShort = 12345;
	private Short testShort;
	
	@Property(name = "test-integer")
	//private Integer testInteger = 12345678;
	private Integer testInteger;
	
	@Property(name = "test-long")
	//private Long testLong = 123456789L;
	private Long testLong;
	
	@Property(name = "test-float")
	//private Float testFloat = 1.23456789f;
	private Float testFloat;
	
	@Property(name = "test-double")
	//private Double testDouble = 1.23456789d;
	private Double testDouble;
	
	@Property(name = "test-character")
	//private Character testCharacter = 'a';
	private Character testCharacter;
	
	@Property(name = "test-string")
	//private String testString = "<test>He said, \"He's nice & smart\"</test>";
	private String testString;
	
	@Property(name = "test-date")
	//private Date testDate = new Date();
	private Date testDate;
	
	@Property(name = "test-calendar", exclude = "test")
	private Calendar testCalendar = new GregorianCalendar();
	//private Calendar testCalendar;
	
	@Property(name = "test-model")
	//private XmlSubModel testModel = new XmlSubModel();
	private XmlSubModel testModel;
	
	@Property(name = "null-string-array")
	private String[] nullStringArray;
	
	@Property(name = "empty-string-array")
	private String[] emptyStringArray = new String[]{};
	
	@Property(name = "test-string-array", elementsName = "element")
	//private String[] testStringArray = new String[]{"one", "two", "three"};
	private String[] testStringArray;
	
	@Property(name = "null-string-list", elementsName = "nullElement")
	private LinkedList<String> nullStringList;
	
	@Property(name = "empty-string-list")
	private LinkedList<String> emptyStringList = new LinkedList<String>();
	
	@Property(name = "test-string-list", elementsName = "element")
	//private LinkedList<String> testStringList = new LinkedList<String>(Arrays.asList(new String[]{"one", "two", "three"}));
	private LinkedList<String> testStringList;
	
	@Property(name = "null-string-map")
	private LinkedHashMap<String, String> nullStringMap;
	
	@Property(name = "empty-string-map")
	private LinkedHashMap<String, String> emptyStringMap = new LinkedHashMap<String, String>();
	
	@Property(name = "test-string-map")
	private LinkedHashMap<String, String> testStringMap = new LinkedHashMap<String, String>();
	
	@Property(name = "test-model-list", elementsName = "element")
	//private LinkedList<XmlSubModel> testModelList = new LinkedList<XmlSubModel>(Arrays.asList(new XmlSubModel[]{new XmlSubModel(), new XmlSubModel()}));
	private LinkedList<XmlSubModel> testModelList;
	
	@Property(name = "test-model-map")
	private LinkedHashMap<String, XmlSubModel> testModelMap = new LinkedHashMap<String, XmlSubModel>();
	
	public XmlTestModel()
	{
		//testStringMap.put("one", "value one");
		//testStringMap.put("two", "value two");
		//testStringMap.put("three", "value three");
		//testModelMap.put("one", new XmlSubModel());
		//testModelMap.put("two", new XmlSubModel());
		attributeMap.put("mandatory", "true");
		attributeMap.put("validate", "false");
	}
	
	public void init()
	{
		testBoolean = true;
		testByte = 123;
		testShort = 12345;
		testInteger = 12345678;
		testLong = 123456789L;
		testFloat = 1.23456789f;
		testDouble = 1.23456789d;
		testCharacter = 'a';
		testString = "<test>He said, \"He's nice & smart\"</test>";
		testDate = new Date();
		testCalendar = new GregorianCalendar();
		testModel = new XmlSubModel();
		testStringArray = new String[]{"one", "two", "three"};
		testStringList = new LinkedList<String>(Arrays.asList(new String[]{"one", "two", "three"}));
		testModelList = new LinkedList<XmlSubModel>(Arrays.asList(new XmlSubModel[]{new XmlSubModel(), new XmlSubModel()}));
		testStringMap.put("one", "value one");
		testStringMap.put("two", "value two");
		testStringMap.put("three", "value three");
		testModelMap.put("one", new XmlSubModel());
		testModelMap.put("two", new XmlSubModel());
	}
	
	@Override
	public String toString()
	{
		MapperConfig mapperConfig = new MapperConfig().setSerializeNulls(true).setTimeFormat("yyyy-MM-dd HH:mm:ss");
		return new XmlMapper(mapperConfig).setPrettyPrint(true).serialize(this);
	}
	
}