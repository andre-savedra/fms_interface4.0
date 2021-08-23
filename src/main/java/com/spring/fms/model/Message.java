package com.spring.fms.model;

public class Message {
	
	private String name;
	private String channel;
	private String message;
		
	
	public Message(String name, String channel, String message) {		
		this.name = name;
		this.channel = channel;
		this.message = message;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}	

}
