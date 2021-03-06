package com.ceprei.test.oa.st;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

/**
 * 调用Nmap进行数据库注入
 * @author lins
 */
public class NmapInvoker {
	/**
	 *
	 */
	public JSONObject run(JSONObject msg) {
		//JSONObject result = new JSONObject();

		StringBuilder cmd = new StringBuilder();
		cmd.append("ab");
		cmd.append(" -c " + msg.getInt("t_num"));
		cmd.append(" -t " + msg.getInt("duration") * 60);
		cmd.append(" " + msg.getString("url"));

		System.out.println("开始执行 " + cmd.toString());

		StringBuilder resultStr = new StringBuilder();
		Process process = null;
		BufferedReader input = null;
		try {
			process = Runtime.getRuntime().exec(cmd.toString());
			process.waitFor();
			// Thread.sleep(msg.getInt("duration")*60*1000+5000);
			input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			int i = 1;
			while ((line = input.readLine()) != null) {
				if (i++ > 7)
					resultStr.append(line + "<br/>");
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (process != null)
				process.destroy();

			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		msg.accumulate("result", resultStr.toString());

		return msg;
	}

	public static void main(String[] args) {

	}
}
