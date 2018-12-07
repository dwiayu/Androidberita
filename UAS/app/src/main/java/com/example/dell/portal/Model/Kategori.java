package com.example.dell.portal.Model;

import com.google.gson.annotations.SerializedName;

public class Kategori {
    @SerializedName("id_kategori")
    private String idKategori;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("photo_url")
    private String photoUrl;
    private String action;

    public Kategori(String idKategori, String kategori, String photoUrl, String action){
        this.idKategori= idKategori;
        this.kategori= kategori;
        this.photoUrl=photoUrl;
        this.action= action;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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
