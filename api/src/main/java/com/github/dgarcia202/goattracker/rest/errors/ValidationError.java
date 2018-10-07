package com.github.dgarcia202.goattracker.rest.errors;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ApiErrorBase {

    @Getter
    private List<ValidationErrorDetail> details = new ArrayList<>();

    public ValidationError() {
        super(400, "Validation error.");
    }
}
