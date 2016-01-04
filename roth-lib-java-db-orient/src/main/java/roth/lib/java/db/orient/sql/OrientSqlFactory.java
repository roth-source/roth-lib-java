package roth.lib.java.db.orient.sql;

import roth.lib.java.db.sql.SqlFactory;

public interface OrientSqlFactory extends SqlFactory
{
	
	@Override
	default OrientSelect newSelect()
	{
		return new OrientSelect();
	}
	
	@Override
	default OrientColumns newColumns()
	{
		return new OrientColumns();
	}
	
	@Override
	default OrientColumn newColumn()
	{
		return new OrientColumn();
	}
	
	@Override
	default OrientFrom newFrom()
	{
		return new OrientFrom();
	}
	
	@Override
	default OrientJoins newJoins()
	{
		return new OrientJoins();
	}
	
	@Override
	default OrientJoin newJoin()
	{
		return new OrientJoin();
	}
	
	@Override
	default OrientOn newOn()
	{
		return new OrientOn();
	}
	
	@Override
	default OrientWheres newWheres()
	{
		return new OrientWheres();
	}
	
	@Override
	default OrientWhere newWhere()
	{
		return new OrientWhere();
	}
	
	@Override
	default OrientGroups newGroups()
	{
		return new OrientGroups();
	}
	
	@Override
	default OrientGroup newGroup()
	{
		return new OrientGroup();
	}
	
	@Override
	default OrientHavings newHavings()
	{
		return new OrientHavings();
	}
	
	@Override
	default OrientHaving newHaving()
	{
		return new OrientHaving();
	}
	
	@Override
	default OrientOrders newOrders()
	{
		return new OrientOrders();
	}
	
	@Override
	default OrientOrder newOrder()
	{
		return new OrientOrder();
	}
	
	@Override
	default OrientPage newPage()
	{
		return new OrientPage();
	}
	
	@Override
	default OrientInsert newInsert()
	{
		return new OrientInsert();
	}
	
	@Override
	default OrientUpdate newUpdate()
	{
		return new OrientUpdate();
	}
	
	@Override
	default OrientDelete newDelete()
	{
		return new OrientDelete();
	}
	
	@Override
	default OrientSet newSet()
	{
		return new OrientSet();
	}
	
}
