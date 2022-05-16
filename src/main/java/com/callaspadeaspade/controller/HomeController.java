package com.callaspadeaspade.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class HomeController {

	@Autowired
	private FileConverterService fileConverterService;

	@GetMapping
	@RequestMapping("/")
	public ModelAndView home() throws IOException {

		return new ModelAndView("home");
	}

	@PostMapping
	@RequestMapping("/docToPDF")

	public ModelAndView convertPDF(@RequestParam("wordFile") MultipartFile file, HttpServletResponse response)
			throws IOException {

		try {
			fileConverterService.setFileConverterStrategy(new PDFFileConverter());
			String fileName = fileConverterService.convert(file);
			InputStream pdfFile = new FileInputStream(new File(fileConverterService.getFileConverterStrategy().UPLOAD_DIR+ fileName));
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			IOUtils.copy(pdfFile, response.getOutputStream());
			pdfFile.close();
			return null;
		} catch (Exception e) {
			Map<String, String> error = new HashMap<String, String>();
			error.put("error", e.getMessage());
			return new ModelAndView("home", error);
		}

	}
	
	@PostMapping
	@RequestMapping("/PDFToDoc")

	public ModelAndView convertWord(@RequestParam("PDFFile") MultipartFile file, HttpServletResponse response)
			throws IOException {

		try {
			fileConverterService.setFileConverterStrategy(new WordFileConverter());
			String fileName = fileConverterService.convert(file);
			InputStream pdfFile = new FileInputStream(new File(fileConverterService.getFileConverterStrategy().UPLOAD_DIR+ fileName));
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			IOUtils.copy(pdfFile, response.getOutputStream());
			pdfFile.close();
			return null;
		} catch (Exception e) {
			Map<String, String> error = new HashMap<String, String>();
			error.put("error", e.getMessage());
			return new ModelAndView("home", error);
		}

	}


}
