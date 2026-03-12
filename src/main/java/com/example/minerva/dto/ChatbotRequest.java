package com.example.minerva.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatbotRequest {

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("session_id")
    private int sessionId;

    public ChatbotRequest(String msg, int sessionId){
        this.msg = msg;
        this.sessionId = sessionId;
    }

    public ChatbotRequest(String msg, String userName, int sessionId){
        this.msg = msg;
        this.userName = userName;
        this.sessionId = sessionId;
    }

    public String getMsg(){
        return this.msg;
    }

    public int getSessionId(){
        return this.sessionId;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String toString(){
        return "{\"msg\":"+this.msg+
                "\"user_name\":"+this.userName+
                "\"session_id\":"+this.sessionId+"}";
    }
}
