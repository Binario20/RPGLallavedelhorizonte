package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        new Thread(() -> {
            if(!testConn().equals("")){
                Data.setData(MainActivity.this);
                irEleccionRol();
            }else{

            }
        }).start();


    }

    private void initComponents() {
        loading = this.findViewById(R.id.loading);
        Glide.with(MainActivity.this).load(R.drawable.loading).into(loading);

    }

    private void irEleccionRol() {
        startActivity(new Intent(this, ElecionRol.class));
    }

    private String testConn(){
        Log.e("hola1", "j");
        ConnTask connTask = new ConnTask("001"+Data.MASTER+"*"+"Binario18");
        Log.e("hola2", "j");
        connTask.execute();
        Log.e("hola3", "j");
        try {
            String kk = connTask.get().toString().trim();
            Log.e("res", kk);
            Toast.makeText(this, kk, Toast.LENGTH_SHORT).show();
            return kk;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}