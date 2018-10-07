package com.github.dgarcia202.goattracker.rest.errors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode
public abstract class ApiErrorBase {

    private Date timestamp;

    private Integer status;

    private String message;

    public ApiErrorBase(Integer status, String message) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
    }
}
