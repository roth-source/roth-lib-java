package roth.lib.api.cloudflare.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class SettingObj implements Serializable
{
	@Property(name = "userSecuritySetting")
	protected String userSecuritySetting;
	
	@Property(name = "dev_mode")
	protected Integer devMode;
	
	@Property(name = "ipv46")
	protected Integer ipv46;
	
	@Property(name = "ob")
	protected Integer ob;
	
	@Property(name = "cache_lvl")
	protected String cacheLvl;
	
	@Property(name = "outboundLinks")
	protected String outboundLinks;
	
	@Property(name = "async")
	protected String async;
	
	@Property(name = "bic")
	protected String bic;
	
	@Property(name = "chl_ttl")
	protected String chlTtl;
	
	@Property(name = "exp_ttl")
	protected String expTtl;
	
	@Property(name = "fpurge_ts")
	protected String fpurgeTs;
	
	@Property(name = "hotlink")
	protected String hotlink;
	
	@Property(name = "img")
	protected String img;
	
	@Property(name = "lazy")
	protected String lazy;
	
	@Property(name = "minify")
	protected String minify;
	
	@Property(name = "outlink")
	protected String outlink;
	
	@Property(name = "preload")
	protected String preload;
	
	@Property(name = "s404")
	protected String s404;
	
	@Property(name = "sec_lvl")
	protected String secLvl;
	
	@Property(name = "spdy")
	protected String spdy;
	
	@Property(name = "ssl")
	protected String ssl;
	
	@Property(name = "waf_profile")
	protected String wafProfile;
	
	public SettingObj()
	{
		
	}
	
	public String getUserSecuritySetting()
	{
		return userSecuritySetting;
	}
	
	public Integer getDevMode()
	{
		return devMode;
	}
	
	public Integer getIpv46()
	{
		return ipv46;
	}
	
	public Integer getOb()
	{
		return ob;
	}
	
	public String getCacheLvl()
	{
		return cacheLvl;
	}
	
	public String getOutboundLinks()
	{
		return outboundLinks;
	}
	
	public String getAsync()
	{
		return async;
	}
	
	public String getBic()
	{
		return bic;
	}
	
	public String getChlTtl()
	{
		return chlTtl;
	}
	
	public String getExpTtl()
	{
		return expTtl;
	}
	
	public String getFpurgeTs()
	{
		return fpurgeTs;
	}
	
	public String getHotlink()
	{
		return hotlink;
	}
	
	public String getImg()
	{
		return img;
	}
	
	public String getLazy()
	{
		return lazy;
	}
	
	public String getMinify()
	{
		return minify;
	}
	
	public String getOutlink()
	{
		return outlink;
	}
	
	public String getPreload()
	{
		return preload;
	}
	
	public String getS404()
	{
		return s404;
	}
	
	public String getSecLvl()
	{
		return secLvl;
	}
	
	public String getSpdy()
	{
		return spdy;
	}
	
	public String getSsl()
	{
		return ssl;
	}
	
	public String getWafProfile()
	{
		return wafProfile;
	}
	
	public SettingObj setUserSecuritySetting(String userSecuritySetting)
	{
		this.userSecuritySetting = userSecuritySetting;
		return this;
	}
	
	public SettingObj setDevMode(Integer devMode)
	{
		this.devMode = devMode;
		return this;
	}
	
	public SettingObj setIpv46(Integer ipv46)
	{
		this.ipv46 = ipv46;
		return this;
	}
	
	public SettingObj setOb(Integer ob)
	{
		this.ob = ob;
		return this;
	}
	
	public SettingObj setCacheLvl(String cacheLvl)
	{
		this.cacheLvl = cacheLvl;
		return this;
	}
	
	public SettingObj setOutboundLinks(String outboundLinks)
	{
		this.outboundLinks = outboundLinks;
		return this;
	}
	
	public SettingObj setAsync(String async)
	{
		this.async = async;
		return this;
	}
	
	public SettingObj setBic(String bic)
	{
		this.bic = bic;
		return this;
	}
	
	public SettingObj setChlTtl(String chlTtl)
	{
		this.chlTtl = chlTtl;
		return this;
	}
	
	public SettingObj setExpTtl(String expTtl)
	{
		this.expTtl = expTtl;
		return this;
	}
	
	public SettingObj setFpurgeTs(String fpurgeTs)
	{
		this.fpurgeTs = fpurgeTs;
		return this;
	}
	
	public SettingObj setHotlink(String hotlink)
	{
		this.hotlink = hotlink;
		return this;
	}
	
	public SettingObj setImg(String img)
	{
		this.img = img;
		return this;
	}
	
	public SettingObj setLazy(String lazy)
	{
		this.lazy = lazy;
		return this;
	}
	
	public SettingObj setMinify(String minify)
	{
		this.minify = minify;
		return this;
	}
	
	public SettingObj setOutlink(String outlink)
	{
		this.outlink = outlink;
		return this;
	}
	
	public SettingObj setPreload(String preload)
	{
		this.preload = preload;
		return this;
	}
	
	public SettingObj setS404(String s404)
	{
		this.s404 = s404;
		return this;
	}
	
	public SettingObj setSecLvl(String secLvl)
	{
		this.secLvl = secLvl;
		return this;
	}
	
	public SettingObj setSpdy(String spdy)
	{
		this.spdy = spdy;
		return this;
	}
	
	public SettingObj setSsl(String ssl)
	{
		this.ssl = ssl;
		return this;
	}
	
	public SettingObj setWafProfile(String wafProfile)
	{
		this.wafProfile = wafProfile;
		return this;
	}
	
}
