package com.marea_binario.rpg_lallavedelhorizonte.Data;

import android.widget.LinearLayout;

import com.marea_binario.rpg_lallavedelhorizonte.NuevoJugador;

/**
 * Clase de utilidades random
 */
public class Utils {

    public static void cambiarLider(String nombre){
        LinearLayout lider_layout = Data.getLider_layout();
        for (int i = 0 ; i < lider_layout.getChildCount() ; i++){
            if (lider_layout.getChildAt(i) instanceof NuevoJugador){
                if(((NuevoJugador) lider_layout.getChildAt(i)).getNombre().equals(nombre)){
                    ((NuevoJugador) lider_layout.getChildAt(i)).setLider(true);
                }else{
                    ((NuevoJugador) lider_layout.getChildAt(i)).setLider(false);
                }
            }
        }
    }
}
