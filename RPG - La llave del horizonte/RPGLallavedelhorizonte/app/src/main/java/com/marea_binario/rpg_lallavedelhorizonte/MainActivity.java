package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Objeto;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Regiones;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Servicio;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        funcionar();
    }

    private void initData(){
        new Thread(() -> {
            // seteo objetos
            try {
                JSONObject objetosConn = new JSONObject(Utils.getData("get/objetos"));
                JSONObject objetos = objetosConn.getJSONObject("0");
                ArrayList<Objeto> objetosList = new ArrayList<>();
                int i = 0;
                while(true){
                    try {
                        JSONObject objeto = objetos.getJSONObject(String.valueOf(i));
                        int id = i;
                        String nombre = objeto.getString("nombre");
                        String tipo = objeto.getString("tipo");
                        int imagen_id = objeto.getInt("imagen_id");
                        String descripcion = objeto.getString("descripcion");
                        Integer objeto_principal = Integer.valueOf(objeto.getString("objeto_principal"));
                        Integer objeto_secundario = Integer.valueOf(objeto.getString("objeto_secundario"));
                        objetosList.add(new Objeto(nombre, descripcion, tipo, id, imagen_id, objeto_principal, objeto_secundario));

                    }catch (JSONException e){
                        e.printStackTrace();
                        break;
                    }
                }
                Data.setObjetos(objetosList);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }

            try{
                JSONObject objetosConn = new JSONObject(Utils.getData("get/regiones/no_foraneas"));
                JSONObject regiones = objetosConn.getJSONObject("Regiones");
                JSONObject servicios = objetosConn.getJSONObject("Servicios");
                ArrayList<Regiones> regionesList = new ArrayList<>();
                int i = 0;
                while(true){
                    try {
                        JSONObject region = regiones.getJSONObject(String.valueOf(i));
                        String tipo = region.getString("tipo");
                        String nombre = region.getString("nombre");
                        String descripcion = region.getString("descripcion");
                        String nombre_region = region.getString("nombre_region");
                        int id = region.getInt("id");
                        int id_region = region.getInt("id_region");
                        Integer imagen_id = Integer.valueOf(region.getString("imagen_id"));
                        Integer imagen_id_2 = Integer.valueOf(region.getString("imagen_id_2"));
                        regionesList.add(new Regiones(tipo, nombre, descripcion, nombre_region, id, id_region, imagen_id, imagen_id_2));

                    }catch (JSONException e){
                        e.printStackTrace();
                        break;
                    }
                }

                while(true){
                    try {
                        JSONObject servicio = servicios.getJSONObject(String.valueOf(i));

                        String nombre = servicio.getString("nombre");
                        String descripcion = servicio.getString("descripcion");
                        int id = servicio.getInt("id");
                        int id_region = servicio.getInt("id_region");
                        for (int j = 0; j < regionesList.size(); j++) {
                            if(regionesList.get(j).getId_region() == id_region){
                                Regiones reg = regionesList.get(j);
                                regionesList.remove(j);
                                reg.addServicio(new Servicio(id, id_region, nombre, descripcion));
                                regionesList.add(reg);
                            }
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                        break;
                    }
                }


                Data.setRegiones(regionesList);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }).start();

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
        initData();
        finish();
    }

    private String testConn(){
        return Utils.getData("post/conecta");
    }
}