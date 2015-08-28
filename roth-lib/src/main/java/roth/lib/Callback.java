package roth.lib;

import java.lang.reflect.Type;

import roth.lib.util.ReflectionUtil;

public abstract class Callback<T>
{
	protected Class<T> klass;
	
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
	
	public boolean isType(Type type)
	{
		Class<?> klass = ReflectionUtil.getTypeClass(type);
		return klass.isAssignableFrom(this.klass);
	}
	
	public abstract void call(T model);
	
}
