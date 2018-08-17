package com.yang.test.servlet.springmvc.fileupload;

import java.io.File;
import java.io.IOException;

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
	public void uploadAjax(@RequestParam MultipartFile file) throws IllegalStateException, IOException {
		String fileName = file.getOriginalFilename();
		
		
		File save = new File("C:/upload/"+fileName);
		
		file.transferTo(save);
	}
}