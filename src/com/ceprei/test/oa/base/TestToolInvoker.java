package com.ceprei.test.oa.base;

import net.sf.json.JSONObject;

/**
 * 测试工具调用的ADT
 * @author lins
 */
public interface TestToolInvoker {
	public JSONObject run(JSONObject msg);
}
