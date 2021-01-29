package cftimesheet.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

//    @ExceptionHandler(EOFException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public String resourceNotFoundException(EOFException ex, WebRequest request) {
//        return "Lost Connection to the db";
//    }

}