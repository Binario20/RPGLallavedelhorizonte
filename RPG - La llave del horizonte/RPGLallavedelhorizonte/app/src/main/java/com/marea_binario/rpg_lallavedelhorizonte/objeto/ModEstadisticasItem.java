package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

import org.json.JSONException;
import org.json.JSONObject;

public class ModEstadisticasItem extends LinearLayout {

    private int id_jugador, vitalidad, resistencia, fuerza, velocidad, inteligencia, punteria, magia;
    private ScrollView modEst;
    private Button showEst, acceptEst;
    private SuperText nomPerEst;
    private EditText modVitalidad, modResistencia, modFuerza, modVelocidad, modInteligencia, modPunteria, modMagia;

    public ModEstadisticasItem(Context context, int id_jugador, int vitalidad, int resistencia, int fuerza, int velocidad, int inteligencia, int punteria, int magia) {
        super(context);
        this.id_jugador = id_jugador;
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
        inflate(getContext(), R.layout.mod_estadisticas_item, this);
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
        modVitalidad.setText(String.valueOf(vitalidad));
        modResistencia.setText(String.valueOf(resistencia));
        modFuerza.setText(String.valueOf(fuerza));
        modVelocidad.setText(String.valueOf(velocidad));
        modInteligencia.setText(String.valueOf(inteligencia));
        modPunteria.setText(String.valueOf(punteria));
        modMagia.setText(String.valueOf(magia));
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
            resis = Integer.parseInt(modResistencia.getText().toString());
            fuerte = Integer.parseInt(modFuerza.getText().toString());
            vel = Integer.parseInt(modVelocidad.getText().toString());
            intel = Integer.parseInt(modInteligencia.getText().toString());
            punt = Integer.parseInt(modPunteria.getText().toString());
            mag = Integer.parseInt(modMagia.getText().toString());

            destreza = Math.round((punt + vel) >> 1); // x / 2
            Log.e("destreza", String.valueOf(destreza));

            JSONObject newEst = new JSONObject();
            try {
                newEst.put("id_jugador",id_jugador);
                newEst.put("vitalidad",vital);
                newEst.put("resistencia",resis);
                newEst.put("fuerza",fuerte);
                newEst.put("velocidad",vel);
                newEst.put("inteligencia",intel);
                newEst.put("punteria",punt);
                newEst.put("magia",mag);
                newEst.put("destreza",destreza);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Utils.getData("put/conectados/estadisticas?upd="+newEst);

            modEst.setVisibility(View.GONE);
            acceptEst.setVisibility(View.GONE);
        });
    }

    public void setNombre(String nombre) {
        nomPerEst.setEncodedText(nombre);
    }
}
