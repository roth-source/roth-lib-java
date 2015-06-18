package roth.lib.api.rackspace.data.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Endpoint implements Serializable
{
	@Property(name = "region")
	protected String region;
	
	@Property(name = "tenantId")
	protected String tenantId;
	
	@Property(name = "publicURL")
	protected String publicUrl;
	
	@Property(name = "internalURL")
	protected String internalUrl;
	
	@Property(name = "versionInfo")
	protected String versionInfo;
	
	@Property(name = "versionList")
	protected String versionList;
	
	@Property(name = "versionId")
	protected String versionId;
	
	public Endpoint()
	{
		
	}
	
	public String getRegion()
	{
		return region;
	}
	
	public String getTenantId()
	{
		return tenantId;
	}
	
	public String getPublicUrl()
	{
		return publicUrl;
	}
	
	public String getInternalUrl()
	{
		return internalUrl;
	}
	
	public String getVersionInfo()
	{
		return versionInfo;
	}
	
	public String getVersionList()
	{
		return versionList;
	}
	
	public String getVersionId()
	{
		return versionId;
	}
	
	public Endpoint setRegion(String region)
	{
		this.region = region;
		return this;
	}
	
	public Endpoint setTenantId(String tenantId)
	{
		this.tenantId = tenantId;
		return this;
	}
	
	public Endpoint setPublicUrl(String publicUrl)
	{
		this.publicUrl = publicUrl;
		return this;
	}
	
	public Endpoint setInternalUrl(String internalUrl)
	{
		this.internalUrl = internalUrl;
		return this;
	}
	
	public Endpoint setVersionInfo(String versionInfo)
	{
		this.versionInfo = versionInfo;
		return this;
	}
	
	public Endpoint setVersionList(String versionList)
	{
		this.versionList = versionList;
		return this;
	}
	
	public Endpoint setVersionId(String versionId)
	{
		this.versionId = versionId;
		return this;
	}
	
}
