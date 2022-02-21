package com.marea_binario.rpg_lallavedelhorizonte;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;

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

    private void setColorBut() {
        if (this.nombre.equals(Data.MASTER)) {
            this.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.master));
            this.setTextColor(ContextCompat.getColor(getContext(), R.color.masterText));
        }else {
            this.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.jugador));
        }
    }

    private void cambiarLider() {
        Utils.cambiarLider(nombre);
//        Toast.makeText(getContext(), lider + " " + nombre, Toast.LENGTH_SHORT).show();
    }

    /*public boolean esLider(){
        return lider;
    }*/

    public void setLider(boolean lider) {
        this.lider = lider;
        if (this.lider) {
            Data.setLider(nombre);
            this.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.jugador_seleccionado));
            this.setTextColor(ContextCompat.getColor(getContext(), R.color.liderText));
        } else {
            setColorBut();
        }
    }

    public String getNombre(){
        return nombre;
    }
}
