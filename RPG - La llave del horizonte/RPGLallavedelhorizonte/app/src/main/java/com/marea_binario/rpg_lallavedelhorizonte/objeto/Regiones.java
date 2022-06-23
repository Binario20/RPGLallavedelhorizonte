package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import java.util.ArrayList;

public class Regiones extends MrWorldwide{
    private String tipo, descripcion, nombre_region;
    private int id_region;
    private Integer imagen_id_2;
    private ArrayList<Servicio> servicios = new ArrayList<Servicio>();

    public Regiones(String tipo, String nombre, String descripcion, String nombre_region, int id, int id_region, Integer imagen_id, Integer imagen_id_2) {
        super(id, imagen_id, nombre);
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.nombre_region = nombre_region;
        this.id_region = id_region;
        this.imagen_id_2 = imagen_id_2;
    }

    public int getId_region(){
        return id_region;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre_region() {
        return nombre_region;
    }

    public Integer getImagen_id_2() {
        return imagen_id_2;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void addServicio(Servicio servicio){
        servicios.add(servicio);
    }
}
