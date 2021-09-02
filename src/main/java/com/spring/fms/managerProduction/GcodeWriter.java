package com.spring.fms.managerProduction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GcodeWriter {

	private String filename, path, content;
	private FileWriter gcodeFile;
	
	public GcodeWriter() {
		
	}
	
	public GcodeWriter(String filename, String path, String content) {
		this.filename = filename;
		this.path = path;
		this.content = content;
	}

	public GcodeWriter(String filename, String path) {
		this.filename = filename;
		this.path = path;
	}

	public boolean createFile(String filename, String path, String content) {
		String fullPath = path + "\\" + filename;
		System.out.println(fullPath);
		try {
			gcodeFile = new FileWriter(new File(fullPath));
			gcodeFile.write(content);
			gcodeFile.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean createFile(String content) {

		String fullPath = path + "\\" + filename;
		System.out.println(fullPath);
		try {
			gcodeFile = new FileWriter(new File(fullPath));
			gcodeFile.write(content);
			gcodeFile.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean createFile() {
		String fullPath = path + "\\" + filename;
		System.out.println(fullPath);
		try {
			gcodeFile = new FileWriter(new File(fullPath));
			gcodeFile.write(content);
			gcodeFile.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FileWriter getGcodeFile() {
		return gcodeFile;
	}

	public void setGcodeFile(FileWriter gcodeFile) {
		this.gcodeFile = gcodeFile;
	}
	
	

}
