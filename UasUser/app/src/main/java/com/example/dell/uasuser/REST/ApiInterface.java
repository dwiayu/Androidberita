package com.example.dell.uasuser.REST;

import com.example.dell.uasuser.Model.GetKategori;
import com.example.dell.uasuser.Model.GetUser;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @GET("index.php/user/all")
    Call<GetKategori> getKategori();

    //USER profil
    @GET("index.php/userp/all")
    Call<GetUser> getUser();
    @Multipart
    @POST("index.php/userp/all")
    Call<GetUser> putUser(
            @Part MultipartBody.Part file,
            @Part("id_user") RequestBody idUser,
            @Part("nama") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("email") RequestBody email,
            @Part("action") RequestBody action
    );





}
