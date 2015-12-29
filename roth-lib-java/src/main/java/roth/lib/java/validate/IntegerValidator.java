package roth.lib.java.validate;

import roth.lib.java.reflector.EntityReflector;

public abstract class IntegerValidator implements Validator
{
	
	@Override
	public void validate(Object value, Object request, EntityReflector entityReflector) throws ValidatorException
	{
		boolean valid = false;
		if(value instanceof Integer)
		{
			valid = isValid((Integer) value);
		}
		if(!valid)
		{
			throw new ValidatorException();
		}
	}
	
	public abstract boolean isValid(int value);
	
}
