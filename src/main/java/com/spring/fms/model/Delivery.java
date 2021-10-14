package com.spring.fms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_DELIVERY")
public class Delivery {
	
	@Id
	private Long id;
	
	@NotNull
	private Long orderToDelivery;
	
	@NotNull
	private boolean delivered;
	
	@NotNull
	private boolean notExist;
	
	public Delivery() {		
		this.orderToDelivery = 0L;
		this.delivered = false;
		this.notExist = false;
	}
	
	public Delivery(Long id, Long order, boolean delivered)
	{
		this.id = id;
		this.orderToDelivery = order;
		this.delivered = delivered;
		this.notExist = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderToDelivery() {
		return orderToDelivery;
	}

	public void setOrderToDelivery(Long orderToDelivery) {
		this.orderToDelivery = orderToDelivery;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public boolean isNotExist() {
		return notExist;
	}

	public void setNotExist(boolean notExist) {
		this.notExist = notExist;
	}
	
	
	
}
