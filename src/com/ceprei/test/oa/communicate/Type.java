package com.ceprei.test.oa.communicate;

public enum Type {
	JMETER("performance test using apache jmeter on web application.");
	
	String description;
	private Type(String arg0){
		this.description = arg0;
	}
	
	public String getDescription(){
		return description;
	}
}
