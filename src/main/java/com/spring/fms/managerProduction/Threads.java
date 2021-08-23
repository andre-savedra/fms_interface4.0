package com.spring.fms.managerProduction;

public class Threads extends Thread {

	private boolean allDone = false;
	
	public Threads() {
		super();
		allDone = false;		
	}

	public Threads(Runnable target, String name) {
		super(target, name);
		allDone = false;
		}

	public Threads(Runnable target) {
		super(target);
		allDone = false;
		}

	public Threads(String name) {
		super(name);
		allDone = false;
		}

	public Threads(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
		allDone = false;
		}

	public Threads(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		allDone = false;
		}

	public Threads(ThreadGroup group, Runnable target) {
		super(group, target);
		allDone = false;
		}

	public Threads(ThreadGroup group, String name) {
		super(group, name);
		allDone = false;
		}

	public boolean isAllDone() {
		return allDone;
	}

	public void setAllDone(boolean allDone) {
		this.allDone = allDone;
	}


	
	

}
