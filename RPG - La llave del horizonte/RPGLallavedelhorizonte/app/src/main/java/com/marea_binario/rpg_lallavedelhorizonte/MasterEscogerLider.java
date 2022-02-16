package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

public class MasterEscogerLider extends AppCompatActivity {
    private ConstraintLayout caja_jugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_escoger_lider);
        initComponents();
        initJugadores();
    }

    private void initJugadores() {
        for (int i=0; i<5; i++) {
            caja_jugadores.addView(new NuevoJugador(this, String.valueOf(i)));
        }
    }

    private void initComponents() {
        caja_jugadores = this.findViewById(R.id.caja_jugadores);
    }
}