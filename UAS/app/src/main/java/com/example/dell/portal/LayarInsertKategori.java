package com.example.dell.portal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.portal.Model.GetKategori;
import com.example.dell.portal.REST.ApiClient;
import com.example.dell.portal.REST.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class LayarInsertKategori extends AppCompatActivity {
    Context mContext;
    ImageView mImageView;
    Button btAddPhotoId, btAddBack, btAddData;
    EditText edtAddIdKategori, edtAddNama;
    TextView tvAddMessage;
    String imagePath = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_kategori);
        Context mContext;
        ImageView mImageView;
        Button btAddPhotoId, btAddBack, btAddData;
        EditText edtAddIdKategori, edtAddNamaPembeli;
        TextView tvAddMessage;
        String imagePath = "";

        btAddData = (Button) findViewById(R.id.btAddData);
        btAddBack = (Button) findViewById(R.id.btAddBack);
        tvAddMessage = (TextView) findViewById(R.id.tvAddMessage);

        btAddData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                MultipartBody.Part body = null;

                if (!imagePath.isEmpty()){
                    // Buat file dari image yang dipilih
                    File file = new File(imagePath);

                    // Buat RequestBody instance dari file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);

                    // MultipartBody.Part digunakan untuk mendapatkan nama file
                    body = MultipartBody.Part.createFormData("photo_url", file.getName(),
                            requestFile);
                }
                RequestBody reqNama = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNama.getText().toString().isEmpty())?"":edtAddNama.getText().toString());

                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "insert");
                Call<GetKategori> mKategoriCall = mApiInterface.postKategori(body, reqNama,
                        reqAction );

            }
        }
    }
}
