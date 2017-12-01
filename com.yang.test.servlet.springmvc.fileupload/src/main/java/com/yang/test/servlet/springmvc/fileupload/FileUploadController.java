package com.yang.test.servlet.springmvc.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(@RequestParam MultipartFile file) {
		System.out.println();
	}

	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST)
	@ResponseBody
	public void uploadAjax(@RequestParam MultipartFile file) {
		System.out.println();
	}

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download() throws IOException {
		File file = new File("D:/123.png");
		byte[] body = null;
		InputStream is = new FileInputStream(file);
		body = new byte[is.available()];
		is.read(body);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attchement;filename=" + file.getName());
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);

		is.close();
		return entity;
	}

	@RequestMapping("/downloadExcel")
	public ResponseEntity<byte[]> downloadExcel() throws IOException {
		byte[] body = null;
		InputStream is = getClass().getResourceAsStream("1.xls");
		body = new byte[is.available()];
		is.read(body);
		// 以上都是excel数据获取

		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attchement;filename=1.xls");
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
		is.close();
		return entity;
	}
}