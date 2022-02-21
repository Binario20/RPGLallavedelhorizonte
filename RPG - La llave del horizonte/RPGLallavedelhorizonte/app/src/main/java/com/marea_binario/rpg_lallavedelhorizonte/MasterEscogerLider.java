package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;

public class MasterEscogerLider extends AppCompatActivity {

    private LinearLayout caja_jugadores;
    private Button todosListos;

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
        todosListos = this.findViewById(R.id.lider_but);
        todosListos.setOnClickListener(view -> alertaConfirmacion());
    }

    private void alertaConfirmacion() {
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
                // Aceptar Lider
                finish();
            });
            rightButton.setOnClickListener(view2 -> {
                alertEraseAlert.cancel();
            });
    }
}