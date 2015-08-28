package roth.lib.template.test;

public class TestModel
{
	protected String test1;
	protected int test2;
	protected boolean test3;
	
	public TestModel()
	{
		this.test1 = "<b>test1</b>";
		this.test2 = 123;
		this.test3 = true;
	}
	
	public String getTest1()
	{
		return test1;
	}
	
	public int getTest2()
	{
		return test2;
	}
	
	public boolean isTest3()
	{
		return test3;
	}
	
	public void setTest1(String test1)
	{
		this.test1 = test1;
	}
	
	public void setTest2(int test2)
	{
		this.test2 = test2;
	}
	
	public void setTest3(boolean test3)
	{
		this.test3 = test3;
	}
	
}
