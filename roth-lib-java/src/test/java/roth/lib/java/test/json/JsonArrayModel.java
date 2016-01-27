package roth.lib.java.test.json;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.lang.List;

@Entity
public class JsonArrayModel
{
	
	@Property(name = "test_string_array")
	private String[] testStringArray = {"one", "two", "three"};
	
	@Property(name = "test_string_list")
	private List<String> testStringList = List.fromArray("one", "two", "three");
	
	public JsonArrayModel()
	{
		
	}
	
}
