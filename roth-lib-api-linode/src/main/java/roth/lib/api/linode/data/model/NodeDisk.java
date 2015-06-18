package roth.lib.api.linode.data.model;

import java.io.Serializable;
import java.util.Calendar;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class NodeDisk implements Serializable
{
	@Property(name = "LINODEID")
	protected Integer linodeId;
	
	@Property(name = "DISKID")
	protected Integer diskId;
	
	@Property(name = "CREATED_DT")
	protected Calendar createdDt;
	
	@Property(name = "UPDATE_DT")
	protected Calendar updateDt;
	
	@Property(name = "LABEL")
	protected String label;
	
	@Property(name = "TYPE")
	protected String type;
	
	@Property(name = "ISREADONLY")
	protected Boolean readOnly;
	
	@Property(name = "STATUS")
	protected Integer status;
	
	@Property(name = "SIZE")
	protected Integer size;
	
	public NodeDisk()
	{
		
	}
	
	public Integer getLinodeId()
	{
		return linodeId;
	}
	
	public Integer getDiskId()
	{
		return diskId;
	}
	
	public Calendar getCreatedDt()
	{
		return createdDt;
	}
	
	public Calendar getUpdateDt()
	{
		return updateDt;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getType()
	{
		return type;
	}
	
	public Boolean getReadOnly()
	{
		return readOnly;
	}
	
	public Integer getStatus()
	{
		return status;
	}
	
	public Integer getSize()
	{
		return size;
	}
	
	public NodeDisk setLinodeId(Integer linodeId)
	{
		this.linodeId = linodeId;
		return this;
	}
	
	public NodeDisk setDiskId(Integer diskId)
	{
		this.diskId = diskId;
		return this;
	}
	
	public NodeDisk setCreatedDt(Calendar createdDt)
	{
		this.createdDt = createdDt;
		return this;
	}
	
	public NodeDisk setUpdateDt(Calendar updateDt)
	{
		this.updateDt = updateDt;
		return this;
	}
	
	public NodeDisk setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public NodeDisk setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public NodeDisk setReadOnly(Boolean readOnly)
	{
		this.readOnly = readOnly;
		return this;
	}
	
	public NodeDisk setStatus(Integer status)
	{
		this.status = status;
		return this;
	}
	
	public NodeDisk setSize(Integer size)
	{
		this.size = size;
		return this;
	}
	
}
