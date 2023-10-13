package pet.store.controller.error;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;



// Tells Springs its a Controller Error
@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
 private enum LogStatus {
	 Stack_Trace, Message_Only
 }
  
 @Data
 private class ExceptionMessage{
	private String message; 
	private String statusReason; 
	private int statusCode; 
	private String timestamp; 
	private String uri; 
 }
 
 @ExceptionHandler(NoSuchElementException.class)
 @ResponseStatus(code = HttpStatus.NOT_FOUND)
 public ExceptionMessage handleNoSuchElementException(NoSuchElementException ex, WebRequest webRequest) {
	 return buildExceptionMessage(ex, HttpStatus.NOT_FOUND, webRequest, LogStatus.Message_Only); 
 }

private ExceptionMessage buildExceptionMessage(NoSuchElementException ex, HttpStatus status, WebRequest webRequest,
		LogStatus logStatus) {
	String message = ex.toString(); 
	String statusReason = status.getReasonPhrase(); 
	int statusCode = status.value(); 
	String uri = null; 
	String timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME); 
	
	if(webRequest instanceof ServletWebRequest swr) {
		uri = swr.getRequest().getRequestURI(); 	
	}
	
	if(logStatus == LogStatus.Message_Only) {
		log.error("Exception: {}", ex.toString());
	}
	
	else {
		log.error("Exception: ", ex);
	}
	
	ExceptionMessage excMsg = new ExceptionMessage(); 
	
	excMsg.setMessage(message);
	excMsg.setStatusCode(statusCode);
	excMsg.setStatusReason(statusReason);
	excMsg.setUri(uri);
	excMsg.setTimestamp(timestamp);
	
	return excMsg;
}
	
}
