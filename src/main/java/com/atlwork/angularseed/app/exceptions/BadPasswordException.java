package com.atlwork.angularseed.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "BAD_PASSWORD_STATUS")
public class BadPasswordException extends RuntimeException {

}
