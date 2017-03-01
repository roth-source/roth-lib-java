package roth.lib.java.test.form;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
public class FormSubModel
{
	@Property(name = "test1")
	protected String test1;
	
	@Property(name = "test2")
	protected String test2;
	
	@Property(name = "test3")
	protected String test3;
	
	public FormSubModel()
	{
		
	}
	
	public FormSubModel(String test1, String test2, String test3)
	{
		super();
		this.test1 = test1;
		this.test2 = test2;
		this.test3 = test3;
	}
	
}
