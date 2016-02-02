package roth.lib.java.jdbc.mysql.sql;

import roth.lib.java.jdbc.sql.On;
import roth.lib.java.jdbc.sql.Select;

@SuppressWarnings("serial")
public class MysqlSelect extends Select implements MysqlSqlFactory
{
	
	public MysqlSelect()
	{
		
	}
	
	public Select from(String name, MysqlIndexHint indexHint)
	{
		return from(newFrom().setIndexHint(indexHint).setName(name));
	}
	
	public Select fromAs(String name, String alias, MysqlIndexHint indexHint)
	{
		return from(newFrom().setIndexHint(indexHint).setName(name).setAlias(alias));
	}
	
	public Select join(String table1, String name1, String table2, String name2, MysqlIndexHint indexHint)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(JOIN).setTable(table1).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Select joinAs(String table1, String alias, String name1, String table2, String name2, MysqlIndexHint indexHint)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(JOIN).setTable(table1).setAlias(alias).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Select join(String table, MysqlIndexHint indexHint, On...ons)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(JOIN).setTable(table).addOns(ons));
	}
	
	public Select joinAs(String table, String alias, MysqlIndexHint indexHint, On...ons)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(JOIN).setTable(table).setAlias(alias).addOns(ons));
	}
	
	public Select joinInner(String table1, String name1, String table2, String name2, MysqlIndexHint indexHint)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(INNER_JOIN).setTable(table1).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Select joinInnerAs(String table1, String alias, String name1, String table2, String name2, MysqlIndexHint indexHint)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(INNER_JOIN).setTable(table1).setAlias(alias).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Select joinInner(String table, MysqlIndexHint indexHint, On...ons)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(INNER_JOIN).setTable(table).addOns(ons));
	}
	
	public Select joinInnerAs(String table, String alias, MysqlIndexHint indexHint, On...ons)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(INNER_JOIN).setTable(table).setAlias(alias).addOns(ons));
	}
	
	public Select joinLeft(String table1, String name1, String table2, String name2, MysqlIndexHint indexHint)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(LEFT_JOIN).setTable(table1).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Select joinLeftAs(String table1, String alias, String name1, String table2, String name2, MysqlIndexHint indexHint)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(LEFT_JOIN).setTable(table1).setAlias(alias).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Select joinLeft(String table, MysqlIndexHint indexHint, On...ons)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(LEFT_JOIN).setTable(table).addOns(ons));
	}
	
	public Select joinLeftAs(String table, String alias, MysqlIndexHint indexHint, On...ons)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(LEFT_JOIN).setTable(table).setAlias(alias).addOns(ons));
	}
	
	public Select joinRight(String table1, String name1, String table2, String name2, MysqlIndexHint indexHint)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(RIGHT_JOIN).setTable(table1).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Select joinRightAs(String table1, String alias, String name1, String table2, String name2, MysqlIndexHint indexHint)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(RIGHT_JOIN).setTable(table1).setAlias(alias).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Select joinRight(String table, MysqlIndexHint indexHint, On...ons)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(RIGHT_JOIN).setTable(table).addOns(ons));
	}
	
	public Select joinRightAs(String table, String alias, MysqlIndexHint indexHint, On...ons)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(RIGHT_JOIN).setTable(table).setAlias(alias).addOns(ons));
	}
	
	public Select joinOuter(String table1, String name1, String table2, String name2, MysqlIndexHint indexHint)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(OUTER_JOIN).setTable(table1).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Select joinOuterAs(String table1, String alias, String name1, String table2, String name2, MysqlIndexHint indexHint)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(OUTER_JOIN).setTable(table1).setAlias(alias).addOns(newOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2)));
	}
	
	public Select joinOuter(String table, MysqlIndexHint indexHint, On...ons)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(OUTER_JOIN).setTable(table).addOns(ons));
	}
	
	public Select joinOuterAs(String table, String alias, MysqlIndexHint indexHint, On...ons)
	{
		return join(newJoin().setIndexHint(indexHint).setJoinType(OUTER_JOIN).setTable(table).setAlias(alias).addOns(ons));
	}
	
}
