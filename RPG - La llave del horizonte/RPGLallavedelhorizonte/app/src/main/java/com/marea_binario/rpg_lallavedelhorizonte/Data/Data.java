package com.marea_binario.rpg_lallavedelhorizonte.Data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.marea_binario.rpg_lallavedelhorizonte.R;

import java.util.ArrayList;

public class Data {

    public static String JUGADOR = "Jugador", MASTER = "Master", LIDER = "Lider";
    private static String lider = "";
    private static String rol = Data.JUGADOR;

    private static LinearLayout lider_layout, master_layout;

    private static ArrayList<Drawable> listaImagenes;


    public static void setRol(String rol){
        Data.rol = rol;
    }

    public static String getRol() {
        return rol;
    }

    public static LinearLayout getLider_layout() {
        return lider_layout;
    }

    public static void setLider_layout(LinearLayout lider_layout) {
        Data.lider_layout = lider_layout;
    }

    public static String getLider() {
        return lider;
    }

    public static void setLider(String lider) {
        Data.lider = lider;
    }

    public static void setData(Context c) {
        listaImagenes = new ArrayList<>();
        listaImagenes.add(ContextCompat.getDrawable(c, R.drawable.newplayericon));
    }

    public static Drawable getImageJugador(int idImage) {
        try {
            return listaImagenes.get(idImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
