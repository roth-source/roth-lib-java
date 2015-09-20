package roth.lib.java.api.linode.data.request.get;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.LinodeRequest;

@Entity
@SuppressWarnings("serial")
public class GetApiKeyRequest extends LinodeRequest
{
	@Property(name = "username")
	protected String username;
	
	@Property(name = "password")
	protected String password;
	
	@Property(name = "token")
	protected String token;
	
	@Property(name = "expires")
	protected Integer expires;
	
	@Property(name = "label")
	protected String label;
	
	public GetApiKeyRequest(String username, String password)
	{
		super();
		setUsername(username);
		setPassword(password);
		setExpires(0);
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getToken()
	{
		return token;
	}
	
	public Integer getExpires()
	{
		return expires;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public GetApiKeyRequest setUsername(String username)
	{
		this.username = username;
		return this;
	}
	
	public GetApiKeyRequest setPassword(String password)
	{
		this.password = password;
		return this;
	}
	
	public GetApiKeyRequest setToken(String token)
	{
		this.token = token;
		return this;
	}
	
	public GetApiKeyRequest setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public GetApiKeyRequest setExpires(Integer expires)
	{
		if(0 <= expires && expires <= 8760)
		{
			this.expires = expires;
		}
		return this;
	}
	
}
