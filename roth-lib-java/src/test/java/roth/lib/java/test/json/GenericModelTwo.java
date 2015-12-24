package roth.lib.java.test.json;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
public class GenericModelTwo<A, B>
{
	@Property(name = "value")
	protected String value;
	
	@Property(name = "model1")
	protected A model1;
	
	@Property(name = "model2")
	protected B model2;
	
	public GenericModelTwo()
	{
		
	}
	
	public String getValue()
	{
		return value;
	}
	
	public A getModel1()
	{
		return model1;
	}
	
	public B getModel2()
	{
		return model2;
	}
	
	public GenericModelTwo<A, B> setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	public GenericModelTwo<A, B> setModel1(A model1)
	{
		this.model1 = model1;
		return this;
	}
	
	public GenericModelTwo<A, B> setModel2(B model2)
	{
		this.model2 = model2;
		return this;
	}
	
}
