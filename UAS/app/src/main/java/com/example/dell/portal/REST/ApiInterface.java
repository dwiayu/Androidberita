package com.example.dell.portal.REST;

import com.example.dell.portal.Model.GetBerita;
import com.example.dell.portal.Model.GetKategori;
import com.example.dell.portal.Model.GetSumber;
import com.example.dell.portal.Model.PostPutDellBerita;

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

        @GET("index.php/berita/user")
        Call<GetBerita> getBerita();

    @FormUrlEncoded
    @POST("index.php/berita/user")
    Call<PostPutDellBerita> postBerita
            (@Field("id_berita") String idBerita, @Field("id_sumber") String idSumber,
             @Field("id_kategori") String idKategori, @Field("judul") String judul,
             @Field("isi") String isi);

    @FormUrlEncoded
    @PUT("index.php/berita/user")
    Call<PostPutDellBerita> putBerita(
            @Field("id_berita") String idBerita, @Field("id_sumber") String idSumber,
            @Field("id_kategori") String idKategori, @Field("judul") String judul,
            @Field("isi") String isi);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "index.php/berita/user", hasBody = true)
    Call<PostPutDellBerita> deleteBerita(@Field("id_berita") String idBerita);

//    KATEGORI

    @GET("index.php/kategori/all")
    Call<GetKategori> getKategori();

    @Multipart
    @POST("index.php/kategori/all")
    Call<GetKategori> postKategori(
            @Part MultipartBody.Part file,
            @Part("kategori") RequestBody kategori,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("index.php/kategori/all")
    Call<GetKategori> putKategori(
            @Part MultipartBody.Part file,
            @Part("id_kategori") RequestBody idKategori,
            @Part("kategori") RequestBody kategori,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("index.php/kategori/all")
    Call<GetKategori> deleteKategori(
            @Part("id_kategori") RequestBody idKategori,
            @Part("action") RequestBody action);

//SUMBER
@GET("index.php/sumber/all")
Call<GetSumber> getSumber();

    @Multipart
    @POST("index.php/sumber/all")
    Call<GetSumber> postSumber(
            @Part MultipartBody.Part file,
            @Part("nama_sumber") RequestBody namaSumber,
            @Part("pj_sumber") RequestBody pjSumber,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("index.php/sumber/all")
    Call<GetSumber> putSumber(
            @Part MultipartBody.Part file,
            @Part("id_sumber") RequestBody idSumber,
            @Part("nama_sumber") RequestBody namaSumber,
            @Part ("pj_sumber") RequestBody pjSumber,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("index.php/sumber/all")
    Call<GetSumber> deleteSumber(
            @Part("id_sumber") RequestBody idSumber,
            @Part("action") RequestBody action);
}
