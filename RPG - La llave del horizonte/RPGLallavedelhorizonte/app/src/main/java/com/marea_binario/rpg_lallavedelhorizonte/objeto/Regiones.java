package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import java.util.ArrayList;

public class Regiones {
    private String tipo, nombre, descripcion, nombre_region;
    private int id, id_region;
    private Integer imagen_id, imagen_id_2;
    private ArrayList<Servicio> servicios;

    public Regiones(String tipo, String nombre, String descripcion, String nombre_region, int id, int id_region, Integer imagen_id, Integer imagen_id_2) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombre_region = nombre_region;
        this.id = id;
        this.id_region = id_region;
        this.imagen_id = imagen_id;
        this.imagen_id_2 = imagen_id_2;
    }

    public int getId_region(){
        return id_region;
    }

    public void addServicio(Servicio servicio){
        servicios.add(servicio);
    }
}
