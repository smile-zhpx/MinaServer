package com.ceprei.test.oa.pt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class ApacheBenchInvoker {
	/**
	 * 
	 * ab -c 10 -t 1 http://www.google.com/必须以斜杠结尾
	 * 
	 * @param msg
	 *            url t_num method think start_delay duration load_policy
	 */
	public JSONObject run(JSONObject msg) {
		JSONObject result = new JSONObject();

		StringBuilder cmd = new StringBuilder();
		cmd.append("ab");
		cmd.append(" -c " + msg.getInt("t_num"));
		cmd.append(" -t " + msg.getInt("duration") * 60);
		cmd.append(" " + msg.getString("url"));
		
		System.out.println("开始执行 "+cmd.toString());

		StringBuilder resultStr = new StringBuilder();
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(cmd.toString());
			process.waitFor();
			// Thread.sleep(msg.getInt("duration")*60*1000+5000);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			int i = 1;
			while ((line = input.readLine()) != null) {
				if (i++ > 7)
					resultStr.append(line+"<br/>");
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.accumulate("result", resultStr.toString());

		return result;
	}

	public static void main(String[] args) throws MalformedURLException {
		StringBuilder resultStr = new StringBuilder();
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ab -c 10 -t 30 http://www.baidu.com/");
			process.waitFor();
			// Thread.sleep(msg.getInt("duration")*60*1000+5000);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			int i = 1;
			while ((line = input.readLine()) != null) {
				if (i++ > 7)
					resultStr.append(line+"\n");
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(resultStr);
	}
}
