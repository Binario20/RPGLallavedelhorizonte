package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ArmaBlanca;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ArmaNegra;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Bestia;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.DepositoObjetosItem;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ItemListItem;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Magia;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ModEstadisticasItem;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Objeto;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Regiones;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class PaginaControlMaster extends AppCompatActivity {
    private Button modDineros, reloadMaster, armasBut, objetosBut, bestiarioBut, magiaBut, regionesBut, depositoObjetosBut;
    private SuperText dineros;
    private ImageView PviewInM, personajesBut, atributosBut;
    private JSONObject listaDeposito, listaPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_control_master);

        initContent();
        initListeners();
        setConfigIfLider();
        try {
            listaDeposito = new JSONObject(Utils.getData("get/obj_grupo"));
            String x = Utils.getData("get/conectados/estadisticas");
            if (x.equals("204 OK"))
                listaPersonas = new JSONObject();
            else
                listaPersonas = new JSONObject(x);
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
        depositoObjetosBut.setOnClickListener(view -> creatDepositoDisplayAlert());

        armasBut.setOnClickListener(view -> listaArmas());

        objetosBut.setOnClickListener(view -> listaObjetos());

        bestiarioBut.setOnClickListener(view -> listaBestiario());

        magiaBut.setOnClickListener(view -> listaMagia());

        regionesBut.setOnClickListener(view -> listaRegiones());

        personajesBut.setOnClickListener(view -> {
            // poder canviar los objetos/armas... que tiene un jugador
        });

        atributosBut.setOnClickListener(view -> {
            listaEstadisticas();
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

            modDineros.setOnClickListener(view -> creatGestionaDinerosAlert());
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

    private void createAddCosaAlert(boolean addImg, Integer img_id, String tipo, int id_cosa) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(PaginaControlMaster.this);
        builder.setCancelable(true);
        View popupView = getLayoutInflater().inflate(R.layout.gestion_cantidad_item, null);

        builder.setView(popupView);

        androidx.appcompat.app.AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        // initComponents
        Button subOne, addOne, acceptBut, turnBack, reLoad, showImg;
        TextView howMuch, escojeTV;
        RadioGroup rg;
        View divCant, divShow;
        final int[] cantidad = {1};

        rg = popupView.findViewById(R.id.forWho);
        divCant = popupView.findViewById(R.id.divCant);
        divShow = popupView.findViewById(R.id.divShow);
        howMuch = popupView.findViewById(R.id.howMuch);
        subOne = popupView.findViewById(R.id.subOne);
        addOne = popupView.findViewById(R.id.addOne);
        escojeTV = popupView.findViewById(R.id.escojeTV);
        acceptBut = popupView.findViewById(R.id.acceptBut);
        turnBack = popupView.findViewById(R.id.turnBack);
        reLoad = popupView.findViewById(R.id.reLoad);
        showImg = popupView.findViewById(R.id.showImg);

        //Que cantidad de Cosa
        addOne.setOnClickListener(view -> {
            cantidad[0]++;
            howMuch.setText(String.valueOf(cantidad[0]));
        });
        subOne.setOnClickListener(view -> {
            cantidad[0]--;
            howMuch.setText(String.valueOf(cantidad[0]));
        });

        // A quien?
        if (tipo.equals("grupalObject")) {
            divCant.setVisibility(View.GONE);
            escojeTV.setVisibility(View.GONE);
            rg.setVisibility(View.GONE);
            reLoad.setVisibility(View.GONE);
            acceptBut.setOnClickListener(view -> {
                try {
                    JSONObject obj = new JSONObject();
                    obj.put("id_objeto",id_cosa);
                    obj.put("cantidad",cantidad[0]);
                    Utils.getData("put/obj_grupo?upd="+obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                isGroupObject(id_cosa, cantidad[0]);
                alertEraseAlert.cancel();
            });
        } else {
            Iterator<String> iter = listaPersonas.keys();
            rg.setOrientation(RadioGroup.VERTICAL);
            while (iter.hasNext()) {
                try {
                    JSONObject object = listaPersonas.getJSONObject(iter.next());
                    SuperRadioButton rb = new SuperRadioButton(this);
                    rb.setId(Integer.parseInt(object.getString("id")));
                    rb.setEncodedText(object.getString("nombre"));
                    rg.addView(rb);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            acceptBut.setOnClickListener(view -> {
                int id_persona = rg.getCheckedRadioButtonId();
                Log.e("id_persona", String.valueOf(id_persona));
                if (id_persona >= 0){
                    try {
                        JSONObject cosa = new JSONObject();
                        cosa.put("id_jugador",id_persona);
                        cosa.put("id_cosa", id_cosa);
                        cosa.put("tipo", tipo);
                        cosa.put("cantidad", cantidad[0]);
                        Utils.getData("post/cosa_adquirida?new="+cosa);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    alertEraseAlert.cancel();
                }
            });
            reLoad.setOnClickListener(view -> {
                try {
                    listaPersonas = new JSONObject(Utils.getData("get/conectados/estadisticas"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Iterator<String> keys = listaPersonas.keys();
                rg.setOrientation(RadioGroup.VERTICAL);
                while (keys.hasNext()) {
                    try {
                        JSONObject object = listaPersonas.getJSONObject(keys.next());
                        SuperRadioButton rb = new SuperRadioButton(this);
                        rb.setId(Integer.parseInt(object.getString("id")));
                        rb.setEncodedText(object.getString("nombre"));
                        rg.addView(rb);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        turnBack.setOnClickListener(v -> alertEraseAlert.cancel());

        if (addImg) {
            showImg.setOnClickListener(view -> {
                setImg(img_id);
                alertEraseAlert.cancel();
            });
        } else {
            divShow.setVisibility(View.GONE);
            showImg.setVisibility(View.GONE);
        }
    }

    private void setImg(int img_id) {
        Utils.getData("put/img_on_display?id="+img_id);
        PviewInM.setImageResource(Data.getImg(img_id));
    }

    private void listaBestiario() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(PaginaControlMaster.this);
        builder.setCancelable(true);
        View popupView = getLayoutInflater().inflate(R.layout.item_list_display, null);

        builder.setView(popupView);

        androidx.appcompat.app.AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        LinearLayout caja_objetos = popupView.findViewById(R.id.caja_items);
        caja_objetos.removeAllViews();
        for(Bestia bestia : Data.getBestiario()){
            ItemListItem item = new ItemListItem(this, bestia.getId(), Data.BESTIARIO, bestia);
            item.getAdd().setOnClickListener(view -> {
                if (bestia.isMontura())
                    createAddCosaAlert(true,bestia.getImg_id(), Data.BESTIARIO, bestia.getId());
                else
                    setImg(bestia.getImg_id());
            });
            caja_objetos.addView(item);
        }
    }

    private void listaObjetos() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(PaginaControlMaster.this);
        builder.setCancelable(true);
        View popupView = getLayoutInflater().inflate(R.layout.item_list_display, null);

        builder.setView(popupView);

        androidx.appcompat.app.AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        LinearLayout caja_objetos = popupView.findViewById(R.id.caja_items);
        caja_objetos.removeAllViews();
        for(Objeto objeto : Data.getObjetos()){
            ItemListItem item = new ItemListItem(this, objeto.getId(), Data.OBJETO, objeto);
            item.getAdd().setOnClickListener(view -> {
                if (isGroupObject(objeto.getId(), null))
                    createAddCosaAlert(true, objeto.getImg_id(), "grupalObject", objeto.getId());
                else
                    createAddCosaAlert(true,objeto.getImg_id(), Data.OBJETO, objeto.getId());
            });
            caja_objetos.addView(item);
        }
    }

    private boolean isGroupObject(int id_cosa, Integer cantidad) {
        Iterator<String> iter = listaDeposito.keys();
        boolean isEqual = false;
        while (iter.hasNext()) {
            try {
                JSONObject object = listaDeposito.getJSONObject(iter.next());
                String id_object = object.getString("id_objeto");
                //Log.e("id's", id_cosa+" =? "+id_object);
                if (String.valueOf(id_cosa).equals(id_object)) {
                    if (cantidad != null) {
                        int cant = Integer.parseInt(object.getString("cantidad"));
                        object.put("cantidad", cant + cantidad);
                    }
                    isEqual = true;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isEqual;
    }

    private void listaArmas() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(PaginaControlMaster.this);
        builder.setCancelable(true);
        View popupView = getLayoutInflater().inflate(R.layout.item_list_display, null);

        builder.setView(popupView);

        androidx.appcompat.app.AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        LinearLayout caja_objetos = popupView.findViewById(R.id.caja_items);
        caja_objetos.removeAllViews();
        for(ArmaBlanca armaBlanca : Data.getArmasBlancas()){
            ItemListItem item = new ItemListItem(this, armaBlanca.getId(), Data.ARMA_BLANCA, armaBlanca);
            item.getAdd().setOnClickListener(view -> createAddCosaAlert(true, armaBlanca.getImg_id(), Data.ARMA_BLANCA, armaBlanca.getId()));
            caja_objetos.addView(item);
        }

        for(ArmaNegra armaNegra : Data.getArmasNegras()){
            ItemListItem item = new ItemListItem(this, armaNegra.getId(), Data.ARMA_NEGRA, armaNegra);
            item.getAdd().setOnClickListener(view -> createAddCosaAlert(true,armaNegra.getImg_id(), Data.ARMA_NEGRA, armaNegra.getId()));
            caja_objetos.addView(item);
        }
    }

    private void listaMagia() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(PaginaControlMaster.this);
        builder.setCancelable(true);
        View popupView = getLayoutInflater().inflate(R.layout.item_list_display, null);

        builder.setView(popupView);

        androidx.appcompat.app.AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        LinearLayout caja_objetos = popupView.findViewById(R.id.caja_items);
        caja_objetos.removeAllViews();

        for(Magia libro : Data.getMagia()){
            ItemListItem item = new ItemListItem(this, libro.getId(), Data.MAGIA, libro);
            item.getAdd().setOnClickListener(view -> createAddCosaAlert(false,libro.getImg_id(), Data.MAGIA, libro.getId()));
            caja_objetos.addView(item);
        }
    }

    private void listaRegiones() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(PaginaControlMaster.this);
        builder.setCancelable(true);
        View popupView = getLayoutInflater().inflate(R.layout.item_list_display, null);

        builder.setView(popupView);

        androidx.appcompat.app.AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        LinearLayout caja_objetos = popupView.findViewById(R.id.caja_items);
        caja_objetos.removeAllViews();

        for(Regiones regiones : Data.getRegiones()){
            ItemListItem item = new ItemListItem(this, regiones.getId(), Data.REGIONES, regiones);
            item.getAdd().setOnClickListener(view -> setImg(regiones.getImg_id()));
            caja_objetos.addView(item);
        }
    }

    private void listaEstadisticas() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(PaginaControlMaster.this);
        builder.setCancelable(true);
        View popupView = getLayoutInflater().inflate(R.layout.item_list_display, null);

        builder.setView(popupView);

        androidx.appcompat.app.AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        LinearLayout caja_objetos = popupView.findViewById(R.id.caja_items);
        caja_objetos.removeAllViews();
        Iterator<String> iter = listaPersonas.keys();
        while (iter.hasNext()) {
            try {
                JSONObject object = listaPersonas.getJSONObject(iter.next());
                Log.e("estPerso", String.valueOf(object));
                ModEstadisticasItem perso = new ModEstadisticasItem(
                        this,
                        object.getInt("id"),
                        Integer.parseInt(object.getString("vitalidad")),
                        Integer.parseInt(object.getString("resistencia")),
                        Integer.parseInt(object.getString("fuerza")),
                        Integer.parseInt(object.getString("velocidad")),
                        Integer.parseInt(object.getString("inteligencia")),
                        Integer.parseInt(object.getString("punteria")),
                        Integer.parseInt(object.getString("magia"))
                        );
                perso.setNombre(object.getString("nombre"));
                caja_objetos.addView(perso);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}