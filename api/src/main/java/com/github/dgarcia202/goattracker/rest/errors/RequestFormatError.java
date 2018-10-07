package com.github.dgarcia202.goattracker.rest.errors;

public class RequestFormatError extends ApiErrorBase {

    public RequestFormatError() {
        super(400, "Request data is either is malformed or does not have the expected structure.");
    }
}
