package com.atlwork.angularseed.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "EMAIL_NOT_REGISTERED_STATUS")
public class EmailNotRegisteredException extends RuntimeException {

}
