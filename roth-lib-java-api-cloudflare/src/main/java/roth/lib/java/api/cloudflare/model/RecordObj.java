package roth.lib.java.api.cloudflare.model;

import java.io.Serializable;
import java.util.Calendar;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class RecordObj implements Serializable
{
	@Property(name = "rec_id")
	protected String recId;
	
	@Property(name = "rec_tag")
	protected String recTag;
	
	@Property(name = "zone_name")
	protected String zoneName;
	
	@Property(name = "name")
	protected String name;
	
	@Property(name = "display_name")
	protected String displayName;
	
	@Property(name = "type")
	protected String type;
	
	@Property(name = "prio")
	protected String prio;
	
	@Property(name = "content")
	protected String content;
	
	@Property(name = "display_content")
	protected String displayContent;
	
	@Property(name = "ttl")
	protected String ttl;
	
	@Property(name = "ttl_ceil")
	protected Integer ttlCeil;
	
	@Property(name = "ssl_id")
	protected String sslId;
	
	@Property(name = "ssl_status")
	protected String sslStatus;
	
	@Property(name = "ssl_expires_on")
	protected Calendar sslExpiresOn;
	
	@Property(name = "auto_ttl")
	protected Integer autoTtl;
	
	@Property(name = "service_mode")
	protected String serviceMode;
	
	@Property(name = "props")
	protected RecordProps props;
	
	public RecordObj()
	{
		
	}
	
	public String getRecId()
	{
		return recId;
	}
	
	public String getRecTag()
	{
		return recTag;
	}
	
	public String getZoneName()
	{
		return zoneName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDisplayName()
	{
		return displayName;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getPrio()
	{
		return prio;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public String getDisplayContent()
	{
		return displayContent;
	}
	
	public String getTtl()
	{
		return ttl;
	}
	
	public Integer getTtlCeil()
	{
		return ttlCeil;
	}
	
	public String getSslId()
	{
		return sslId;
	}
	
	public String getSslStatus()
	{
		return sslStatus;
	}
	
	public Calendar getSslExpiresOn()
	{
		return sslExpiresOn;
	}
	
	public Integer getAutoTtl()
	{
		return autoTtl;
	}
	
	public String getServiceMode()
	{
		return serviceMode;
	}
	
	public RecordProps getProps()
	{
		return props;
	}
	
	public RecordObj setRecId(String recId)
	{
		this.recId = recId;
		return this;
	}
	
	public RecordObj setRecTag(String recTag)
	{
		this.recTag = recTag;
		return this;
	}
	
	public RecordObj setZoneName(String zoneName)
	{
		this.zoneName = zoneName;
		return this;
	}
	
	public RecordObj setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public RecordObj setDisplayName(String displayName)
	{
		this.displayName = displayName;
		return this;
	}
	
	public RecordObj setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public RecordObj setPrio(String prio)
	{
		this.prio = prio;
		return this;
	}
	
	public RecordObj setContent(String content)
	{
		this.content = content;
		return this;
	}
	
	public RecordObj setDisplayContent(String displayContent)
	{
		this.displayContent = displayContent;
		return this;
	}
	
	public RecordObj setTtl(String ttl)
	{
		this.ttl = ttl;
		return this;
	}
	
	public RecordObj setTtlCeil(Integer ttlCeil)
	{
		this.ttlCeil = ttlCeil;
		return this;
	}
	
	public RecordObj setSslId(String sslId)
	{
		this.sslId = sslId;
		return this;
	}
	
	public RecordObj setSslStatus(String sslStatus)
	{
		this.sslStatus = sslStatus;
		return this;
	}
	
	public RecordObj setSslExpiresOn(Calendar sslExpiresOn)
	{
		this.sslExpiresOn = sslExpiresOn;
		return this;
	}
	
	public RecordObj setAutoTtl(Integer autoTtl)
	{
		this.autoTtl = autoTtl;
		return this;
	}
	
	public RecordObj setServiceMode(String serviceMode)
	{
		this.serviceMode = serviceMode;
		return this;
	}
	
	public RecordObj setProps(RecordProps props)
	{
		this.props = props;
		return this;
	}
	
}
