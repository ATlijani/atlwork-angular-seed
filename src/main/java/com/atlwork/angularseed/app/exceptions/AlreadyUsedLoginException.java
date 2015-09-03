package com.atlwork.angularseed.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ALREADY_USED_LOGIN_STATUS")
public class AlreadyUsedLoginException extends RuntimeException {

}
