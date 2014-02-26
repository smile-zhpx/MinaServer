package com.ceprei.test.oa.base;

public interface ITestExecutor {
	public TestResult excute();
	public TestResult getTestResult();
	public void setTestSchedule(TestSchedule arg0);
	public TestSchedule getTestSchedule();
	public void setTestMonitor(TestMonitor arg0);
	public TestMonitor getTestMonitor();
	//public void get
}
