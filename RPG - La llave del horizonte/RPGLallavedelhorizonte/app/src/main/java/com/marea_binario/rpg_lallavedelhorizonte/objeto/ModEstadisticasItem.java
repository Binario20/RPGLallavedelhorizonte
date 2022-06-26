package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

public class ModEstadisticasItem extends LinearLayout {

    private int id_perso, vitalidad, resistencia, fuerza, velocidad, inteligencia, punteria, magia;
    private LinearLayout modEst;
    private Button showEst, acceptEst;
    private SuperText nomPerEst;
    private EditText modVitalidad, modResistencia, modFuerza, modVelocidad, modInteligencia, modPunteria, modMagia;

    public ModEstadisticasItem(Context context, int id_perso, int vitalidad, int resistencia, int fuerza, int velocidad, int inteligencia, int punteria, int magia) {
        super(context);
        this.id_perso = id_perso;
        this.vitalidad = vitalidad;
        this.resistencia = resistencia;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.inteligencia = inteligencia;
        this.punteria = punteria;
        this.magia = magia;

        initComponents();
        initData();
        initListeners();
    }

    private void initComponents() {
        modVitalidad = this.findViewById(R.id.modVitalidad);
        modResistencia = this.findViewById(R.id.modResistencia);
        modFuerza = this.findViewById(R.id.modFuerza);
        modVelocidad = this.findViewById(R.id.modVelocidad);
        modInteligencia = this.findViewById(R.id.modInteligencia);
        modPunteria = this.findViewById(R.id.modPunteria);
        modMagia = this.findViewById(R.id.modMagia);

        nomPerEst = this.findViewById(R.id.nomPerEst);
        modEst = this.findViewById(R.id.modEst);
        showEst = this.findViewById(R.id.showEst);
        acceptEst = this.findViewById(R.id.acceptEst);
    }

    private void initData() {
        Log.e("vitalidad",String.valueOf(vitalidad));
        this.modVitalidad.setText(String.valueOf(vitalidad));
        this.modResistencia.setText(String.valueOf(resistencia));
        this.modFuerza.setText(String.valueOf(resistencia));
        this.modVelocidad.setText(String.valueOf(resistencia));
        this.modInteligencia.setText(String.valueOf(inteligencia));
        this.modPunteria.setText(String.valueOf(punteria));
        this.modMagia.setText(String.valueOf(magia));
    }

    private void initListeners() {
        showEst.setOnClickListener(view -> {
            if (modEst.getVisibility() == View.VISIBLE) {
                modEst.setVisibility(View.GONE);
                acceptEst.setVisibility(View.GONE);
            } else if (modEst.getVisibility() == View.GONE) {
                modEst.setVisibility(View.VISIBLE);
                acceptEst.setVisibility(View.VISIBLE);
            }
        });

        acceptEst.setOnClickListener(view -> {
            int vital,resis,fuerte,vel, intel,punt,mag,destreza;
            vital = Integer.parseInt(modVitalidad.getText().toString());
            Log.e("vitalidad", String.valueOf(vital));
            resis = Integer.parseInt(modResistencia.getText().toString());
            Log.e("resistencia", String.valueOf(resis));
            fuerte = Integer.parseInt(modFuerza.getText().toString());
            Log.e("fuerza", String.valueOf(fuerte));
            vel = Integer.parseInt(modVelocidad.getText().toString());
            Log.e("velocidad", String.valueOf(vel));
            intel = Integer.parseInt(modInteligencia.getText().toString());
            Log.e("inteligencia", String.valueOf(intel));
            punt = Integer.parseInt(modPunteria.getText().toString());
            Log.e("punteria", String.valueOf(punt));
            mag = Integer.parseInt(modMagia.getText().toString());
            Log.e("magia", String.valueOf(mag));

            destreza = Math.round((punt + vel) >> 1); // x / 2
            Log.e("destreza", String.valueOf(destreza));
        });
    }

    public void setNombre(String nombre) {
        nomPerEst.setEncodedText(nombre);
    }
}
