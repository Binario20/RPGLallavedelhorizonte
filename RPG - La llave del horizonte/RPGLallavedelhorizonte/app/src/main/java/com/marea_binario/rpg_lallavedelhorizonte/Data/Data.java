package com.marea_binario.rpg_lallavedelhorizonte.Data;

public class Data {

    public static String JUGADOR = "Jugador", MASTER = "Master", LIDER = "Lider";

    private static String rol = Data.JUGADOR;

    public static void setRol(String rol){
        Data.rol = rol;
    }

    public static String getRol() {
        return rol;
    }
}
