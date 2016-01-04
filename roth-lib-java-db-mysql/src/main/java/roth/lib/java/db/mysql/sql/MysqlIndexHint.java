package roth.lib.java.db.mysql.sql;

import java.util.Arrays;
import java.util.LinkedList;

import roth.lib.java.db.sql.Sql;

@SuppressWarnings("serial")
public class MysqlIndexHint extends Sql implements MysqlSqlFactory
{
	protected String indexHintType = FORCE;
	protected LinkedList<String> names = new LinkedList<String>();
	
	protected MysqlIndexHint()
	{
		
	}
	
	public MysqlIndexHint setIndexHintType(String indexHintType)
	{
		this.indexHintType = indexHintType;
		return this;
	}
	
	public MysqlIndexHint setNames(LinkedList<String> names)
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
