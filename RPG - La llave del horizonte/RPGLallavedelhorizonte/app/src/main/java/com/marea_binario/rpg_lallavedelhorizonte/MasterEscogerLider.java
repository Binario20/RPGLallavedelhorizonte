package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;

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

        caja_jugadores.addView(new NuevoJugador(this, Data.MASTER));

        for (int i=1; i<=20; i++) {
            caja_jugadores.addView(new NuevoJugador(this, String.valueOf(i)));
        }

        Data.setLider_layout(caja_jugadores);
    }

    private void initComponents() {
        caja_jugadores = this.findViewById(R.id.caja_jugadores);
    }
}