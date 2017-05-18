package com.example.marya.cantwrite;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    List<String> all;
    List <String>lastDayUri;
    String lastUri;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this,
                new String[]{"com.example.marya.firsttestingproject.permission.READ_ALL","com.example.marya.firsttestingproject.permission"}, 2);
        setContentView(R.layout.activity_main);
        editText=(EditText) findViewById(R.id.edittext);
    }
    public void onClick(View view){
        lastUri="";
        lastDayUri=new ArrayList<>();
        all=new ArrayList<>();
        Button bt=(Button)view;
        switch (bt.getId()) {
            case R.id.all: {
                Uri uri = Uri.parse("content://com.example.marya.firsttestingproject/uri/values");
                Log.d("check", uri.toString());
                try {
                    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                    while (cursor.moveToNext()) {
                        Log.d("check", "g");
                        String name = cursor.getString(0);
                        all.add(name);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    Toast.makeText(this, all.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (SecurityException e){
                    Toast.makeText(this, "Insufficient rights", Toast.LENGTH_SHORT).show();
                }
                catch (NullPointerException a){
                    Toast.makeText(this, "Insufficient rights", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.lastday:{
                Uri uri = Uri.parse("content://com.example.marya.firsttestingproject/uri/last_day_values");
                Log.d("check", uri.toString());
                try {
                    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                    while (cursor != null && cursor.moveToNext()) {
                        Log.d("check", "g");
                        String name = cursor.getString(0);
                        lastDayUri.add(name);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    Toast.makeText(this, lastDayUri.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (SecurityException e){
                    Toast.makeText(this, "Insufficient rights", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.last:{
                Uri uri = Uri.parse("content://com.example.marya.firsttestingproject/uri/last_value");
                Log.d("check", uri.toString());
                try {
                    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                    while (cursor != null && cursor.moveToNext()) {
                        Log.d("check", "g");
                        String name = cursor.getString(0);
                        lastUri=name;
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    Toast.makeText(this, lastUri, Toast.LENGTH_SHORT).show();
                }
                catch (SecurityException e){
                    Toast.makeText(this, "Insufficient rights", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.add:{
                Uri uri = Uri.parse("content://com.example.marya.firsttestingproject/uri");
                Log.d("check", uri.toString());
                ContentValues contentValues = new ContentValues();
                String str=String.valueOf(editText.getText());
                str=str.trim();
                if (!str.equals("")) {
                    contentValues.put("value", String.valueOf(editText.getText()));;
                    try {
                        getContentResolver().insert(uri, contentValues);
                        Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                    }
                    catch (SecurityException e){
                        Toast.makeText(this, "Insufficient rights", Toast.LENGTH_SHORT).show();
                    }

                }
                editText.setText("");
                break;
            }
        }
    }
}