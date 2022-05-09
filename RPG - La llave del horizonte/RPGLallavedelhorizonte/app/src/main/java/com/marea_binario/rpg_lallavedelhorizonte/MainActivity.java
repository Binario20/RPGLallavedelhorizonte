package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Item;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        funcionar();
    }

    private void funcionar(){

        new Thread(() -> {
            if(testConn().equalsIgnoreCase("204 OK")){
                Data.setData(MainActivity.this);
                irEleccionRol();
            }else{
                funcionar();
            }
        }).start();

    }

    private void initComponents() {
        Data.setImg();
        loading = this.findViewById(R.id.loading);
        Glide.with(MainActivity.this).load(R.drawable.loading).into(loading);

    }

    private void irEleccionRol() {
        startActivity(new Intent(this, ElecionRol.class));
        finish();
    }

    private String testConn(){
        ConnTask connTask = new ConnTask("post/conecta");
        connTask.execute();
        try {
            return connTask.get().toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}