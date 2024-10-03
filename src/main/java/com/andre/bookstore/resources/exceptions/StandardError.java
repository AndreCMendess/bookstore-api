package com.andre.bookstore.resources.exceptions;

public class StandardError {

    private long timestamp;
    private Integer status;
    private String error;

    public StandardError() {
        super();
    }

    public StandardError(String error, long timestamp, Integer status) {
        super();
        this.error = error;
        this.timestamp = timestamp;
        this.status = status;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}