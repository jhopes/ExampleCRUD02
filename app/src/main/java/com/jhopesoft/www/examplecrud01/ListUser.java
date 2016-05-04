package com.jhopesoft.www.examplecrud01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import Controller.ClsUser;
import Modell.User;

public class ListUser extends AppCompatActivity {

    ListView list_user;
    User u;
    ClsUser cu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list_user = (ListView) findViewById(R.id.ListvUser);
        u = new User();
        cu = new ClsUser(ListUser.this);
        ListUser();

        list_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, final long _id) {

                final String[] items = {"Editar", "Eliminar"};

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(ListUser.this);

                builder.setTitle("Selección")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item){
                                    case 0:
                                        Toast.makeText(getApplicationContext(),"edit",Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        AlertDialog.Builder builder =
                                                new AlertDialog.Builder(ListUser.this);

                                        builder.setMessage("¿Esta seguro que desea eliminar?")
                                                .setTitle("Confirmación")
                                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                       //eliminar
                                                        u.setId(_id+"");
                                                        if(cu.DeleteUser(u)){
                                                            ListUser();
                                                            Toast.makeText(ListUser.this, "Eliminado", Toast.LENGTH_SHORT).show();
                                                        }else{
                                                            Toast.makeText(ListUser.this, "Error de eliminación", Toast.LENGTH_SHORT).show();
                                                        }
                                                       dialog.cancel();
                                                    }
                                                })
                                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });

                                        builder.create().show();
                                        break;
                                }
                            }
                        });
                builder.create().show();
            }
        });
    }
    public void ListUser(){
        String[] columnas = new String[]{"campo1","campo2"};
        int[] id_views = new int[]{android.R.id.text1, android.R.id.text2};
        Cursor listuser = cu.readUser();
        if(listuser.moveToFirst()){
            SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getApplicationContext(), android.R.layout.simple_list_item_2, listuser, columnas, id_views);
            list_user.setAdapter(adaptador);
        }
    }

}
