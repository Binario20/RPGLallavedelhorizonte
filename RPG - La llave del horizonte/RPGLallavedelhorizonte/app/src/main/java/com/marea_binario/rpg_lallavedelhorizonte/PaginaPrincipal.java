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

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.DepositoObjetosItem;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Item;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Personajes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class PaginaPrincipal extends AppCompatActivity {

    private ImageView imgJugador, fondo, dinerosImg;
    private SuperText fuerza, velocidad, destreza, magia, vitalidad, resistencia, inteligencia, punteria, nombre, dineros;
    private Button reloadPlayer, modDinerosP, depositoObjetosBut;
    private final Item[] items = new Item[4];
    private int id_perso = -1, id_jugador = -1;
    private Personajes personaje = null;
    private JSONObject objetos_iniciales;
    private JSONObject listaDeposito;
    private boolean segunda_lengua = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);
        Intent i = getIntent();
        try {
            id_perso = i.getIntExtra("idPerso", -1);
            id_jugador = i.getIntExtra("id_jugador", -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        initListeners();
        initData();
    }

    private void initData() {
        try {
            JSONObject personaje_info = new JSONObject(Utils.getData("get/personaje?id="+id_perso));
            Log.e("fonko??", String.valueOf(personaje_info));
            JSONObject perso = personaje_info.getJSONObject("Personaje").getJSONObject("0");
            personaje = new Personajes(
                    perso.getString("nombre"),
                    Integer.parseInt(perso.getString("id_procedencia")),
                    Integer.parseInt(perso.getString("id_especie")),
                    Integer.parseInt(perso.getString("edad")),
                    Float.parseFloat(perso.getString("altura_m").replace(",",".")),
                    Float.parseFloat(perso.getString("peso_kg").replace(",",".")),
                    perso.getString("sexo"),
                    Integer.parseInt(perso.getString("id_clase")),
                    Utils.stringToInteger(perso.getString("id_lengua")),
                    Integer.parseInt(perso.getString("vitalidad")),
                    Integer.parseInt(perso.getString("resistencia")),
                    Integer.parseInt(perso.getString("fuerza")),
                    Integer.parseInt(perso.getString("velocidad")),
                    Integer.parseInt(perso.getString("inteligencia")),
                    Integer.parseInt(perso.getString("punteria")),
                    Integer.parseInt(perso.getString("magia")),
                    perso.getString("personalidad"),
                    perso.getString("fisico")
            );
            personaje.setProcedencia(perso.getString("procedencia"));
            personaje.setEspecie(perso.getString("especie"));
            personaje.setClase(perso.getString("clase"));
            personaje.setLengua1(perso.getString("lengua"));

            JSONObject habil = personaje_info.getJSONObject("Habilidades");
            if (habil.length() != 0) {
                Iterator<String> iter = habil.keys();
                StringBuilder habil_str = new StringBuilder();
                while (iter.hasNext()) {
                    JSONObject object = habil.getJSONObject(iter.next());
                    habil_str.append("- ");
                    habil_str.append(object.getString("habilidad"));
                    habil_str.append(":\n   ");
                    habil_str.append(object.getString("descripcion"));
                    habil_str.append("\n");
                }
                personaje.setHabilidades(String.valueOf(habil_str));
            }

            objetos_iniciales = personaje_info.getJSONObject("Inicio").getJSONObject("0");
            Log.e("xkno?", String.valueOf(id_jugador));
            String arma, objeto;
            arma = objetos_iniciales.getString("id_arma");
            if (!arma.equals("NULL")) {
                JSONObject cosa = new JSONObject();
                cosa.put("id_jugador", id_jugador);
                cosa.put("cantidad", 1);
                cosa.put("id_cosa", arma);
                if (personaje.getClase().equals("Tirador"))
                    cosa.put("tipo", "arma negra");
                else
                    cosa.put("tipo", "arma blanca");
                Utils.getData("post/cosa_adquirida?new="+cosa);
            }
            objeto = objetos_iniciales.getString("id_objeto");
            if (!objeto.equals("NULL")) {
                JSONObject cosa = new JSONObject();
                cosa.put("id_jugador", id_jugador);
                cosa.put("cantidad", 1);
                cosa.put("id_cosa", objeto);
                cosa.put("tipo", "objeto");
                Utils.getData("post/cosa_adquirida?new="+cosa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        nombre.setEncodedText(personaje.getNombre());
        loadDataPlayer();

    }

    private void initListeners() {
        String isLider = Utils.getData("get/soy_lider");
        Log.e("Kpasao?",isLider);
        while (isLider.equals("")) {
            isLider = Utils.getData("get/soy_lider");
            Log.e("Kpasao?",isLider);
        }
        if (isLider.equals("true")) {
            modDinerosP.setVisibility(View.VISIBLE);
            modDinerosP.setOnClickListener(view -> creatGestionaDinerosAlert());
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
            Log.e("funkoo!!", String.valueOf(listaDeposito));
            JSONObject perso_est = new JSONObject(Utils.getData("get/personaje/estadisticas?id="+id_perso)).getJSONObject("0");
            Log.e("funko?", String.valueOf(perso_est));
            personaje.setEstadisticas(
                    Integer.parseInt(perso_est.getString("vitalidad")),
                    Integer.parseInt(perso_est.getString("resistencia")),
                    Integer.parseInt(perso_est.getString("fuerza")),
                    Integer.parseInt(perso_est.getString("velocidad")),
                    Integer.parseInt(perso_est.getString("inteligencia")),
                    Integer.parseInt(perso_est.getString("punteria")),
                    Integer.parseInt(perso_est.getString("magia"))
            );
            vitalidad.setEncodedText(String.valueOf(personaje.getVitalidad()));
            resistencia.setEncodedText(String.valueOf(personaje.getResistencia()));
            fuerza.setEncodedText(String.valueOf(personaje.getFuerza()));
            velocidad.setEncodedText(String.valueOf(personaje.getVelocidad()));
            inteligencia.setEncodedText(String.valueOf(personaje.getInteligencia()));
            punteria.setEncodedText(String.valueOf(personaje.getPunteria()));
            magia.setEncodedText(String.valueOf(personaje.getMagia()));
            destreza.setEncodedText(String.valueOf(personaje.getDestreza()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!segunda_lengua && personaje.getInteligencia() >= 4) {
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
        fondo.setBackground(AppCompatResources.getDrawable(this, R.drawable.todoynada));

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
                //Log.d("Id. Lengua", id+". "+nombre);
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
            int id_lengua = rg.getCheckedRadioButtonId();
            //Log.e("RB checked", String.valueOf(id_lengua));
            if (id_lengua != -1) {
                Log.e("fonko?", Utils.getData("post/segunda_lengua?id="+ id_lengua));
                personaje.setLengua2(id_lengua, lenguas[id_lengua]);
                segunda_lengua = true;
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

        // INFORMACIÓN GENERAL
        nombre = popupView.findViewById(R.id.nomPerso);
        especie = popupView.findViewById(R.id.especiePerso);
        procedencia = popupView.findViewById(R.id.procedenciaPerso);
        clase = popupView.findViewById(R.id.clasePerso);
        lengua1 = popupView.findViewById(R.id.lengua1Perso);
        lengua2 = popupView.findViewById(R.id.lengua2Perso);

        nombre.setEncodedText(personaje.getNombre());
        especie.setEncodedText(personaje.getEspecie());
        procedencia.setEncodedText(personaje.getProcedencia());
        clase.setEncodedText(personaje.getClase());
        String lengua1_str = personaje.getLengua1();
        Log.e("Kpasao1?", lengua1_str);
        if (personaje.getLengua1() == null || lengua1_str.equals("NULL")) {
            lengua1.setEncodedText("");
        } else {
            lengua1.setEncodedText(lengua1_str);
        }
        if (segunda_lengua) {
            String lengua2_str = personaje.getLengua2();
            Log.e("Kpasao2?", lengua2_str);
            if (lengua2_str == null || lengua2_str.equals("NULL")) {
                lengua2.setEncodedText("");
            } else {
                lengua2.setEncodedText(lengua2_str);
            }
        }

        // INFORMACIÓN ESPECIFICA
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

        sexo.setEncodedText(personaje.getSexo());
        edad.setEncodedText(String.valueOf(personaje.getEdad()));
        altura.setEncodedText(String.valueOf(personaje.getAltura()));
        peso.setEncodedText(String.valueOf(personaje.getPeso()));
        if (personaje.getFisico() == null || personaje.getFisico().equals("NULL")) {
            fisicoI.setVisibility(View.GONE);
            fisico.setVisibility(View.GONE);
        } else {
            fisico.setEncodedText(personaje.getFisico());
        }
        if (personaje.getPersonalidad() == null || personaje.getPersonalidad().equals("NULL")) {
            personalidadI.setVisibility(View.GONE);
            personalidad.setVisibility(View.GONE);
        } else {
            personalidad.setEncodedText(personaje.getPersonalidad());
        }
        if (personaje.getHabilidades() == null) {
            habilidadesI.setVisibility(View.GONE);
            habilidades.setVisibility(View.GONE);
        } else {
            habilidades.setEncodedText(personaje.getHabilidades());
        }

        // ESTADISTICAS
        vitalidad = popupView.findViewById(R.id.vitalidad);
        resistencia = popupView.findViewById(R.id.resistencia);
        fuerza = popupView.findViewById(R.id.fuerza);
        velocidad = popupView.findViewById(R.id.velocidad);
        inteligencia = popupView.findViewById(R.id.inteligencia);
        punteria = popupView.findViewById(R.id.punteria);
        magia = popupView.findViewById(R.id.magia);
        destreza = popupView.findViewById(R.id.destreza);

        vitalidad.setEncodedText(String.valueOf(personaje.getVitalidad()));
        resistencia.setEncodedText(String.valueOf(personaje.getResistencia()));
        fuerza.setEncodedText(String.valueOf(personaje.getFuerza()));
        velocidad.setEncodedText(String.valueOf(personaje.getVelocidad()));
        inteligencia.setEncodedText(String.valueOf(personaje.getInteligencia()));
        punteria.setEncodedText(String.valueOf(personaje.getPunteria()));
        magia.setEncodedText(String.valueOf(personaje.getMagia()));
        destreza.setEncodedText(String.valueOf(personaje.getDestreza()));
    }
}