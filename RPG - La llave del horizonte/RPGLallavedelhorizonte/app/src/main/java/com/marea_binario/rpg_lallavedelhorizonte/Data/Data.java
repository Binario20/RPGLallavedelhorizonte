package com.marea_binario.rpg_lallavedelhorizonte.Data;

import android.widget.LinearLayout;

public class Data {

    public static String JUGADOR = "Jugador", MASTER = "Master", LIDER = "Lider";
    private static String lider = "";

    private static String rol = Data.JUGADOR;

    private static LinearLayout lider_layout, master_layout;

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
}
