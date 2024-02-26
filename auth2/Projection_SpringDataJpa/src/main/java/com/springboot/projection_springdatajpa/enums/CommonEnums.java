package com.springboot.projection_springdatajpa.enums;

public enum CommonEnums {
    EXCEPTION_MSG("User Not Found : ");
    private String errors;

    CommonEnums(String errors) {
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
