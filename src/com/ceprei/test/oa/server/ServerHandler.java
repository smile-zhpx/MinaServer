package com.ceprei.test.oa.server;

import net.sf.json.JSONObject;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.ceprei.test.oa.pt.ApacheBenchInvoker;

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
		
		ApacheBenchInvoker abi = new ApacheBenchInvoker();
		
		session.write(abi.run(msgIn));
	}
	
	@Override
	public void sessionClosed(IoSession session){
		System.out.println("Task end: " + session.getRemoteAddress());
	}
}
