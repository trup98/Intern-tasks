package com.springmvc.swaggerui.enums;


public enum CommonEnums {
    EXCEPTION_MSG("User Not Found With Given Id");
    private String message;
    CommonEnums(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
