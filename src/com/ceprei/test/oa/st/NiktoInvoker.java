package com.ceprei.test.oa.st;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ceprei.test.oa.base.TestToolInvoker;

import net.sf.json.JSONObject;

/**
 * 开源工具Nikto的云化实现
 * 
 * @author ceprei
 *
 */
public class NiktoInvoker implements TestToolInvoker {
	/**
	 *
	 */
	public JSONObject run(JSONObject msg) {
		JSONObject result = new JSONObject();

		 StringBuilder cmd = new StringBuilder();
		 cmd.append("perl /home/ceprei/download/nikto-2.1.5/nikto.pl");
		 cmd.append(" -h " + msg.getString("url"));
		 cmd.append(" -C all" );

		 System.out.println("执行端开始执行 " + cmd.toString());

		StringBuilder resultStr = new StringBuilder();
		Process process = null;
		BufferedReader input = null;
		try {
			process = Runtime.getRuntime().exec(cmd.toString());
//			process = Runtime
//					.getRuntime()
//					.exec("perl /home/ceprei/download/nikto-2.1.5/nikto.pl -h http://127.0.0.1:8080/cloud-testing/ -C all");
			process.waitFor();
			// Thread.sleep(msg.getInt("duration")*60*1000+5000);
			input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			int i = 1;
			while ((line = input.readLine()) != null) {
				if (i++ > 3)
					resultStr.append(line + "<br/>");
				// System.out.println(line);
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

		result.accumulate("result", resultStr.toString());

		return result;
	}

	public static void main(String[] args) {
		NiktoInvoker ni = new NiktoInvoker();
		ni.run(null);
	}
}
