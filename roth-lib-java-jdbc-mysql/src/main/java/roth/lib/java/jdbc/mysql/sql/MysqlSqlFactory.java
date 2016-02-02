package roth.lib.java.jdbc.mysql.sql;

import roth.lib.java.jdbc.sql.SqlFactory;

public interface MysqlSqlFactory extends SqlFactory
{
	String FORCE		= " FORCE";
	String USE		= " USE";
	String IGNORE  	= " IGNORE";
	String INDEX 	= " INDEX ";
	
	@Override
	default MysqlSelect newSelect()
	{
		return new MysqlSelect();
	}
	
	@Override
	default MysqlColumns newColumns()
	{
		return new MysqlColumns();
	}
	
	@Override
	default MysqlColumn newColumn()
	{
		return new MysqlColumn();
	}
	
	@Override
	default MysqlFrom newFrom()
	{
		return new MysqlFrom();
	}
	
	@Override
	default MysqlJoins newJoins()
	{
		return new MysqlJoins();
	}
	
	@Override
	default MysqlJoin newJoin()
	{
		return new MysqlJoin();
	}
	
	@Override
	default MysqlOn newOn()
	{
		return new MysqlOn();
	}
	
	@Override
	default MysqlWheres newWheres()
	{
		return new MysqlWheres();
	}
	
	@Override
	default MysqlWhere newWhere()
	{
		return new MysqlWhere();
	}
	
	@Override
	default MysqlGroups newGroups()
	{
		return new MysqlGroups();
	}
	
	@Override
	default MysqlGroup newGroup()
	{
		return new MysqlGroup();
	}
	
	@Override
	default MysqlHavings newHavings()
	{
		return new MysqlHavings();
	}
	
	@Override
	default MysqlHaving newHaving()
	{
		return new MysqlHaving();
	}
	
	@Override
	default MysqlOrders newOrders()
	{
		return new MysqlOrders();
	}
	
	@Override
	default MysqlOrder newOrder()
	{
		return new MysqlOrder();
	}
	
	@Override
	default MysqlPage newPage()
	{
		return new MysqlPage();
	}
	
	@Override
	default MysqlInsert newInsert()
	{
		return new MysqlInsert();
	}
	
	@Override
	default MysqlUpdate newUpdate()
	{
		return new MysqlUpdate();
	}
	
	@Override
	default MysqlDelete newDelete()
	{
		return new MysqlDelete();
	}
	
	@Override
	default MysqlSet newSet()
	{
		return new MysqlSet();
	}
	
}
