package com.example.dell.portal.Model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBerita {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Berita> listDataBerita;
    @SerializedName("message")
    String message;
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Berita> getListDataBerita() {
        return listDataBerita;
    }

    public void setListDataBerita(List<Berita> listDataBerita) {
        this.listDataBerita = listDataBerita;
    }



}
