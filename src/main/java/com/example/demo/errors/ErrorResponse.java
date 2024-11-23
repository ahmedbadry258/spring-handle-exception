package com.example.demo.errors;

import java.util.List;


public class ErrorResponse {

    private String msg;
    private List<String> details;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public ErrorResponse(String msg, List<String> details) {
        super();
        this.msg = msg;
        this.details = details;
    }


}
