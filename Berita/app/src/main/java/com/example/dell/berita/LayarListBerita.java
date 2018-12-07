package com.example.dell.berita;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dell.berita.Adapter.BeritaAdapter;
import com.example.dell.berita.Model.Berita;
import com.example.dell.berita.Model.GetBerita;
import com.example.dell.berita.Rest.APIClient;
import com.example.dell.berita.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListBerita extends Activity {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;
    Button button_tambah, button_get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        button_tambah = (Button) findViewById(R.id.button_tambah);
        button_get = (Button) findViewById(R.id.button_get);

        button_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = APIClient.getClient().create(ApiInterface.class);
                Call<GetBerita> mBeritaCall = mApiInterface.getBerita();
                mBeritaCall.enqueue(new Callback<GetBerita>() {
                    @Override
                    public void onResponse(Call<GetBerita> call, Response<GetBerita> response) {
                        Log.d("Get Berita",response.body().getStatus());
                        List<Berita> listBerita = response.body().getResult();
                        mAdapter = new BeritaAdapter(listBerita);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    @Override
                    public void onFailure(Call<GetBerita> call, Throwable t) {
                        Log.d("Get Pembeli",t.getMessage());
                    }
                });
            }
        });
        button_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LayarInsertBerita.class);
                startActivity(intent);
            }
        });
    }


}
