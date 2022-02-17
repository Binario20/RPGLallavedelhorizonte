package com.marea_binario.rpg_lallavedelhorizonte;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;

public class NuevoJugador extends androidx.appcompat.widget.AppCompatButton {
    private String nombre;
    private boolean lider = false;

    public NuevoJugador(@NonNull Context context,@NonNull String nombre) {
        super(context);
        this.nombre = nombre;
        this.setText(this.nombre);
        setColorBut();
        this.setHeight(30);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarLider();
            }
        });
    }

    private void setColorBut() {
        if (this.nombre.equals(Data.MASTER)) {
            this.setBackground(getContext().getDrawable(R.drawable.master));
        }else {
            this.setBackground(getContext().getDrawable(R.drawable.jugador));
        }
    }

    private void cambiarLider() {
        Utils.cambiarLider(nombre);
//        Toast.makeText(getContext(), lider + " " + nombre, Toast.LENGTH_SHORT).show();
    }

    public boolean esLider(){
        return lider;
    }

    public void setLider(boolean lider) {
        this.lider = lider;
        if (this.lider) {
            this.setBackground(getContext().getDrawable(R.drawable.jugador_seleccionado));
        } else {
            setColorBut();
        }
    }

    public String getNombre(){
        return nombre;
    }
}
