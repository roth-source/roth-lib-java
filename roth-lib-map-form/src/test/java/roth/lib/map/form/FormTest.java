package roth.lib.map.form;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

import roth.lib.map.form.FormConfig;
import roth.lib.map.form.FormMapper;

public class FormTest
{
	
	public static void main(String[] args)
	{
		FormConfig config = new FormConfig().setPrettyPrinting(true).setSerializeNulls(true).setTimeFormat("yyyy-MM-dd HH:mm:ss");
		FormMapper mapper = new FormMapper();
		serializeModel(config, mapper);
		//serializeMap(config, mapper);
		//deserializeModel(config, mapper);
		//deserializeMap(config, mapper);
	}
	
	protected static void serializeModel(FormConfig config, FormMapper mapper)
	{
		FormModel model = new FormModel();
		mapper.serialize(model, System.out, config);
	}
	
	protected static void serializeMap(FormConfig config, FormMapper mapper)
	{
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("test_boolean", true);
		map.put("test_byte", 123);
		map.put("test_short", 12345);
		map.put("test_integer", 12345678);
		map.put("test_long", 123456789L);
		map.put("test_float", 1.2345679f);
		map.put("test_double", 1.2345679d);
		map.put("test_character", 'a');
		map.put("test_string", "test");
		map.put("test_date", new Date());
		map.put("test_calendar", new GregorianCalendar());
		mapper.serialize(map, System.out, config);
	}
	
	protected static void deserializeModel(FormConfig config, FormMapper mapper)
	{
		String data = "null_primitive_boolean=false&null_primitive_byte=0&null_primitive_short=0&null_primitive_integer=0&null_primitive_long=0&null_primitive_float=0.0&null_primitive_double=0.0&null_primitive_character=%00&null_wrapper_boolean=&null_wrapper_byte=&null_wrapper_short=&null_wrapper_integer=&null_wrapper_long=&null_wrapper_float=&null_wrapper_double=&null_wrapper_character=&null_string=&null_date=&null_calendar=&test_boolean=true&test_byte=123&test_short=12345&test_integer=12345678&test_long=123456789&test_float=1.2345679&test_double=1.23456789&test_character=a&test_string=test&test_date=2015-01-12+12%3A15%3A03&test_calendar=2015-01-12+12%3A15%3A03";
		FormModel model = mapper.deserialize(data, FormModel.class, config);
		System.out.println(model);
	}
	
	protected static void deserializeMap(FormConfig config, FormMapper mapper)
	{
		String data = "test_boolean=true&test_byte=123&test_short=12345&test_integer=12345678&test_long=123456789&test_float=1.2345679&test_double=1.2345679&test_character=a&test_string=test&test_date=2015-01-12+14%3A05%3A01&test_calendar=2015-01-12+14%3A05%3A01";
		LinkedHashMap<String, Object> map = mapper.deserialize(data, config);
		System.out.println(map);
	}
	
}
