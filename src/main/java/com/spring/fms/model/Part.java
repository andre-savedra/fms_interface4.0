package com.spring.fms.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_PART")
public class Part {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Lob
	private String pathImg;
			
	@ManyToOne
	private OrderType type;
	
	@OneToOne
	@NotNull
	private ProcessPart process;
	
	@ManyToMany
	private List<Model> model;	
	
	//@NotBlank - new
	//@Lob
	//private String gcode;
		
	//@ManyToOne - new
	//private Machine machine;
	
	//@OneToMany - new
	//private List<Customization> customizations;

	
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

	public String getPathImg() {
		return pathImg;
	}

	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}

	public ProcessPart getProcess() {
		return process;
	}

	public void setProcess(ProcessPart process) {
		this.process = process;
	}

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}

	public List<Model> getModel() {
		return model;
	}

	public void setModel(List<Model> model) {
		this.model = model;
	}
	
	

	/*public String getGcode() {
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

	public List<Customization> getCustomizations() {
		return customizations;
	}

	public void setCustomizations(List<Customization> customizations) {
		this.customizations = customizations;
	}
	*/

}
