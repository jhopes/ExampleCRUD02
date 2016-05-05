package com.jhopesoft.www.examplecrud01;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
    Button btnsave, btnir, btnsearch;
    User u;
    ClsUser cu;
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
        btnir = (Button) findViewById(R.id.BtnGo);
        //btnsearch = (Button) findViewById(R.id.BtnSearch);
         u = new User();
         cu = new ClsUser(MainActivity.this);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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

    public void setOnClickListenerSearch(View v){
        u.setName_user(user.getText().toString());
        Cursor listuser = cu.searchUser(u);
        if(listuser.moveToFirst()){
            email.setText(listuser.getString(2).toString());
            pw.setText(listuser.getString(3).toString());
            cpw.setText(listuser.getString(3).toString());
        }else{
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);

            builder.setMessage("El usuario no existe.").setTitle("Información");
            builder.create().show();
        }
    }
    public void setOnClickListenerUpdate(View v){
        //actualizar
        u.setName_user(user.getText().toString());
        u.setEmail(email.getText().toString());
        u.setPassword(pw.getText().toString());
        if(cpw.getText().toString().equals(pw.getText().toString())) {
            if (cu.UpdateUser(u)) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Succesfull update").setTitle("Información");
                builder.create().show();
            }
            else{
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Error de actualización").setTitle("Información");
                builder.create().show();
            }
        }else{
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);

            builder.setMessage("Error de coincidencia de pw").setTitle("Información");
            builder.create().show();
        }
    }

}
