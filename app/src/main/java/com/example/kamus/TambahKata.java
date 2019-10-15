package com.example.kamus;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TambahKata extends AppCompatActivity {

    private SQLiteDatabase db = null;

    private EditText txtIndonesia;
    private EditText txtKutai;
    private EditText txtPolandia;
    private DatabaseHelper datakamus = null;
    public static final String INDONESIA = "indonesia";
    public static final String KUTAI = "kutai";
    public static final String POLANDIA = "polandia";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datakamus = new DatabaseHelper(this);
        db = datakamus.getWritableDatabase();
        setContentView(R.layout.activity_tambah_kata);
        txtIndonesia = (EditText)
                findViewById(R.id.txtIndonesia);
        txtKutai = (EditText)
                findViewById(R.id.txtKutai);
        txtPolandia = (EditText)
                findViewById(R.id.txtPolandia);
    }

    public void saveData(View view){
        String bhskutai =
                txtKutai.getText().toString();
        String bhspolandia =
                txtPolandia.getText().toString();
        String bhsindonesia =
                txtIndonesia.getText().toString();
        ContentValues cv=new ContentValues();
        cv.put(INDONESIA, bhsindonesia);
        cv.put(KUTAI, bhskutai);
        cv.put(POLANDIA, bhspolandia);
        if (db.insert("kamus", INDONESIA, cv)>0){
            Toast.makeText(getBaseContext(),
                    "Save Data Succes", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(),
                    "Save Data Fail", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        db.close();
    }
}
