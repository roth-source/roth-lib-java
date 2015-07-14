package roth.lib;

public abstract class Callback<T>
{
	protected Class<T> klass;
	
	public Callback()
	{
		
	}
	
	public Callback(Class<T> klass)
	{
		this.klass = klass;
	}
	
	public Class<T> getKlass()
	{
		return klass;
	}
	
	public Callback<T> setKlass(Class<T> klass)
	{
		this.klass = klass;
		return this;
	}
	
	public abstract void call(T model);
	
}
