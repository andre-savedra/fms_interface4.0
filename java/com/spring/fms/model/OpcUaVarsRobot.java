package com.spring.fms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="TB_ROBOT_VARIABLES")
public class OpcUaVarsRobot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private boolean statusAlarmRobot;
		
	private Long statusRobot;	
		
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime actualDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isStatusAlarmRobot() {
		return statusAlarmRobot;
	}

	public void setStatusAlarmRobot(boolean statusAlarmRobot) {
		this.statusAlarmRobot = statusAlarmRobot;
	}

	public Long getStatusRobot() {
		return statusRobot;
	}

	public void setStatusRobot(Long statusRobot) {
		this.statusRobot = statusRobot;
	}

	public LocalDateTime getActualDate() {
		return actualDate;
	}

	public void setActualDate(LocalDateTime actualDate) {
		this.actualDate = actualDate;
	}
		
	
}
