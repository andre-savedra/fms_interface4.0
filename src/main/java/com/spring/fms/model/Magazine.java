package com.spring.fms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_MAGAZINE")
public class Magazine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private boolean located = false;
	
	private Long orderId;
	
	private int orderSubindex;
	
	private boolean isFlex = false;
	
	private Long lastMachineStep = 0L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLocated() {
		return located;
	}

	public void setLocated(boolean located) {
		this.located = located;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getOrderSubindex() {
		return orderSubindex;
	}

	public void setOrderSubindex(int orderSubindex) {
		this.orderSubindex = orderSubindex;
	}

	public boolean isFlex() {
		return isFlex;
	}

	public void setFlex(boolean isFlex) {
		this.isFlex = isFlex;
	}

	public Long getLastMachineStep() {
		return lastMachineStep;
	}

	public void setLastMachineStep(Long lastMachineStep) {
		this.lastMachineStep = lastMachineStep;
	}
	
	

}
