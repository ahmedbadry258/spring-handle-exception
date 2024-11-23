package com.example.demo.errors;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestControllerAdvice
@RestControllerAdvice(basePackages = {"com.example.demo.controller"})
//@RestControllerAdvice(assignableTypes = {EmployeeController.class})
public class GlobalException extends ResponseEntityExceptionHandler {
    private static final String BASE_TYPE_URI = "https://egabifsi.com/errors";

    @ExceptionHandler(FieldException.class)
    public ProblemDetail handleFieldException(FieldException ex, WebRequest req) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        problemDetail.setType(URI.create(BASE_TYPE_URI +"/field-input-error"));
        problemDetail.setTitle("Field Exception");
        System.out.println(URI.create(req.getDescription(false).substring(4)));
        problemDetail.setInstance(URI.create(req.getDescription(false).substring(4)));
        problemDetail.setProperty(ex.getKey(),ex.getValue());
        return problemDetail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleEmployeeException(IllegalArgumentException ex, WebRequest req) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        problemDetail.setType(URI.create(BASE_TYPE_URI +"/argument-error"));
        return problemDetail;
    }

    @ExceptionHandler(RecordNotFoundException.class)

    public final ProblemDetail handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        problemDetail.setType(URI.create(BASE_TYPE_URI +"/record-not-found-error"));
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleExceptions(Exception ex, WebRequest req) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return problemDetail;
    }

    //	  @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
