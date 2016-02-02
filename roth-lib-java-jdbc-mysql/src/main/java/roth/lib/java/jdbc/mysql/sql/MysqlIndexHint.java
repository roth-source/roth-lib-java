package roth.lib.java.jdbc.mysql.sql;

import java.util.Arrays;

import roth.lib.java.jdbc.sql.Sql;
import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class MysqlIndexHint extends Sql implements MysqlSqlFactory
{
	protected String indexHintType = FORCE;
	protected List<String> names = new List<String>();
	
	protected MysqlIndexHint()
	{
		
	}
	
	public MysqlIndexHint setIndexHintType(String indexHintType)
	{
		this.indexHintType = indexHintType;
		return this;
	}
	
	public MysqlIndexHint setNames(List<String> names)
	{
		this.names = names;
		return this;
	}
	
	public MysqlIndexHint addNames(String...names)
	{
		this.names.addAll(Arrays.asList(names));
		return this;
	}
	
	public static MysqlIndexHint force(String...names)
	{
		return new MysqlIndexHint().setIndexHintType(FORCE).addNames(names);
	}
	
	public static MysqlIndexHint use(String...names)
	{
		return new MysqlIndexHint().setIndexHintType(USE).addNames(names);
	}
	
	public static MysqlIndexHint ignore(String...names)
	{
		return new MysqlIndexHint().setIndexHintType(IGNORE).addNames(names);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(indexHintType);
		builder.append(INDEX);
		builder.append("(");
		builder.append(tick(names));
		builder.append(")");
		return builder.toString();
	}
	
}
