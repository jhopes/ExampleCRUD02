package com.jhopesoft.www.examplecrud01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Controller.ClsUser;
import Modell.User;

public class MainActivity extends AppCompatActivity {

    EditText user, email, pw, cpw;
    Button btnsave, btnir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (EditText) findViewById(R.id.EdtUser);
        email = (EditText) findViewById(R.id.EdtEmail);
        pw = (EditText) findViewById(R.id.EdtPw);
        cpw = (EditText) findViewById(R.id.EdtCPw);
        btnsave = (Button) findViewById(R.id.BtnSave);
        btnir = (Button) findViewById(R.id.BtnIr);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();
                ClsUser cu = new ClsUser(MainActivity.this);

                u.setName_user(user.getText().toString());
                u.setEmail(email.getText().toString());
                u.setPassword(pw.getText().toString());
                u.setConfirmpw(cpw.getText().toString());

                if(cu.createUser(u)){
                    Toast.makeText(getApplicationContext(),"succesfull",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext() ,ListUser.class);
                startActivity(i);
                MainActivity.this.finish();
            }
        });
    }


}
