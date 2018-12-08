package com.example.dell.portal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.portal.Model.GetKategori;
import com.example.dell.portal.Model.GetSumber;
import com.example.dell.portal.REST.ApiClient;
import com.example.dell.portal.REST.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarInsertSumber extends AppCompatActivity {
    Context mContext;
    ImageView mImageView;
    Button  btAddBack, btAddData;
    EditText edtAddIdSumber, edtAddNama, edtAddPj;
    TextView tvAddMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_sumber);
        mContext = getApplicationContext();


        edtAddIdSumber= (EditText) findViewById(R.id.edtIdSumber);
        edtAddNama = (EditText) findViewById(R.id.edAddNama);
        edtAddPj = (EditText) findViewById(R.id.edAddPj);

        btAddData = (Button) findViewById(R.id.btAddData);
        btAddBack = (Button) findViewById(R.id.btAddBack);
        tvAddMessage = (TextView) findViewById(R.id.tvAddMessage);
        btAddData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                MultipartBody.Part body = null;
                RequestBody reqNama = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddNama.getText().toString().isEmpty()) ? "" : edtAddNama.getText().toString());
                RequestBody reqPj = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (edtAddPj.getText().toString().isEmpty()?"": edtAddPj.getText().toString()));
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "insert");
                Call<GetSumber> mSumberCall = mApiInterface.postSumber(body, reqNama,reqPj,
                        reqAction);
                mSumberCall.enqueue(new Callback<GetSumber>() {

                    @Override
                    public void onResponse(Call<GetSumber> call, Response<GetSumber> response) {
                        if (response.body().getStatus().equals("failed")) {
                            tvAddMessage.setText("Retrofit Insert \n Status = " + response.body()
                                    .getStatus() + "\n" +
                                    "Message = " + response.body().getMessage() + "\n");

                        } else {
                            String detail = "\n" +
                                    "id_sumber = " + response.body().getResult().get(0).getIdSumber() + "\n" +
                                    "nama_sumber = " + response.body().getResult().get(0).getNamaSumber() + "\n" +
                                    "pj_sumber = " + response.body().getResult().get(0).getPjSumber()
                                    + "\n";
                            tvAddMessage.setText("Retrofit Insert \n Status = " + response.body().getStatus() + "\n" +
                                    "Message = " + response.body().getMessage() + detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetSumber> call, Throwable t) {
                        tvAddMessage.setText("Retrofit Insert Failure \n Status = " + t.getMessage
                                ());
                    }
                });
            }
        });
        btAddBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LayarListSumber.class);
                startActivity(intent);
            }
        });
    }

}
