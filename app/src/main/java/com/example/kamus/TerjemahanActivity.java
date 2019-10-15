package com.example.kamus;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TerjemahanActivity extends AppCompatActivity {
    private SQLiteDatabase db = null;
    private Cursor kamusCursor = null;
    private EditText textPolandia;
    private EditText textIndonesia;
    private EditText textKutai;
    private DatabaseHelper datakamus = null;


    public static final String POLANDIA = "polandia";
    public static final String INDONESIA = "indonesia";
    public static final String KUTAI= "kutai";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        datakamus = new DatabaseHelper(this);
        db = datakamus.getWritableDatabase();
        setContentView(R.layout.activity_terjemahan);
        textPolandia = findViewById(R.id.txtPolandia);
        textIndonesia = findViewById(R.id.txtIndonesia);
        textKutai = findViewById(R.id.txtKutai);

        Button btnTranslate = findViewById(R.id.btnTerjemah);
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTerjemahan();
            }
        });
    }
    public void getTerjemahan (){
        String bhspolandia = "";
        String bhskutai = "";
        String bhsindonesia = textIndonesia.getText().toString();

        try {
            kamusCursor = db.rawQuery(
                    "SELECT * FROM" +
                            " kamus WHERE INDONESIA='"+ bhsindonesia +"' ORDER BY _ID",null);
            if (kamusCursor.moveToFirst()){
                for(; !kamusCursor.isAfterLast(); kamusCursor.moveToNext()){
                    bhspolandia = kamusCursor.getString(2);
                    bhskutai = kamusCursor.getString(3);
                }
            }
            else
            {
                Toast.makeText(getBaseContext(), "Terjemahan tidak ditemukan", Toast.LENGTH_SHORT).show();
            }
            textPolandia.setText(bhspolandia);
            textKutai.setText(bhskutai);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        try{
            kamusCursor.close();
            db.close();
        }catch (Exception e){

        }
    }
}
