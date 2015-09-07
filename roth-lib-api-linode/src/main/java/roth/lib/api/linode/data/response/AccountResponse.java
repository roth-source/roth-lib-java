package roth.lib.api.linode.data.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class AccountResponse implements Serializable
{
	@Property(name = "ACTIVE_SINCE")
	protected Calendar activeSince;
	
	@Property(name = "TRANSFER_POOL")
	protected Integer transferPool;
	
	@Property(name = "TRANSFER_USED")
	protected Integer transferUsed;
	
	@Property(name = "TRANSFER_BILLABLE")
	protected Integer transferBillable;
	
	@Property(name = "MANAGED")
	protected Boolean managed;
	
	@Property(name = "BALANCE")
	protected BigDecimal balance;
	
	public AccountResponse()
	{
		
	}
	
	public Calendar getActiveSince()
	{
		return activeSince;
	}
	
	public Integer getTransferPool()
	{
		return transferPool;
	}
	
	public Integer getTransferUsed()
	{
		return transferUsed;
	}
	
	public Integer getTransferBillable()
	{
		return transferBillable;
	}
	
	public Boolean getManaged()
	{
		return managed;
	}
	
	public BigDecimal getBalance()
	{
		return balance;
	}
	
	public AccountResponse setActiveSince(Calendar activeSince)
	{
		this.activeSince = activeSince;
		return this;
	}
	
	public AccountResponse setTransferPool(Integer transferPool)
	{
		this.transferPool = transferPool;
		return this;
	}
	
	public AccountResponse setTransferUsed(Integer transferUsed)
	{
		this.transferUsed = transferUsed;
		return this;
	}
	
	public AccountResponse setTransferBillable(Integer transferBillable)
	{
		this.transferBillable = transferBillable;
		return this;
	}
	
	public AccountResponse setManaged(Boolean managed)
	{
		this.managed = managed;
		return this;
	}
	
	public AccountResponse setBalance(BigDecimal balance)
	{
		this.balance = balance;
		return this;
	}
	
}
