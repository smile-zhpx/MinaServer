package com.ceprei.test.oa.st;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * 调用DoSend工具进行网络攻击
 * @author lins
 */
public class DoSendInvoker {
	public JSONObject run(){
		JSONObject result = new JSONObject();
		
		Process process = null;  
        List<String> processList = new ArrayList<String>();  
        try {  
            process = Runtime.getRuntime().exec("/home/ceprei/DoSend -d 127.0.0.1 -y 23 -n 10");
//            process.getOutputStream().write("123456".getBytes());
//        	process = Runtime.getRuntime().exec("/home/ceprei/apache-jmeter-2.11/bin/jmeter");
            process.waitFor();
           // Thread.sleep(5000);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));  
            String line = "";  
            while ((line = input.readLine()) != null) {  
                processList.add(line);  
            }  
            input.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        for (String line : processList) {  
            System.out.println(line);  
        }  
        
		return result;
	}
	
	public static void main(String[] args){
		DoSendInvoker dsi = new DoSendInvoker();
		dsi.run();
	}
}
