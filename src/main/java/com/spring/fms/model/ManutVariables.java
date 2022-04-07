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
	
	private Long CounterPartMax;
		
	private Long CounterPort;
	
	private Long CounterPortMax;
	
	private Long CounterClamping;
	
	private Long CounterClampingMax;
	
	private Float HoursMachining;
	
	private Float HoursMachiningMax;
	
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

	public Long getCounterPartMax() {
		return CounterPartMax;
	}

	public void setCounterPartMax(Long counterPartMax) {
		CounterPartMax = counterPartMax;
	}

	public Long getCounterPort() {
		return CounterPort;
	}

	public void setCounterPort(Long counterPort) {
		CounterPort = counterPort;
	}

	public Long getCounterPortMax() {
		return CounterPortMax;
	}

	public void setCounterPortMax(Long counterPortMax) {
		CounterPortMax = counterPortMax;
	}

	public Long getCounterClamping() {
		return CounterClamping;
	}

	public void setCounterClamping(Long counterClamping) {
		CounterClamping = counterClamping;
	}

	public Long getCounterClampingMax() {
		return CounterClampingMax;
	}

	public void setCounterClampingMax(Long counterClampingMax) {
		CounterClampingMax = counterClampingMax;
	}

	public Float getHoursMachining() {
		return HoursMachining;
	}

	public void setHoursMachining(Float hoursMachining) {
		HoursMachining = hoursMachining;
	}

	public Float getHoursMachiningMax() {
		return HoursMachiningMax;
	}

	public void setHoursMachiningMax(Float hoursMachiningMax) {
		HoursMachiningMax = hoursMachiningMax;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	
	
	
	
}
