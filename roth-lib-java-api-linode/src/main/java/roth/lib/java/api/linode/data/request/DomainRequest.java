package roth.lib.java.api.linode.data.request;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.type.DomainType;
import roth.lib.java.api.linode.data.type.StatusType;

@Entity
@SuppressWarnings("serial")
public abstract class DomainRequest extends LinodeRequest
{
	@Property(name = "Domain")
	protected String domain;
	
	@Property(name = "Description")
	protected String description;
	
	@Property(name = "Type")
	protected String type;
	
	@Property(name = "SOA_Email")
	protected String soaEmail;
	
	@Property(name = "Refresh_sec")
	protected Integer refreshSec;
	
	@Property(name = "Retry_sec")
	protected Integer retrySec;
	
	@Property(name = "Expire_sec")
	protected Integer expireSec;
	
	@Property(name = "TTL_sec")
	protected Integer ttlSec;
	
	@Property(name = "lpm_displayGroup")
	protected String lpmDisplayGroup;
	
	@Property(name = "status")
	protected Integer status;
	
	@Property(name = "master_ips")
	protected String masterIps;
	
	@Property(name = "axfr_ips")
	protected String axfrIps;
	
	public DomainRequest()
	{
		
	}
	
	public String getDomain()
	{
		return domain;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getSoaEmail()
	{
		return soaEmail;
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
	
	public String getLpmDisplayGroup()
	{
		return lpmDisplayGroup;
	}
	
	public Integer getStatus()
	{
		return status;
	}
	
	public String getMasterIps()
	{
		return masterIps;
	}
	
	public String getAxfrIps()
	{
		return axfrIps;
	}
	
	public DomainRequest setDomain(String domain)
	{
		this.domain = domain;
		return this;
	}
	
	public DomainRequest setDescription(String description)
	{
		this.description = description;
		return this;
	}
	
	public DomainRequest setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public DomainRequest setSoaEmail(String soaEmail)
	{
		this.soaEmail = soaEmail;
		return this;
	}
	
	public DomainRequest setRefreshSec(Integer refreshSec)
	{
		this.refreshSec = refreshSec;
		return this;
	}
	
	public DomainRequest setRetrySec(Integer retrySec)
	{
		this.retrySec = retrySec;
		return this;
	}
	
	public DomainRequest setExpireSec(Integer expireSec)
	{
		this.expireSec = expireSec;
		return this;
	}
	
	public DomainRequest setTtlSec(Integer ttlSec)
	{
		this.ttlSec = ttlSec;
		return this;
	}
	
	public DomainRequest setLpmDisplayGroup(String lpmDisplayGroup)
	{
		this.lpmDisplayGroup = lpmDisplayGroup;
		return this;
	}
	
	public DomainRequest setStatus(Integer status)
	{
		this.status = status;
		return this;
	}
	
	public DomainRequest setMasterIps(String masterIps)
	{
		this.masterIps = masterIps;
		return this;
	}
	
	public DomainRequest setAxfrIps(String axfrIps)
	{
		this.axfrIps = axfrIps;
		return this;
	}
	
	public DomainRequest setType(DomainType domainType)
	{
		this.type = domainType.get();
		return this;
	}
	
	public DomainRequest setStatus(StatusType statusType)
	{
		this.status = statusType.get();
		return this;
	}
	
}
