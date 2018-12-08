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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarEditSumber extends AppCompatActivity {
    EditText edtIdSumber, edtAddNama, edtAddPj;
    TextView tvMessage;
    Context mContext;
    Button btUpdate, btDelete, btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_sumber);
        mContext = getApplicationContext();

        edtIdSumber = (EditText) findViewById(R.id.edtIdSumber);
        edtAddNama= (EditText) findViewById(R.id.edtAddNama);
        edtAddPj = (EditText) findViewById(R.id.edtAddPj);
        tvMessage = (TextView) findViewById(R.id.tvMessage);

        btUpdate = (Button) findViewById(R.id.btUpdate);
        btDelete = (Button) findViewById(R.id.btDelete);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdSumber.setText(mIntent.getStringExtra("id_sumber"));
        edtAddNama.setText(mIntent.getStringExtra("nama_sumber"));
        edtAddPj.setText(mIntent.getStringExtra("pj_sumber"));
    }
    private void setListener() {
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultipartBody.Part body = null;
                RequestBody reqIdSumber =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtIdSumber
                                        .getText().toString().isEmpty()) ?
                                        "" : edtIdSumber.getText().toString());

                RequestBody reqNama =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAddNama.getText().toString().isEmpty()) ?
                                        "" : edtAddNama.getText().toString());
                RequestBody reqPj =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtAddPj.getText().toString().isEmpty()) ?
                                        "" : edtAddPj.getText().toString());
                RequestBody reqAction =
                        MultipartBody.create(MediaType.parse("multipart/form-data"), "update");
                Call<GetSumber> callUpdate = mApiInterface.putSumber(body, reqIdSumber, reqNama, reqPj,
                        reqAction);
                callUpdate.enqueue(new Callback<GetSumber>() {
                    @Override
                    public void onResponse(Call<GetSumber> call, Response<GetSumber> response) {
                        if (response.body().getStatus().equals("failed")) {
                            tvMessage.setText("Retrofit Update \n Status = " + response.body()
                                    .getStatus() + "\n" +
                                    "Message = " + response.body().getMessage() + "\n");
                        } else {
                            String detail = "\n" +
                                    "id_sumber = " + response.body().getResult().get(0).getIdSumber() + "\n" +
                                    "nama_sumber = " + response.body().getResult().get(0).getNamaSumber() + "\n" +
                                    "pj_sumber = " + response.body().getResult().get(0).getPjSumber()
                                    + "\n";
                            tvMessage.setText("Retrofit Update \n Status = " + response.body().getStatus() + "\n" +
                                    "Message = " + response.body().getMessage() + detail);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetSumber> call, Throwable t) {
                        tvMessage.setText("Retrofit Update \n Status = " + t.getMessage());
                    }
                });

            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody reqIdSumber =
                        MultipartBody.create(MediaType.parse("multipart/form-data"),
                                (edtIdSumber.getText().toString().isEmpty()) ?
                                        "" : edtIdSumber.getText().toString());
                RequestBody reqAction =
                        MultipartBody.create(MediaType.parse("multipart/form-data"), "delete");
                Call<GetSumber> callDelete = mApiInterface.deleteSumber(reqIdSumber, reqAction);
                callDelete.enqueue(new Callback<GetSumber>() {
                    @Override
                    public void onResponse(Call<GetSumber> call, Response<GetSumber> response) {
                        tvMessage.setText("Retrofit Delete \n Status = " + response.body()
                                .getStatus() + "\n" +
                                "Message = " + response.body().getMessage() + "\n");
                    }

                    @Override
                    public void onFailure(Call<GetSumber> call, Throwable t) {
                        tvMessage.setText("Retrofit Delete \n Status = " + t.getMessage());
                    }
                });
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tempIntent = new Intent(mContext, LayarListSumber.class);
                startActivity(tempIntent);
            }
        });
            }
    }
