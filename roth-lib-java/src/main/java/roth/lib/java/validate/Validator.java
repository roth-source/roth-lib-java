package roth.lib.java.validate;

import roth.lib.java.reflector.EntityReflector;

public interface Validator
{
	public boolean isValid(Object value, Object request, EntityReflector entityReflector);
	
}
