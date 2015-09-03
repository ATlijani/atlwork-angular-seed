package com.atlwork.angularseed.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DO_NOT_MATCH_CURRENT_STATUS")
public class DoNotMatchCurrentPasswordException extends RuntimeException {

}
