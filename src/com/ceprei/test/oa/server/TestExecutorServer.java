package com.ceprei.test.oa.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 用于监听网络传输并处理请求的主类
 * @author lins
 */
public class TestExecutorServer {

	public static void main(String[] args) {
		IoAcceptor acpt = new NioSocketAcceptor();
		acpt.getSessionConfig().setReadBufferSize(512);
		acpt.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
//		acpt.getFilterChain().addLast(
//				"filter-x",
//				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
//						.forName("UTF-8"), LineDelimiter.UNIX.getValue(),
//						LineDelimiter.UNIX.getValue())));
		
		acpt.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		
		acpt.setHandler(new TestExecutorSeverHandler());
		try {
			acpt.bind(new InetSocketAddress(9100));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(new Date()) + " 执行端服务器启动.");
	}

}
