package com.ceprei.test.oa.communicate;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	public Type type = null;

	public String from = "";
	public String to = "";

	public JSONObject content = new JSONObject();

	public String toString() {
		return (this.type.getDescription() + "\nfrom " + from + " to " + to + content
				.toString());
	}

	public static void main(String[] args) {
		JSONObject a = new JSONObject();
		a.accumulate("123123", "sdada");
		a.accumulate("!!!!", "@@@@");
		System.out.println(a.toString());
	}
}
