package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.marea_binario.rpg_lallavedelhorizonte.objeto.NuevoPersonaje;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class EscojerPersonaje extends AppCompatActivity {
    private TableLayout tablaPersonajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escojer_personaje);
        initComponents();
    }

    private void initComponents() {
        View.OnClickListener listenerPersonajeNuevo = view -> {
          startActivity(new Intent(this, CrearPersonajeNuevo.class));
          finish();
        };
        View.OnClickListener listenerEstablecerPersonaje = view -> {
            Integer id = NuevoPersonaje.getIdPerso();
            id=3;
            ConnTask connTask = new ConnTask("put/set_personaje?id="+id);
            connTask.execute();
            try{
                String kk = connTask.get().toString().trim();
                Log.e("fonko?", kk);
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent i = new Intent(this, PaginaPrincipal.class);
            i.putExtra("Id", id);
            startActivity(i);
            finish();
        };
        // GET PERSONAJES
        ConnTask connTask = new ConnTask("get/nombre_personaje");
        connTask.execute();
        String personajes = null;
        JSONObject listP = null;
        try{
            personajes = connTask.get().toString().trim();
            Log.e("fonko?", personajes);
            listP = new JSONObject(personajes);
        }catch (Exception e){
            e.printStackTrace();
        }
        Iterator<String> iter = null;
        if (listP != null) {
            iter = listP.keys();
        }

        // CREA TABLA (MOSTRAR PERSONAJES)
        tablaPersonajes = findViewById(R.id.tablaPersonajes);
        for (int i = 0; i < 3; i++) {
            TableRow tr = new TableRow(this);
            tr.setGravity(Gravity.CENTER);
            for (int j = 0; j < 4; j++) {
                if (i == 0 && j == 0) {
                    NuevoPersonaje np = new NuevoPersonaje(this, "Nuevo Personaje" ,null, 0);
                    np.setOnClickListener(listenerPersonajeNuevo);
                    tr.addView(np);
                } else {
                    // PONER PERSONAJES BD
                    if (iter.hasNext()) {
                        NuevoPersonaje np;
                        try {
                            JSONObject perso = new JSONObject(personajes).getJSONObject(iter.next());
                            np = new NuevoPersonaje(this, perso.getString("nombre"), Integer.valueOf(perso.getString("id")), 1);
                            np.setOnClickListener(listenerEstablecerPersonaje);
                            tablaPersonajes.addView(tr);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            tablaPersonajes.addView(tr);
        }
    }


}