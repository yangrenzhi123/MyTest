package com.zyxk.sevice.check;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class BootController {

	public static Logger logger = LogManager.getLogger();
	
	@RequestMapping("/")
	@ResponseBody
	Boolean home() {
		return Checkor.scaning;
	}


	@RequestMapping("/process/info")
	@ResponseBody
	List<ProcessInfo> process() {
		List<ProcessInfo> l = new ArrayList<ProcessInfo>();
		try{
			Iterator<Entry<Integer, java.lang.Process>> iter = Checkor.pm.entrySet().iterator();
			while (iter.hasNext()) {
			      Map.Entry<Integer, java.lang.Process> entry = (Map.Entry<Integer, java.lang.Process>) iter.next();
			      Integer port = (Integer)entry.getKey();
			      java.lang.Process process = (java.lang.Process) entry.getValue();

			      ProcessInfo item = new ProcessInfo();
			      item.setPort(port);
			      item.setIsAlive(process.isAlive());
			      l.add(item);
			}
		}catch(Exception e){
			logger.error("", e);
			throw new RuntimeException(e);
		}
		
		return l;
	}

	
	public static void main(String[] args) throws Exception {
		new Thread(new Runnable() {
			public void run() {
				Checkor.justDo();
			}
		}).start();
		
		
		SpringApplication.run(BootController.class, args);
	}
}