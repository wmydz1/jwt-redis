package com.logoocc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// @ExceptionHandler(SQLException.class)
	// public String handleSQLException(HttpServletRequest request, Exception
	// ex){
	// logger.info("SQLException Occured:: URL="+request.getRequestURL());
	// return "database_error";
	// }

	// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException
	// occured")
	// @ExceptionHandler(IOException.class)
	// public void handleIOException() {
	// logger.error("IOException handler executed");
	// // returning 404 error code
	// }

	// @Autowired
	// private ErrorAttributes errorAttributes;

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Map<String, Object>> handle(HttpServletRequest request, Exception exception) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		// Map body = this.errorAttributes.getErrorAttributes(requestAttributes,
		// false);
		Map customMsg = new HashMap<String, Object>();
		customMsg.put("message", "some para is missing !");

		// return new ResponseEntity<Map<String, Object>>(body,
		// HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Map<String, Object>>(customMsg, HttpStatus.BAD_REQUEST);
	}

}
