package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;

public class ElecionRol extends AppCompatActivity {

    private Button master_but, jugador_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elecion_rol);
        initComponents();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Utils.getData("put/set_master-off");
    }

    private void initComponents() {

        master_but = this.findViewById(R.id.master_but);
        jugador_but = this.findViewById(R.id.jugador_but);

        master_but.setOnClickListener(view -> {
            Data.setRol(Data.MASTER);

            try {
                String kk = Utils.getData("get/hay_master");
                if (kk.contains("false") || kk.contains("false*")) {
                    Intent intent = new Intent(getApplicationContext(), MasterEscogerLider.class);
                    intent.putExtra("set-master", kk.contains("false*"));
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