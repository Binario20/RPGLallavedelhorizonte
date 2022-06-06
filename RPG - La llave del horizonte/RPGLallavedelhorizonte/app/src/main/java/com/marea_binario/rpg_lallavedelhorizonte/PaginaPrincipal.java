package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.DepositoObjetosItem;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Item;

import org.json.JSONObject;

import java.util.Iterator;

public class PaginaPrincipal extends AppCompatActivity {

    private ImageView imgJugador, fondo, dinerosImg;
    private SuperText fuerza, velocidad, destreza, magia, vitalidad, resistencia, inteligencia, punteria, nombre, dineros;
    private Button reloadPlayer, modDinerosP, depositoObjetosBut;
    private String intel_txt;
    private final Item[] items = new Item[4];
    private int id_perso = -1;
    private JSONObject listaDeposito;
    private boolean segunda_lengua = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);
        Intent i = getIntent();
        try{
            id_perso = i.getIntExtra("Id", -1);
        }catch (Exception e){
            e.printStackTrace();
        }
        initComponents();
        initListeners();
        initData();
    }

    private void initData() {
        String j = String.valueOf(this.id_perso);
        //j = "1";
        ConnTask connTask = new ConnTask("get/personaje?id=" + j);
        connTask.execute();
        try {
            String kk = connTask.get().toString().trim();
            //Toast.makeText(this, kk, Toast.LENGTH_SHORT).show();
            Log.e("jj", kk);
            JSONObject perso = new JSONObject(kk).getJSONObject("0");
            Toast.makeText(this, perso.toString(), Toast.LENGTH_SHORT).show();
            intel_txt = perso.getString("inteligencia");
            fuerza.setEncodedText(perso.getString("fuerza"));
            inteligencia.setEncodedText(intel_txt);
            vitalidad.setEncodedText(perso.getString("vitalidad"));
            resistencia.setEncodedText(perso.getString("resistencia"));
            velocidad.setEncodedText(perso.getString("velocidad"));
            punteria.setEncodedText(perso.getString("punteria"));
            magia.setEncodedText(perso.getString("magia"));
            destreza.setEncodedText(perso.getString("destreza"));
            nombre.setEncodedText(perso.getString("nombre"));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, String.valueOf(e.getMessage()), Toast.LENGTH_SHORT).show();
        }

        loadDataPlayer();

    }

    private void initListeners() {
        ConnTask connTask = new ConnTask("get/soy_lider");
        connTask.execute();
        String isLider = "";
        try {
            isLider = connTask.get().toString().trim();
            Log.e("isLider", isLider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (isLider.equals("")) {
            ConnTask connTask2 = new ConnTask("get/soy_lider");
            connTask2.execute();
            try {
                isLider = connTask2.get().toString().trim();
                Log.e("isLider", isLider);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (isLider.equals("true")) {
            modDinerosP.setVisibility(View.VISIBLE);

            modDinerosP.setOnClickListener(view -> {
                creatGestionaDinerosAlert();
            });
        } else if (isLider.equals("false")) {
            modDinerosP.setVisibility(View.GONE);
        }

        reloadPlayer.setOnClickListener(view -> loadDataPlayer());

        dinerosImg.setOnClickListener(view -> Utils.getDineros(dineros));

        depositoObjetosBut.setOnClickListener(view -> creatDepositoDisplayAlert());
    }

    private void loadDataPlayer() {
        Utils.getDineros(dineros);
        listaDeposito = Utils.getDepositoObjetos();
        if (!segunda_lengua && Integer.parseInt(intel_txt) >= 4) {
            creatSegundaLenguaAlert();
        }
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
        dinerosImg = this.findViewById(R.id.dinerosImg);
        dineros = this.findViewById(R.id.dineros);

        reloadPlayer = this.findViewById(R.id.reloadPlayer);
        modDinerosP = this.findViewById(R.id.modDinerosP);
        depositoObjetosBut = this.findViewById(R.id.dipositoObjetosBut);

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
        dinerosImg.setImageDrawable(roundedBitmapDrawable);

        nombre = this.findViewById(R.id.nombre);

    }

    private void creatGestionaDinerosAlert() {
        AlertDialog.Builder gestiona_dineros_builder = new AlertDialog.Builder(PaginaPrincipal.this);
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
            alertEraseAlert.cancel();
        });

        gestionPopup.findViewById(R.id.supDinerosBut).setOnClickListener(view -> {
            EditText sup_num = gestionPopup.findViewById(R.id.supDineros);
            int sup = Integer.parseInt(sup_num.getText().toString());
            Utils.subDineros(dineros, sup);
            alertEraseAlert.cancel();
        });
    }

    private void creatDepositoDisplayAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PaginaPrincipal.this);
        builder.setCancelable(true);
        View popupView = getLayoutInflater().inflate(R.layout.item_list_display, null);

        builder.setView(popupView);

        AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        Iterator<String> iter = listaDeposito.keys();
        LinearLayout caja_objetos = popupView.findViewById(R.id.caja_items);
        caja_objetos.removeAllViews();
        while (iter.hasNext()) {
            try {
                JSONObject object = listaDeposito.getJSONObject(iter.next());
                //Log.e("Object List", String.valueOf(object));
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

    private void creatSegundaLenguaAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PaginaPrincipal.this);
        builder.setCancelable(false);
        View popupView = getLayoutInflater().inflate(R.layout.segunda_lengua_escojer, null);

        builder.setView(popupView);

        AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        ConnTask connTask2 = new ConnTask("get/lenguas_antiguas");
        connTask2.execute();
        String lengList = null;
        JSONObject lengJson = null;
        try {
            lengList = connTask2.get().toString().trim();
            //Log.e("Lenguas Antiguas", lengList);
            lengJson = new JSONObject(lengList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator<String> iter = lengJson.keys();
        RadioGroup rg = popupView.findViewById(R.id.lenguaRadioGroup);
        rg.setOrientation(RadioGroup.VERTICAL);
        while (iter.hasNext()) {
            try {
                JSONObject object = lengJson.getJSONObject(iter.next());
                Log.e("Lengua List", String.valueOf(object));
                String nombre = object.getString("nombre");
                int id = Integer.parseInt(object.getString("id"));
                Log.d("Id. Lengua", id+". "+nombre);
                if (id != 3) {
                    //SegundaLenguaItem rb = new SegundaLenguaItem(this, nombre);
                    RadioButton rb = new RadioButton(this);
                    rb.setId(id);
                    rb.setText(nombre);
                    rg.addView(rb);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Button accept = popupView.findViewById(R.id.acceptLeng);
        accept.setOnClickListener(view -> {
            int check = rg.getCheckedRadioButtonId();
            Log.e("RB checked", String.valueOf(check));
            if (check != -1) {
                ConnTask connTask = new ConnTask("post/segunda_lengua?id="+check);
                connTask.execute();
                try {
                    String kk = connTask.get().toString().trim();
                    Log.e("fonko?", kk);
                    segunda_lengua = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                alertEraseAlert.cancel();
            }
        });

    }
}