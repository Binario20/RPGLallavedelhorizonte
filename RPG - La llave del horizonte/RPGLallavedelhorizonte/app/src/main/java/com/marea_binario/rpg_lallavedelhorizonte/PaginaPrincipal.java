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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.DepositoObjetosItem;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Item;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class PaginaPrincipal extends AppCompatActivity {

    private ImageView imgJugador, fondo, dinerosImg;
    private SuperText fuerza, velocidad, destreza, magia, vitalidad, resistencia, inteligencia, punteria, nombre, dineros;
    private Button reloadPlayer, modDinerosP, depositoObjetosBut;
    private String est_inteligencia;
    private final Item[] items = new Item[4];
    private int id_perso = -1;
    private JSONObject personaje_info;
    private JSONObject perso_est_actu;
    private JSONObject listaDeposito;
    private int segunda_lengua_id = -1;
    private String segunda_lengua;

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
        try {
            personaje_info = new JSONObject(Utils.getData("get/personaje?id="+id_perso));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Log.e("fonko??", String.valueOf(personaje_info));
            JSONObject perso = personaje_info.getJSONObject("Personaje").getJSONObject("0");
            nombre.setEncodedText(perso.getString("nombre"));
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, String.valueOf(e.getMessage()), Toast.LENGTH_SHORT).show();
        }

        loadDataPlayer();

    }

    private void initListeners() {
        String isLider = Utils.getData("get/soy_lider");
        while (isLider.equals("")) {
            isLider = Utils.getData("get/soy_lider");
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

        imgJugador.setOnClickListener(view -> crearPersonajeDisplayAlert());
    }

    private void loadDataPlayer() {
        Utils.getDineros(dineros);
        try {
            listaDeposito = new JSONObject(Utils.getData("get/obj_grupo"));
            perso_est_actu = new JSONObject(Utils.getData("get/personaje/estadisticas?id="+id_perso)).getJSONObject("0");
            est_inteligencia = perso_est_actu.getString("inteligencia");
            fuerza.setEncodedText(perso_est_actu.getString("fuerza"));
            inteligencia.setEncodedText(est_inteligencia);
            vitalidad.setEncodedText(perso_est_actu.getString("vitalidad"));
            resistencia.setEncodedText(perso_est_actu.getString("resistencia"));
            velocidad.setEncodedText(perso_est_actu.getString("velocidad"));
            punteria.setEncodedText(perso_est_actu.getString("punteria"));
            magia.setEncodedText(perso_est_actu.getString("magia"));
            destreza.setEncodedText(perso_est_actu.getString("destreza"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (segunda_lengua_id == -1 && Integer.parseInt(est_inteligencia) >= 4) {
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

        JSONObject lengJson = new JSONObject();
        try {
            lengJson = new JSONObject(Utils.getData("get/lenguas_antiguas"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Iterator<String> iter = lengJson.keys();
        RadioGroup rg = popupView.findViewById(R.id.lenguaRadioGroup);
        rg.setOrientation(RadioGroup.VERTICAL);
        String[] lenguas = new String[lengJson.length()];
        while (iter.hasNext()) {
            try {
                JSONObject object = lengJson.getJSONObject(iter.next());
                //Log.e("Lengua List", String.valueOf(object));
                String nombre = object.getString("nombre");
                int id = Integer.parseInt(object.getString("id"));
                Log.d("Id. Lengua", id+". "+nombre);
                lenguas[id] = nombre;

                if (id != 3) {
                    SuperRadioButton rb = new SuperRadioButton(this);
                    rb.setId(id);
                    rb.setEncodedText(nombre);
                    rg.addView(rb);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Button accept = popupView.findViewById(R.id.acceptLeng);
        accept.setOnClickListener(view -> {
            segunda_lengua_id = rg.getCheckedRadioButtonId();
            //Log.e("RB checked", String.valueOf(segunda_lengua_id));
            if (segunda_lengua_id != -1) {
                Log.e("fonko?", Utils.getData("post/segunda_lengua?id="+ segunda_lengua_id));
                segunda_lengua = lenguas[segunda_lengua_id];
                alertEraseAlert.cancel();
            }
        });
    }

    private void crearPersonajeDisplayAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PaginaPrincipal.this);
        builder.setCancelable(true);
        View popupView = getLayoutInflater().inflate(R.layout.info_personaje_display, null);

        builder.setView(popupView);

        AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        SuperText nombre, especie, procedencia, clase, lengua1, lengua2;
        SuperText sexo, edad, altura, peso, fisico, personalidad, habilidades;
        SuperText fisicoI, personalidadI, habilidadesI;
        SuperText vitalidad, resistencia, fuerza, velocidad, inteligencia, punteria, magia, destreza;

        nombre = popupView.findViewById(R.id.nomPerso);
        especie = popupView.findViewById(R.id.especiePerso);
        procedencia = popupView.findViewById(R.id.procedenciaPerso);
        clase = popupView.findViewById(R.id.clasePerso);
        lengua1 = popupView.findViewById(R.id.lengua1Perso);
        lengua2 = popupView.findViewById(R.id.lengua2Perso);

        sexo = popupView.findViewById(R.id.sexoPerso);
        edad = popupView.findViewById(R.id.edadPerso);
        altura = popupView.findViewById(R.id.alturaPerso);
        peso = popupView.findViewById(R.id.pesoPerso);
        fisicoI = popupView.findViewById(R.id.fisicoPersoI);
        fisico = popupView.findViewById(R.id.fisicoPerso);
        personalidadI = popupView.findViewById(R.id.personalidadPersoI);
        personalidad = popupView.findViewById(R.id.personalidadPerso);
        habilidadesI = popupView.findViewById(R.id.habilidadesPersoI);
        habilidades = popupView.findViewById(R.id.habilidadesPerso);

        vitalidad = popupView.findViewById(R.id.vitalidad);
        resistencia = popupView.findViewById(R.id.resistencia);
        fuerza = popupView.findViewById(R.id.fuerza);
        velocidad = popupView.findViewById(R.id.velocidad);
        inteligencia = popupView.findViewById(R.id.inteligencia);
        punteria = popupView.findViewById(R.id.punteria);
        magia = popupView.findViewById(R.id.magia);
        destreza = popupView.findViewById(R.id.destreza);

        try {
            JSONObject perso = personaje_info.getJSONObject("Personaje").getJSONObject("0");
            nombre.setEncodedText(perso.getString("nombre"));
            especie.setEncodedText(perso.getString("especie"));
            procedencia.setEncodedText(perso.getString("procedencia"));
            clase.setEncodedText(perso.getString("clase"));
            lengua1.setEncodedText(perso.getString("lengua"));
            lengua2.setEncodedText(segunda_lengua);

            sexo.setEncodedText(perso.getString("sexo"));
            edad.setEncodedText(perso.getString("edad"));
            altura.setEncodedText(perso.getString("altura_m"));
            peso.setEncodedText(perso.getString("peso_kg"));
            if (perso.getString("fisico").equals("NULL")) {
                fisicoI.setVisibility(View.GONE);
                fisico.setVisibility(View.GONE);
            } else {
                fisico.setEncodedText(perso.getString("fisico"));
            }
            if (perso.getString("personalidad").equals("NULL")) {
                personalidadI.setVisibility(View.GONE);
                personalidad.setVisibility(View.GONE);
            } else {
                fisico.setEncodedText(perso.getString("personalidad"));
            }
            JSONObject habil = personaje_info.getJSONObject("Habilidades");
            if (habil.length() == 0) {
                habilidadesI.setVisibility(View.GONE);
                habilidades.setVisibility(View.GONE);
            } else {
                Iterator<String> iter = habil.keys();
                String habil_str = "";
                while (iter.hasNext()) {
                    JSONObject object = habil.getJSONObject(iter.next());
                    habil_str += "- ";
                    habil_str += object.getString("habilidad");
                    habil_str += "\n  ";
                    habil_str += object.getString("descripcion");
                    habil_str += "\n";
                }
            }

            vitalidad.setEncodedText(perso_est_actu.getString("vitalidad"));
            resistencia.setEncodedText(perso_est_actu.getString("resistencia"));
            fuerza.setEncodedText(perso_est_actu.getString("fuerza"));
            velocidad.setEncodedText(perso_est_actu.getString("velocidad"));
            inteligencia.setEncodedText(perso_est_actu.getString("inteligencia"));
            punteria.setEncodedText(perso_est_actu.getString("punteria"));
            magia.setEncodedText(perso_est_actu.getString("magia"));
            destreza.setEncodedText(perso_est_actu.getString("destreza"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}