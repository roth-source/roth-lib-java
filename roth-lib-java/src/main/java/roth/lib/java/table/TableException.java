package roth.lib.java.table;

@SuppressWarnings("serial")
public class TableException extends RuntimeException
{
	protected static final String ROW_COLUMN_MESSAGE = "row %d column %s : %s";
	
	public TableException(String message)
	{
		super(message);
	}
	
	public TableException(Throwable cause)
	{
		super(cause);
	}
	
	public TableException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public TableException(int row, int column, Throwable cause)
	{
		super(String.format(ROW_COLUMN_MESSAGE, row, column, cause.getMessage()), cause);
	}
	
}
