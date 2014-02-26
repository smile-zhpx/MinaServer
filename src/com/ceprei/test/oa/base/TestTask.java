package com.ceprei.test.oa.base;

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
