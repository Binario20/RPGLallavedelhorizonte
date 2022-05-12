package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.marea_binario.rpg_lallavedelhorizonte.objeto.NuevoPersonaje;

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
            startActivity(new Intent(this, PaginaPrincipal.class));
            finish();
        };
        tablaPersonajes = findViewById(R.id.tablaPersonajes);
        for (int i = 0; i < 3; i++) {
            TableRow tr = new TableRow(this);
            tr.setGravity(Gravity.CENTER);
            for (int j = 0; j < 4; j++) {
                if (i == 0 && j == 0) {
                    NuevoPersonaje np = new NuevoPersonaje(this, "Nuevo Personaje" , 0);
                    np.setOnClickListener(listenerPersonajeNuevo);
                    tr.addView(np);
                } else {
                    NuevoPersonaje np = new NuevoPersonaje(this, i + " " + j, 1);
                    np.setOnClickListener(listenerEstablecerPersonaje);
                    tr.addView(np);
                }
            }
            tablaPersonajes.addView(tr);
        }
    }
}