package dtos.error;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) 
	{
		ErrorDetail errDetail = new ErrorDetail();
		errDetail.setTimeStamp(new Date().getTime());
		errDetail.setDetail("Thanks Issy");
		errDetail.setDeveloperMessage(rnfe.getMessage());
		return new ResponseEntity<>(errDetail, HttpStatus.NOT_FOUND);
	}
}
