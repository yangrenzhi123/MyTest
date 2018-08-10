package com.zyxk.sevice.check;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("restriction")
public class Checkor {

	public static Logger logger = LogManager.getLogger();

	public static Boolean scaning = true;
	
	public static final Map<Integer, java.lang.Process> pm = new HashMap<Integer, java.lang.Process>();

	public static void justDo() {
		while(true){
			try{
				PConfig config = getConfig();
				
				if(scaning){
					logger.info("scan started");
					
					for(final Process process : config.getProcesses().getProcess()){
						PConfig pc = getConfig();
						if(new Integer(1).equals(pc.getSuspend())){
							break;
						}
						
						
						Socket socket = new Socket();
						
						boolean timeout = false;
						try{
							socket.connect(new InetSocketAddress("127.0.0.1", process.getPort()), 3000);
						}catch(SocketTimeoutException e){
							timeout = true;
						}catch(ConnectException e){
							timeout = true;
						}
						if(timeout){
							logger.info("start:"+process.getExec());
							
							java.lang.Process p = pm.get(process.getPort());
							if(p != null){
								p.destroy();
							}
							final java.lang.Process p2 = Runtime.getRuntime().exec(process.getExec());
							new Thread(new Runnable() {
								public void run() {
									try {
										getInputStream(p2);
									} catch (IOException e) {
										logger.error("", e);
									}
								}
							}).start();
							new Thread(new Runnable() {
								public void run() {
									try {
										getErrorStream(p2);
									} catch (IOException e) {
										logger.error("", e);
									}
								}
							}).start();
							pm.put(process.getPort(), p2);
							
							Long execTime = process.getExecTime();
							if(execTime != null){
								Thread.sleep(execTime);
							}else{
								Thread.sleep(30000);
							}
							logger.info("end:"+process.getExec());
						}

						socket.close();
					}
					logger.info("scan stoped");
				}
				
				Thread.sleep(config.getFrequency());
			}catch(Exception e){
				logger.error("", e);
			}
		}
	}

	public static PConfig getConfig() throws IOException, JAXBException{
		String configFilePath = System.getProperty("configFilePath");
		File file = new File(configFilePath);
		byte[] filecontent = new byte[(int)file.length()];
		FileInputStream in = new FileInputStream(file);  
        in.read(filecontent);  
        in.close();
		
		String xml = new String(filecontent);
		
		StreamSource streamSource = new StreamSource(new StringReader(xml));
		JAXBContext jaxbRes = JAXBContext.newInstance(PConfig.class.getPackage().getName(), PConfig.class.getClassLoader());
		Unmarshaller unmarshaller = jaxbRes.createUnmarshaller();
		JAXBElement<PConfig> reponseElement = unmarshaller.unmarshal(streamSource, PConfig.class);
		PConfig config = reponseElement.getValue();
		return config;
	}
	
	private static void getErrorStream(java.lang.Process p) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			//System.out.println(line);
		}
		br.close();
	}

	private static void getInputStream(java.lang.Process p) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			//System.out.println(line);
		}
		br.close();
	}
}