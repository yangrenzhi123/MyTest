import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

@SuppressWarnings("deprecation")
public class Sha1 {

	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		
		System.out.println(uuid);
		System.out.println(timestamp);
		System.out.println(DigestUtils.shaHex("jsapi_ticket=HoagFKDcsGMVCIY2vOjf9sTpVMbQ4kfaVd_UnjEDtjzMPG_GfL2z3EYhIYyrDrJF3tNcROy09MHRbafe43UtFg&noncestr=27ff9be5-4d0f-400c-8a10-aa15ec6eb4ba&timestamp=1542110890&url=http://www.yaoee.com:8080/"));
	}
}