package com.spring.fms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_MACHINERY")
public class Machinery {

	@Id
	private Long id;
	
	@NotNull
	@OneToOne
	private Machine machine;
	@NotNull
	private boolean enabled;
	
	private String jobName;
	private boolean hasJob;
	private boolean jobAccepted;
	private boolean jobEnded;
	private boolean machining;
	private boolean machineReady;
	private boolean permissionToStart;
	private Long orderId;	
	private int stepId;
	
	private String filename;
	
	private String path;
	
	private Long info;
	
	public Machinery() {
		
	}	
	
	public Machinery(Long id, Machine machine, boolean enabled) {
		this.id = id;
		this.machine = machine;
		this.enabled = enabled;
		
		this.jobName = "";
		this.hasJob = false;
		this.jobAccepted = false;
		this.jobEnded = false;
		this.machining = false;
		this.machineReady = false;
		this.permissionToStart = false;		
		this.orderId = 0L;
		this.stepId = 0;
		this.filename = "";
		this.path = "";
		
		this.info = 0L;		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Machine getMachine() {
		return machine;
	}


	public void setMachine(Machine machine) {
		this.machine = machine;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public boolean isHasJob() {
		return hasJob;
	}

	public void setHasJob(boolean hasJob) {
		this.hasJob = hasJob;
	}

	public boolean isJobAccepted() {
		return jobAccepted;
	}


	public void setJobAccepted(boolean jobAccepted) {
		this.jobAccepted = jobAccepted;
	}


	public boolean isPermissionToStart() {
		return permissionToStart;
	}


	public void setPermissionToStart(boolean permissionToStart) {
		this.permissionToStart = permissionToStart;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	

	public int getStepId() {
		return stepId;
	}

	public void setStepId(int stepId) {
		this.stepId = stepId;
	}

	public Long getInfo() {
		return info;
	}


	public void setInfo(Long info) {
		this.info = info;
	}

	public boolean isJobEnded() {
		return jobEnded;
	}

	public void setJobEnded(boolean jobEnded) {
		this.jobEnded = jobEnded;
	}

	public boolean isMachining() {
		return machining;
	}

	public void setMachining(boolean machining) {
		this.machining = machining;
	}

	public boolean isMachineReady() {
		return machineReady;
	}

	public void setMachineReady(boolean machineReady) {
		this.machineReady = machineReady;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
		
	
}
