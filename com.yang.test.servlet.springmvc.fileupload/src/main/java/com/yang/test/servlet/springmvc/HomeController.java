package com.yang.test.servlet.springmvc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "")
@Controller
public class HomeController {
	
	@RequestMapping(value = "/bcm/getWord", method = RequestMethod.POST)
	@ResponseBody
	public byte[] bcmGetWord(@RequestBody String d) throws IOException {
		File file = new File(this.getClass().getClassLoader().getResource("交行结汇通知书.doc").getFile());
		return common(d, file);
	}
	
	@RequestMapping(value = "/icbc/getWord", method = RequestMethod.POST)
	@ResponseBody
	public byte[] icbcGetWord(@RequestBody String d) throws IOException {
		File file = new File(this.getClass().getClassLoader().getResource("工行结汇通知书.doc").getFile());
		return common(d, file);
	}
	
	@RequestMapping(value = "/ecitic/getWord", method = RequestMethod.POST)
	@ResponseBody
	public byte[] eciticGetWord(@RequestBody String d) throws IOException {
		File file = new File(this.getClass().getClassLoader().getResource("中信结汇通知书.doc").getFile());
		return common(d, file);
	}

	@RequestMapping(value = "/boc/getWord", method = RequestMethod.POST)
	@ResponseBody
	public byte[] bocGetWord(@RequestBody String d) throws IOException {
		File file = new File(this.getClass().getClassLoader().getResource("中行结汇通知书.doc").getFile());
		return common(d, file);
	}

	private byte[] common(String d, File file) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		WordData data = mapper.readValue(d, WordData.class);
		InputStream fis = new FileInputStream(file);
		POIFSFileSystem pfs = new POIFSFileSystem(fis);
		HWPFDocument doc = new HWPFDocument(pfs);
		Range range = doc.getRange(); 
		range.replaceText("{currencyName}", data.currencyName);
		range.replaceText("{bankAccount}", data.bankAccount);
		range.replaceText("{amountCn}", data.amountCn);
		range.replaceText("{amount}", data.amount);
		range.replaceText("{targetBankAccount}", data.targetBankAccount);
		range.replaceText("{currency}", data.currency);
		range.replaceText("{time}", dateFormat.format(new Date()));
		range.replaceText("{man}", URLDecoder.decode(data.man, "UTF-8"));
		
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		doc.write(swapStream);
		return swapStream.toByteArray();
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\111.doc");
		InputStream fis = new FileInputStream(file);/*
		POIFSFileSystem pfs = new POIFSFileSystem(fis);*/
		HWPFDocument doc = new HWPFDocument(fis);/*
		Range range = doc.getRange(); 
		range.replaceText("{currencyName}", "1");
		range.replaceText("{bankAccount}", "2");
		range.replaceText("{amountCn}", "3");
		range.replaceText("{amount}", "4");
		range.replaceText("{targetBankAccount}", "5");*/
		
		System.out.println(doc.getDataStream().length);
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		doc.write(swapStream);
		swapStream.toByteArray();
	}
}