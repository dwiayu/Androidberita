package com.example.dell.portal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dell.portal.Adapter.KategoriAdapter;
import com.example.dell.portal.Adapter.SumberAdapter;
import com.example.dell.portal.Model.GetKategori;
import com.example.dell.portal.Model.GetSumber;
import com.example.dell.portal.Model.Kategori;
import com.example.dell.portal.Model.Sumber;
import com.example.dell.portal.REST.ApiClient;
import com.example.dell.portal.REST.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListSumber extends Activity {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;
    Button btGet, btAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_sumber);
        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btAddData = (Button) findViewById(R.id.btAddData);
        btGet = (Button) findViewById(R.id.btGet);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetSumber> mSumberCall = mApiInterface.getSumber();
                mSumberCall.enqueue(new Callback<GetSumber>() {
                    @Override
                    public void onResponse(Call<GetSumber> call, Response<GetSumber> response) {
                        Log.d("Get Sumber", response.body().getStatus());
                        List<Sumber> listSumber = response.body().getResult();
                        mAdapter = new SumberAdapter(listSumber);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetSumber> call, Throwable t) {
                        Log.d("Get Sumber", t.getMessage());
                    }
                });
            }
        });
        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LayarInsertSumber.class);
                startActivity(intent);
            }
        });

    }
}


