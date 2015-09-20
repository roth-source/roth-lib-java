package roth.lib.java.api.linode;

import java.lang.reflect.Type;
import java.util.List;

import roth.lib.java.api.FormJsonApiClient;
import roth.lib.java.api.linode.data.model.LinodeError;
import roth.lib.java.api.linode.data.request.LinodeRequest;
import roth.lib.java.api.linode.data.response.LinodeResponse;
import roth.lib.java.net.http.HttpHeader;
import roth.lib.java.net.http.HttpMethod;
import roth.lib.java.net.http.HttpProtocol;
import roth.lib.java.net.http.HttpResponse;
import roth.lib.java.net.http.HttpUrl;

public abstract class LinodeClient extends FormJsonApiClient<LinodeRequest, LinodeResponse<?>>
{
	protected static String GET_NODE						= "linode.list";
	protected static String CREATE_NODE						= "linode.create";
	protected static String UPDATE_NODE						= "linode.update";
	protected static String DELETE_NODE						= "linode.delete";
	protected static String CLONE_NODE						= "linode.clone";
	protected static String BOOT_NODE						= "linode.boot";
	protected static String REBOOT_NODE						= "linode.reboot";
	protected static String SHUTDOWN_NODE					= "linode.shutdown";
	protected static String RESIZE_NODE						= "linode.resize";
	protected static String GET_NODE_CONFIG					= "linode.config.list";
	protected static String CREATE_NODE_CONFIG				= "linode.config.create";
	protected static String UPDATE_NODE_CONFIG				= "linode.config.update";
	protected static String DELETE_NODE_CONFIG				= "linode.config.delete";
	protected static String GET_NODE_DISK					= "linode.disk.list";
	protected static String CREATE_NODE_DISK				= "linode.disk.create";
	protected static String CREATE_NODE_DISK_FROM_DIST		= "linode.disk.createfromdistribution";
	protected static String CREATE_NODE_DISK_FROM_SCRIPT	= "linode.disk.createfromstackscript";
	protected static String UPDATE_NODE_DISK				= "linode.disk.update";
	protected static String DELETE_NODE_DISK				= "linode.disk.delete";
	protected static String DUPLICATE_NODE_DISK				= "linode.disk.duplicate";
	protected static String RESIZE_NODE_DISK				= "linode.disk.resize";
	protected static String GET_NODE_IP						= "linode.ip.list";
	protected static String ADD_PRIVATE_NODE_IP				= "linode.ip.addprivate";
	protected static String ADD_PUBLIC_NODE_IP				= "linode.ip.addpublic";
	protected static String SET_RDNS_NODE_IP				= "linode.ip.setrdns";
	protected static String SWAP_NODE_IP					= "linode.ip.swap";
	protected static String GET_NODE_JOB					= "linode.job.list";
	protected static String GET_NODE_BALANCER				= "nodebalancer.list";
	protected static String CREATE_NODE_BALANCER			= "nodebalancer.create";
	protected static String UPDATE_NODE_BALANCER			= "nodebalancer.update";
	protected static String DELETE_NODE_BALANCER			= "nodebalancer.delete";
	protected static String GET_NODE_BALANCER_CONFIG		= "nodebalancer.config.list";
	protected static String CREATE_NODE_BALANCER_CONFIG		= "nodebalancer.config.create";
	protected static String UPDATE_NODE_BALANCER_CONFIG		= "nodebalancer.config.update";
	protected static String DELETE_NODE_BALANCER_CONFIG		= "nodebalancer.config.delete";
	protected static String GET_NODE_BALANCER_NODE			= "nodebalancer.node.list";
	protected static String CREATE_NODE_BALANCER_NODE		= "nodebalancer.node.create";
	protected static String UPDATE_NODE_BALANCER_NODE		= "nodebalancer.node.update";
	protected static String DELETE_NODE_BALANCER_NODE		= "nodebalancer.node.delete";
	protected static String GET_STACK_SCRIPT		 		= "stackscript.list";
	protected static String CREATE_STACK_SCRIPT				= "stackscript.create";
	protected static String UPDATE_STACK_SCRIPT				= "stackscript.update";
	protected static String DELETE_STACK_SCRIPT				= "stackscript.delete";
	protected static String GET_DOMAIN		 				= "domain.list";
	protected static String CREATE_DOMAIN		 			= "domain.create";
	protected static String UPDATE_DOMAIN		 			= "domain.update";
	protected static String DELETE_DOMAIN		 			= "domain.delete";
	protected static String GET_DOMAIN_RESOURCE				= "domain.resource.list";
	protected static String CREATE_DOMAIN_RESOURCE			= "domain.resource.create";
	protected static String UPDATE_DOMAIN_RESOURCE			= "domain.resource.update";
	protected static String DELETE_DOMAIN_RESOURCE			= "domain.resource.delete";
	protected static String GET_INVOICE_ESTIMATE 			= "account.estimateinvoice";
	protected static String GET_ACCOUNT_INFO 				= "account.info";
	protected static String GET_API_KEY		 				= "user.getapikey";
	protected static String GET_API_SPEC 					= "api.spec";
	protected static String GET_AVAIL_DATA_CENTERS 			= "avail.datacenters";
	protected static String GET_AVAIL_DISTRIBUTIONS 		= "avail.distributions";
	protected static String GET_AVAIL_KERNELS				= "avail.kernels";
	protected static String GET_AVAIL_PLANS					= "avail.linodeplans";
	protected static String GET_AVAIL_NODE_BALANCERS		= "avail.nodebalancers";
	protected static String GET_AVAIL_STACK_SCRIPTS			= "avail.stackscripts";
	protected static String TEST_ECHO 						= "test.echo";
	protected static String TEST_FAIL 						= "test.fail";
	
	protected static String TIME_FORMAT						= "yyyy-MM-dd HH:mm:ss.S";
	
	protected String apiKey;
	
	protected LinodeClient(String apiKey)
	{
		this(apiKey, false);
	}
	
	protected LinodeClient(String apiKey, boolean debug)
	{
		super(debug);
		this.apiKey = apiKey;
		this.debug = debug;
		setTimeFormat(TIME_FORMAT);
	}
	
	protected HttpUrl url()
	{
		return new HttpUrl().setProtocol(HttpProtocol.HTTPS).setHost("api.linode.com");
	}
	
	protected <T extends LinodeResponse<?>> T connect(HttpUrl url, LinodeRequest linodeRequest, Type type, HttpMethod method, boolean gzip, HttpHeader... headers)
	{
		if(linodeRequest != null)
		{
			linodeRequest.setApiKey(apiKey);
		}
		else
		{
			throw new LinodeException("request cannot be null");
		}
		return super.connect(url, linodeRequest, type, method, gzip, headers);
	}
	
	protected <T extends LinodeResponse<?>> void checkResponse(HttpResponse<T> response)
	{
		T linodeResponse = response.getEntity();
		if(linodeResponse != null)
		{
			List<LinodeError> errors = linodeResponse.getErrors();
			if(errors != null && !errors.isEmpty())
			{
				StringBuilder builder = new StringBuilder();
				String seperator = "";
				for(LinodeError error : errors)
				{
					builder.append(seperator);
					builder.append(error.getErrorCode());
					builder.append( " : ");
					builder.append(error.getErrorMessage());
					if("".equals(seperator))
					{
						seperator = ", ";
					}
				}
				throw new LinodeException(builder.toString());
			}
		}
	}
	
}
