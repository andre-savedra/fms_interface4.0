package com.spring.fms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="TB_TURN_VARIABLES")
public class OpcUaVarsTurn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private boolean statusAlarmTurn;
		
	private boolean TurnMachining;
	
	private Long CounterTurnPart;
	
	private boolean portOpened;
		
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime actualDate;
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isStatusAlarmTurn() {
		return statusAlarmTurn;
	}

	public void setStatusAlarmTurn(boolean statusAlarmTurn) {
		this.statusAlarmTurn = statusAlarmTurn;
	}

	public boolean isTurnMachining() {
		return TurnMachining;
	}

	public void setTurnMachining(boolean turnMachining) {
		TurnMachining = turnMachining;
	}

	public Long getCounterTurnPart() {
		return CounterTurnPart;
	}

	public void setCounterTurnPart(Long counterTurnPart) {
		CounterTurnPart = counterTurnPart;
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
	
	
	
	
	
	
	
	
	

