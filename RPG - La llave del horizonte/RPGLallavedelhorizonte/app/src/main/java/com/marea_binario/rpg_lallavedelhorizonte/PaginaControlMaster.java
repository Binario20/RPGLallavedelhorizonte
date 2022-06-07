package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.DepositoObjetosItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class PaginaControlMaster extends AppCompatActivity {
    private Button modDineros, reloadMaster, armasBut, objetosBut, bestiarioBut, magiaBut, regionesBut, depositoObjetosBut;
    private SuperText dineros;
    private ImageView PviewInM, personajesBut, atributosBut;
    private JSONObject listaDeposito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_control_master);

        initContent();
        initListeners();
        setConfigIfLider();
        try {
            listaDeposito = new JSONObject(Utils.getData("get/obj_grupo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initContent() {
        modDineros = this.findViewById(R.id.modDinerosM);
        reloadMaster = this.findViewById(R.id.reloadMaster);
        armasBut = this.findViewById(R.id.armasMaster);
        objetosBut = this.findViewById(R.id.objetosMaster);
        bestiarioBut = this.findViewById(R.id.bestiarioMaster);
        magiaBut = this.findViewById(R.id.magiaMaster);
        regionesBut = this.findViewById(R.id.regionesMaster);
        depositoObjetosBut = this.findViewById(R.id.dipositoObjetosBut2);

        dineros = this.findViewById(R.id.dinerosM);

        PviewInM = this.findViewById(R.id.PviewInM);
        personajesBut = this.findViewById(R.id.personajesMaster);
        atributosBut = this.findViewById(R.id.atributosMaster);
    }

    private void initListeners() {
        depositoObjetosBut.setOnClickListener(view ->
                creatDepositoDisplayAlert()
        );

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
        String isLider = Utils.getData("get/soy_lider");
        while (isLider.equals("")) {
            isLider = Utils.getData("get/soy_lider");
        }
        if (isLider.equals("true")) {
            modDineros.setVisibility(View.VISIBLE);
            reloadMaster.setVisibility(View.GONE);

            modDineros.setOnClickListener(view -> {
                creatGestionaDinerosAlert();
            });
        } else if (isLider.equals("false")) {
            modDineros.setVisibility(View.GONE);
            reloadMaster.setVisibility(View.VISIBLE);

            reloadMaster.setOnClickListener(view -> Utils.getDineros(dineros));
        }
    }

    private void creatGestionaDinerosAlert() {
        AlertDialog.Builder gestiona_dineros_builder = new AlertDialog.Builder(PaginaControlMaster.this);
        gestiona_dineros_builder.setCancelable(true);
        View gestionPopup = getLayoutInflater().inflate(R.layout.gestion_dineros_item, null);

        gestiona_dineros_builder.setView(gestionPopup);

        AlertDialog alertEraseAlert = gestiona_dineros_builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        gestionPopup.findViewById(R.id.addDinerosBut).setOnClickListener(view -> {
            EditText add_num = gestionPopup.findViewById(R.id.addDineros);
            int add = Integer.parseInt(add_num.getText().toString());
            Utils.addDineros(dineros, add);
            Utils.getDineros(dineros);
            alertEraseAlert.cancel();
        });

        gestionPopup.findViewById(R.id.supDinerosBut).setOnClickListener(view -> {
            EditText sup_num = gestionPopup.findViewById(R.id.supDineros);
            int sup = Integer.parseInt(sup_num.getText().toString());
            Utils.subDineros(dineros, sup);
            Utils.getDineros(dineros);
            alertEraseAlert.cancel();
        });
    }

    private void creatDepositoDisplayAlert() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(PaginaControlMaster.this);
        builder.setCancelable(true);
        View popupView = getLayoutInflater().inflate(R.layout.item_list_display, null);

        builder.setView(popupView);

        androidx.appcompat.app.AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        Iterator<String> iter = listaDeposito.keys();
        LinearLayout caja_objetos = popupView.findViewById(R.id.caja_items);
        caja_objetos.removeAllViews();
        while (iter.hasNext()) {
            try {
                JSONObject object = listaDeposito.getJSONObject(iter.next());
                int id_objeto = Integer.parseInt(object.getString("id_objeto"));
                String cantidad = object.getString("cantidad");
                int imagen_id = Integer.parseInt(object.getString("imagen_id"));
                String nombre = object.getString("nombre");
                String descripcion = object.getString("descripcion");
                caja_objetos.addView(new DepositoObjetosItem(this, id_objeto, imagen_id,
                        nombre, descripcion, cantidad));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}