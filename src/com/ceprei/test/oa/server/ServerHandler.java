package com.ceprei.test.oa.server;

import net.sf.json.JSONObject;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.ceprei.test.oa.base.TestToolInvoker;
import com.ceprei.test.oa.pt.ApacheBenchInvoker;
import com.ceprei.test.oa.st.NiktoInvoker;

public class ServerHandler extends IoHandlerAdapter {
	@Override
	public void sessionOpened(IoSession session) {
		System.out.println("执行端探测到web端接入: " + session.getRemoteAddress());
	}

	/**
	 * 
	 */
	@Override
	public void messageReceived(IoSession session, Object message) {
		JSONObject msgIn = (JSONObject) message;
		System.out.println("执行端接收到来自web端的信息: " + msgIn.toString());

		TestToolInvoker tti = null;

		String type = msgIn.getString("type");
		if (type.equals("pt"))
			tti = new ApacheBenchInvoker();
		else if(type.equals("st"))
			tti = new NiktoInvoker();

		session.write(tti == null ? null : tti.run(msgIn));
		System.out.println("执行端完成测试请求: " + msgIn.toString());
	}

	@Override
	public void sessionClosed(IoSession session) {
		System.out.println("执行端探测到web端离开: " + session.getRemoteAddress());
	}
}
