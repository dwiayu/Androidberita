package com.example.dell.uasuser;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dell.uasuser.Adapter.KategoriAdapter;
import com.example.dell.uasuser.Model.GetKategori;
import com.example.dell.uasuser.Model.Kategori;
import com.example.dell.uasuser.REST.ApiClient;
import com.example.dell.uasuser.REST.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListKategori extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;
    Button btGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_kategori);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btGet = (Button) findViewById(R.id.btGet);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetKategori> mKategoriCall = mApiInterface.getKategori();
                mKategoriCall.enqueue(new Callback<GetKategori>() {
                    @Override
                    public void onResponse(Call<GetKategori> call, Response<GetKategori> response) {
                        Log.d("Get Kategori",response.body().getStatus());
                        List<Kategori> listKategori = response.body().getResult();
                        mAdapter = new KategoriAdapter(listKategori);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetKategori> call, Throwable t) {
                        Log.d("Get Kategori",t.getMessage());
                    }
                });
            }
        });
    }
}
