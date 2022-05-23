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
//        View.OnClickListener listenerEstablecerPersonaje = view -> {
//            Integer id = ((NuevoPersonaje)view).getIdPerso();
//            Log.e("np-cl??", String.valueOf(((NuevoPersonaje)view).getIdPerso()));
//            //id=3;
//            ConnTask connTask = new ConnTask("put/set_personaje?id="+id);
//            connTask.execute();
//            try{
//                String kk = connTask.get().toString().trim();
//                Log.e("fonko?", kk);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            Intent i = new Intent(this, PaginaPrincipal.class);
//            i.putExtra("Id", id);
//            startActivity(i);
//            finish();
//        };
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
                        try {
                            JSONObject perso = new JSONObject(personajes).getJSONObject(iter.next());
                            Log.e("JSON??", String.valueOf(perso));
//                            NuevoPersonaje np = ;
//                            Log.e("np-if??", String.valueOf(NuevoPersonaje.getIdPerso()));
//                            np.setOnClickListener(listenerEstablecerPersonaje);
                            tr.addView(new NuevoPersonaje(this, perso.getString("nombre"), Integer.valueOf(perso.getString("id")), 1));
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