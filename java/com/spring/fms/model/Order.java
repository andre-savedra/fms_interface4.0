package com.spring.fms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TB_ORDER")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private User user;

	//@ManyToOne -new
	//private Machine machine;

	@OneToOne
	//@NotNull
	private ProcessOrder process;
	
	@ManyToOne
	private OrderType type;
	
	//@NotBlank
	//@NotNull
	private String dimensions;	

	//@NotBlank -new
	//@NotNull
	//@Lob
	//private String gcode;

	//@NotNull
	private Integer units;

	//@NotBlank
	private String ordername;

	private boolean produced;

	private Integer unitsProduced;
		
	private boolean manufacturing;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime inputDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime outputDate;

	@OneToOne
	private Model model;
	
	
	public ProcessOrder getProcess() {
		return process;
	}

	public void setProcess(ProcessOrder process) {
		this.process = process;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}*/

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrdername() {
		return ordername;
	}

	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}

	public boolean isProduced() {
		return produced;
	}

	public void setProduced(boolean produced) {
		this.produced = produced;
	}

	public Integer getUnitsProduced() {
		return unitsProduced;
	}

	public void setUnitsProduced(Integer unitsProduced) {
		this.unitsProduced = unitsProduced;
	}

	/*public String getGcode() {
		return gcode;
	}

	public void setGcode(String gcode) {
		this.gcode = gcode;
	}*/

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public LocalDateTime getInputDate() {
		return inputDate;
	}

	public void setInputDate(LocalDateTime inputDate) {
		this.inputDate = inputDate;
	}

	public LocalDateTime getOutputDate() {
		return outputDate;
	}

	public void setOutputDate(LocalDateTime outputDate) {
		this.outputDate = outputDate;
	}

	public boolean isManufacturing() {
		return manufacturing;
	}

	public void setManufacturing(boolean manufacturing) {
		this.manufacturing = manufacturing;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}		
	
	
	
	
}
