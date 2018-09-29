package com.github.dgarcia202.goattracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The referenced object can't be found.")
public class NotFoundException extends RuntimeException {
}
