package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        master_but.setOnClickListener(view -> {
            Data.setRol(Data.MASTER);
            ConnTask connTask = new ConnTask("get/hay_master");
            connTask.execute();

            try {
                String kk = connTask.get().toString().trim();
                if (kk.contains("false") || kk.contains("false*")) {
                    Toast.makeText(this, "1. no hay master", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MasterEscogerLider.class);
                    Toast.makeText(this, kk, Toast.LENGTH_SHORT).show();
                    intent.putExtra("set-master", kk.contains("false*"));
                    Toast.makeText(this, "2. pasa pagina", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (kk.contains("true")){
                    Data.setRol(Data.JUGADOR);
                    startActivity(new Intent(getApplicationContext(), EscojerPersonaje.class));
                } else {
                    Toast.makeText(this, kk, Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        jugador_but.setOnClickListener(view -> {
            Data.setRol(Data.JUGADOR);
            startActivity(new Intent(getApplicationContext(), EscojerPersonaje.class));
        });
    }
}