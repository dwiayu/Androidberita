package com.example.dell.portal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.portal.Model.PostPutDellBerita;
import com.example.dell.portal.REST.ApiClient;
import com.example.dell.portal.REST.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarDetail extends AppCompatActivity {
    EditText edtIdBerita, edtIdSumber, edtIdKategori, edtJudul, edtIsi;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_detail);

        edtIdBerita = (EditText) findViewById(R.id.edtIdBerita);
        edtIdSumber = (EditText) findViewById(R.id.edtIdSumber);
        edtIdKategori = (EditText) findViewById(R.id.edtIdKategori);
        edtJudul = (EditText) findViewById(R.id.edtJudul);
        edtIsi = (EditText) findViewById(R.id.edtIsi);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);


        Intent mIntent = getIntent();
        edtIdBerita.setText(mIntent.getStringExtra("id_berita"));
        edtIdSumber.setText(mIntent.getStringExtra("id_sumber"));
        edtIdKategori.setText(mIntent.getStringExtra("id_kategori"));
        edtJudul.setText(mIntent.getStringExtra("judul"));
        edtIsi.setText(mIntent.getStringExtra("isi"));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDellBerita> updateBeritaCall = mApiInterface.putBerita(
                        edtIdBerita.getText().toString(),
                        edtIdSumber.getText().toString(),
                        edtIdKategori.getText().toString(),
                        edtJudul.getText().toString(),
                        edtIsi.getText().toString());

                updateBeritaCall.enqueue(new Callback<PostPutDellBerita>() {
                    @Override
                    public void onResponse(Call<PostPutDellBerita> call, Response<PostPutDellBerita> response) {
                        tvMessage.setText(" Retrofit Update: " +
                                "\n " + " Status Update : " +response.body().getStatus() +
                                "\n " + " Message Update : "+ response.body().getMessage());
                    }
                    @Override
                    public void onFailure(Call<PostPutDellBerita> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :"+ t.getMessage());
                    }
                });
            }
        });
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDellBerita> postBeritaCall = mApiInterface.postBerita(
                        edtIdBerita.getText().toString(),
                        edtIdSumber.getText().toString(),
                        edtIdKategori.getText().toString(),
                        edtJudul.getText().toString(),
                        edtIsi.getText().toString());

                postBeritaCall.enqueue(new Callback<PostPutDellBerita>() {
                    @Override
                    public void onResponse(Call<PostPutDellBerita> call, Response<PostPutDellBerita> response) {
                        tvMessage.setText(" Retrofit Insert: " +
                                "\n " + " Status Insert : " +
                                response.body().getStatus() +
                                "\n " + " Message Insert : "+ response.body().getMessage());
                    }
                    @Override
                    public void onFailure(Call<PostPutDellBerita> call, Throwable t) {
                        tvMessage.setText("Retrofit Insert: \n Status Insert :"+ t.getMessage());
                    }
                });
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtIdBerita.getText().toString().trim().isEmpty()){

                    Call<PostPutDellBerita> deleteBerita = mApiInterface.deleteBerita(edtIdBerita.getText().toString());
                    deleteBerita.enqueue(new Callback<PostPutDellBerita>() {
                        @Override
                        public void onResponse(Call<PostPutDellBerita> call, Response<PostPutDellBerita> response) {
                            tvMessage.setText(" Retrofit Delete: " +
                                    "\n " + " Status Delete : " +response.body().getStatus() +
                                    "\n " + " Message Delete : "+ response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<PostPutDellBerita> call, Throwable t) {
                            tvMessage.setText("Retrofit Delete: \n Status Delete :"+ t.getMessage());
                        }
                    });
                }else{
                    tvMessage.setText("id_pembelian harus diisi");
                }
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
