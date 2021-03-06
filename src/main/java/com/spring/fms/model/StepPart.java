package com.spring.fms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="TB_STEP_PART")
public class StepPart {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Lob
	private String gcode;
	
	@ManyToOne
	private Machine machine;
	
	
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

	public String getGcode() {
		return gcode;
	}

	public void setGcode(String gcode) {
		this.gcode = gcode;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

				
	
}
