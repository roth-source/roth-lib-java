package roth.lib.java.test.table;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.json.JsonMapper;

@Entity
@SuppressWarnings("serial")
public class TableModel implements Serializable
{
	@Property(name = "TransactionDateTime")
	protected String transactionDateTime;
	
	@Property(name = "account_num")
	protected String accountNum;
	
	@Property(name = "Trans_Id")
	protected String transId;
	
	@Property(name = "att_num")
	protected String attNum;
	
	@Property(name = "TransactionType")
	protected String transactionType;
	
	@Property(name = "Amount")
	protected String amount;
	
	@Property(name = "NetAmount")
	protected String netAmount;
	
	@Property(name = "inv_num")
	protected String invNum;
	
	@Property(name = "Details")
	protected String details;
	
	@Property(name = "AchReturnCode")
	protected String achReturnCode;

	@Property(name = "ReturnCodeDescription")
	protected String returnCodeDescription;
	
	@Property(name = "ChargeBackResponeCode")
	protected String chargeBackResponeCode;
	
	@Property(name = "ResponeCodeDescription")
	protected String responeCodeDescription;
	
	public TableModel()
	{
		
	}
	
	public TableModel(String transactionDateTime, String accountNum, String transId, String attNum, String transactionType, String amount, String netAmount, String invNum, String details, String achReturnCode, String returnCodeDescription, String chargeBackResponeCode, String responeCodeDescription)
	{
		this.transactionDateTime = transactionDateTime;
		this.accountNum = accountNum;
		this.transId = transId;
		this.attNum = attNum;
		this.transactionType = transactionType;
		this.amount = amount;
		this.netAmount = netAmount;
		this.invNum = invNum;
		this.details = details;
		this.achReturnCode = achReturnCode;
		this.returnCodeDescription = returnCodeDescription;
		this.chargeBackResponeCode = chargeBackResponeCode;
		this.responeCodeDescription = responeCodeDescription;
	}
	
	public String getTransactionDateTime()
	{
		return transactionDateTime;
	}
	
	public String getAccountNum()
	{
		return accountNum;
	}
	
	public String getTransId()
	{
		return transId;
	}
	
	public String getAttNum()
	{
		return attNum;
	}
	
	public String getTransactionType()
	{
		return transactionType;
	}
	
	public String getAmount()
	{
		return amount;
	}
	
	public String getNetAmount()
	{
		return netAmount;
	}
	
	public String getInvNum()
	{
		return invNum;
	}
	
	public String getDetails()
	{
		return details;
	}
	
	public String getAchReturnCode()
	{
		return achReturnCode;
	}
	
	public String getReturnCodeDescription()
	{
		return returnCodeDescription;
	}
	
	public String getChargeBackResponeCode()
	{
		return chargeBackResponeCode;
	}
	
	public String getResponeCodeDescription()
	{
		return responeCodeDescription;
	}
	
	public TableModel setTransactionDateTime(String transactionDateTime)
	{
		this.transactionDateTime = transactionDateTime;
		return this;
	}
	
	public TableModel setAccountNum(String accountNum)
	{
		this.accountNum = accountNum;
		return this;
	}
	
	public TableModel setTransId(String transId)
	{
		this.transId = transId;
		return this;
	}
	
	public TableModel setAttNum(String attNum)
	{
		this.attNum = attNum;
		return this;
	}
	
	public TableModel setTransactionType(String transactionType)
	{
		this.transactionType = transactionType;
		return this;
	}
	
	public TableModel setAmount(String amount)
	{
		this.amount = amount;
		return this;
	}
	
	public TableModel setNetAmount(String netAmount)
	{
		this.netAmount = netAmount;
		return this;
	}
	
	public TableModel setInvNum(String invNum)
	{
		this.invNum = invNum;
		return this;
	}
	
	public TableModel setDetails(String details)
	{
		this.details = details;
		return this;
	}
	
	public TableModel setAchReturnCode(String achReturnCode)
	{
		this.achReturnCode = achReturnCode;
		return this;
	}
	
	public TableModel setReturnCodeDescription(String returnCodeDescription)
	{
		this.returnCodeDescription = returnCodeDescription;
		return this;
	}
	
	public TableModel setChargeBackResponeCode(String chargeBackResponeCode)
	{
		this.chargeBackResponeCode = chargeBackResponeCode;
		return this;
	}
	
	public TableModel setResponeCodeDescription(String responeCodeDescription)
	{
		this.responeCodeDescription = responeCodeDescription;
		return this;
	}
	
	@Override
	public String toString()
	{
		return new JsonMapper().setPrettyPrint(true).serialize(this);
	}
	
}
