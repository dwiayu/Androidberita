package com.example.dell.uasuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()) {

            case R.id.menuListKategori:
                mIntent = new Intent(this, LayarListKategori.class);
                startActivity(mIntent);
                return true;
            case R.id.menuProfil:
                mIntent = new Intent(this, LayarListUser.class);
                startActivity(mIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
