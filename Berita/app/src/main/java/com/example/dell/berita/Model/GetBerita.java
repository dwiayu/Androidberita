package com.example.dell.berita.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetBerita {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Berita> result = new ArrayList<Berita>();
    @SerializedName("message")
    private String message;

    public GetBerita() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Berita> getResult() {
        return result;
    }

    public void setResult(List<Berita> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}