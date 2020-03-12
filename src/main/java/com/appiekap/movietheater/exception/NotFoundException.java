package com.appiekap.movietheater.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundException
 * @author N00B 1337
 */
@ResponseStatus(value= HttpStatus.I_AM_A_TEAPOT, reason="No movie with that id or title found")
public class NotFoundException extends RuntimeException{

}