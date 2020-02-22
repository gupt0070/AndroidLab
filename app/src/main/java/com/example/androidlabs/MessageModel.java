package com.example.androidlabs;


public class MessageModel {
    private String message;
    private String type;
    private   long id;

    public MessageModel(Long id , String message, String type) {
        this.id = id;
        this.message = message;
        this.type = type;
    }

    public MessageModel(String message, boolean isSend) {
        this.message = message;
        this.type = isSend ? "SEND" : "RECEIVE";
    }


    public String getMessage() {
        return message;
    }
    public long getId(){ return id;}
    public void setId(long id){this.id= id;}


    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSend() {
        return type != null && type.equalsIgnoreCase("SEND") ;
    }


    public String getType(){return type;}
    public void setType(String type){this.type = type; }


}