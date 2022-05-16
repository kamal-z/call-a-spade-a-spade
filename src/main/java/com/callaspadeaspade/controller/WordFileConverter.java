package com.callaspadeaspade.controller;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.callaspadeaspade.controller.exception.NoFileChoosedException;
import com.callaspadeaspade.controller.exception.PDFExtentionException;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
 
 

public class WordFileConverter implements FileConverterStrategy {

	@Override
	public String convert(MultipartFile file) throws IOException, PDFExtentionException, NoFileChoosedException {
		String fileName = file.getOriginalFilename();
		validateExtention(fileName);
		upload(file);
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile(UPLOAD_DIR + fileName);
        String newFileName = fileName.split("\\.")[0] + ".docx";
        doc.saveToFile(UPLOAD_DIR + newFileName , FileFormat.DOCX);
        doc.close();
		return newFileName;
	}

	@Override
	public void validateExtention(String fileName) throws PDFExtentionException, NoFileChoosedException {
		 if (fileName.isEmpty()) {
			 throw new NoFileChoosedException();
		 }
		
		if (!fileName.endsWith(".pdf")) {
			throw new PDFExtentionException("Wrong extention, Please choose an pdf extention");
		}
		
		
	}

}
