package roth.lib.java.test.table;

import java.io.File;

import roth.lib.java.Characters;
import roth.lib.java.lang.List;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.table.TableMapper;
import roth.lib.java.time.Time;

public class TableTest implements Characters
{
	
	public static void main(String[] args) throws Exception
	{
		//serialize();
		//deserialize();
		//deserializeFile();
		//missingColumns();
		deserializeList();
	}
	
	protected static void serialize() throws Exception
	{
		List<TableModel> tableModels = new List<>();
		for(int i = 1; i < 10; i++)
		{
			tableModels.add(new TableModel
			(
				new Time(), 
				"accountNum" + i, 
				"transId" + i, 
				"attNum" + i, 
				"transactionType" + i, 
				12345,
				12345,
				"invNum" + i, 
				"details" + i, 
				"achReturnCode" + i, 
				"returnCodeDescription" + i, 
				"chargeBackResponeCode" + i, 
				"responeCodeDescription" + i
			));
		}
		new TableMapper().serialize(tableModels, System.out);
	}
	
	protected static void deserialize() throws Exception
	{
		/*
		StringBuilder builder = new StringBuilder();
		builder.append(" TransactionDateTime,account_num,Trans_Id,att_num,TransactionType,Amount,NetAmount,inv_num,Details,AchReturnCode,ReturnCodeDescription,ChargeBackResponeCode,ResponeCodeDescription\r\n");
		builder.append(" transactionDateTime1,accountNum1,transId1,attNum1,transactionType1,amount1,netAmount1,invNum1,details1,achReturnCode1,returnCodeDescription1,chargeBackResponeCode1,responeCodeDescription1\r\n");
		builder.append(" transactionDateTime2,accountNum2,transId2,attNum2,transactionType2,amount2,netAmount2,invNum2,details2,achReturnCode2,returnCodeDescription2,chargeBackResponeCode2,responeCodeDescription2\r\n");
		builder.append(" transactionDateTime3,accountNum3,transId3,attNum3,transactionType3,amount3,netAmount3,invNum3,details3,achReturnCode3,returnCodeDescription3,chargeBackResponeCode3,responeCodeDescription3\r\n");
		builder.append(" transactionDateTime4,accountNum4,transId4,attNum4,transactionType4,amount4,netAmount4,invNum4,details4,achReturnCode4,returnCodeDescription4,chargeBackResponeCode4,responeCodeDescription4\r\n");
		builder.append(" transactionDateTime5,accountNum5,transId5,attNum5,transactionType5,amount5,netAmount5,invNum5,details5,achReturnCode5,returnCodeDescription5,chargeBackResponeCode5,responeCodeDescription5\r\n");
		builder.append(" transactionDateTime6,accountNum6,transId6,attNum6,transactionType6,amount6,netAmount6,invNum6,details6,achReturnCode6,returnCodeDescription6,chargeBackResponeCode6,responeCodeDescription6\r\n");
		builder.append(" transactionDateTime7,accountNum7,transId7,attNum7,transactionType7,amount7,netAmount7,invNum7,details7,achReturnCode7,returnCodeDescription7,chargeBackResponeCode7,responeCodeDescription7\r\n");
		builder.append(" transactionDateTime8,accountNum8,transId8,attNum8,transactionType8,amount8,netAmount8,invNum8,details8,achReturnCode8,returnCodeDescription8,chargeBackResponeCode8,responeCodeDescription8\r\n");
		builder.append(" \"transaction,DateTime9\",accountNum9,transId9,attNum9,transactionType9,amount9,netAmount9,invNum9,details9,achReturnCode9,returnCodeDescription9,chargeBackResponeCode9,responeCodeDescription9\r\n");
		System.out.println(new TableMapper().deserializeList(builder.toString(), TableModel.class));
		*/
		StringBuilder builder = new StringBuilder();
		builder.append("Value1,Value2\r");
		builder.append("123,test1\r");
		builder.append("456,test2");
		System.out.println(new TableMapper().deserializeList(builder.toString(), TableModel2.class));
	}
	
	protected static void deserializeFile() throws Exception
	{
		System.out.println(new TableMapper(new MapperConfig().setDeserializeColumnOrder(true)).deserializeList(new File("dev/test.csv"), TableModel.class));
	}
	
	protected static void missingColumns() throws Exception
	{
		System.out.println(new TableMapper().missingColumns(new File("dev/test2.csv"), TableModel.class));
	}
	
	protected static void deserializeList() throws Exception
	{
		List<List<String>> rows = new TableMapper(new MapperConfig().setTableHeader(true).setDeserializeColumnOrder(true)).deserializeList(new File("dev/testList.csv"));
		for(List<String> row : rows)
		{
			//System.out.println(row);
			System.out.println();
			for(String value : row)
			{
				System.out.println(value != null ? value.replaceAll("\n", "") : "");
			}
		}
	}
	
}
