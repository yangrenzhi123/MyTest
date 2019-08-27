package com.example.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/** 注意Controller包在同包或者子包下 */
@SpringBootApplication
public class MonitorStartup {

	@Autowired
	MonitorForMysql monitorForMysql;
	@Autowired
	MonitorForKafka monitorForKafka;
	@Autowired
	MonitorForRedis monitorForRedis;
	@Autowired
	MonitorForService monitorForService;
	@Autowired
	MonitorForDevice monitorForDevice;
	@Autowired
	MonitorForDisk monitorForDisk;

	@Bean
	public String getInitor() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				try {
					monitorForMysql.execute();
					monitorForKafka.execute();
					monitorForRedis.execute();
					monitorForService.execute();
					monitorForDevice.execute();
					monitorForDisk.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 0, 300000);

		return "1";
	}

	public static final Map<String, MoniResult> result = new HashMap<String, MoniResult>();

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(MonitorStartup.class, args);
	}
}

@RestController
class HelloController {

	@Autowired
	MonitorForMysql dbMonitor;

	@GetMapping("/info")
	public List<String> info() throws ClassNotFoundException, SQLException {
		List<String> l = new ArrayList<>();
		DateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Iterator<Entry<String, MoniResult>> iter = MonitorStartup.result.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, MoniResult> entry = (Map.Entry<String, MoniResult>) iter.next();
			Object key = entry.getKey();
			MoniResult val = (MoniResult) entry.getValue();

			l.add("检测时间：" + yyyy.format(val.getCheckTime()) + "，结果：" + (val.getResult() == 1 ? "成功" : "<span style='color:red'>失败</span>") + "，" + val.getName() + "，备注：" + key);
		}
		
		Collections.sort(l);
		return l;
	}

	@RequestMapping("/")
	public ModelAndView index() throws IOException {
		ModelAndView mav = new ModelAndView("console");
		return mav;
	}
}