package roth.lib.api.linode.data.model;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class NodeBalancerConfig implements Serializable
{
	@Property(name = "NODEBALANCERID")
	protected Integer nodeBalancerId;
	
	@Property(name = "CONFIGID")
	protected Integer configId;
	
	@Property(name = "STICKINESS")
	protected String stickiness;
	
	@Property(name = "CHECK_PATH")
	protected String checkPath;
	
	@Property(name = "PORT")
	protected Integer port;
	
	@Property(name = "CHECK_BODY")
	protected String checkBody;
	
	@Property(name = "CHECK")
	protected String check;
	
	@Property(name = "CHECK_INTERVAL")
	protected Integer checkInterval;
	
	@Property(name = "PROTOCOL")
	protected String protocol;
	
	@Property(name = "ALGORITHM")
	protected String algorithm;
	
	@Property(name = "CHECK_TIMEOUT")
	protected Integer checkTimeout;
	
	@Property(name = "CHECK_ATTEMPTS")
	protected Integer checkAttempts;
	
	@Property(name = "SSL_FINGERPRINT")
	protected String sslFingerprint;
	
	@Property(name = "SSL_COMMONNAME")
	protected String sslCommonname;
	
	public NodeBalancerConfig()
	{
		
	}
	
	public Integer getNodeBalancerId()
	{
		return nodeBalancerId;
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public String getStickiness()
	{
		return stickiness;
	}
	
	public String getCheckPath()
	{
		return checkPath;
	}
	
	public Integer getPort()
	{
		return port;
	}
	
	public String getCheckBody()
	{
		return checkBody;
	}
	
	public String getCheck()
	{
		return check;
	}
	
	public Integer getCheckInterval()
	{
		return checkInterval;
	}
	
	public String getProtocol()
	{
		return protocol;
	}
	
	public String getAlgorithm()
	{
		return algorithm;
	}
	
	public Integer getCheckTimeout()
	{
		return checkTimeout;
	}
	
	public Integer getCheckAttempts()
	{
		return checkAttempts;
	}
	
	public String getSslFingerprint()
	{
		return sslFingerprint;
	}
	
	public String getSslCommonname()
	{
		return sslCommonname;
	}
	
	public NodeBalancerConfig setNodeBalancerId(Integer nodeBalancerId)
	{
		this.nodeBalancerId = nodeBalancerId;
		return this;
	}
	
	public NodeBalancerConfig setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
	public NodeBalancerConfig setStickiness(String stickiness)
	{
		this.stickiness = stickiness;
		return this;
	}
	
	public NodeBalancerConfig setCheckPath(String checkPath)
	{
		this.checkPath = checkPath;
		return this;
	}
	
	public NodeBalancerConfig setPort(Integer port)
	{
		this.port = port;
		return this;
	}
	
	public NodeBalancerConfig setCheckBody(String checkBody)
	{
		this.checkBody = checkBody;
		return this;
	}
	
	public NodeBalancerConfig setCheck(String check)
	{
		this.check = check;
		return this;
	}
	
	public NodeBalancerConfig setCheckInterval(Integer checkInterval)
	{
		this.checkInterval = checkInterval;
		return this;
	}
	
	public NodeBalancerConfig setProtocol(String protocol)
	{
		this.protocol = protocol;
		return this;
	}
	
	public NodeBalancerConfig setAlgorithm(String algorithm)
	{
		this.algorithm = algorithm;
		return this;
	}
	
	public NodeBalancerConfig setCheckTimeout(Integer checkTimeout)
	{
		this.checkTimeout = checkTimeout;
		return this;
	}
	
	public NodeBalancerConfig setCheckAttempts(Integer checkAttempts)
	{
		this.checkAttempts = checkAttempts;
		return this;
	}
	
	public NodeBalancerConfig setSslFingerprint(String sslFingerprint)
	{
		this.sslFingerprint = sslFingerprint;
		return this;
	}
	
	public NodeBalancerConfig setSslCommonname(String sslCommonname)
	{
		this.sslCommonname = sslCommonname;
		return this;
	}
	
}
