package com.spring.fms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<StepOrder> steps = new ArrayList<StepOrder>();
		
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
	
	
	public void resetStepsProcess(){
		setConcluded(true);
		
		for(StepOrder step : getSteps())
		{
			step.resetStep();
		}
	}
	
}

