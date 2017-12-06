package com.yang.test.servlet.springmvc.fileupload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

	@RequestMapping("/downloadExcel")
	public ResponseEntity<byte[]> downloadExcel() throws IOException {
		//读取模板
		HSSFWorkbook wb = new HSSFWorkbook(getClass().getResourceAsStream("1.xls"));
		
		
		
		//插入数据
		// 此处省略N行代码
		
		//获取字节流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        wb.write(baos);

		
		//下载
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attchement;filename=1.xls");
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(baos.toByteArray(), headers, statusCode);
		return entity;
	}
}