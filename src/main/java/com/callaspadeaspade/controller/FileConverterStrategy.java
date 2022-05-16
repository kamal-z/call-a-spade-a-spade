package com.callaspadeaspade.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.callaspadeaspade.controller.exception.NoFileChoosedException;
import com.callaspadeaspade.controller.exception.PDFExtentionException;

public interface FileConverterStrategy {
	
	public static final String UPLOAD_DIR="/app/myfiles"; 
	
	String convert(MultipartFile file) throws IOException, PDFExtentionException, NoFileChoosedException;
	
	void validateExtention(String fileName) throws PDFExtentionException, NoFileChoosedException;
	
	default void upload(MultipartFile file) throws IllegalStateException, IOException {
		file.transferTo(new File(UPLOAD_DIR + file.getOriginalFilename()));
		
	};

}
