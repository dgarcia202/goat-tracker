package com.github.dgarcia202.goattracker.rest.errors;

import lombok.Value;

@Value
public class ValidationErrorDetail {
    private String field;

    private Object rejectedValue;

    private String message;
}
