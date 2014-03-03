package com.ceprei.test.oa.pt;

import java.net.MalformedURLException;

import net.sf.json.JSONObject;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampleResult;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

/**
 * 调用JMeter测试工具的类(暂未完全搞定)
 * @author lins * 
 */
public class JMeterInvoker {

	public static void loadGen() {
		// 初始化jmeter引擎
		StandardJMeterEngine engine = new StandardJMeterEngine();
		JMeterUtils.loadJMeterProperties("src/jmeter.properties");
		JMeterUtils.loadProperties("src/saveservice.properties");
		JMeterUtils.setJMeterHome(".");

		ResultCollector s = new ResultCollector();
		s.setFilename("ss");
		
		// http测试样例
		HTTPSampler httpSampler = new HTTPSampler();
		httpSampler.setDomain("www.baidu.com");
		httpSampler.setPort(80);
		httpSampler.setPath("/");
		httpSampler.setMethod("GET");
		httpSampler.setName("我的httpsampler");
		

		// 循环控制器初始化
		LoopController loopCtrl = new LoopController();
		loopCtrl.setLoops(100);
		loopCtrl.addTestElement(httpSampler);
		loopCtrl.setFirst(true);
		loopCtrl.setName("我的loopcrtl");
		

		// 设置线程组初始化
		SetupThreadGroup setupThreadGroup = new SetupThreadGroup();
		setupThreadGroup.setNumThreads(1);
		setupThreadGroup.setRampUp(1);
		setupThreadGroup.setSamplerController(loopCtrl);
		setupThreadGroup.setName("我的setupthreadgroup");
		//setupThreadGroup.addTestElement(s);
						
		// 初始化测试plan
		TestPlan testPlan = new TestPlan("我的test plan");
//		testPlan.addThreadGroup(setupThreadGroup);
//		testPlan.addTestElement(s);
		
		// 测试配置
		HashTree config = new HashTree();
		config.add("testPlan", testPlan);
		config.add("loopCtrl", loopCtrl);
		config.add("threadGroup", setupThreadGroup);
		config.add("httpSampler", httpSampler);
		config.add("summariser", s);
		
		// 配置jmeter引擎
		engine.configure(config);		
		// 开始测试
		engine.run();
		
//		JMeterThread jmt = new JMeterThread(config, setupThreadGroup, new ListenerNotifier());
//		jmt.run();
		// JMeterContextService.getContext().get;		
//		SampleResult sr = httpSampler.sample();
//		sr.getSampleCount();
	}

	/**
	 * url t_num method think start_delay duration load_policy
	 * @param msg
	 */
	public HTTPSampleResult run(JSONObject msg) {
		HTTPSampler httpSampler = new HTTPSampler();
		
		String url = msg.getString("url").replaceAll("^http://", "");
		httpSampler.setDomain(url);
		httpSampler.setPort(80);
		//httpSampler.setPath("/cloud-testing/");
		httpSampler.setMethod(msg.getString("method"));

		
		return ((HTTPSampleResult) httpSampler.sample());		
	}

	public static void main(String[] args) throws MalformedURLException {
		JMeterInvoker.loadGen();
	}

}
