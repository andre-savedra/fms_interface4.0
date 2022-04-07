package com.spring.fms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="TB_MANUT_VARIABLES")
public class ManutVariables {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@OneToOne
	private Machine machine;		
	
	private Long CounterPart;	
		
	private Long CounterPort;
	
	private Long CounterClamping;
	
	private Float HoursMachining;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime lastUpdate;

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

	public Long getCounterPart() {
		return CounterPart;
	}

	public void setCounterPart(Long counterPart) {
		CounterPart = counterPart;
	}

	public Long getCounterPort() {
		return CounterPort;
	}

	public void setCounterPort(Long counterPort) {
		CounterPort = counterPort;
	}

	public Long getCounterClamping() {
		return CounterClamping;
	}

	public void setCounterClamping(Long counterClamping) {
		CounterClamping = counterClamping;
	}
	

	public Float getHoursMilling() {
		return HoursMachining;
	}

	public void setHoursMilling(Float hoursMilling) {
		HoursMachining = hoursMilling;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
	
	
	
}
