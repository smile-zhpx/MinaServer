package com.ceprei.test.oa.base;

/**
 * 测试任务的ADT，记录了测试任务的关键信息(暂未使用)
 * @author lins
 */
public class TestTask {
	public String name;
	public long taskReceiveTime;
	public long taskExecuteTime;
	public long taskEndTime;
	
	//public Invoker invoker;
	public ITestExecutor iTestExecutor;
	
	public TestTyep testType;
	
	public TestResult run(){
		taskExecuteTime = System.currentTimeMillis();
		return iTestExecutor.excute();
	}
	
	public enum TestTyep{
		WebAppPerformanceTest;
	}
}
