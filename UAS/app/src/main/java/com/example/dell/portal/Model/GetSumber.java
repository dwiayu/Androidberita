package com.example.dell.portal.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetSumber {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Sumber> result = new ArrayList<Sumber>();
    @SerializedName("message")
    private String message;
    public GetSumber() {}
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Sumber> getResult() {
        return result;
    }

    public void setResult(List<Sumber> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
