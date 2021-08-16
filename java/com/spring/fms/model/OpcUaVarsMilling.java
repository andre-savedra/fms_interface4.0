package com.spring.fms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="TB_MILLING_VARIABLES")
public class OpcUaVarsMilling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private boolean statusAlarmMill;
		
	private boolean MillMachining;
	
	private Long CounterMillPart;	
		
	private boolean portOpened;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime actualDate;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isStatusAlarmMill() {
		return statusAlarmMill;
	}

	public void setStatusAlarmMill(boolean statusAlarmMill) {
		this.statusAlarmMill = statusAlarmMill;
	}

	public boolean isMillMachining() {
		return MillMachining;
	}

	public void setMillMachining(boolean millMachining) {
		MillMachining = millMachining;
	}

	public Long getCounterMillPart() {
		return CounterMillPart;
	}

	public void setCounterMillPart(Long counterMillPart) {
		CounterMillPart = counterMillPart;
	}

	public LocalDateTime getActualDate() {
		return actualDate;
	}

	public void setActualDate(LocalDateTime actualDate) {
		this.actualDate = actualDate;
	}

	public boolean isPortOpened() {
		return portOpened;
	}

	public void setPortOpened(boolean portOpened) {
		this.portOpened = portOpened;
	}
	
}
