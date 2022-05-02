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

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Item;

import org.json.JSONObject;

public class PaginaPrincipal extends AppCompatActivity {

    private ImageView imgJugador, fondo, dineros;
    private TextView fuerza, velocidad, destreza, magia, vitalidad, resistencia, inteligencia, punteria, nombre;
    private final Item[] items = new Item[4];
    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);
        Intent i = getIntent();
        try{
            id = i.getIntExtra("Id", -1);
        }catch (Exception e){
            e.printStackTrace();
        }
        initListeners();
        initComponents();
        initData();
    }

    private void initData() {
        String j = String.valueOf(id);
        j = "1";
        ConnTask connTask = new ConnTask("/tablas/personajes/info?id=" + j);
        connTask.execute();
        try {
            String kk = connTask.get().toString().trim();
            Toast.makeText(this, kk, Toast.LENGTH_SHORT).show();
            Log.e("jj", kk);
            JSONObject perso = new JSONObject(kk).getJSONObject("0");
            Toast.makeText(this, perso.toString(), Toast.LENGTH_SHORT).show();
            fuerza.setText(perso.getString("fuerza"));
            inteligencia.setText(perso.getString("inteligencia"));
            vitalidad.setText(perso.getString("vitalidad"));
            resistencia.setText(perso.getString("resistencia"));
            velocidad.setText(perso.getString("velocidad"));
            punteria.setText(perso.getString("punteria"));
            magia.setText(perso.getString("magia"));
            destreza.setText(perso.getString("destreza"));
            nombre.setText(perso.getString("nombre"));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, String.valueOf(e.getMessage()), Toast.LENGTH_SHORT).show();
        }

    }

    private void initListeners() {

    }

    private void initComponents() {

        imgJugador = this.findViewById(R.id.imgJugador);
        fondo = this.findViewById(R.id.fondo);
        items[0] = this.findViewById(R.id.item1);
        items[1] = this.findViewById(R.id.item2);
        items[2] = this.findViewById(R.id.item3);
        items[3] = this.findViewById(R.id.item4);

        int i = 1;
        for (Item item: items) {
            item.setCustomId(i++);
        }
        dineros = this.findViewById(R.id.dinerosImg);

        fuerza = this.findViewById(R.id.fuerza);
        velocidad = this.findViewById(R.id.velocidad);
        destreza = this.findViewById(R.id.destreza);
        magia = this.findViewById(R.id.magia);
        vitalidad = this.findViewById(R.id.vitalidad);
        resistencia = this.findViewById(R.id.resistencia);
        inteligencia = this.findViewById(R.id.inteligencia);
        punteria = this.findViewById(R.id.punteria);

        imgJugador.setBackground(AppCompatResources.getDrawable(this, Data.getRandomWallPaper()));
        fondo.setBackground(AppCompatResources.getDrawable(this, Data.getRandomWallPaper()));

        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.dineros);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(this.getResources(), icon);
        roundedBitmapDrawable.setCircular(true);
        dineros.setImageDrawable(roundedBitmapDrawable);

        nombre = this.findViewById(R.id.nombre);

    }
}