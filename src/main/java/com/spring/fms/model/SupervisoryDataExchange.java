package com.spring.fms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_SUPERVISORY_EXCHANGE")
public class SupervisoryDataExchange {

	@Id	
	private Long id;
	
	private boolean gcodeTurnLoaded;
	
	private boolean gcodeMillLoaded;
	
	private Long orderTurnToManufacture;
	
	private Long orderMillToManufacture;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isGcodeTurnLoaded() {
		return gcodeTurnLoaded;
	}

	public void setGcodeTurnLoaded(boolean gcodeTurnLoaded) {
		this.gcodeTurnLoaded = gcodeTurnLoaded;
	}

	public boolean isGcodeMillLoaded() {
		return gcodeMillLoaded;
	}

	public void setGcodeMillLoaded(boolean gcodeMillLoaded) {
		this.gcodeMillLoaded = gcodeMillLoaded;
	}

	public Long getOrderTurnToManufacture() {
		return orderTurnToManufacture;
	}

	public void setOrderTurnToManufacture(Long orderTurnToManufacture) {
		this.orderTurnToManufacture = orderTurnToManufacture;
	}

	public Long getOrderMillToManufacture() {
		return orderMillToManufacture;
	}

	public void setOrderMillToManufacture(Long orderMillToManufacture) {
		this.orderMillToManufacture = orderMillToManufacture;
	}

	
		
	
}
