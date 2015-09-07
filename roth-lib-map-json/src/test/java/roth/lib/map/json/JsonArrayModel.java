package roth.lib.map.json;

import java.util.Arrays;
import java.util.LinkedList;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
public class JsonArrayModel
{
	
	@Property(name = "test_string_array")
	private String[] testStringArray = new String[]{"one", "two", "three"};
	
	@Property(name = "test_string_list")
	private LinkedList<String> testStringList = new LinkedList<String>(Arrays.asList(new String[]{"one", "two", "three"}));
	
	public JsonArrayModel()
	{
		
	}
	
}
