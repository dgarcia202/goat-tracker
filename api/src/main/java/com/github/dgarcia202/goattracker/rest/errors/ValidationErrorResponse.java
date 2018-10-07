package com.github.dgarcia202.goattracker.rest.errors;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse extends ApiErrorResponse {

    @Getter
    private List<ValidationErrorDetail> details = new ArrayList<>();

    public ValidationErrorResponse() {
        super(400, "Validation error.");
    }
}
