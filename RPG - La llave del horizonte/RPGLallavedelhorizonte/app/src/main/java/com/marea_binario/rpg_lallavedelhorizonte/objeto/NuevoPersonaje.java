package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.R;

@SuppressLint("ViewConstructor")
public class NuevoPersonaje extends LinearLayout {
    private static Integer idPerso;
    private String nombre;
    private int idImagen;
    private TextView tvNombrePersonaje;
    private ImageView ivImagenPersonaje;

    public NuevoPersonaje(Context context, String nombre, Integer idPerso, int idImagen) {
        super(context);
        inflate(getContext(), R.layout.display_personaje, this);
        this.nombre = nombre;
        NuevoPersonaje.idPerso = idPerso;
        Log.e("np??", String.valueOf(idPerso));
        this.idImagen = idImagen;
        initComponents();
    }

    private void initComponents() {
        tvNombrePersonaje = findViewById(R.id.tvNombrePersonaje);
        ivImagenPersonaje = findViewById(R.id.ivImagenPersonaje);
        tvNombrePersonaje.setText(nombre);
        if (idImagen == 0) {
            ivImagenPersonaje.setImageDrawable(Data.getImageJugador(idImagen));
        }
    }

    public static Integer getIdPerso() { return idPerso; }
}
