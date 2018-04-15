package com.udemy.fragmentmail;

/**
 * Created by INSPIRON on 15/4/2018.
 */

public class Mail {
    private String subject;
    private String message;
    private String senderName;
    private String color;

    public Mail(String subject,String message, String senderName){
        this.subject = subject;
        this.message = message;
        this.senderName = senderName;
        this.color = Util.getRandomColor();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
