package com.yang.test.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class Jpush {

	public static void main(String[] args) throws APIConnectionException, APIRequestException {
		String masterSecret = "e826403e2ed31a1d603f5b0a";
		String appKey = "729d0a0b7fc81a1442f04075";
		JPushClient jpc = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());

		//PushPayload p = PushPayload.alertAll("i am a content");
		PushPayload p = PushPayload.newBuilder()
        .setPlatform(Platform.all())
        .setAudience(Audience.alias("1.0.0")) //箱体编号+大屏App设备编号
        .setNotification(Notification.alert("1"))
        .build();

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