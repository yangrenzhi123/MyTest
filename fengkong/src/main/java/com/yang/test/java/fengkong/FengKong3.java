package com.yang.test.java.fengkong;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.dingxianginc.ctu.client.CtuClient;
import com.dingxianginc.ctu.client.model.CtuRequest;
import com.dingxianginc.ctu.client.model.CtuResponse;
import com.dingxianginc.ctu.client.model.RiskLevel;

public class FengKong3 {

	public static void main(String[] args) throws Exception {
        /**业务请求数据**/
		Map<String, Object> data = new HashMap<>();
		data.put("user_id", 1024);//用户 ID
		data.put("ext_grouptID", "1024");//居民ID（一张卡对应一个ID）
		data.put("ext_orderID", "20200606007");//订单编号
		data.put("ext_rewardScore", 90);//投放奖励积分
		/*获取流程：01获取前，02获取成功，03获取失败
		通过：01获取前，02获取成功，03获取失败
		复审：01获取前，通过02获取成功，拒绝03获取失败
		拒绝：01获取前，拒绝03获取失败*/
		data.put("ext_state", "01");
		
		data.put("const_id", "exxxxxxwbZsF1PqoflWOyhKLIhAzw9X2");//设备指纹 token，端上获取传入后台
		data.put("phone_number", "13800138011");//手机号
		data.put("source", 2);//登录来源
		data.put("ip", "127.0.0.1");//电子秤ip
		data.put("ext_projectID", "222");//项目id
		data.put("ext_userphone", "13800138011");//居民手机号
		data.put("ext_scoreUserID", "111");//居民积分账户ID
		data.put("ext_scoreBalance", 111.0);//居民ID账户可用积分
		data.put("ext_moneyBalance", 11.1);//居民ID账户可用余额
		data.put("ext_inDate", "2020-06-03 15:00:00");//投放时间（格式要求）yyyy-MM-dd HH:mm:ss
		data.put("ext_recoverType", "01");//投放品类编号
		data.put("ext_scoreType", "02");//积分获取渠道（积分类型：分好啦，电子秤，智慧云等)
		data.put("ext_inWeight", 111.11);//投放重量
		data.put("ext_inPrice", 111.2);//投放单价
		data.put("ext_weightScore", 11.1);//投放计重积分
		data.put("ext_weightMoney", 11.11);//投放计重金额
		data.put("ext_rewardMoney", 222.22);//投放奖励金额
		data.put("ext_recoverBox", "19520577");//电子秤ID
		data.put("ext_electronicType", "01");//电子秤类型：01一体式电子秤，02分离式电子秤
		data.put("ext_userType", 1);//居民类型：1-绿色用户，2-业主，3-大型商户，4-演示卡
		data.put("ext_version", "1.0");//版本号
		data.put("ext_reviewState", "01");//失败类型：01人工拒绝，02业务处理异常
        /**创建一个请求数据实例**/
        CtuRequest request = new CtuRequest();
        /**设置事件编码**/
        request.setEventCode("gain");
        /**设置该次风控请求的业务数据**/
        request.setData(data);
        request.setFlag("activity_" + System.currentTimeMillis());
        /**创建一个客户端实例**/
        CtuClient client = new CtuClient("http://192.168.10.76:7776/ctu/event.do", "2ca619fa85132afb3b24629e10db2f19", "4fac986d0cf32ce5430edc53a407b694");
        /**CtuClient client=new CtuClient(url,appKey,appSecret,connectTimeout,connectionRequestTimeout,socketTimeout)
		         用户可以自定义超时设置 connectTimeout，connectionRequestTimeout，socketTimeout 单位：毫秒
		         默认超时设置均为 2000 毫秒
		         实时决策接入手册
         **/
        /**向风控引擎发送请求，获取引擎返回的结果**/
        CtuResponse response = client.checkRisk(request);
        if (RiskLevel.ACCEPT.equals(response.getResult().getRiskLevel())) {
        	System.out.println(1);
            System.out.printf(JSON.toJSONString(response));
            //...业务代码，当前请求没有风险
        } else if (RiskLevel.REVIEW.equals(response.getResult().getRiskLevel())) {
        	System.out.println(2);
            System.out.printf(JSON.toJSONString(response));
            //...业务代码，当前请求有一定风险，建议复审
        } else if (RiskLevel.REJECT.equals(response.getResult().getRiskLevel())) {
        	System.out.println(3);
            System.out.printf(JSON.toJSONString(response));
            //...业务代码，当前请求有风险，建议拒绝
        }
	}
}