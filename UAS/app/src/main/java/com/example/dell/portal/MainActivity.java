package com.example.dell.portal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dell.portal.Adapter.MyAdapter;
import com.example.dell.portal.Model.Berita;
import com.example.dell.portal.Model.GetBerita;
import com.example.dell.portal.REST.ApiClient;
import com.example.dell.portal.REST.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btGet;
    ApiInterface mApiInterface;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btGet = (Button) findViewById(R.id.btGet);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetBerita> beritaCall = mApiInterface.getBerita();
                beritaCall.enqueue(new Callback<GetBerita>() {
                    @Override
                    public void onResponse(Call<GetBerita> call, Response<GetBerita> response) {
                        List<Berita> beritaList = response.body().getListDataBerita();
                        Log.d("Retrofit Get", "Jumlah data berita: " + String.valueOf(beritaList.size()));

                        mAdapter = new MyAdapter(beritaList);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetBerita> call, Throwable t) {
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()) {

            case R.id.menuTambahTransBerita:
                mIntent = new Intent(this, LayarDetail.class);
                startActivity(mIntent);
                return true;

            case R.id.menuListKategori:
                mIntent = new Intent(this, LayarListKategori.class);
                startActivity(mIntent);
                return true;
            case R.id.menuInsertDataKategori:
                Intent intent = new Intent(this, LayarInsertKategori.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
