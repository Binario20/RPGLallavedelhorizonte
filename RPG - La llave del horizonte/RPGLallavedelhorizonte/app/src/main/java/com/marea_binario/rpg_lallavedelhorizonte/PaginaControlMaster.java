package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;

public class PaginaControlMaster extends AppCompatActivity {
    private Button modDineros, reloadMaster, armasBut, objetosBut, bestiarioBut, magiaBut, regionesBut;
    private TextView dineros;
    private ImageView PviewInM, personajesBut, atributosBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_control_master);

        initContent();
        initListeners();
        setConfigIfLider();
    }

    private void initContent() {
        modDineros = this.findViewById(R.id.modDinerosM);
        reloadMaster = this.findViewById(R.id.reloadMaster);
        armasBut = this.findViewById(R.id.armasMaster);
        objetosBut = this.findViewById(R.id.objetosMaster);
        bestiarioBut = this.findViewById(R.id.bestiarioMaster);
        magiaBut = this.findViewById(R.id.magiaMaster);
        regionesBut = this.findViewById(R.id.regionesMaster);

        dineros = this.findViewById(R.id.dinerosM);

        PviewInM = this.findViewById(R.id.PviewInM);
        personajesBut = this.findViewById(R.id.personajesMaster);
        atributosBut = this.findViewById(R.id.atributosMaster);
    }

    private void initListeners() {
        armasBut.setOnClickListener(view -> {
            // mostrar lista de armas
        });

        objetosBut.setOnClickListener(view -> {
            // mostrar lista de objetos
        });

        bestiarioBut.setOnClickListener(view -> {
            // mostrar lista de bestiaio
        });

        magiaBut.setOnClickListener(view -> {
            // mostrar lista de magia
        });

        regionesBut.setOnClickListener(view -> {
            // mostrar lista de regiones (no foraneas)
        });

        personajesBut.setOnClickListener(view -> {
            // poder canviar los objetos/armas... que tiene un jugador
        });

        atributosBut.setOnClickListener(view -> {
            // poder modificar las estadisticas de un peronaje
        });
    }

    private void setConfigIfLider() {
        ConnTask connTask = new ConnTask("get/soy_lider");
        connTask.execute();
        String isLider = null;
        try {
            isLider = connTask.get().toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!(isLider == null)) {
            ConnTask connTask2 = new ConnTask("get/soy_lider");
            connTask2.execute();
            try {
                isLider = connTask2.get().toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (isLider.equals("true")) {
            modDineros.setVisibility(View.VISIBLE);
            reloadMaster.setVisibility(View.GONE);

            modDineros.setOnClickListener(view -> {
                Utils.addDineros(dineros, 5);
                Utils.subDineros(dineros, 5);
                Utils.getDineros(dineros);
            });
        } else if (isLider.equals("false")) {
            modDineros.setVisibility(View.GONE);
            reloadMaster.setVisibility(View.VISIBLE);

            reloadMaster.setOnClickListener(view -> Utils.getDineros(dineros));
        }
    }
}