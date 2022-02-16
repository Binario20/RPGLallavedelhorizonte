package com.marea_binario.rpg_lallavedelhorizonte;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class NuevoJugador extends androidx.appcompat.widget.AppCompatButton {
    private String nombre;
    private boolean lider = false;

    public NuevoJugador(@NonNull Context context,@NonNull String nombre) {
        super(context);
        this.nombre = nombre;
        this.setText(this.nombre);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarLider();
            }
        });
    }

    private void cambiarLider() {
        lider = !lider;
        Toast.makeText(getContext(), lider + " " + nombre, Toast.LENGTH_SHORT).show();
    }
}
