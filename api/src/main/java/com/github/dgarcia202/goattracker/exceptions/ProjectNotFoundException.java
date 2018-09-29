package com.github.dgarcia202.goattracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No project with that id has been found.")
public class ProjectNotFoundException extends RuntimeException {
}
