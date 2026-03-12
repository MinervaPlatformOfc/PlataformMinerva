package com.example.minerva.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ChatbotResponse {

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("session_id")
    private int sessionId;

    @JsonProperty("detail")
    private List<Map<String,Object>> detail;

    public ChatbotResponse(){}

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public int getSessionId(){
        return sessionId;
    }

    public void setSessionId(int sessionId){
        this.sessionId = sessionId;
    }

    public List<Map<String,Object>> getDetail(){
        return detail;
    }

    public void setDetail(List<Map<String,Object>> detail){
        this.detail = detail;
    }

    public String toString(){
        return "{\"msg\":" + this.msg +
                "\n\"session_id\":" + this.sessionId +
                "\n\"detail\":"+this.detail+"}";
    }
}