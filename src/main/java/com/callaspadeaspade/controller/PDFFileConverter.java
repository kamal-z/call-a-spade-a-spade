package com.callaspadeaspade.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;

import com.callaspadeaspade.controller.exception.NoFileChoosedException;
import com.callaspadeaspade.controller.exception.PDFExtentionException;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class PDFFileConverter implements FileConverterStrategy{
	
	private List<String> allowedWordExtention = new ArrayList<String>() {{
		add("doc");
		add("docx");
	}};

	@Override
	public String convert(MultipartFile file) throws IOException, PDFExtentionException, NoFileChoosedException {
		String fileName = file.getOriginalFilename();
		validateExtention(fileName);
		upload(file);
		FileInputStream docFile = new FileInputStream(new File(UPLOAD_DIR + fileName));
		String newFileName = fileName.split("\\.")[0] + ".pdf";
		XWPFDocument doc = new XWPFDocument(docFile);
		OutputStream out = new FileOutputStream(new File(UPLOAD_DIR + newFileName));
		PdfConverter.getInstance().convert(doc, out, PdfOptions.create());
		doc.close();
		out.close();
		return newFileName;
		 
	}

	@Override
	public void validateExtention(String fileName) throws PDFExtentionException, NoFileChoosedException {
		 if (fileName.isEmpty()) {
			 throw new NoFileChoosedException();
		 }
		
		if (!allowedWordExtention.contains(fileName.split("\\.")[1])) {
			throw new PDFExtentionException("Wrong extention, Please choose an doc extention e.g doc, docx");
		}
		
	}

}
