package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MasterEscogerLider extends AppCompatActivity {

    private LinearLayout caja_jugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_escoger_lider);
        initComponents();
        initJugadores();
    }

    private void initJugadores() {
        for (int i=0; i<20; i++) {
            caja_jugadores.addView(new NuevoJugador(this, String.valueOf(i)));
        }
    }

    private void initComponents() {
        caja_jugadores = this.findViewById(R.id.caja_jugadores);
    }
}