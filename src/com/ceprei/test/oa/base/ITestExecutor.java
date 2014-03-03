package com.ceprei.test.oa.base;

/**
 * 测试执行的接口(暂未使用)
 * @author lins
 */
public interface ITestExecutor {
	public TestResult excute();
	public TestResult getTestResult();
	public void setTestSchedule(TestSchedule arg0);
	public TestSchedule getTestSchedule();
	public void setTestMonitor(TestMonitor arg0);
	public TestMonitor getTestMonitor();
	//public void get
}
