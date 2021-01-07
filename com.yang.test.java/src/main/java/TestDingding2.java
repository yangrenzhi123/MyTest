import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestDingding2 {
	private static Logger logger = LoggerFactory.getLogger(TestDingding.class);

	public static String sendPostByMap(String url, Map<String, Object> mapParam) {
		Map<String, String> headParam = new HashMap<>();
		headParam.put("Content-type", "application/json;charset=UTF-8");
		return sendPost(url, mapParam, headParam);
	}

	public static String sendPost(String url, Map<String, Object> param, Map<String, String> headParam) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Fiddler");

			if (headParam != null) {
				for (Entry<String, String> entry : headParam.entrySet()) {
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(JSON.toJSONString(param));
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送 POST 请求出现异常！", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void test(String dingDingToken, String content) {
		Map<String, Object> text = new HashMap<>();
		text.put("content", content);

		Map<String, Object> atMobiles = new HashMap<>();
		List<String> mobiles = new ArrayList<>();
		mobiles.add("15313226225");
		atMobiles.put("atMobiles", mobiles);
		//atMobiles.put("isAtAll", true);

		Map<String, Object> json = new HashMap<>();
		json.put("msgtype", "text");
		json.put("text", text);
		json.put("at", atMobiles);

		String response = TestDingding.sendPostByMap(dingDingToken, json);
		System.out.println("相应结果：" + response);
	}

	public static void main(String[] args) {
		TestDingding2.test("https://oapi.dingtalk.com/robot/send?access_token=b1c200e3843d8c4f064c6c6697a39081474ba09fa4a796fbf6fee48b7a5f7a20", "test... \r\n");
	}
}