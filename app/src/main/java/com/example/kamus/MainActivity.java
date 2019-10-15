package com.example.kamus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;
public class MainActivity extends AppCompatActivity {
    private ViewFlipper Flipper;
    private Animation fadeIn, fadeOut;
    ImageView keluar, bantuan, developer, kamus, tambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button keluar
        keluar = findViewById(R.id.keluar);
        keluar.setClickable(true);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        bantuan = findViewById(R.id.bantuan);
        bantuan.setClickable(true);
        bantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, BantuanActivity.class);
                startActivity(intent);
            }
        });

        developer = findViewById(R.id.developer);
        developer.setClickable(true);

        developer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( MainActivity.this, DeveloperActivity.class);
                startActivity(intent);
            }
         });

        kamus = findViewById(R.id.kosakata);
        kamus.setClickable(true);

        kamus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( MainActivity.this, DaftarKata.class);
                startActivity(intent);
            }
        });

        tambah = findViewById(R.id.tambahkata);
        tambah.setClickable(true);

        tambah.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( MainActivity.this, TambahKata.class);
                startActivity(intent);
            }
        });


        //animasi Backgroud Slider
        Flipper = findViewById(R.id.Flipper);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation( this, R.anim.fade_out);

        Flipper.setInAnimation(fadeIn);
        Flipper.setOutAnimation(fadeOut);

        Flipper.setAutoStart(true);
        Flipper.setFlipInterval(5000);
        Flipper.startFlipping();
        //Button 1
        ImageView tampilTerjemahan = findViewById(R.id.terjemahanView);
        tampilTerjemahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, TerjemahanActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed(){
        //biar kalau di pencet keluar muncul dialgo alert
        //yang ini onBackpressed ini bukan untuk button di layout, tapi yang di hp kita
        showAlertDialog();
    }
    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("tutup aplikasi ini?")
                .setNegativeButton("tidak", new DialogInterface.OnClickListener() {
                    @Override
                        public void onClick(DialogInterface dialogInterface, int i){
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("ya", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        dialogInterface.dismiss();
                        finish();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
