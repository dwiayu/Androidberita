package com.example.dell.berita;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.berita.Rest.ApiInterface;

public class HomeActivity extends AppCompatActivity {
    public static  HomeActivity layarutama;
    Button btGet;
    ApiInterface mApiInterface;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle b = getIntent().getExtras();

        TextView username = (TextView) findViewById(R.id.username);
        username.setText(b.getCharSequence("username"));
    }
    public void button_onClick(View view){
        SharedPreferences set = this.getSharedPreferences("key", Context.MODE_PRIVATE );
        SharedPreferences . Editor editor = set.edit();
        editor.remove("username");
        editor.remove("password");
        editor.commit();
        finish();

        Intent i = new Intent(this.getApplicationContext(), MainActivity.class);
    }
}
