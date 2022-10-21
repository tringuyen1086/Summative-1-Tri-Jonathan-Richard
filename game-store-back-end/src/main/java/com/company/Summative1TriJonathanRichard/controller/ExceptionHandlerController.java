package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.exception.NotFoundException;
import com.company.Summative1TriJonathanRichard.model.CustomErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    //created as part of the record store app on Oct. 6, 2022 in class.
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<List<CustomErrorResponse>> newResponseErrors(MethodArgumentNotValidException e) {
        // BindingResult holds the validation errors
        BindingResult result = e.getBindingResult();
        // Validation errors are stored in FieldError objects
        List<FieldError> fieldErrors = result.getFieldErrors();
        // Translate the FieldErrors to CustomErrorResponse
        List<CustomErrorResponse> errorResponseList = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            CustomErrorResponse errorResponse = new CustomErrorResponse(returnHttpStatus, fieldError.getDefaultMessage());
//            errorResponse.setTimestamp(LocalDateTime.now());
//            errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            errorResponseList.add(errorResponse);
        }
        // Create and return the ResponseEntity
        ResponseEntity<List<CustomErrorResponse>> responseVal = new ResponseEntity<>(errorResponseList, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseVal;
    }

    @ExceptionHandler(value = MismatchedInputException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomErrorResponse> handleMismatchedError(MismatchedInputException e) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, e.getMessage());
        ResponseEntity<CustomErrorResponse> responseVal = new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseVal;
    }
    @ExceptionHandler(value ={InvalidFormatException.class})
    public ResponseEntity<CustomErrorResponse> handleHttpMessageNotReadableException(InvalidFormatException ex){
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, ex.getMessage());
        ResponseEntity<CustomErrorResponse> returnVal = new ResponseEntity<>(error, returnHttpStatus);
        return returnVal;
    }

    @ExceptionHandler(value ={NotFoundException.class})
    public ResponseEntity<CustomErrorResponse> handleNotFoundException(NotFoundException ex){
        HttpStatus returnHttpStatus = HttpStatus.NOT_FOUND;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, ex.getMessage());
        ResponseEntity<CustomErrorResponse> returnVal = new ResponseEntity<>(error, returnHttpStatus);
        return returnVal;
    }

}
