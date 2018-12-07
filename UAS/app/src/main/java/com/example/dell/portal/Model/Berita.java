package com.example.dell.portal.Model;

import com.google.gson.annotations.SerializedName;

public class Berita {
    @SerializedName("id_berita")
    private String id_berita;
    @SerializedName("id_sumber")
    private String id_sumber;
    @SerializedName("id_kategori")
    private String id_kategori;
    @SerializedName("judul")
    private String judul;
    @SerializedName("isi")
    private String isi;
    @SerializedName("gambar")
    private String gambar;
    private String action;
    public Berita(String id_berita, String id_sumber, String id_kategori, String judul, String isi,String gambar) {
        this.id_berita = id_berita;
        this.id_sumber = id_sumber;
        this.id_kategori = id_kategori;
        this.judul = judul;
        this.isi = isi;
        this.gambar= gambar;
        this.action= action;
    }
    public String getId_berita() {
        return id_berita;
    }
    public void setId_berita(String id_berita) {
        this.id_berita = id_berita;
    }

    public String getId_sumber() {
        return id_sumber;
    }

    public void setId_sumber(String id_sumber) {
        this.id_sumber = id_sumber;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
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

    public void setGambar(String gambar) {
        this.gambar= gambar;
    }
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}

