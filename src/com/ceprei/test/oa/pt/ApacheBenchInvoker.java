package com.ceprei.test.oa.pt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import com.ceprei.test.oa.base.TestToolInvoker;

import net.sf.json.JSONObject;

/**
 * 调用ApacheBench测试工具的类
 * @author lins
 */
public class ApacheBenchInvoker implements TestToolInvoker{
	/**
	 * 
	 * ab -c 10 -t 1 http://www.google.com/必须以斜杠结尾
	 * @param msg
	 *            传送过来的请求信息，请求内包含url，t_num，method，think，start_delay，duration，load_policy
	 */
	public JSONObject run(JSONObject msg) {
		// 初始化执行结果
		//JSONObject result = new JSONObject();

		// 根据传送过来的消息msg构建命令行
		StringBuilder cmd = new StringBuilder();
		cmd.append("ab");
		cmd.append(" -c " + msg.getInt("t_num"));
		cmd.append(" -t " + msg.getInt("duration") * 60);
		cmd.append(" " + msg.getString("url"));

		System.out.println("开始执行 " + cmd.toString());

		// 调用测试工具，执行测试
		StringBuilder resultStr = new StringBuilder();
		Process process = null;
		BufferedReader input = null;
		try {
			process = Runtime.getRuntime().exec(cmd.toString());
			process.waitFor();
			// Thread.sleep(msg.getInt("duration")*60*1000+5000);
			input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));//读取测试工具的执行结果
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

		// 构建并返回测试结果
		msg.accumulate("result", resultStr.toString());

		return msg;
	}

	public static void main(String[] args) throws MalformedURLException {
		StringBuilder resultStr = new StringBuilder();
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(
					"ab -c 10 -t 30 http://www.baidu.com/");
			process.waitFor();
			// Thread.sleep(msg.getInt("duration")*60*1000+5000);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			int i = 1;
			while ((line = input.readLine()) != null) {
				if (i++ > 7)
					resultStr.append(line + "\n");
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(resultStr);
	}
}
