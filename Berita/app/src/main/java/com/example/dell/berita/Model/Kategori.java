package com.example.dell.berita.Model;

import com.google.gson.annotations.SerializedName;

public class Kategori {
    @SerializedName("id_kategori")
    private String id_kategori;
    @SerializedName("nama")
    private String nama;
    @SerializedName("photo_url")
    private String photoUrl;
    private String action;

    public Kategori(String id_kategori, String nama, String photoUrl, String
            action) {
        this.id_kategori = id_kategori;
        this.nama = nama;
        this.photoUrl = photoUrl;
        this.action = action;
    }
    public String getIdKategori() {
        return id_kategori;
    }
    public void setIdKategori(String idKategori) {
        this.id_kategori = id_kategori;
    }
    public String getNama(){
        return  nama;
    }
    public void setNama(String nama){
        this.nama=nama;
    }
    public String getPhotoUrl(){
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl=photoUrl;
    }
    public String getAction(){
        return action;
    }
    public void setAction(String action){
        this.action=action;
    }



}

