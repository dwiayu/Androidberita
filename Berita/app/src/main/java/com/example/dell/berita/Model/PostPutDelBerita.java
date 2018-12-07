package com.example.dell.berita.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelBerita {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Berita mBerita;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Berita getBerita() {
        return mBerita;
    }
    public void setBerita(Berita berita) {
        mBerita = berita;
    }
}
