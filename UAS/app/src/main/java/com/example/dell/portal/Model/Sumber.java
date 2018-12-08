package com.example.dell.portal.Model;

import com.google.gson.annotations.SerializedName;

public class Sumber {
    @SerializedName("id_sumber")
    private String idSumber;
    @SerializedName("nama_sumber")
    private String namaSumber;
    @SerializedName("pj_sumber")
    private String pjSumber;
    private String action;
    public Sumber(String idSumber, String namaSumber, String pjSumber, String action){
        this.idSumber= idSumber;
        this.namaSumber= namaSumber;
        this.pjSumber=pjSumber;
        this.action= action;
    }

    public String getIdSumber() {
        return idSumber;
    }

    public void setIdSumber(String idSumber) {
        this.idSumber = idSumber;
    }

    public String getNamaSumber() {
        return namaSumber;
    }

    public void setNamaSumber(String namaSumber) {
        this.namaSumber = namaSumber;
    }

    public String getPjSumber() {
        return pjSumber;
    }

    public void setPjSumber(String pjSumber) {
        this.pjSumber = pjSumber;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
