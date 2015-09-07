package roth.lib.api.linode.data.request;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class NodeBalancerConfigRequest extends LinodeRequest
{
	@Property(name = "NodeBalancerID")
	protected Integer nodeBalancerId;
	
	@Property(name = "Port")
	protected Integer port;
	
	@Property(name = "Protocol")
	protected String protocol;
	
	@Property(name = "Algorithm")
	protected String algorithm;
	
	@Property(name = "Stickiness")
	protected String stickiness;
	
	@Property(name = "check")
	protected String check;
	
	@Property(name = "check_interval")
	protected Integer checkInterval;
	
	@Property(name = "check_timeout")
	protected String checkTimeout;
	
	@Property(name = "check_attempts")
	protected String checkAttempts;
	
	@Property(name = "check_path")
	protected String checkPath;
	
	@Property(name = "check_body")
	protected String checkBody;
	
	@Property(name = "ssl_cert")
	protected String sslCert;
	
	@Property(name = "ssl_key")
	protected String sslKey;
	
	public NodeBalancerConfigRequest()
	{
		
	}
	
	public Integer getNodeBalancerId()
	{
		return nodeBalancerId;
	}
	
	public Integer getPort()
	{
		return port;
	}
	
	public String getProtocol()
	{
		return protocol;
	}
	
	public String getAlgorithm()
	{
		return algorithm;
	}
	
	public String getStickiness()
	{
		return stickiness;
	}
	
	public String getCheck()
	{
		return check;
	}
	
	public Integer getCheckInterval()
	{
		return checkInterval;
	}
	
	public String getCheckTimeout()
	{
		return checkTimeout;
	}
	
	public String getCheckAttempts()
	{
		return checkAttempts;
	}
	
	public String getCheckPath()
	{
		return checkPath;
	}
	
	public String getCheckBody()
	{
		return checkBody;
	}
	
	public String getSslCert()
	{
		return sslCert;
	}
	
	public String getSslKey()
	{
		return sslKey;
	}
	
	public NodeBalancerConfigRequest setNodeBalancerId(Integer nodeBalancerId)
	{
		this.nodeBalancerId = nodeBalancerId;
		return this;
	}
	
	public NodeBalancerConfigRequest setPort(Integer port)
	{
		this.port = port;
		return this;
	}
	
	public NodeBalancerConfigRequest setProtocol(String protocol)
	{
		this.protocol = protocol;
		return this;
	}
	
	public NodeBalancerConfigRequest setAlgorithm(String algorithm)
	{
		this.algorithm = algorithm;
		return this;
	}
	
	public NodeBalancerConfigRequest setStickiness(String stickiness)
	{
		this.stickiness = stickiness;
		return this;
	}
	
	public NodeBalancerConfigRequest setCheck(String check)
	{
		this.check = check;
		return this;
	}
	
	public NodeBalancerConfigRequest setCheckInterval(Integer checkInterval)
	{
		this.checkInterval = checkInterval;
		return this;
	}
	
	public NodeBalancerConfigRequest setCheckTimeout(String checkTimeout)
	{
		this.checkTimeout = checkTimeout;
		return this;
	}
	
	public NodeBalancerConfigRequest setCheckAttempts(String checkAttempts)
	{
		this.checkAttempts = checkAttempts;
		return this;
	}
	
	public NodeBalancerConfigRequest setCheckPath(String checkPath)
	{
		this.checkPath = checkPath;
		return this;
	}
	
	public NodeBalancerConfigRequest setCheckBody(String checkBody)
	{
		this.checkBody = checkBody;
		return this;
	}
	
	public NodeBalancerConfigRequest setSslCert(String sslCert)
	{
		this.sslCert = sslCert;
		return this;
	}
	
	public NodeBalancerConfigRequest setSslKey(String sslKey)
	{
		this.sslKey = sslKey;
		return this;
	}
	
}
