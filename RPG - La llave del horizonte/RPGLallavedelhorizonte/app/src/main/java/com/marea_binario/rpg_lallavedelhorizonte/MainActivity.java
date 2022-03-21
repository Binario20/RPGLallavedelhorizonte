package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //testConn();

        Data.setData(this);
        irEleccionRol();
    }
    private void irEleccionRol() {
        startActivity(new Intent(this, ElecionRol.class));
    }

    private void testConn(){
        ArrayList<String> key = new ArrayList<>(Collections.singletonList("userID"));
        ArrayList<String> value = new ArrayList<>(Collections.singletonList("ivan"));
        ConnTask connTask = new ConnTask(key, value, "ledoff");
        connTask.execute();
        try {
            String kk = connTask.get().toString().trim();
            Log.e("res", kk);
            Toast.makeText(this, kk, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}