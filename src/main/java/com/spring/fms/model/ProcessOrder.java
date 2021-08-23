package com.spring.fms.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_PROCESS_ORDER")
public class ProcessOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@OneToMany
	private List<StepOrder> steps;
		
	private boolean concluded;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<StepOrder> getSteps() {
		return steps;
	}

	public void setSteps(List<StepOrder> steps) {
		this.steps = steps;
	}

	public boolean isConcluded() {
		return concluded;
	}

	public void setConcluded(boolean concluded) {
		this.concluded = concluded;
	}
	
	
	
}

