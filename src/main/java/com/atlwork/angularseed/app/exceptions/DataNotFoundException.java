package com.atlwork.angularseed.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "DATA_NOT_FOUND_STATUS")
public class DataNotFoundException extends RuntimeException {

}
