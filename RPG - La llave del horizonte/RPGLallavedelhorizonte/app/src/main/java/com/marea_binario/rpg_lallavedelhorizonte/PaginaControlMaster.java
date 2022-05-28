package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;

public class PaginaControlMaster extends AppCompatActivity {
    private Button multiCtrolBut, armasBut, objetosBut, bestiarioBut, magiaBut, regionesBut;
    private TextView dineros;
    private ImageView PviewInM, personajesBut, atributosBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_control_master);
        
        initContent();
        initListeners();
    }

    private void initContent() {
        multiCtrolBut = findViewById(R.id.multiControlM);

        dineros = findViewById(R.id.dinerosM);
    }

    private void initListeners() {
        multiCtrolBut.setOnClickListener(view -> {
            Utils.getDineros(dineros);
        });
    }
}