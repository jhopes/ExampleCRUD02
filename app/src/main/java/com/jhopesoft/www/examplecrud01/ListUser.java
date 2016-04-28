package com.jhopesoft.www.examplecrud01;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ListView;

import Controller.ClsUser;

public class ListUser extends AppCompatActivity {

    ListView list_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list_user = (ListView) findViewById(R.id.ListvUser);
        ListUser();

    }
    public void ListUser(){
        ClsUser cu = new ClsUser(getApplicationContext());
        String[] columnas = new String[]{"campo1","campo2"};
        int[] id_views = new int[]{android.R.id.text1, android.R.id.text2};
        Cursor listuser = cu.readUser();
        if(listuser.moveToFirst()){
            SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getApplicationContext(), android.R.layout.simple_list_item_2, listuser, columnas, id_views);
            list_user.setAdapter(adaptador);
        }
    }

}
