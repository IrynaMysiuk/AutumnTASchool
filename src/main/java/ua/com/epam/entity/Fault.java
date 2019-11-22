package ua.com.epam.entity;

import java.util.Objects;

public class Fault {
    private String timeStamp;
    private int statusCode;
    private String error;
    private String errorMessage;

    public Fault() {
    }

    public Fault(String timeStamp, int statusCode, String error, String errorMessage) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fault fault = (Fault) o;
        return statusCode == fault.statusCode &&
                Objects.equals(error, fault.error) &&
                Objects.equals(errorMessage, fault.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, error, errorMessage);
    }
}
