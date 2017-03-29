package com.pranaykumar.dbmsproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewDatabase extends AppCompatActivity {

    ArrayList<String> mList;
    ListView listView;
    ArrayAdapter<String> adapter;
    MyDbHandler dbHandler;
    int databaseEmpty = 0;

    String[] optionsarray = {"View Record" , "Delete Record"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_database);

        dbHandler=new MyDbHandler(this, null, null, 1);
        mList=dbHandler.getAllUsers();
        listView=(ListView)findViewById(R.id.myList);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String eid = String.valueOf(parent.getItemAtPosition(position));

                AlertDialog.Builder optionlist = new AlertDialog.Builder(ViewDatabase.this);
                optionlist.setItems(optionsarray, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which)
                        {
                            case 0: Intent intent=new Intent(getApplicationContext(), ShowData.class);
                                    intent.putExtra("eid", eid);
                                    startActivity(intent);
                                    break;

                            case 1: AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewDatabase.this);
                                    alertDialogBuilder.setMessage("Are you sure you want to delete this record?");
                                    alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            dbHandler.deleteRow(eid);
                                            Toast.makeText(getApplicationContext(), "Record deleted", Toast.LENGTH_SHORT).show();
                                            SharedPreferences sharedpreference = getSharedPreferences(
                                                    getString(R.string.preference_key), Context.MODE_PRIVATE);
                                            databaseEmpty = sharedpreference.getInt(getString(R.string.empty_status),0);
                                            databaseEmpty--;
                                            SharedPreferences.Editor editor = sharedpreference.edit();
                                            editor.putInt(getString(R.string.empty_status), databaseEmpty);
                                            editor.apply();
                                            adapter.clear();
                                            mList=dbHandler.getAllUsers();
                                            adapter.addAll(mList);
                                        }
                                    });
                                    alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getApplicationContext(), "Record not deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                    break;

                        }
                    }
                }).create().show();
            }
        });
    }
}
