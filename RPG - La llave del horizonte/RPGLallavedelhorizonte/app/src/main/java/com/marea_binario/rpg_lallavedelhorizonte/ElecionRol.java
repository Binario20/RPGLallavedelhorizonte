package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;

public class ElecionRol extends AppCompatActivity {

    private Button master_but, jugador_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elecion_rol);
        initComponents();
    }

    private void initComponents() {

        master_but = this.findViewById(R.id.master_but);
        jugador_but = this.findViewById(R.id.jugador_but);

        master_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.setRol(Data.MASTER);
                showRol();
            }
        });

        jugador_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.setRol(Data.JUGADOR);
                showRol();
            }
        });
    }

    private void showRol(){
        Toast.makeText(this, Data.getRol(), Toast.LENGTH_SHORT).show();
    }
}