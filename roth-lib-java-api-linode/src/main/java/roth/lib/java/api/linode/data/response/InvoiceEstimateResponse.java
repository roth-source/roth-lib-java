package roth.lib.java.api.linode.data.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class InvoiceEstimateResponse implements Serializable
{
	@Property(name = "INVOICE_TO")
	protected Calendar invoiceTo;
	
	@Property(name = "PRICE")
	protected BigDecimal price;
	
	public InvoiceEstimateResponse()
	{
		
	}
	
	public Calendar getInvoiceTo()
	{
		return invoiceTo;
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	
	public InvoiceEstimateResponse setInvoiceTo(Calendar invoiceTo)
	{
		this.invoiceTo = invoiceTo;
		return this;
	}
	
	public InvoiceEstimateResponse setPrice(BigDecimal price)
	{
		this.price = price;
		return this;
	}
	
}
