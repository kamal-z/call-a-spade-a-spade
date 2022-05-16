package com.callaspadeaspade.controller.exception;

public class NoFileChoosedException extends Exception {
	
	public NoFileChoosedException () {
		super("Please, choose a File to convert.");
	}

}
