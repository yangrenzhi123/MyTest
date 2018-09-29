package com.zyxk.sevice.check;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	public static void main(String[] args) throws Exception {
		new Thread(new Runnable() {
			public void run() {
				Checkor.justDo();
			}
		}).start();

		SpringApplication.run(BootController.class, args);
	}

    @RequestMapping("/ps")
    @ResponseBody
    public List<String> page1(String cmd) throws IOException{
    	List<String> out = new ArrayList<String>();
    	
    	
    	if(StringUtils.isEmpty(cmd)) {
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
    


    @RequestMapping("/test")
    public ModelAndView test2() throws IOException{
        ModelAndView mav = new ModelAndView("test");
        return mav;
    }

    @RequestMapping("/1111111111")
    public ModelAndView test() throws IOException{
    	List<String> out = new ArrayList<String>();
    	java.lang.Process p = Runtime.getRuntime().exec("netstat -anp | grep LISTEN");
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
    	
    	
    	
        ModelAndView mav = new ModelAndView("ps");
        mav.addObject("out", out);
        return mav;
    }
	
	@RequestMapping("/")
	@ResponseBody
	Boolean home() {
		return Checkor.scaning;
	}

	@RequestMapping("/0")
	@ResponseBody
	void end() throws IOException, JAXBException {
		Checkor.start = false;
	}

	@RequestMapping("/1")
	@ResponseBody
	void start() throws IOException, JAXBException {
		Checkor.start = true;
	}

	@RequestMapping("/suspend")
	@ResponseBody
	void suspend() throws IOException, JAXBException {
		Checkor.suspend = 60000;
	}

	@RequestMapping("/suspend/{value}")
	@ResponseBody
	void suspend2(@PathVariable(value = "value") int value) throws IOException, JAXBException {
		Checkor.suspend = value;
	}

	@RequestMapping("/process/info")
	@ResponseBody
	List<ProcessInfo> process() {
		List<ProcessInfo> l = new ArrayList<ProcessInfo>();
		try {
			Iterator<Entry<Integer, java.lang.Process>> iter = Checkor.pm.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<Integer, java.lang.Process> entry = (Map.Entry<Integer, java.lang.Process>) iter.next();
				Integer port = (Integer) entry.getKey();
				java.lang.Process process = (java.lang.Process) entry.getValue();

				ProcessInfo item = new ProcessInfo();
				item.setPort(port);
				item.setIsAlive(process.isAlive());
				l.add(item);
			}
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}

		return l;
	}
}