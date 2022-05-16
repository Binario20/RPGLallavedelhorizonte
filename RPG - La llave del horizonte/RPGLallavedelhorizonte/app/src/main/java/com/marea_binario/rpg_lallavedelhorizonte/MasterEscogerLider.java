package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.NuevoJugador;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class MasterEscogerLider extends AppCompatActivity {

    private LinearLayout caja_jugadores;
    private Button todosListos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_escoger_lider);

        initComponents();
        Bundle b = getIntent().getExtras();
        if (!b.getBoolean("set-master")) {
            setMaster();
        }

        Context conte = this;
        runOnUiThread(() -> {
            initJugadores(conte);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }



    private void setMaster() {
        ConnTask connTask2 = new ConnTask("put/set_master-on");
        connTask2.execute();
        try{
            String kk2 = connTask2.get().toString().trim();
            Log.e("fonko?", kk2);
        }catch (Exception e2){
            e2.printStackTrace();
        }
    }

    private void initJugadores(Context conte) {
        ConnTask connTask2 = new ConnTask("get/conectados");
        connTask2.execute();
        try {
            String kk2 = connTask2.get().toString().trim();
            Log.e("fonko?", kk2);
            JSONObject con = new JSONObject(kk2);
            int b = 0;
            while ( true ) {
                try {
                    JSONObject l = con.getJSONObject(b+"");
                    if (l.getString("master").equals("1")) {
                        caja_jugadores.addView(new NuevoJugador(conte,
                                Data.MASTER, Integer.valueOf(l.getString("id"))));
                    } else {
                        caja_jugadores.addView(new NuevoJugador(conte,
                                l.getString("personaje"),Integer.valueOf(l.getString("id"))));
                    }
                } catch (JSONException ex) {
                    //ex.printStackTrace();
                    break;
                }
                b++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Data.setLider_layout(caja_jugadores);
    }

    private void initComponents() {
        caja_jugadores = this.findViewById(R.id.caja_jugadores);
        todosListos = this.findViewById(R.id.lider_but);
        todosListos.setOnClickListener(view -> alertaConfirmacion());
    }

    private void alertaConfirmacion() {
        Toast.makeText(this, Data.getLider(), Toast.LENGTH_SHORT).show();
        Log.e("lider", Data.getLider());
        AlertDialog.Builder builder = new AlertDialog.Builder(MasterEscogerLider.this);
            builder.setCancelable(true);
            View alertEraseView = getLayoutInflater().inflate(R.layout.confirmacion, null);

            TextView textViewAlert = alertEraseView.findViewById(R.id.textViewAlert);
            Button leftButton = alertEraseView.findViewById(R.id.leftButton);
            Button rightButton = alertEraseView.findViewById(R.id.rightButton);

            leftButton.setText(getApplicationContext().getString(R.string.yes));
            rightButton.setText(getApplicationContext().getString(R.string.no));

            leftButton.setBackgroundColor(Color.GREEN);
            rightButton.setBackgroundColor(Color.RED);

            if (Data.getLider().equals(Data.MASTER)) {
                textViewAlert.setText(getApplicationContext().getString(R.string.acceptMaster));
            } else {
                textViewAlert.setText(getApplicationContext().getString(R.string.acceptJugador));
            }
            builder.setView(alertEraseView);

            AlertDialog alertEraseAlert = builder.create();
            alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertEraseAlert.show();

            leftButton.setOnClickListener(view2 -> {
                startActivity(new Intent(getApplicationContext(), PaginaControlMaster.class));
                // Aceptar Lider
                Integer lider = Data.getLider_id();
                ConnTask connTask = new ConnTask("put/set_lider-on?id="+lider);
                connTask.execute();
                try{
                    String kk = connTask.get().toString().trim();
                    Toast.makeText(this, kk, Toast.LENGTH_SHORT).show();
                    Log.e("fonko?", kk);
                }catch (Exception e2){
                    e2.printStackTrace();
                }
                finish();
            });
            rightButton.setOnClickListener(view2 -> {
                alertEraseAlert.cancel();
            });
    }
}