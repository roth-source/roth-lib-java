package roth.lib.java.api.linode.data.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Domain implements Serializable
{
	@Property(name = "DOMAINID")
	protected Integer domainId;
	
	@Property(name = "DESCRIPTION")
	protected String description;
	
	@Property(name = "TYPE")
	protected String type;
	
	@Property(name = "STATUS")
	protected Integer status;
	
	@Property(name = "SOA_EMAIL")
	protected String soaEmail;
	
	@Property(name = "DOMAIN")
	protected String domain;
	
	@Property(name = "REFRESH_SEC")
	protected Integer refreshSec;
	
	@Property(name = "RETRY_SEC")
	protected Integer retrySec;
	
	@Property(name = "EXPIRE_SEC")
	protected Integer expireSec;
	
	@Property(name = "TTL_SEC")
	protected Integer ttlSec;
	
	@Property(name = "MASTER_IPS")
	protected String masterIps;
	
	public Domain()
	{
		
	}
	
	public Integer getDomainId()
	{
		return domainId;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getType()
	{
		return type;
	}
	
	public Integer getStatus()
	{
		return status;
	}
	
	public String getSoaEmail()
	{
		return soaEmail;
	}
	
	public String getDomain()
	{
		return domain;
	}
	
	public Integer getRefreshSec()
	{
		return refreshSec;
	}
	
	public Integer getRetrySec()
	{
		return retrySec;
	}
	
	public Integer getExpireSec()
	{
		return expireSec;
	}
	
	public Integer getTtlSec()
	{
		return ttlSec;
	}
	
	public String getMasterIps()
	{
		return masterIps;
	}
	
	public Domain setDomainId(Integer domainId)
	{
		this.domainId = domainId;
		return this;
	}
	
	public Domain setDescription(String description)
	{
		this.description = description;
		return this;
	}
	
	public Domain setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public Domain setStatus(Integer status)
	{
		this.status = status;
		return this;
	}
	
	public Domain setSoaEmail(String soaEmail)
	{
		this.soaEmail = soaEmail;
		return this;
	}
	
	public Domain setDomain(String domain)
	{
		this.domain = domain;
		return this;
	}
	
	public Domain setRefreshSec(Integer refreshSec)
	{
		this.refreshSec = refreshSec;
		return this;
	}
	
	public Domain setRetrySec(Integer retrySec)
	{
		this.retrySec = retrySec;
		return this;
	}
	
	public Domain setExpireSec(Integer expireSec)
	{
		this.expireSec = expireSec;
		return this;
	}
	
	public Domain setTtlSec(Integer ttlSec)
	{
		this.ttlSec = ttlSec;
		return this;
	}
	
	public Domain setMasterIps(String masterIps)
	{
		this.masterIps = masterIps;
		return this;
	}
	
}
