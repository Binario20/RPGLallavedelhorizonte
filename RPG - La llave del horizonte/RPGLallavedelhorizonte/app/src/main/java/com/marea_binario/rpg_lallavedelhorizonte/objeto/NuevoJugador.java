package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.R;

@SuppressLint("ViewConstructor")
public class NuevoJugador extends androidx.appcompat.widget.AppCompatButton {
    private final String nombre;
    private boolean lider = false;

    public NuevoJugador(@NonNull Context context,@NonNull String nombre) {
        super(context);
        this.nombre = nombre;
        this.setText(this.nombre);
        setColorBut();
        this.setHeight(30);
        this.setOnClickListener(view -> cambiarLider());
    }

    @SuppressLint("ResourceType")
    private void setColorBut() {
        if (this.nombre.equals(Data.MASTER)) {
            this.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.master));
        }else {
            this.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.jugador));
        }
    }

    private void cambiarLider() {
        Utils.cambiarLider(nombre);
//        Toast.makeText(getContext(), lider + " " + nombre, Toast.LENGTH_SHORT).show();
    }

    public void setLider(boolean lider) {
        this.lider = lider;
        if (this.lider) {
            Data.setLider(nombre);
            this.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.jugador_seleccionado));
        } else {
            setColorBut();
        }
    }

    public String getNombre(){
        return nombre;
    }
}
