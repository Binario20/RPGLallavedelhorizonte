package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ArmaBlanca;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ArmaNegra;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Bestia;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Hechizo;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Magia;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Objeto;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Regiones;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Servicio;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
                ArrayList<Objeto> objetosList = new ArrayList<>();
                int i = 0;
                while(true){
                    try {
                        JSONObject objeto = objetosConn.getJSONObject(String.valueOf(i));
                        int id = objeto.getInt("id");
                        String nombre = objeto.getString("nombre");
                        String tipo = objeto.getString("tipo");
                        int imagen_id = objeto.getInt("imagen_id");
                        String descripcion = objeto.getString("descripcion");
                        Integer objeto_principal = Utils.stringToInteger(objeto.getString("objeto_principal"));
                        Integer objeto_secundario = Utils.stringToInteger(objeto.getString("objeto_secundario"));
                        Objeto obj = new Objeto(nombre, descripcion, tipo, id, imagen_id, objeto_principal, objeto_secundario);
                        obj.setObj1(objeto.getString("obj1"));
                        obj.setObj2(objeto.getString("obj2"));
                        objetosList.add(obj);
                        Log.e("Objeto", String.valueOf(objeto));
                        i++;
                    }catch (JSONException e){
                        break;
                    }
                }
                Data.setObjetos(objetosList);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            //seteo regiones
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
                        Integer imagen_id = Utils.stringToInteger(region.getString("imagen_id"));
                        Integer imagen_id_2 = Utils.stringToInteger(region.getString("imagen_id_2"));
                        regionesList.add(new Regiones(tipo, nombre, descripcion, nombre_region, id, id_region, imagen_id, imagen_id_2));
                        Log.e("Region", String.valueOf(region));
                        i++;
                    }catch (Exception e){
                        break;
                    }
                }
                i=0;
                while(true){
                    try {
                        JSONObject servicio = servicios.getJSONObject(String.valueOf(i));
                        String nombre = servicio.getString("nombre");
                        String descripcion = servicio.getString("descripcion");
                        int id = servicio.getInt("id");
                        int id_region = servicio.getInt("id_region");
                        for (Regiones region : regionesList) {
                            if(region.getId() == id_region){
                                region.addServicio(new Servicio(id, id_region, nombre, descripcion));
                                break;
                            }
                        }
                        Log.e("Servicio", String.valueOf(servicio));
                        i++;
                    }catch (JSONException e){
                        break;
                    }
                }
                Data.setRegiones(regionesList);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            // seteo armas
            try {
                //JSONObject armas = new JSONObject(Utils.getData("get/objetos/armas"));
                JSONObject proyectiles = new JSONObject(Utils.getData("get/objetos/armas/proyectil"));
                ArrayList<ArmaNegra> negrasList = new ArrayList<>();
                int i = 0;
                //armas negras
                while(true){
                    try {
                        JSONObject arma = proyectiles.getJSONObject(String.valueOf(i));
                        String nombre = arma.getString("nombre");
                        int id_arma = arma.getInt("id_arma");
                        String subtipo = arma.getString("subtipo");
                        Integer requisito1 = Utils.stringToInteger(arma.getString("requisito1"));
                        Integer requisito2 = Utils.stringToInteger(arma.getString("requisito2"));
                        String campo1 = arma.getString("campo1");
                        String campo2 = arma.getString("campo2");
                        int daño = arma.getInt("dano");
                        String ataque = arma.getString("ataque");
                        String rango = arma.getString("rango");
                        Integer normal = Utils.stringToInteger(arma.getString("normal"));
                        Integer imagen_id = Utils.stringToInteger(arma.getString("imagen_id"));
                        String descripcion = arma.getString("descripcion");
                        Integer objeto_principal = Utils.stringToInteger(arma.getString("objeto_principal"));
                        Integer objeto_secundario = Utils.stringToInteger(arma.getString("objeto_secundario"));
                        ArmaNegra armaNegra = new ArmaNegra(nombre, subtipo,campo1, campo2, ataque, daño, rango, descripcion,
                                id_arma,requisito1, requisito2, normal, imagen_id, objeto_principal, objeto_secundario);
                        armaNegra.setObj1(arma.getString("obj1"));
                        armaNegra.setObj2(arma.getString("obj2"));
                        negrasList.add(armaNegra);
                        Log.e("Arma Negra", String.valueOf(arma));
                        i++;
                    }catch (JSONException e){
                        break;
                    }
                }
                Data.setArmasNegras(negrasList);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }


            try {
                JSONObject cuerpo = new JSONObject(Utils.getData("get/objetos/armas/cuerpo"));
                ArrayList<ArmaBlanca> blancasList = new ArrayList<>();
                //armas blancas
                int i=0;
                while(true){
                    try {
                        JSONObject arma = cuerpo.getJSONObject(String.valueOf(i));
                        String nombre = arma.getString("nombre");
                        int id_arma = arma.getInt("id_arma");
                        String subtipo = arma.getString("subtipo");
                        Integer requisito = Utils.stringToInteger(arma.getString("requisito"));
                        String campo = arma.getString("campo");
                        String requisito_campo = arma.getString("requisito_campo");
                        String operacion = arma.getString("operacion");
                        Integer suma1 = Utils.stringToInteger(arma.getString("suma1"));
                        String suma1_campo = arma.getString("suma1_campo");
                        Integer suma2 = Utils.stringToInteger(arma.getString("suma2"));
                        String suma2_campo = arma.getString("suma2_campo");
                        String ataque = arma.getString("ataque");
                        String rango = arma.getString("rango");
                        Integer normal = Utils.stringToInteger(arma.getString("normal"));
                        Integer imagen_id = Utils.stringToInteger(arma.getString("imagen_id"));
                        String descripcion = arma.getString("descripcion");
                        Integer objeto_principal = Utils.stringToInteger(arma.getString("objeto_principal"));
                        Integer objeto_secundario = Utils.stringToInteger(arma.getString("objeto_secundario"));
                        ArmaBlanca armaBlanca = new ArmaBlanca(nombre, subtipo, campo, ataque, operacion, suma1_campo, suma2_campo,
                                suma1, suma2, rango, descripcion, id_arma, requisito, requisito_campo, normal, imagen_id,
                                objeto_principal, objeto_secundario);
                        armaBlanca.setObj1(arma.getString("obj1"));
                        armaBlanca.setObj2(arma.getString("obj2"));
                        blancasList.add(armaBlanca);
                        Log.e("Arma Blanca", String.valueOf(arma));
                        i++;
                    }catch (JSONException e){
                        e.printStackTrace();
                        break;
                    }
                }
                Data.setArmasBlancas(blancasList);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            // seteo bestiario
            try {
                JSONObject bestiario = new JSONObject(Utils.getData("get/bestiario"));
                ArrayList<Bestia> bestiasList = new ArrayList<>();
                int i = 0;
                while(true){
                    try {
                        JSONObject bestia = bestiario.getJSONObject(String.valueOf(i));
                        int id = bestia.getInt("id");
                        String nombre = bestia.getString("nombre");
                        String tipo = bestia.getString("tipo");
                        String clasificacion = bestia.getString("clasificacion");
                        boolean montura = bestia.getInt("montura") == 1;
                        String tamaño = bestia.getString("tamano");
                        int daño = bestia.getInt("dano");
                        int vida = bestia.getInt("vida");
                        int velocidad = bestia.getInt("velocidad");
                        int imagen_id = bestia.getInt("imagen_id");
                        String descripcion = bestia.getString("descripcion");
                        String clasificacion_add = bestia.getString("clasificacion_add");
                        Integer experiencia_derrota = Utils.stringToInteger(bestia.getString("experiencia_derrota"));
                        String resistente = bestia.getString("resistente");
                        String vulnerable = bestia.getString("vulnerable");
                        String extras = bestia.getString("extras");
                        bestiasList.add(new Bestia(id, imagen_id, daño, vida, velocidad, experiencia_derrota, nombre,
                                descripcion, tipo, clasificacion, tamaño, clasificacion_add, resistente, vulnerable, extras, montura));
                        Log.e("Bestia", String.valueOf(bestia));
                        i++;
                    }catch (JSONException e){
                        break;
                    }
                }
                Data.setBestiario(bestiasList);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            //seteo magia
            try{
                JSONObject magia = new JSONObject(Utils.getData("get/magia"));
                JSONObject libros = magia.getJSONObject("Libros");
                JSONObject hechizos = magia.getJSONObject("Hechizos");
                ArrayList<Magia> librosList = new ArrayList<>();
                int i = 0;
                while(true){
                    try {
                        JSONObject libro = libros.getJSONObject(String.valueOf(i));
                        String tipo = libro.getString("tipo");
                        String nombre = libro.getString("nombre");
                        String descripcion = libro.getString("descripcion");
                        String lengua = libro.getString("lengua");
                        int id = libro.getInt("id");
                        int id_lengua = libro.getInt("id_lengua");
                        int requisitos = libro.getInt("requisito");
                        Integer imagen_id = Utils.stringToInteger(libro.getString("imagen_id"));
                        librosList.add(new Magia(id, id_lengua, requisitos, imagen_id, nombre, tipo, descripcion, lengua));
                        Log.e("Magia", String.valueOf(libro));
                        i++;
                    }catch (Exception e){
                        break;
                    }
                }
                i=0;
                while(true){
                    try {
                        JSONObject hechizo = hechizos.getJSONObject(String.valueOf(i));
                        String descripcion = hechizo.getString("descripcion");
                        int id = hechizo.getInt("id");
                        int id_libro = hechizo.getInt("id_libro");
                        String hechiso = hechizo.getString("hechizo");
                        String libro_name = hechizo.getString("libro");
                        for (int j = 0; j < librosList.size(); j++) {
                            if(librosList.get(j).getId() == id_libro){
                                Magia libro = librosList.get(j);
                                librosList.remove(j);
                                libro.addHechizo(new Hechizo(id, id_libro, hechiso, descripcion, libro_name));
                                librosList.add(libro);
                                break;
                            }
                        }
                        Log.e("Hechizo", String.valueOf(hechizo));
                        i++;
                    }catch (JSONException e){
                        break;
                    }
                }
                Data.setMagia(librosList);
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