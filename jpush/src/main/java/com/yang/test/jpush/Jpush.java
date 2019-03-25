package com.yang.test.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

public class Jpush {

	public static void main(String[] args) throws APIConnectionException, APIRequestException {
		String masterSecret = "e826403e2ed31a1d603f5b0a";
		String appKey = "729d0a0b7fc81a1442f04075";
		JPushClient jpc = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());

		PushPayload p = PushPayload.alertAll("i am a content");

		try {
			PushResult r = jpc.sendPush(p);
			System.out.println(r);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			System.out.println(e.getMessage());
		}
	}
}