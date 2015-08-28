package roth.lib.api.cloudflare.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class ZoneProps implements Serializable
{
	@Property(name = "dns_cname")
	protected Integer dnsCname;
	
	@Property(name = "dns_partner")
	protected Integer dnsPartner;
	
	@Property(name = "dns_anon_partner")
	protected Integer dnsAnonPartner;
	
	@Property(name = "pro")
	protected Integer pro;
	
	@Property(name = "expired_pro")
	protected Integer expiredPro;
	
	@Property(name = "pro_sub")
	protected Integer proSub;
	
	@Property(name = "ssl")
	protected Integer ssl;
	
	@Property(name = "expired_ssl")
	protected Integer expiredSsl;
	
	@Property(name = "expired_rs_pro")
	protected Integer expiredRsPro;
	
	@Property(name = "reseller_pro")
	protected Integer resellerPro;
	
	@Property(name = "force_interal")
	protected Integer forceInteral;
	
	@Property(name = "ssl_needed")
	protected Integer sslNeeded;
	
	@Property(name = "alexa_rank")
	protected Integer alexaRank;
	
	public ZoneProps()
	{
		
	}
	
	public Integer getDnsCname()
	{
		return dnsCname;
	}
	
	public Integer getDnsPartner()
	{
		return dnsPartner;
	}
	
	public Integer getDnsAnonPartner()
	{
		return dnsAnonPartner;
	}
	
	public Integer getPro()
	{
		return pro;
	}
	
	public Integer getExpiredPro()
	{
		return expiredPro;
	}
	
	public Integer getProSub()
	{
		return proSub;
	}
	
	public Integer getSsl()
	{
		return ssl;
	}
	
	public Integer getExpiredSsl()
	{
		return expiredSsl;
	}
	
	public Integer getExpiredRsPro()
	{
		return expiredRsPro;
	}
	
	public Integer getResellerPro()
	{
		return resellerPro;
	}
	
	public Integer getForceInteral()
	{
		return forceInteral;
	}
	
	public Integer getSslNeeded()
	{
		return sslNeeded;
	}
	
	public Integer getAlexaRank()
	{
		return alexaRank;
	}
	
	public ZoneProps setDnsCname(Integer dnsCname)
	{
		this.dnsCname = dnsCname;
		return this;
	}
	
	public ZoneProps setDnsPartner(Integer dnsPartner)
	{
		this.dnsPartner = dnsPartner;
		return this;
	}
	
	public ZoneProps setDnsAnonPartner(Integer dnsAnonPartner)
	{
		this.dnsAnonPartner = dnsAnonPartner;
		return this;
	}
	
	public ZoneProps setPro(Integer pro)
	{
		this.pro = pro;
		return this;
	}
	
	public ZoneProps setExpiredPro(Integer expiredPro)
	{
		this.expiredPro = expiredPro;
		return this;
	}
	
	public ZoneProps setProSub(Integer proSub)
	{
		this.proSub = proSub;
		return this;
	}
	
	public ZoneProps setSsl(Integer ssl)
	{
		this.ssl = ssl;
		return this;
	}
	
	public ZoneProps setExpiredSsl(Integer expiredSsl)
	{
		this.expiredSsl = expiredSsl;
		return this;
	}
	
	public ZoneProps setExpiredRsPro(Integer expiredRsPro)
	{
		this.expiredRsPro = expiredRsPro;
		return this;
	}
	
	public ZoneProps setResellerPro(Integer resellerPro)
	{
		this.resellerPro = resellerPro;
		return this;
	}
	
	public ZoneProps setForceInteral(Integer forceInteral)
	{
		this.forceInteral = forceInteral;
		return this;
	}
	
	public ZoneProps setSslNeeded(Integer sslNeeded)
	{
		this.sslNeeded = sslNeeded;
		return this;
	}
	
	public ZoneProps setAlexaRank(Integer alexaRank)
	{
		this.alexaRank = alexaRank;
		return this;
	}
	
}
