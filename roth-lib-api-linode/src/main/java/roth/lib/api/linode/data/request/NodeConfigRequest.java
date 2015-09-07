package roth.lib.api.linode.data.request;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class NodeConfigRequest extends LinodeRequest
{
	@Property(name = "LinodeID")
	protected Integer linodeId;
	
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
	protected Boolean helperDisableUpdateDb;
	
	@Property(name = "helper_xen")
	protected Boolean helperXen;
	
	@Property(name = "helper_depmod")
	protected Boolean helperDepmod;
	
	@Property(name = "devtmpfs_automount")
	protected Boolean devtmpfsAutomount;
	
	public NodeConfigRequest()
	{
		
	}
	
	public Integer getLinodeId()
	{
		return linodeId;
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
	
	public Boolean getHelperDisableUpdateDb()
	{
		return helperDisableUpdateDb;
	}
	
	public Boolean getHelperXen()
	{
		return helperXen;
	}
	
	public Boolean getHelperDepmod()
	{
		return helperDepmod;
	}
	
	public Boolean getDevtmpfsAutomount()
	{
		return devtmpfsAutomount;
	}
	
	public void setLinodeId(Integer linodeId)
	{
		this.linodeId = linodeId;
	}
	
	public void setKernelId(Integer kernelId)
	{
		this.kernelId = kernelId;
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	public void setComments(String comments)
	{
		this.comments = comments;
	}
	
	public void setRamLimit(Integer ramLimit)
	{
		this.ramLimit = ramLimit;
	}
	
	public void setDiskList(String diskList)
	{
		this.diskList = diskList;
	}
	
	public void setRunLevel(String runLevel)
	{
		this.runLevel = runLevel;
	}
	
	public void setRootDeviceNum(Integer rootDeviceNum)
	{
		this.rootDeviceNum = rootDeviceNum;
	}
	
	public void setRootDeviceCustom(String rootDeviceCustom)
	{
		this.rootDeviceCustom = rootDeviceCustom;
	}
	
	public void setRootDeviceRd(Boolean rootDeviceRd)
	{
		this.rootDeviceRd = rootDeviceRd;
	}
	
	public void setHelperDisableUpdateDb(Boolean helperDisableUpdateDb)
	{
		this.helperDisableUpdateDb = helperDisableUpdateDb;
	}
	
	public void setHelperXen(Boolean helperXen)
	{
		this.helperXen = helperXen;
	}
	
	public void setHelperDepmod(Boolean helperDepmod)
	{
		this.helperDepmod = helperDepmod;
	}
	
	public void setDevtmpfsAutomount(Boolean devtmpfsAutomount)
	{
		this.devtmpfsAutomount = devtmpfsAutomount;
	}
	
}
