package com.microservice.MicroServiceApp;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


  @Controller
  
  @ControllerAdvice
  
  @RestController public class ResponseEntityHandler extends
  ResponseEntityExceptionHandler {
  
  
  
  
  @ExceptionHandler(Exception.class) public final ResponseEntity<Object>
  allHandleException(Exception ex,WebRequest request){ ExceptionResponse
  exceptionResponse=new ExceptionResponse( new
  Date(),ex.getMessage(),request.getDescription(false)); return new
  ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR); }
  
  
  
  
  @ExceptionHandler(UserFoundNotException.class) public final
  ResponseEntity<Object> userNotFoundHandleException(UserFoundNotException
  ex,WebRequest request){ ExceptionResponse exceptionResponse= new
  ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
  return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
  
  }
  
  @Override protected ResponseEntity<Object>
  handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
  headers, HttpStatus status, WebRequest request){
  
  ExceptionResponse exceptionResponse=new ExceptionResponse( new
  Date(),"Validation Failed of name",ex.getBindingResult().toString()); return
  new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST); } }
 
 
