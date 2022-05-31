package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Objeto;

import org.json.JSONException;
import org.json.JSONObject;

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
            ConnTask connTask = new ConnTask("get/objetos");
            connTask.execute();
            try {
                String d =  connTask.get().toString().trim();
                JSONObject objetosConn = new JSONObject(d);
                JSONObject objetos = objetosConn.getJSONObject("0");

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
                        new Objeto(nombre, descripcion, tipo, id, imagen_id, objeto_principal, objeto_secundario);

                    }catch (JSONException e){
                        e.printStackTrace();
                        break;
                    }
                }
                Log.e("jeje", d);
            } catch (ExecutionException | InterruptedException | JSONException ex) {
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
        ConnTask connTask = new ConnTask("post/conecta");
        connTask.execute();
        try {
            return connTask.get().toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}