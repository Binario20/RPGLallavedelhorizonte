package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.setData(this);
        irEleccionRol();
    }
    private void irEleccionRol() {
        startActivity(new Intent(this, ElecionRol.class));
    }
}