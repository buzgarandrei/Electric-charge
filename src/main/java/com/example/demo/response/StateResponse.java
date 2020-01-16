package com.example.demo.response;

public class StateResponse {
    private boolean success;

    public StateResponse(boolean success) {
        this.success = success;
    }

    public StateResponse() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
