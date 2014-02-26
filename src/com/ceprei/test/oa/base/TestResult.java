package com.ceprei.test.oa.base;

import java.util.List;

import org.apache.jmeter.assertions.AssertionResult;
import org.apache.jmeter.samplers.SampleResult;

public class TestResult {
	public static final byte[] EMPTY_BA = new byte[0];

	private byte[] responseData = EMPTY_BA;

	private String responseCode = "";// Never return null

	private String label = "";// Never return null

	/** Filename used by ResultSaver */
	private String resultFileName = "";

	/** The data used by the sampler */
	private String samplerData;

	private String threadName = ""; // Never return null

	private String responseMessage = "";

	private String responseHeaders = ""; // Never return null

	private String contentType = ""; // e.g. text/html; charset=utf-8

	private String requestHeaders = "";

	// TODO timeStamp == 0 means either not yet initialised or no stamp
	// available (e.g. when loading a results file)
	/** the time stamp - can be start or end */
	private long timeStamp = 0;

	private long startTime = 0;

	private long endTime = 0;

	private long idleTime = 0;// Allow for non-sample time

	/** Start of pause (if any) */
	private long pauseTime = 0;

	private List<AssertionResult> assertionResults;

	private List<SampleResult> subResults;

	private String dataType = ""; // Don't return null if not set

	private boolean success;

	public byte[] getResponseData() {
		return responseData;
	}

	public void setResponseData(byte[] responseData) {
		this.responseData = responseData;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getResultFileName() {
		return resultFileName;
	}

	public void setResultFileName(String resultFileName) {
		this.resultFileName = resultFileName;
	}

	public String getSamplerData() {
		return samplerData;
	}

	public void setSamplerData(String samplerData) {
		this.samplerData = samplerData;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(String responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(String requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(long idleTime) {
		this.idleTime = idleTime;
	}

	public long getPauseTime() {
		return pauseTime;
	}

	public void setPauseTime(long pauseTime) {
		this.pauseTime = pauseTime;
	}

	public List<AssertionResult> getAssertionResults() {
		return assertionResults;
	}

	public void setAssertionResults(List<AssertionResult> assertionResults) {
		this.assertionResults = assertionResults;
	}

	public List<SampleResult> getSubResults() {
		return subResults;
	}

	public void setSubResults(List<SampleResult> subResults) {
		this.subResults = subResults;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
