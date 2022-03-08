package com.example.apidemo.Springboot.ErrorCode;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class NotValidException extends RuntimeException{
    private static final long serialVersionUID = 7214770022462460376L;
    private final Set<ConstraintViolation<Object>> validations;


    public NotValidException(String massage, Set<ConstraintViolation<Object>> validations) {
        super(massage);
        this.validations = validations;
    }

    public Set<ConstraintViolation<Object>> getValidatons() {
        return validations;
    }
}
