package com.example.dell.berita.Rest;

import com.example.dell.berita.Model.GetBerita;
import com.example.dell.berita.Model.GetKategori;
import com.example.dell.berita.Model.PostPutDelBerita;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterface {
    @GET("berita/user")
    Call<GetBerita> getBerita();

    @FormUrlEncoded
    @POST("berita/user")
    Call<PostPutDelBerita> postBerita
            (@Field("id_berita") String idBerita, @Field("id_sumber") String idSumber,
             @Field("id_kategori") String idKategori, @Field("judul") String judul,
             @Field("isi") String isi);
    @FormUrlEncoded
    @PUT("berita/user")
    Call<PostPutDelBerita> putBerita
            (@Field("id_berita") String idBerita, @Field("id_sumber") String idSumber,
             @Field("id_kategori") String idKategori, @Field("judul") String judul,
             @Field("isi") String isi);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "berita/user", hasBody = true)
    Call<PostPutDelBerita> deleteBerita(@Field("id_berita") String idBerita);
    //KATEGORI
    @GET("kategori/all")
    Call<GetKategori> getKategori();

    @Multipart
    @POST("kategori/all")
    Call<GetKategori> postKategori(
            @Part MultipartBody.Part file,
            @Part("nama") RequestBody nama,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("kategori/all")
    Call<GetKategori> putPembeli(
            @Part MultipartBody.Part file,
            @Part("id_kategori") RequestBody idKategori,
            @Part("nama") RequestBody nama,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("kategori/all")
    Call<GetKategori> deleteKategori(
            @Part("id_kategori") RequestBody idKategori,
            @Part("action") RequestBody action);


}
