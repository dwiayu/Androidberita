package com.example.dell.uasuser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    CheckBox chck_remember;
    Button btn_login;
    EditText ed_username;
    EditText ed_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.checkSavedCredentials();
        this.initComponents();
    }

    private void initComponents() {

        this.chck_remember = (CheckBox) this.findViewById(R.id.chck_remember);
        this.btn_login= (Button) this.findViewById(R.id.btn_login);
        this.ed_username = (EditText) findViewById(R.id.ed_username);
        this.ed_password = (EditText) findViewById(R.id.ed_pass);
    }


    private void checkSavedCredentials() {
        SharedPreferences handler = this.getSharedPreferences("key", Context.MODE_PRIVATE );
        String username = handler.getString("username","" );
        String password = handler.getString("password","" );
        boolean loginCorrect = this.checkCredentials(username, password );
        if (loginCorrect)
            this.openHome(username);
    }
    public  void button_onClick(View view){
        this.login();
    }
    private boolean checkCredentials(String username, String password) {
        if(username.equals("admin") && password.equals(("admin")))
            return true;
        else
            return false;
    }
    private void login() {
        String username= this.ed_username.getText().toString();
        String password = this.ed_password.getText().toString();

        boolean loginCorrect = this.checkCredentials(username,password);

        if(loginCorrect)
        {
            boolean remember = this.chck_remember.isChecked();
            if(remember)
            {
                this.saveCredentials();
            }
            this.openHome(username);
        }else
        {
            Toast.makeText(this.getApplicationContext(),"Invalid username and/or password!",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void saveCredentials() {
        SharedPreferences handler = this.getSharedPreferences("key",Context.MODE_PRIVATE );
        SharedPreferences . Editor editor = handler.edit();
        editor.putString("username", this.ed_username.getText().toString());
        editor.putString("password", this.ed_username.getText().toString());
        editor.apply();
    }
    private void openHome(String username) {

        Intent i = new Intent(this.getApplicationContext(),MainActivity.class);
        i.putExtra("username", username);
        this.startActivity(i);


    }
}
