package com.callaspadeaspade.controller;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.callaspadeaspade.controller.exception.NoFileChoosedException;
import com.callaspadeaspade.controller.exception.PDFExtentionException;

@Service
public class FileConverterService{
	
	private FileConverterStrategy fileConverterStrategy;
	
	
	public String convert(MultipartFile file) throws IOException, PDFExtentionException, NoFileChoosedException {
		return fileConverterStrategy.convert(file);
		
	}

	public FileConverterStrategy getFileConverterStrategy() {
		return fileConverterStrategy;
	}

	public void setFileConverterStrategy(FileConverterStrategy fileConverterStrategy) {
		this.fileConverterStrategy = fileConverterStrategy;
	}
	
	
	
	
	
	

}
