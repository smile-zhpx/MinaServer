package com.ceprei.test.oa.server;

import net.sf.json.JSONObject;

import org.apache.jmeter.protocol.http.sampler.HTTPSampleResult;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.ceprei.test.oa.jmeter.JMeterLoadGenerator;

public class ServerHandler extends IoHandlerAdapter {
	@Override
	public void sessionOpened(IoSession session) {
		System.out.println("Task from: " + session.getRemoteAddress());
	}

	/**
	 * 
	 */
	@Override
	public void messageReceived(IoSession session, Object message) {
		JSONObject msgIn = (JSONObject) message;
		System.out.println("Message: " + msgIn.toString());
		
		int loop = msgIn.getInt("t_num");
		
		JMeterLoadGenerator jmlg = new JMeterLoadGenerator();
		HTTPSampleResult result = jmlg.startLoadGenerating(msgIn);
		
		JSONObject msgOut = new JSONObject();
		msgOut.accumulate("latency", result.getLatency());
		msgOut.accumulate("start_time", result.getStartTime());
		msgOut.accumulate("end_time", result.getEndTime());
		session.write(msgOut);
	}
	
	@Override
	public void sessionClosed(IoSession session){
		System.out.println("Task end: " + session.getRemoteAddress());
	}
}
