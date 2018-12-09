package com.example.dell.uasuser.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("nama")
    private String nama;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("email")
    private String email;
    @SerializedName("photo_url")
    private String photoUrl;
    @SerializedName("action")
    private String action;

    public User(String nama, String alamat, String email, String photoUrl, String action){
        this.nama=nama;
        this.alamat= alamat;
        this.email= email;
        this.photoUrl=photoUrl;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
