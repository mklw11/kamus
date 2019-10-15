package com.example.kamus;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.zip.Inflater;

public class DaftarKata extends AppCompatActivity {
    private DatabaseHelper kamus;
    private SQLiteDatabase db = null;
    private ListView listContent = null;

    private Cursor kamusCursor = null;
    CustomCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kamus = new DatabaseHelper(this);

        setContentView(R.layout.activity_daftar_kata);
        listContent = (ListView)
                findViewById(R.id.list1);
        isDataListView();
    }

    private void isDataListView(){
        try{
            db = kamus.getWritableDatabase();
            kamusCursor = db.query("kamus", new String[]{"_id","indonesia","kutai","polandia"},
                    "_id > 0", null, null, null, null, null);
            String[] from = new String[]{"indonesia","kutai","polandia"};
            int[] to = new int[] {R.id.txtIndonesia, R.id.txtKutai, R.id.txtPolandia};
            adapter = new CustomCursorAdapter(this, R.layout.row, kamusCursor, from, to);

            listContent.setAdapter(adapter);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (db != null && db.isOpen()){
                db.close();
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        try {
            kamusCursor.close();
        } catch (Exception e){

        }
    }

    protected class CustomCursorAdapter extends SimpleCursorAdapter{
        private int layout;
        private LayoutInflater inflater;
        private Context context;

        public CustomCursorAdapter(Context context, int layout, Cursor c,String[] from, int[] to){
            super(context, layout, c, from, to);
            this.layout = layout;
            this.context = context;

            inflater = LayoutInflater.from(context);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent){
            Log.d("NewView","*****xxx");

            View v =
                    inflater.inflate(R.layout.row, parent, false);
            return v;
        }

        @Override
        public void bindView(View v, Context context, Cursor c){
            String indonesia = c.getString(1);
            String kutai = c.getString(2);
            String polandia = c.getString(3);

            TextView name_text = (TextView)
                    v.findViewById(R.id.indonesia);
            TextView des_text = (TextView)
                    v.findViewById(R.id.kutai);
            TextView id_text = (TextView)
                    v.findViewById(R.id.polandia);
            des_text.setText(kutai);
            id_text.setText(polandia);

            if (name_text != null){
                name_text.setText(indonesia);
            }
        }
    }

}
