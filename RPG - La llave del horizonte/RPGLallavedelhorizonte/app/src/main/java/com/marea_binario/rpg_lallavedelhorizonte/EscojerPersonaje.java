package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
        tablaPersonajes = findViewById(R.id.tablaPersonajes);
        for (int i = 0; i < 3; i++) {
            TableRow tr = new TableRow(this);
            tr.setGravity(Gravity.CENTER);
            for (int j = 0; j < 4; j++) {
                if (i == 0 && j == 0) {
                    tr.addView(new NuevoPersonaje(this, "Nuevo Personaje" , 0));
                } else {
                    tr.addView(new NuevoPersonaje(this, i + " " + j, 1));
                }
            }
            tablaPersonajes.addView(tr);
        }
    }
}