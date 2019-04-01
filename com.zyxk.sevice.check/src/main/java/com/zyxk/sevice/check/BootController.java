package com.zyxk.sevice.check;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAutoConfiguration
public class BootController {

	public static Logger logger = LogManager.getLogger();

//    @Bean
//    public FilterRegistrationBean testFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new ExceptionFilter(123));//添加过滤器
//        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
//        registration.setName("ExceptionFilter");//设置优先级
//        registration.setOrder(1);//设置优先级
//        return registration;
//    }

	public static void main(String[] args) throws Exception {
		new Thread(new Runnable() {
			public void run() {
				Checkor.justDo();
			}
		}).start();

		SpringApplication.run(BootController.class, args);
	}

	@RequestMapping("/runStatus")
	@ResponseBody
	public String runStatus() throws IOException, JAXBException {
		PConfig c = Checkor.getConfig();
		Integer start = c.getStart();

		if (start == 1 && Checkor.start == true) {
			return "运行中";
		} else {
			return "停止运行";
		}
	}

	@RequestMapping("/serviceStatus")
	@ResponseBody
	public List<String> status() throws IOException, JAXBException {
		List<String> out = new ArrayList<String>();

		PConfig c = Checkor.getConfig();
		if (c != null && c.getProcesses() != null && c.getProcesses().getProcess() != null
				&& c.getProcesses().getProcess().size() > 0) {
			for (Process element : c.getProcesses().getProcess()) {
				String[] command = { "/bin/sh", "-c", "netstat -anp | grep -w LISTEN | grep -w " + element.getPort() };
				java.lang.Process p = Runtime.getRuntime().exec(command);

				out.add("[[element.getPort()]]");

				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
				String line;
				while ((line = br.readLine()) != null) {
					out.add(line);
				}
				br.close();

				br = new BufferedReader(new InputStreamReader(p.getErrorStream(), "GBK"));
				while ((line = br.readLine()) != null) {
					out.add(line);
				}
				br.close();
			}
		}

		return out;
	}

	@RequestMapping("/ps")
	@ResponseBody
	public List<String> page1(String cmd) throws IOException {
		List<String> out = new ArrayList<String>();

		if (StringUtils.isEmpty(cmd)) {
			cmd = "netstat -anp | grep LISTEN";
		}
		java.lang.Process p = Runtime.getRuntime().exec(cmd);
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			out.add(line);
		}
		br.close();

		br = new BufferedReader(new InputStreamReader(p.getErrorStream(), "GBK"));
		while ((line = br.readLine()) != null) {
			out.add(line);
		}
		br.close();
		return out;
	}

	@RequestMapping("/console")
	public ModelAndView test2() {
		ModelAndView mav = new ModelAndView("console");
		return mav;
	}

	@RequestMapping("/0")
	@ResponseBody
	void end() {
		Checkor.start = false;
	}

	@RequestMapping("/1")
	@ResponseBody
	void start() {
		Checkor.start = true;
	}

	@RequestMapping("/suspend")
	@ResponseBody
	void suspend() {
		Checkor.suspend = 60000;
	}

	@RequestMapping("/suspend/{value}")
	@ResponseBody
	void suspend2(@PathVariable(value = "value") int value) {
		Checkor.suspend = value;
	}

	@RequestMapping("/getSuspend")
	@ResponseBody
	long suspend2() {
		return Checkor.suspend;
	}
}