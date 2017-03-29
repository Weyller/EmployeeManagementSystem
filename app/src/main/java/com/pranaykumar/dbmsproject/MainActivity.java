package com.pranaykumar.dbmsproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    int databaseEmpty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedpreference = this.getSharedPreferences(
                getString(R.string.preference_key), Context.MODE_PRIVATE);

        File f = new File("/data/data/" + getPackageName() + "/shared_prefs/" + "com.pranaykumar.dbmsproject.xml");

        if (!f.exists()) {
            SharedPreferences.Editor editor = sharedpreference.edit();
            editor.putInt(getString(R.string.empty_status), databaseEmpty);
            editor.apply();
        }
    }

    public void view_database(View v){
        SharedPreferences sharedpreference = this.getSharedPreferences(
                getString(R.string.preference_key), Context.MODE_PRIVATE);
        databaseEmpty = sharedpreference.getInt(getString(R.string.empty_status),0);
        if(databaseEmpty == 0){
            Toast.makeText(getApplicationContext(),"The Database is empty",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i = new Intent(this, ViewDatabase.class);
            startActivity(i);
        }
    }

    public void enter_data(View v) {
        Intent i = new Intent(this, EnterActivity.class);
        startActivity(i);
    }
}
