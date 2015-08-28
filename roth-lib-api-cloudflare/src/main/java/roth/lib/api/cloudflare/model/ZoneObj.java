package roth.lib.api.cloudflare.model;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class ZoneObj implements Serializable
{
	@Property(name = "zone_id")
	protected String zoneId;
	
	@Property(name = "user_id")
	protected String userId;
	
	@Property(name = "zone_name")
	protected String zoneName;
	
	@Property(name = "display_name")
	protected String displayName;
	
	@Property(name = "zone_status")
	protected String zoneStatus;
	
	@Property(name = "zone_mode")
	protected String zoneMode;
	
	@Property(name = "host_id")
	protected String hostId;
	
	@Property(name = "zone_type")
	protected String zoneType;
	
	@Property(name = "host_pubname")
	protected String hostPubname;
	
	@Property(name = "host_website")
	protected String hostWebsite;
	
	@Property(name = "vtxt")
	protected String vtxt;
	
	@Property(name = "fqdns")
	protected LinkedList<String> fqdns = new LinkedList<String>();
	
	@Property(name = "step")
	protected String step;
	
	@Property(name = "zone_status_class")
	protected String zoneStatusClass;
	
	@Property(name = "zone_status_desc")
	protected String zoneStatusDesc;
	
	@Property(name = "ns_vanity_map")
	protected LinkedList<String> nsVanityMap = new LinkedList<String>();
	
	@Property(name = "orig_registrar")
	protected String origRegistrar;
	
	@Property(name = "orig_dnshost")
	protected String origDnshost;
	
	@Property(name = "orig_ns_names")
	protected String origNsNames;
	
	@Property(name = "props")
	protected ZoneProps props;
	
	@Property(name = "confirm_code")
	protected ConfirmCode confirmCode;
	
	@Property(name = "allow")
	protected LinkedList<String> allow = new LinkedList<String>();
	
	public ZoneObj()
	{
		
	}
	
	public String getZoneId()
	{
		return zoneId;
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public String getZoneName()
	{
		return zoneName;
	}
	
	public String getDisplayName()
	{
		return displayName;
	}
	
	public String getZoneStatus()
	{
		return zoneStatus;
	}
	
	public String getZoneMode()
	{
		return zoneMode;
	}
	
	public String getHostId()
	{
		return hostId;
	}
	
	public String getZoneType()
	{
		return zoneType;
	}
	
	public String getHostPubname()
	{
		return hostPubname;
	}
	
	public String getHostWebsite()
	{
		return hostWebsite;
	}
	
	public String getVtxt()
	{
		return vtxt;
	}
	
	public LinkedList<String> getFqdns()
	{
		return fqdns;
	}
	
	public String getStep()
	{
		return step;
	}
	
	public String getZoneStatusClass()
	{
		return zoneStatusClass;
	}
	
	public String getZoneStatusDesc()
	{
		return zoneStatusDesc;
	}
	
	public LinkedList<String> getNsVanityMap()
	{
		return nsVanityMap;
	}
	
	public String getOrigRegistrar()
	{
		return origRegistrar;
	}
	
	public String getOrigDnshost()
	{
		return origDnshost;
	}
	
	public String getOrigNsNames()
	{
		return origNsNames;
	}
	
	public ZoneProps getProps()
	{
		return props;
	}
	
	public ConfirmCode getConfirmCode()
	{
		return confirmCode;
	}
	
	public LinkedList<String> getAllow()
	{
		return allow;
	}
	
	public ZoneObj setZoneId(String zoneId)
	{
		this.zoneId = zoneId;
		return this;
	}
	
	public ZoneObj setUserId(String userId)
	{
		this.userId = userId;
		return this;
	}
	
	public ZoneObj setZoneName(String zoneName)
	{
		this.zoneName = zoneName;
		return this;
	}
	
	public ZoneObj setDisplayName(String displayName)
	{
		this.displayName = displayName;
		return this;
	}
	
	public ZoneObj setZoneStatus(String zoneStatus)
	{
		this.zoneStatus = zoneStatus;
		return this;
	}
	
	public ZoneObj setZoneMode(String zoneMode)
	{
		this.zoneMode = zoneMode;
		return this;
	}
	
	public ZoneObj setHostId(String hostId)
	{
		this.hostId = hostId;
		return this;
	}
	
	public ZoneObj setZoneType(String zoneType)
	{
		this.zoneType = zoneType;
		return this;
	}
	
	public ZoneObj setHostPubname(String hostPubname)
	{
		this.hostPubname = hostPubname;
		return this;
	}
	
	public ZoneObj setHostWebsite(String hostWebsite)
	{
		this.hostWebsite = hostWebsite;
		return this;
	}
	
	public ZoneObj setVtxt(String vtxt)
	{
		this.vtxt = vtxt;
		return this;
	}
	
	public ZoneObj setFqdns(LinkedList<String> fqdns)
	{
		this.fqdns = fqdns;
		return this;
	}
	
	public ZoneObj setStep(String step)
	{
		this.step = step;
		return this;
	}
	
	public ZoneObj setZoneStatusClass(String zoneStatusClass)
	{
		this.zoneStatusClass = zoneStatusClass;
		return this;
	}
	
	public ZoneObj setZoneStatusDesc(String zoneStatusDesc)
	{
		this.zoneStatusDesc = zoneStatusDesc;
		return this;
	}
	
	public ZoneObj setNsVanityMap(LinkedList<String> nsVanityMap)
	{
		this.nsVanityMap = nsVanityMap;
		return this;
	}
	
	public ZoneObj setOrigRegistrar(String origRegistrar)
	{
		this.origRegistrar = origRegistrar;
		return this;
	}
	
	public ZoneObj setOrigDnshost(String origDnshost)
	{
		this.origDnshost = origDnshost;
		return this;
	}
	
	public ZoneObj setOrigNsNames(String origNsNames)
	{
		this.origNsNames = origNsNames;
		return this;
	}
	
	public ZoneObj setProps(ZoneProps props)
	{
		this.props = props;
		return this;
	}
	
	public ZoneObj setConfirmCode(ConfirmCode confirmCode)
	{
		this.confirmCode = confirmCode;
		return this;
	}
	
	public ZoneObj setAllow(LinkedList<String> allow)
	{
		this.allow = allow;
		return this;
	}
	
}
