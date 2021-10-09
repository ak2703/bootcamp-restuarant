package me.akshayvilekar.bootcamprestuarant.menu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class MenuAdvice {

  @ResponseBody
  @ExceptionHandler(MenuNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String menuNotFoundHandler(MenuNotFoundException ex) {
    return ex.getMessage();
  }
  
  @ResponseBody
  @ExceptionHandler(MenuCannotBeDeletedException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String menuCannotBeDeletedHandler(MenuCannotBeDeletedException ex) {
    return ex.getMessage();
  }
}