package roth.lib.api.linode.data.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class NodeConfig implements Serializable
{
	@Property(name = "LinodeID")
	protected Integer linodeId;
	
	@Property(name = "ConfigID")
	protected Integer configId;
	
	@Property(name = "KernelID")
	protected Integer kernelId;
	
	@Property(name = "Label")
	protected String label;
	
	@Property(name = "Comments")
	protected String comments;
	
	@Property(name = "RAMLimit")
	protected Integer ramLimit;
	
	@Property(name = "DiskList")
	protected String diskList;
	
	@Property(name = "RunLevel")
	protected String runLevel;
	
	@Property(name = "RootDeviceNum")
	protected Integer rootDeviceNum;
	
	@Property(name = "RootDeviceCustom")
	protected String rootDeviceCustom;
	
	@Property(name = "RootDeviceRO")
	protected Boolean rootDeviceRd;
	
	@Property(name = "helper_disableUpdateDB")
	protected Boolean helperDisableUpdateDB;
	
	@Property(name = "helper_xen")
	protected Boolean helperXen;
	
	@Property(name = "helper_depmod")
	protected Boolean helperDepmod;
	
	@Property(name = "helper_libtls")
	protected Boolean helperLibtls;
	
	public NodeConfig()
	{
		
	}
	
	public Integer getLinodeId()
	{
		return linodeId;
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public Integer getKernelId()
	{
		return kernelId;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getComments()
	{
		return comments;
	}
	
	public Integer getRamLimit()
	{
		return ramLimit;
	}
	
	public String getDiskList()
	{
		return diskList;
	}
	
	public String getRunLevel()
	{
		return runLevel;
	}
	
	public Integer getRootDeviceNum()
	{
		return rootDeviceNum;
	}
	
	public String getRootDeviceCustom()
	{
		return rootDeviceCustom;
	}
	
	public Boolean getRootDeviceRd()
	{
		return rootDeviceRd;
	}
	
	public Boolean getHelperDisableUpdateDB()
	{
		return helperDisableUpdateDB;
	}
	
	public Boolean getHelperXen()
	{
		return helperXen;
	}
	
	public Boolean getHelperDepmod()
	{
		return helperDepmod;
	}
	
	public Boolean getHelperLibtls()
	{
		return helperLibtls;
	}
	
	public NodeConfig setLinodeId(Integer linodeId)
	{
		this.linodeId = linodeId;
		return this;
	}
	
	public NodeConfig setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
	public NodeConfig setKernelId(Integer kernelId)
	{
		this.kernelId = kernelId;
		return this;
	}
	
	public NodeConfig setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public NodeConfig setComments(String comments)
	{
		this.comments = comments;
		return this;
	}
	
	public NodeConfig setRamLimit(Integer ramLimit)
	{
		this.ramLimit = ramLimit;
		return this;
	}
	
	public NodeConfig setDiskList(String diskList)
	{
		this.diskList = diskList;
		return this;
	}
	
	public NodeConfig setRunLevel(String runLevel)
	{
		this.runLevel = runLevel;
		return this;
	}
	
	public NodeConfig setRootDeviceNum(Integer rootDeviceNum)
	{
		this.rootDeviceNum = rootDeviceNum;
		return this;
	}
	
	public NodeConfig setRootDeviceCustom(String rootDeviceCustom)
	{
		this.rootDeviceCustom = rootDeviceCustom;
		return this;
	}
	
	public NodeConfig setRootDeviceRd(Boolean rootDeviceRd)
	{
		this.rootDeviceRd = rootDeviceRd;
		return this;
	}
	
	public NodeConfig setHelperDisableUpdateDB(Boolean helperDisableUpdateDB)
	{
		this.helperDisableUpdateDB = helperDisableUpdateDB;
		return this;
	}
	
	public NodeConfig setHelperXen(Boolean helperXen)
	{
		this.helperXen = helperXen;
		return this;
	}
	
	public NodeConfig setHelperDepmod(Boolean helperDepmod)
	{
		this.helperDepmod = helperDepmod;
		return this;
	}
	
	public NodeConfig setHelperLibtls(Boolean helperLibtls)
	{
		this.helperLibtls = helperLibtls;
		return this;
	}
	
}
