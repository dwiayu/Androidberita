package com.example.dell.uasuser;

import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class Kamera extends AppCompatActivity {
    private Uri UrlGambar;
    private ImageView SetImageView;

    private static final int CAMERA = 1;
    private static final int FILE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamera);

    }
}
