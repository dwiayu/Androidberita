package com.example.dell.uasuser.Model;

import com.google.gson.annotations.SerializedName;

public class Kategori {
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("photo_url")
    private String photoUrl;
    @SerializedName("judul")
    private  String judul;
    @SerializedName("isi")
    private String isi;
    @SerializedName("nama_sumber")
    private String namaSumber;
    public Kategori(String kategori,String judul, String isi, String namaSumber, String photoUrl){
    this.kategori= kategori;
    this.judul = judul;
    this.isi= isi;
    this.namaSumber = namaSumber;
    this.photoUrl=photoUrl;

    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
    public String getNamaSumber(){
        return namaSumber;
    }

    public void setNamaSumber(String namaSumber) {
        this.namaSumber = namaSumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
