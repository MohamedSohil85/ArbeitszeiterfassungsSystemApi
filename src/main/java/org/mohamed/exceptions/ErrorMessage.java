package org.mohamed.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrorMessage {

    private  String error_Message;
    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss" ,timezone = "Europe/Berlin")
    private Date error_Date;

    public ErrorMessage(String message, Date date) {
    }
    public String getError_Message() {
        return error_Message;
    }

    public void setError_Message(String error_Message) {
        this.error_Message = error_Message;
    }

    public Date getError_Date() {
        return error_Date;
    }

    public void setError_Date(Date error_Date) {
        this.error_Date = error_Date;
    }
}
