package com.marea_binario.rpg_lallavedelhorizonte.objeto;

public class Servicio {
    private int id, id_region;
    private String nombre, descripcion;

    public Servicio(int id, int id_region, String nombre, String descripcion) {
        this.id = id;
        this.id_region = id_region;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_region(){
        return id_region;
    }
}
